package server.games;

import static server.games.ModelDefaults.VALID_COLORS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import proxy.ITranslator;
import proxy.TranslatorJSON;
import shared.ServerMethodRequests.CreateGameRequest;
import shared.ServerMethodRequests.JoinGameRequest;
import shared.definitions.GameDescription;
import shared.definitions.PlayerDescription;
import shared.definitions.ServerModel;
import shared.locations.HexLocation;
import shared.model.Hex;
import shared.model.Map;
import shared.model.Player;
import shared.model.Port;

public abstract class AGamesFacade implements IGamesFacade {
	protected ArrayList<GameDescription> gameDescriptionsList;
	private List<ServerModel> gameModelsList;
	
	public AGamesFacade(List<ServerModel> gameModels) {
		this.gameModelsList = gameModels;
	}
	
	@Override
	public List<GameDescription> listGames() {
		return gameDescriptionsList;
	}
	
	@Override
	public GameDescription createGame(CreateGameRequest request) throws InvalidCreateGameRequest {
		if(request == null || !request.validate()) {
			throw new InvalidCreateGameRequest("Error: invalid create game request");
		}
		
		//
		// Update gameDescriptionsList
		//
		
		GameDescription newGameDescription = new GameDescription(request.getName(), this.gameDescriptionsList.size(), new ArrayList<PlayerDescription>(4));
		gameDescriptionsList.add(newGameDescription);
		
		
		//
		// Update gameModelsList
		//
		
		// Randomize as Requested
		ArrayList<Hex> hexes = (ArrayList<Hex>) ((request.isRandomTiles()) ? this.getRandomTiles(ModelDefaults.getDefualtHexes()) : ModelDefaults.getDefualtHexes());
		hexes = (ArrayList<Hex>) ((request.isRandomNumbers()) ? this.getRandomChits(hexes) : hexes);
		ArrayList<Port> ports = (ArrayList<Port>) ((request.isRandomPorts()) ? this.getRandomPorts(ModelDefaults.getDefaultPorts()) : ModelDefaults.getDefaultPorts());

		// Update Robber Location
		HexLocation robberLocation = new HexLocation(0, -2);
		for(Hex h: hexes) {
			if(h.getResourceType() == null) {
				robberLocation = new HexLocation(h.getLocation().getX(), h.getLocation().getY());
				break;
			}
		}
		
		Map newGameMap = new Map(hexes, ports, robberLocation.getX(), robberLocation.getY());
		ArrayList<Player> newGamePlayers = new ArrayList<Player>(4) {{
			add(null);
			add(null);
			add(null);
			add(null);
		}};
		
		this.gameModelsList.add(new ServerModel(newGameMap, newGamePlayers));
		
		/*
		System.out.println("\n");
		ITranslator translator = new TranslatorJSON();
		System.out.println(translator.translateTo(gameModelsList.get(gameModelsList.size() -1)));
		System.out.println("\n");
		*/
		
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
	
	private List<Hex> getRandomTiles(List<Hex> defaultHexes) {
		ArrayList<Hex> randomResources = new ArrayList<Hex>(defaultHexes);
	
		List<String> randomNames = ModelDefaults.getDefaultResourceNames();
		randomizeList(randomNames);
		
		int desertIndex = randomNames.indexOf("desert");
		int oldDesertChitValue = -1;
		if(desertIndex != 0) {
			oldDesertChitValue = randomResources.get(desertIndex).getChit();
		}
		
		for(int i = 0; i < randomResources.size(); i++) {
			randomResources.get(i).setResourceType(randomNames.get(i));
			if(randomNames.get(i).equals("desert")) {
				HexLocation location = randomResources.get(i).getLocation();
				randomResources.set(i, new Hex(new HexLocation(location.getX(), location.getY())));
			}
		}
		
		if(desertIndex != 0) {
			randomResources.get(0).setChit(oldDesertChitValue);
		}
		return randomResources;
	}
	
	private List<Hex> getRandomChits(List<Hex> defaultHexes) {
		ArrayList<Hex> randomChits = new ArrayList<Hex>(defaultHexes);
		List<Integer> randomValues = ModelDefaults.getDefaultNumbers();
		randomizeList(randomValues);
		int chitIndex = 0;
		for(Hex hex : randomChits) {
			if(hex.getResourceType() != null) {
				hex.setChit(randomValues.get(chitIndex++));
			}
		}
		return randomChits;
	}
	
	private List<Port> getRandomPorts(List<Port> defaultPorts) {
		ArrayList<Port> randomPorts = new ArrayList<Port>(defaultPorts);
		List<String> randomPortNames = ModelDefaults.getDefaultPortNames();
		randomizeList(randomPortNames);
		for(int i = 0; i < randomPorts.size(); i++) {
			String name = randomPortNames.get(i);
			Port port = randomPorts.get(i);
			HexLocation portLocation = port.getLocation();
			if(name == null) {
				randomPorts.set(i, new Port(null, portLocation.getX(), portLocation.getY(), port.getDirection(), 3));
			}
			else {
				randomPorts.set(i, new Port(name, portLocation.getX(), portLocation.getY(), port.getDirection(), 2));
			}
		}
		return randomPorts;
	}
	
	private void randomizeList(List<?> list) {
		long seed = System.nanoTime();
		Collections.shuffle(list, new Random(seed));
	}
}
