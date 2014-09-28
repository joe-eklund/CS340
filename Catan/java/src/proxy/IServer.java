package proxy;

import java.io.UnsupportedEncodingException;

import shared.ServerMethodResponses.*;
import shared.definitions.CatanColor;
import shared.definitions.DiceRoll;
import shared.definitions.Log;
import shared.definitions.PlayerIndex;
import shared.definitions.ResourceHand;
import shared.definitions.ResourceType;
import shared.definitions.ServerLogLevel;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * An interface for a Server
 *
 */
public interface IServer {	
	
	/**
	 * Logs an existing user into the game server
	 * 
	 * @pre 
	 * 	None
	 * 
	 * @post 
	 * 	if the passed in (username, password) pair is valid:
	 *  <ol>
	 * 	  <li>The Player is logged in on the server (a catan.user cookie is set for the player)</li>
	 * 	  <li>Returns LoginUserResponse Object with the following field settings (See RegisteredUserResponse class for more info): 
	 *	    <ol>	 
	 *		  <li>successful = true</li>
	 *	      <li>message = null</li>
	 *	      <li>cookie = the player's catan.user cookie</li>
	 *	      <li>name = player's username</li>
	 *        <li>userID = players userID (not to be confused with their gameID when they join a game)</li>
	 *      </ol>
	 *    </li>
	 *  </ol>
	 *  else:
	 *  <ol>
	 * 	  <li>Returns LoginUserResponse Object with the following field settings (See RegisteredUserResponse class for more info): 
	 *	  	 <ol>
	 *		   <li>successful = false</li>
	 *	       <li>message = error message to be displayed to user</li>
	 *	       <li>cookie = null</li>
	 *	       <li>name = null</li>
	 *         <li>userID = null (0)</li>
	 *       </ol>
	 *    </li>
	 *  </ol>
	 * 	
	 * @param username: username for registered user
	 * @param password: password for registered user
	 * @throws UnsupportedEncodingException 
	 */
	public ILoginUserResponse loginUser(String username, String password) throws UnsupportedEncodingException;
	
	
	/**
	 *  Registers a new game user
	 *  
	 *  This call performs the following 2 actions:
	 *  <ol>
	 *    <li>Creates a new user account</li>
	 *	  <li>Logs the new user into the game server</li>
	 *  </ol>
	 * 
	 * @pre
	 * 	None
	 * 
	 * @post 
	 * 	if there is no existing user with the specified name:
	 *  <ol>
	 * 	  <li>An account is established for the player with the provided username and password</li>
	 * 	  <li>The Player is logged in on the server (a catan.user cookie is set for the player)</li>
	 * 	  <li>Returns RegisterUserResponse Object with the following field settings (See RegisteredUserResponse class for more info): 
	 *	  	 <ol>
	 *		   <li>successful = true</li>
	 *	       <li>message = null</li>
	 *	       <li>cookie = the player's catan.user cookie</li>
	 *	       <li>name = player's username</li>
	 *         <li>userID = players userID (not to be confused with their gameID when they join a game)</li>
	 *	     </ol>
	 *    </li>
	 *  </ol>
	 *  else:
	 *  <ol>
	 * 	  <li>Returns RegisterUserResponse Object with the following field settings (See RegisteredUserResponse class for more info): 
	 *	  	 <ol>
	 *		   <li>successful = false</li>
	 *	       <li>message = error message to be displayed to user</li>
	 *	       <li>cookie = null</li>
	 *	       <li>name = null</li>
	 *         <li>userID = null (0)</li>
	 *       </ol>
	 *     </li>
	 *  </ol>	
	 * 
	 *  @param username: the username the player wishes to use when logging into the game
	 *  @param password: the password the player wishes to use for logging in with the specified username
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	public IRegisterUserResponse registerUser(String username, String password) throws UnsupportedEncodingException;
	
	
	/**
	 * This call returns information about all of the current games
	 * 
	 * @pre
	 * 	None
	 * 
	 * @post
	 * <ol>
	 *   <li>returns a ListGamesResponse Object with the following field settings (See ListGamesResponse class for more details):
	 * 	   <ol>
	 * 		 <li>successful = true</li>
	 * 	     <li>gameDescriptions = a list containing one game description for each current game on the Catan server</li>
	 *     </ol>
	 *   </li>
	 * </ol>
	 * 
	 */
	public IListGamesResponse listGames();
	
	
	/**
	 * Creates an empty game on the server
	 * 
	 * @pre
	 * 	None
	 * 
	 * @post
	 * <ol>
	 *   <li>creates an empty game on server</li>
	 * 	 <li>returns a CreateGameResponse Object with the following field settings (See CreateGameResponse class for more details):
	 * 	   <ol>
	 * 		 <li>successful = true</li>
	 * 	     <li>gameDescription = a game description for the just created game ("title" field is set to provided name parameter and "id"
	 * 							field is set to the server assigned id for the game)</li>
	 * 	   </ol>
	 *   </li>
	 * </ol>
	 * 
	 *  @param name: the name which the created game will have
	 * 
	 */
	/*
	 * need to verify if name parameter is needed... Specs are worthless
	 */
	public ICreateGameResponse createGame(String name);
	
	
	/**
	 * Adds a player to the game and sets their game cookie
	 * 
	 * @pre
	 * <ol>
	 *  <li>Player has a valid catan.user cookie set</li>
	 * 	<li>Player is already part of game or there is space for the player to join game</li>
	 *  <li>The color submitted is a valid color (red, green, blue, yellow, puce, brown, white, purple, orange) and is available in the game</li>
	 * </ol>
	 * @post
	 * 	<oL>
	 * 	  <li>The player is now listed as a player for game associated with gameID and has the specified color</li>
	 *    <li>returns a JoinGameResponse Object with the following field settings (See JoinGameResponse class for more details):
	 *      <ol>
	 *      </ol>
	 *    </li>
	 *  </ol>
	 * 
	 * @param color: the available color the player wishes to use
	 * @param gameID: the ID for the game which the player wishes to join
	 */
	public IJoinGameResponse joinGame(CatanColor color, int gameID, String cookie);
	
	
	/**
	 * Fetches the JSON for the model of the current game.
	 * 
	 * @pre
	 * 	1) Requester has a valid catan.user and catan.game id set in cookie
	 * 
	 * @post
	 *  if the server catan model is the same as the client model 
	 *     1) returns null
	 *  else 
	 *     1) returns a the current server game model
	 * 
	 */
	public GetGameModelResponse getGameModel(int version);
	
	
	/**
	 * Resets the game to how it was after all the players joined
	 * 
	 * @pre
	 *  1) Requester has a valid catan.user and catan.game id set in cookie
	 * 
	 * @post
	 * 	1) Returns reset game model
	 * 
	 */
	public ResetGameResponse resetGame();
	
	
	/**
	 * Gets a list of all the commands played on a game
	 * 
	 * @pre
	 *  1) Requester has a valid catan.user and catan.game id set in cookie
	 *  
	 * @post
	 *  2) returns a list of the commands that have been executed in the game
	 * 
	 * @return string: see post for possible values
	 * 
	 */
	public GetGameCommandsResponse getGameCommands();
	
	
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
	 *     1) returns a game model
	 * 
	 *  @param commands: list of valid game commands ordered chronologically from earliest to most recent
	 * 
	 */
	public PostGameCommandsResponse postGameCommands(Log commands);
	
	
	/**
	 * Lists the available AI types that may be added to a game
	 * 
	 * @pre
	 *  None
	 * 
	 * @post
	 *  1) returns a list of strings 
	 * 
	 */
	public ListAIResponse listAI();
	
	
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
	 * 
	 * @param aiToAdd string from list<string> provided by listAI() command
	 */
	public AddAIResponse addAI(String aiToAdd);
	
	
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
	public ChangeLogLevelResponse changeLogLevel(ServerLogLevel logLevel);
	
	
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
	public void discardCards(ResourceHand resourceHand);

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
	public void rollNumber(DiceRoll number);
	
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
	public void buildSettlement(boolean free, VertexLocation location);
	
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
	public void buildCity(VertexLocation location);
	
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
	public void offerTrade(ResourceHand offer, PlayerIndex receiver);
	
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
	public void maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource);
	
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
	public void playYearOfPlentyCard(ResourceType resourceOne, ResourceType resourceTwo);
	
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
	public void playMonopolyCard(ResourceType resource);

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
	public void playSoldierCard(HexLocation location, PlayerIndex victimIndex);
	
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
}
