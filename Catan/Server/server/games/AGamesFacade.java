package server.games;

import static server.games.ModelDefaults.VALID_COLORS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;

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
	protected List<ServerModel> gameModelsList;
	
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
		@SuppressWarnings("serial")
		ArrayList<Player> newGamePlayers = new ArrayList<Player>(4) {{
			add(null);
			add(null);
			add(null);
			add(null);
		}};
		
		this.gameModelsList.add(new ServerModel(newGameMap, newGamePlayers));
		
		return newGameDescription;
	}
	
	@Override
	public boolean joinGame(JoinGameRequest request, String username, int userID) throws InvalidJoinGameRequest {		
		if(request == null || !request.validate()) {
			throw new InvalidJoinGameRequest("Error: invalid join game request");
		}
		
		boolean result = true;
		
		boolean validColor = true;
		if(VALID_COLORS.indexOf(request.getColor().toLowerCase()) == -1) { // invalid color option
			validColor = false;
			result = false;
		}
		
		
		if (validColor) {
			List<PlayerDescription> players = gameDescriptionsList.get(request.getID()).getPlayerDescriptions();
			
			int playerGameIndex = getPlayerIndexInGame(username, players);
			
			if (players.size() < 4) {
				result = !isColorTaken(request, players, playerGameIndex);
				if(result) {
					if(playerGameIndex != -1) { // update player's color
						updatePlayerColorDescription(request.getID(), playerGameIndex, request.getColor());
						updatePlayerColorModel(request.getID(), playerGameIndex, request.getColor());
					}
					else { // add new player to game
						addPlayerToGameDescription(request.getID(), username, request.getColor(), userID);
						addPlayerToGameModel(request.getID(), username, request.getColor(), userID);
					}
				}
			} else { // game is full
				if(playerGameIndex != -1 && !isColorTaken(request, players, playerGameIndex)) { // player wants to change color in full game they already joined
					updatePlayerColorDescription(request.getID(), playerGameIndex, request.getColor());
					updatePlayerColorModel(request.getID(), playerGameIndex, request.getColor());
				}
				else {
					result = false;
				}
			}
		}
		if(result) {
			TranslatorJSON translator = new TranslatorJSON();
			System.out.println(translator.translateTo(this.gameModelsList.get(request.getID())));
		}
		return result;
	}
	
	@Override
	public void saveGame(int id, String name) throws IOException{
		XStream xstream = new XStream();
		ServerModel game = gameModelsList.get(id);
		String xml = xstream.toXML(game);
		FileWriter fw = new FileWriter("/saves/" + name + ".xml");
		fw.write(xml);
		fw.close();
	}
	
	@Override
	public int loadGame(String name) throws IOException{
		XStream xstream = new XStream();
		File file = new File("/saves/" + name + ".xml");
		ServerModel game = (ServerModel) xstream.fromXML(file);
		gameModelsList.add(game);
		return gameModelsList.size() - 1;
	}
	
	public boolean validateGameID(int id){
		if(id >= 0 && id < gameModelsList.size()){
			return true;
		}
		else return false;
	}
	
	protected void addPlayerToGameModel(int gameID, String username, String color, int userID) {
		Player newPlayer = new Player(color, username, -1, userID);
		this.gameModelsList.get(gameID).addPlayer(newPlayer);
	}
	
	private void updatePlayerColorModel(int gameID, int playerIndexInGame, String newColor) {
		this.gameModelsList.get(gameID).changePlayerColor(playerIndexInGame, newColor);
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
		newPlayer.setIndex(gameDescriptionsList.get(gameID).getPlayerDescriptions().size());
		gameDescriptionsList.get(gameID).add(newPlayer);
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
	private void updatePlayerColorDescription(int gameID, int playerIndexInGame, String newColor) {
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
