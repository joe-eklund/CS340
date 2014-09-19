package proxy;

import java.util.List;
import java.util.Map;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;

/**
 * An interface for a Server Proxy
 *
 */
public interface IServerProxy {	
	
	/**
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
	 * @param username: username for registered user
	 * @param password: password for registered user associated with specified username
	 * 
	 * @return string: see post condition for possible values
	 */
	public String loginUser(String username, String password);
	
	
	/**
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
	 *  @param username: the username the player wishes to use when logging into the game
	 *  @param password: the password the player wishes to use for logging in with the specified username
	 *  
	 *  @return string: see post for possible values
	 * 
	 */
	public String registerUser(String username, String password);
	
	
	/**
	 * This call returns information about all of the current games
	 * 
	 * @pre
	 * 	None
	 * 
	 * @post
	 * 	1) returns a String starting with 'OK' followed by a string representation of a JSON object of the following format
	 * 		<p>[
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
	 * 		]</p>
	 * 
	 * 		where ids are integers and color is one of the following 9 values:
	 * 			red, green, blue, yellow, puce, brown, white, purple, orange	
	 * 
	 * @return string: see post for possible values
	 * 
	 */
	public String listGames();
	
	
	/**
	 * Creates an empty game on the server
	 * 
	 * @pre
	 * 	None
	 * 
	 * @post
	 * 	1) returns a String starting with 'OK' followed by string representation of a JSON object of the following format
	 * 		<p>{
	 * 			"title": "Game Name",
	 * 			"id": 0,
	 * 			"players": [
	 * 				{},
	 * 				{},
	 * 				{},
	 * 				{}
	 *  		]
	 *  	}</p>
	 * 
	 *  @param name: the name which the created game will have
	 *  
	 *  @return string: see post for possible values
	 * 
	 */
	public String createGame(String name);
	
	
	/**
	 * Adds a player to the game and sets their game cookie
	 * 
	 * @pre
	 * <ul>
	 * 	<li>Player has a valid catan.user cookie set</li>
	 * 	<li>Player is already part of game or there is space for the player to join game</li>
	 *  <li>The color submitted is a valid color (red, green, blue, yellow, puce, brown, white, purple, orange) and is available in the game</li>
	 * </ul>
	 * @post
	 * 	<ul><li>The player is now listed as a player for game associated with gameID and has the specified color</li></ul>
	 * 
	 * @param color: the available color the player wishes to use
	 * @param gameID: the ID for the game which the player wishes to join
	 */
	public void joinGame(String color, int gameID);
	
	
	/**
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
	 *  @return string: see post for possible values
	 * 
	 */
	public String getGameModel();
	
	
	/**
	 * Resets the game to how it was after all the players joined
	 * 
	 * @pre
	 *  1) Requester has a valid catan.user and catan.game id set in cookie
	 * 
	 * @post
	 * 	1) Returns String starting with 'OK' followed by string representation of JSON format of game model
	 * 
	 * @return string: see post for possible values
	 * 
	 */
	public String resetGame();
	
	
	/**
	 * Gets a list of all the commands played on a game
	 * 
	 * @pre
	 *  1) Requester has a valid catan.user and catan.game id set in cookie
	 *  
	 * @post
	 *  2) returns a String starting with 'OK' followed by string representation JSON list of the commands that have been executed in the game
	 * 
	 * @return string: see post for possible values
	 * 
	 */
	public String getGameCommands();
	
	
	/**
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
	 *  @param commands: list of valid game commands ordered chronologically from earliest to most recent
	 * 
	 *  @return string: see post for possible values
	 * 
	 */
	public String postGameCommands(List<String> commands);
	
	
	/**
	 * Lists the available AI types that may be added to a game
	 * 
	 * @pre
	 *  None
	 * 
	 * @post
	 *  1) returns a String starting with 'OK' followed by a string representation of a JSON list of strings 
	 * 
	 *  @return string: see post for possible values
	 * 
	 */
	public String listAI();
	
	
	/**
	 * Adds an AI to the game
	 * 
	 * @pre
	 * <ul>
	 *  <li> The player has a valid catan.user and catan.game id</li>
	 *  <li> There is space in the game for an AI player</li>
	 * </ul>
	 *  
	 * @post
	 * <ul>
	 *  <li> The AI player is added to the next open spot in the game associated with the poster’s catan.game cookie</li>
	 *  <li> The AI player uses a color not taken by any other player in the game</li>
	 * </ul>
	 */
	public void addAI();
	
	
	/**
	 * Sets the server’s logging level
	 * 
	 * @pre
	 *  1) The poster specifies a valid logging level. Valid values include: 
	 *     SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST
	 * 
	 * @post
	 *  1) The Server uses that logging level
	 *  
	 *  @param logLevel: desired logging level for server
	 *  
	 */
	public void changeLogLevel(String logLevel);
	
	
	/**
	 * Sends chat message to game chat log
	 * 
	 * @pre
	 *  None
	 * 
	 * @post
	 *  1) chat log contains message
	 * 
	 * @param message: message player wishes to post to chat log 
	 * 
	 */
	public void sendChat(String message);
	
	
	/**
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
	 * 
	 * @param willAccept: boolean value of whether or not player accepts proposed trade 
	 *  
	 */
	public void acceptTrade(boolean willAccept);
	
	
	/**
	 * Discards cards from players hand
	 * 
	 * @pre
	 * <ul>
	 *  <li> Player is in 'Discarding' state</li>
	 *  <li> Player has over 7 cards</li>
	 *  <li> Player has the cards player is choosing to discard.</li>
	 * </ul>
	 * 
	 * @post
	 * <ul>
	 *  <li> Player client now enters 'Robbing' state</li>
	 *  <li> Player gives up specified cards</li>
	 * </ul>
	 *  
	 *  @param resources: list of valid resource types
	 *  @param resourceHand: contains map of each resource type and the associated number of cards for the type the player is dropping
	 */
	public void discardCards(List<ResourceType> resources, Map<ResourceType, Integer> resourceHand);

