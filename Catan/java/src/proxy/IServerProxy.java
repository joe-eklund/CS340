package proxy;

import java.util.List;
import java.util.Map;

import shared.definitions.ResourceType;

public interface IServerProxy {	
	
	/*
	 * Logs an existing user into the game server
	 * 
	 * @pre 
	 * 	None
	 * 
	 * @post 
	 * 	if the passed in (username, password) pair is valid: 
	 *	   1) returns a string of 'OK'
	 *  else:
	 *     1) returns a string starting with 'BAD' followed by an error message to be displayed to the user
	 * 	
	 */
	String loginUser(String username, String password);
	
	
	/*
	 *  This call performs the following 2 actions:
	 *  1)Creates a new user account
	 *	2) Logs the new user into the game server
	 * 
	 * @pre
	 * 	None
	 * 
	 * @post
	 * 	if there is no existing user with the specified name:
	 * 	   1) returns a string of 'OK'
	 * 	if there is an existing user with the specified name: 
	 * 	   2) returns a string starting with 'BAD' followed by an error message to be displayed to the user 	
	 * 
	 */
	String registerUser(String username, String password);
	
	
	/*
	 * This call returns information about all of the current games
	 * 
	 * @pre
	 * 	None
	 * 
	 * @post
	 * 	1) returns a String starting with 'OK' followed by a string representation of a JSON object of the following format
	 * 		[
	 * 			{
	 * 				"title": "Game Name",
	 * 				"id": 0,
	 * 				"players": [
	 * 					{
	 * 						"color": orange,
	 * 						"name" : "playerName",
	 * 						"id": 0
	 * 					},...
	 *  			]
	 *  		},...
	 * 		]
	 * 
	 * 		where ids are integers and color is one of the following 9 values:
	 * 			red, green, blue, yellow, puce, brown, white, purple, orange	
	 * 
	 */
	String listGames();
	
	
	/*
	 * Creates an empty game on the server
	 * 
	 * @pre
	 * 	None
	 * 
	 * @post
	 * 	1) returns a String starting with 'OK' followed by string representation of a JSON object of the following format
	 * 		{
	 * 			"title": "Game Name",
	 * 			"id": 0,
	 * 			"players": [
	 * 				{},
	 * 				{},
	 * 				{},
	 * 				{}
	 *  		]
	 *  	}
	 * 
	 */
	String createGame(String name);
	
	
	/*
	 * Adds a player to the game and sets their game cookie
	 * 
	 * @pre
	 * 	1)Player has a valid catan.user cookie set
	 * 	2)Player is already part of game or there is space for the player to join game
	 *  3)The color submitted is a valid color (red, green, blue, yellow, puce, brown, white, purple, orange) and is available in the game
	 * 
	 * @post
	 * 	1)The player is now listed as a player for game associated with gameID and has the specified color
	 * 
	 */
	void joinGame(String color, int gameID);
	
	
	/*
	 * Fetches the JSON for the model of the current game.
	 * 
	 * @pre
	 * 	1) Requester has a valid catan.user and catan.game id set in cookie
	 * 
	 * @post
	 *  if the server catan mode is the same as the client model 
	 *     1) returns a string of 'true'
	 *  else 
	 *     1) returns a String starting with 'OK' followed by string representation of JSON format of game model
	 * 
	 */
	String getGameModel();
	
	
	/*
	 * Resets the game to how it was after all the players joined
	 * 
	 * @pre
	 *  1) Requester has a valid catan.user and catan.game id set in cookie
	 * 
	 * @post
	 * 	1) Returns String starting with 'OK' followed by string representation of JSON format of game model
	 * 
	 */
	String resetGame();
	
	
	/*
	 * Gets a list of all the commands played on a game
	 * 
	 * @pre
	 *  1) Requester has a valid catan.user and catan.game id set in cookie
	 *  
	 * @post
	 *  2) returns a String starting with 'OK' followed by string representation JSON list of the commands that have been executed in the game
	 * 
	 */
	String getGameCommands();
	
	
	/*
	 * Applies a list of commands to the current game.
	 * 
	 * @pre
	 *  1) Requester has a valid catan.user and catan.game id set in cookie
	 * 
	 * @post
	 *  if the content could not be deserialized, or an error in applying the commands:
	 *     1) returns a string starting with "BAD" and followed by an error message detailing failure
	 *  if the commands were successfully deserialized:
	 *     1) returns a string starting with "OK" and followed by string representing JSON format of game model
	 * 
	 */
	String postGameCommands(String commands);
	
	
	/*
	 * Lists the available AI types that may be added to a game
	 * 
	 * @pre
	 *  None
	 * 
	 * @post
	 *  1) returns a String starting with 'OK' followed by a string representation of a JSON list of strings 
	 * 
	 */
	String listAI();
	
	
	/*
	 * Adds an AI to the game
	 * 
	 * @pre
	 *  1) The player has a valid catan.user and catan.game id
	 *  2) There is space in the game for an AI player
	 *  
	 * @post
	 *  1) The AI player is added to the next open spot in the game associated with the poster’s catan.game cookie
	 *  2) The AI player uses a color not taken by any other player in the game
	 *  
	 */
	void addAI();
	
	
	/*
	 * Sets the server’s logging level
	 * 
	 * @pre
	 *  1) The poster specifies a valid logging level. Valid values include: 
	 *     SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST
	 * 
	 * @post
	 *  1) The Server uses that logging level
	 */
	void changeLogLevel(String logLevel);
	
	
	/*
	 * Sends chat message to game chat log
	 * 
	 * @pre
	 *  None
	 * 
	 * @post
	 *  1) chat log contains message
	 * 
	 */
	void sendChat(String message);
	
	
	/*
	 * Accept another player's trade offer
	 * 
	 * @pre
	 * 	1) player has been offered a domestic trade
	 *  if willAccept = true:
	 *     2) player has resources offered on his/her part in trade
	 *     
	 * @post
	 * 	if willAccept = true:
	 *     1) player accepting and player who offered exchange resources specified in trade
	 *  if willAccept = false:
	 *     1) no resources are exchanged
	 *  2) trade offer is removed  
	 *  3) returns String of 'OK'   
	 */
	void acceptTrade(boolean willAccept);
	
	
	/*
	 * Discards cards from players hand
	 * 
	 * @pre
	 *  1) Player is in 'Discarding' state
	 *  2) Player has over 7 cards
	 *  3) Player has the cards player is choosing to discard.
	 * 
	 * @post
	 *  1) Player client now enters 'Robbing' state
	 *  2) Player gives up specified cards
	 */
	void discardCards(List<ResourceType> resources, Map<ResourceType, Integer> resourceHand);

	
	void rollNumber();
	void buildRoad();
	void buildSettlement();
	void buildCity();
	void offerTrade();
	void maritimeTrade();
	void finishTurn();
	void buyDevCard();
	void playYearOfPlentyCard();
	void playRoadBuildingCard();
	void playMonopolyCard();
	void playSoldierCard();
	void playVictoryPointCard();
	//Taylor End
}