package server.games;

import java.util.List;

import shared.ServerMethodRequests.CreateGameRequest;
import shared.ServerMethodRequests.JoinGameRequest;
import shared.definitions.GameDescription;

/**
 * This interface defines a Facade containing the list, create, join, save, and load commands
 *
 */
public interface IGamesFacade {

	/**
	 * @post returns a list of GameDescriptions representing all games hosted on this Catan Server
	 */
	public List<GameDescription> listGames();
	
	/**
	 * Creates an empty game on the server
	 * 
	 * @pre
	 * 	none
	 * 
	 * @param randomTiles : create game with randomized game tiles
	 * @param randomNumbers : create game with randomized numbers ("chits")
	 * @param randomPorts : create game with randomized ports
	 * @param name : the name which the created game will have
	 * @throws InvalidCreateGameRequest 
	 * 
	 * 
	 * @post
	 * 	creates an empty game on server according to parameter booleans "randomTiles", "randomNumbers", and "Random Ports"; the parameter name settings are default if the given parameter is false otherwise the setting (i.e. ports) is randomized</li>
	 * 	returns a gameDescription for the just created game
	 * 		"title" field is set to provided name parameter
	 * 		"id" field is set to the server assigned unique id for the game</li>
	 * 
	 */	
	public GameDescription createGame(CreateGameRequest request) throws InvalidCreateGameRequest;
	
	/**
	 * @pre
	 *  none
	 * 
	 * @param request
	 * @param username
	 * @param userID
	 * @throws InvalidJoinGameRequest 
	 * 
	 * @post
	 * 	if (game associated with request.id is not full and request.color is not taken by another player in the same game) or username is already joined to full game
	 * 	  -player is added to game with requested color (or in the case the player is already in game, his color is updated)
	 *    -returns true
	 *  else
	 *    -no change in game
	 *    -returns false
	 */
	public boolean joinGame(JoinGameRequest request, String username, int userID) throws InvalidJoinGameRequest;
	
}