	/**
	 * Specifies to the server the number that was rolled by the player
	 * @pre
	 * <ul>
	 *  <li> The client model's status is "rolling"</li>
	 *  <li> It is the turn of the player trying to roll the dice</li>
	 * </ul>
	 *  
	 * @post
	 * <ul>
	 *  <li> An integer from 2 to 12 is returned that represents the that was rolled</li>
	 *  <li> The client model's status is now in "discarding" or "robbing" or "playing"</li>
	 * </ul>
	 *  
	 *  @param number: an integer from 2 to 12 that represents the number that was rolled.
	 */
	public void rollNumber(int number);
	
	/**
	 * Builds a road of the same color as the player building the road and at the specified location on the map
	 * 
	 * @pre
	 * <ul>
	 * 	<li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The road location is open</li>
	 *  <li> The road location is connected to another road</li>
	 *  <li> The road location is not on water</li>
	 *  <li> The player has the necessary resources to build a road (1 wood, 1 brick)</li>
	 * </ul>
	 *  
	 * @post
	 * <ul>
	 *  <li> You expend the resources to play the road (1 wood, 1 brick)</li>
	 *  <li> The map lists the road correctly</li>
	 * </ul>
	 * 
	 * @param free: a boolean that specifies whether or not the road piece has been given to the player for free.
	 * @param roadLocation: An EdgeLocation that declares where on the map the road will be built. 
	 */
	public void buildRoad(boolean free, EdgeLocation roadLocation);
	
	/**
	 * Builds a settlement of the same color as the player building the road and at the specified location on the map
	 * 
	 * @pre
	 * <ul>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The settlement location is open</li>
	 *  <li> The settlement location is not on water</li>
	 *  <li> The settlement location is connected to one of your roads</li>
	 *  <li> The player has the necessary resources to build a settlement (1 wood, 1 brick, 1 wheat, 1 sheep)</li>
	 * </ul>
	 *  
	 * @post
	 * <ul>
	 *  <li> The player expends the resources necessary to play the settlement (1 wood, 1 brick, 1 wheat, 1 sheep)</li>
	 *  <li> The map lists the settlement location correctly</li>
	 * </ul>
	 * @param free: a boolean that specifies whether or not the settlement piece has been given to the player for free.
	 */
	public void buildSettlement(boolean free);
	
	/**
	 * Builds a city of the same color as the player building the road and at the specified location on the map
	 * 
	 * @pre
	 * <ul>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The city location is where the player currently has a settlement</li>
	 *  <li> The player has the necessary resources to build a city (2 wheat, 3 ore)</li>
	 * </ul>
	 * 
	 * @post
	 * <ul> 
	 *  <li> The player expends the resources necessary to play the settlement (2 wheat, 3 ore)</li>
	 *  <li> The player gets a settlement back</li>
	 *  <li> The map lists the city location correctly</li>
	 * </ul>
	 */
	public void buildCity();
	
	/**
	 * The player offers a trade of certain resources to a another player in the game in return for different resource.
	 * 
	 * @pre
	 * <ul>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The player owns the designated resources that want to be traded</li>
	 * </ul>
	 * 
	 * @post
	 * <ul>
	 *  <li> The trade is offered to the specified player and stored in the model</li>
	 * </ul>
	 * 
	 * @param offer: ResourceHand indicating the cards to be given and received.  Negative numbers mean the players is receiving cards of that resource in the trade.
	 * @param receiver: playerIndex which specifies the recipient whom the player is trading with 
	 */
	public void offerTrade(ResourceHand offer, playerIndex receiver);
	
