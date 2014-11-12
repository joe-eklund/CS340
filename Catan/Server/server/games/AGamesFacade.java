package server.games;

import java.util.ArrayList;
import java.util.List;

import shared.ServerMethodRequests.CreateGameRequest;
import shared.ServerMethodRequests.JoinGameRequest;
import shared.definitions.GameDescription;
import shared.definitions.PlayerDescription;
import shared.definitions.ServerModel;

public abstract class AGamesFacade implements IGamesFacade {
	protected ArrayList<GameDescription> gameDescriptionsList;
	protected ArrayList<ServerModel> gameModelsList;
	private static final ArrayList<String> VALID_COLORS = new ArrayList<String>(9){
		/**
		 * 
		 */
		private static final long serialVersionUID = -8194941010612096541L;
	{
		add("red");
		add("green");
		add("blue"); 
		add("yellow");
		add("puce");
		add("brown");
		add("white");
		add("purple");
		add("orange");
	}};
	
	@Override
	public List<GameDescription> listGames() {
		return gameDescriptionsList;
	}
	
	@Override
	public GameDescription createGame(CreateGameRequest request) throws InvalidCreateGameRequest {
		if(request == null || !request.validate()) {
			throw new InvalidCreateGameRequest("Error: invalid create game request");
		}
		GameDescription newGameDescription = new GameDescription(request.getName(), this.gameDescriptionsList.size(), new ArrayList<PlayerDescription>(4));
		gameDescriptionsList.add(newGameDescription);
		
		//TODO Initialize new serverModel to represent the newly created game according to request random params
		
		return newGameDescription;
	}
	
	@Override
	public boolean joinGame(JoinGameRequest request, String username, int userID) throws InvalidJoinGameRequest {
		System.out.println("request: color = " + request.getColor() + "; gameID = " + request.getID());
		
		if(request == null || !request.validate()) {
			throw new InvalidJoinGameRequest("Error: invalid join game request");
		}
		
		boolean result = true;
		
		boolean validColor = true;
		if(VALID_COLORS.indexOf(request.getColor().toLowerCase()) == -1) { // invalid color option
			System.out.println("unrecognized color option!");
			validColor = false;
			result = false;
		}
		
		
		if (validColor) {
			System.out.println("recognized color option...");
			List<PlayerDescription> players = gameDescriptionsList.get(request.getID()).getPlayerDescriptions();
			
			int playerGameIndex = getPlayerIndexInGame(username, players);
			
			if (players.size() < 4) {
				System.out.println("room in game...");
				result = !isColorTaken(request, players, playerGameIndex);
				if(result) {
					if(playerGameIndex != -1) { // update player's color
						System.out.println("changing color in game with space...");
						updatePlayerColor(request.getID(), playerGameIndex, request.getColor());
					}
					else { // add new player to game
						System.out.println("adding new player to game...");
						addPlayerToGameDescription(request.getID(), username, request.getColor(), userID);
					}
				}
			} else { // game is full
				if(playerGameIndex != -1 && !isColorTaken(request, players, playerGameIndex)) { // player wants to change color in full game they already joined
					System.out.println("changing color in full game...");
					updatePlayerColor(request.getID(), playerGameIndex, request.getColor());
				}
				else {
					System.out.println("no room in game!");
					result = false;
				}
			}
		}
		System.out.println(result);
		return result;
	}
	
	/*
	 * Adds a new player to a gameDescription with specified color
	 * 
	 * @param gameID
	 * @param request
	 * @param color
	 * 
	 * @pre
	 * 	color is valid
	 *  color is unique for game
	 *  game is not full
	 *  
	 * @post
	 *  player is added to gameDescription with desired color
	 */
	private void addPlayerToGameDescription(int gameID, String username, String color, int userID) {
		PlayerDescription newPlayer = new PlayerDescription(color.toLowerCase(), userID, username);
		System.out.println("New Player Description Created: adding " + newPlayer.getName() + " to game...");
		System.out.println(gameDescriptionsList.get(gameID).getTitle());
		gameDescriptionsList.get(gameID).add(newPlayer);
		System.out.println(gameDescriptionsList.get(gameID).getPlayerDescriptions().isEmpty());
	}

	/* 
	 * Update player's color in game he/she already joined
	 * 
	 * @pre
	 * 	player must already have joined specified game
	 *  color must be valid
	 *  color must not be in use by any other players in specified game (if player already has this color, thats fine)
	 *  gameID is valid and corresponds to games index in gameDescriptionsList
	 *  playerIndexInGame is valid index (i.e. greater than 0 and less than game.playerDescriptions.size)
	 * 
	 * @param gameID
	 * @param playerIndexInGame
	 * @param newColor
	 * 
	 * @post
	 *  player color is changed to specified color
	 */
	private void updatePlayerColor(int gameID, int playerIndexInGame, String newColor) {
		gameDescriptionsList.get(gameID).getPlayerDescriptions().get(playerIndexInGame).setColor(newColor.toLowerCase());
	}
	
	/*
	 * get players index in game
	 * 
	 * @pre 
	 *  none
	 * 
	 * @param username ::= players username
	 * @param players ::= game player wants to join
	 * 
	 * @post 
	 *  returns -1 if player not already in game, 
	 *  else returns player's index in game
	 */
	private int getPlayerIndexInGame(String username,
			List<PlayerDescription> players) {
		int playerGameIndex = -1;
		for (int i = 0; i < players.size(); i++) {
			PlayerDescription pd = players.get(i);
			if(pd.getName().equals(username)) {
				playerGameIndex = i;
				break;
			}
		}
		return playerGameIndex;
	}

	/*
	 * check if requested color is already taken by another player
	 * 
	 * @param request
	 * @param players ::= players for gameID = request.getID
	 * @param playerGameIndex ::=
	 * 							if requester is not already in game, -1
	 * 							else, requester index in game [0-(players.size() - 1)]
	 * @return is the requested color taken by another player
	 */
	private boolean isColorTaken(JoinGameRequest request,
			List<PlayerDescription> players, int playerGameIndex) {
		boolean colorTaken = false;
		for (int i = 0; i < players.size(); i++) {
			PlayerDescription pd = players.get(i);
			if (pd.getColor().toLowerCase().equals(request.getColor().toLowerCase()) && i != playerGameIndex) { // color already taken
				colorTaken = true;
				break;
			}
		}
		return colorTaken;
	}
}