	/**
	 * The player offers a trade of certain resources to the bank at a specified ratio depending on the port the player has a settlement on.
	 * 
	 * @pre
	 * <ul>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The player owns the designated resources that want to be traded</li>
	 * </ul>
	 * 
	 * @post
	 * <ul>
	 *  <li> The trade is offered to the specified player and stored in the model</li>
	 * </ul
	 * @param ratio: an integer of value 2, 3, or 4
	 * @param inputResource: Resource - The resource the player is giving
	 * @param outputResource: Resource - The resoure the player is receiving
	 */
	public void maritimeTrade(int ratio, Resource inputResource, Resource outputResource);
	
	/**
	 * The player finishes his/her turn.
	 * 
	 * @pre
	 * <ul>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 * </ul>
	 *  
	 * @post
	 *  <ul> 
	 *   <li>It is the next players turn</li>
	 *  </ul>
	 */
	public void finishTurn();
	
	/**
	 * The player buys a development card from the bank.
	 * 
	 * @pre
	 * <ul>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The player has the necessary resources (1 ore, 1 wheat, 1 sheep)</li>
	 *  <li> There are development cards left in the deck.</li>
	 * </ul>
	 * @post
	 * <ul>
	 *  <li> The player has the new card</li>
	 *    <ul>
	 *    	<li>If it is a monument card, it goes into the old devcard hand</li>
	 *      <li>If it is any other card, it goes into the new devcard hand (unplayable this turn)</li>
	 *    </ul>
	 *  </ul>
	 */
	public void buyDevCard();
	
	/**
	 * The player can play the "Year of Plenty Card" and gain any two resource from the bank if the preconditions are satisfied.
	 * @pre
	 * <ul>
	 *  <li> The player has the year of plenty card in their "old dev card hand"</li>
	 *  <li> The player hasn't played a devlopment card this turn yet</li>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The two resources the player specifies are in the bank</li>
	 * </ul>
	 * @post
	 * <ul>
	 *  <li>The player gains one each of the two resources specified</li>
	 * </ul>
	 * @param ResourceOne: The first of the two resources that the player desires to receive from the bank
	 * @param ResourceTwo: The second of the two resources that the player desires to receive from the bank
	 */
	public void playYearOfPlentyCard(Resource resourceOne, Resource resourceTwo);
	
	/**
	 * The player can play the "Road Buidling Card" and build two new roads at no charge if the preconditions are satisfied.
	 * 
	 * @pre
	 * <ul>
	 *  <li> The player has the road building card in their "old dev card hand"</li>
	 *  <li> The player hasn't played a devlopment card this turn yet</li>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The first road location is connected to one of the players existing roads</li>
	 *  <li> The second road location is connected to one of the players existing roads</li>
	 *  <li> Neither location of the two new roads is on water</li>
	 *  <li> The player has to roads</li>
	 * </ul>
	 * 
	 *  @post
	 *  <ul>
	 *    <li>The player uses two roads</li>
	 *   <li>The map lists the placement of the roads correctly</li>
	 *  </ul>
	 *  @param spot1: an EdgeLocation that specifies the location of the first road to be built
	 *  @param spot2: an EdgeLocation that specifies the location of the second road to be built
	 */
	public void playRoadBuildingCard(EdgeLocation spot1, EdgeLocation spot2);
	
	/**
	 * The player can play the "Monopoly Card" and he/she specified a resource and all other players must give that resource, if available, to the player playing the monopoly card.
	 * 
	 * @pre
	 * <ul>
	 *  <li> The player has the monopoly card in their "old dev card hand"</li>
	 *  <li> The player hasn't played a devlopment card this turn yet</li>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 * </ul>
	 *  
	 * @post
	 * <ul>
	 *  <li> All other players lost the resource card type chosen</li>
	 *  <li> The player of the card gets as much of the resource as the other players have to give of that resource</li>
	 * </ul>
	 * @param resource: The specified Resource that the player wants to receive
	 */
	public void playMonopolyCard(Resource resource);

	/**
	 * The player can play the "Soldier Card" and he/she now have to move the robber to a new location on the map and steal two resources from a player that has a settlement touching the robbers new location.
	 * 
	 * @pre
	 * <ul>
	 *  <li> The player has the soldier plenty card in their "old dev card hand"</li>
	 *  <li> The player hasn't played a devlopment card this turn yet</li>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The robber must move locations so the new location passed in cannot be the robbers current location</li>
	 *  <li> The player to rob has cards</li>
	 * </ul>
	 *  
	 * @post
	 * <ul>
	 *  <li> The robber is in the new location</li>
	 *  <li> The player to rob gives one random resource card to the player playing the soldier card</li>
	 * </ul>
	 *  
	 * @param location: Hexlocation that specifies the robbers new location on the map
	 * @param victomIndex: playerIndex of whom is getting robbed
	 */
	public void playSoldierCard(HexLocation location, playerIndex victimIndex);
	
	/**
	 * The player can play the "Monument Card" and he/she will gain one victory point
	 * 
	 * @pre
	 *  <ul>
	 *  <li> The player hasn't played a devlopment card this turn yet</li>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  </ul>
	 * @post
	 *  <ul><li> The player gains a victory point</li><ul>
	 */
	public void playMonumentCard();
	//Taylor End
}