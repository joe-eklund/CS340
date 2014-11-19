package proxy;

import shared.ServerMethodResponses.AddAIResponse;
import shared.ServerMethodResponses.ChangeLogLevelResponse;
import shared.ServerMethodResponses.CreateGameResponse;
import shared.ServerMethodResponses.GetGameCommandsResponse;
import shared.ServerMethodResponses.GetGameModelResponse;
import shared.ServerMethodResponses.JoinGameResponse;
import shared.ServerMethodResponses.ListAIResponse;
import shared.ServerMethodResponses.ListGamesResponse;
import shared.ServerMethodResponses.LoadGameResponse;
import shared.ServerMethodResponses.LoginUserResponse;
import shared.ServerMethodResponses.MoveResponse;
import shared.ServerMethodResponses.PostGameCommandsResponse;
import shared.ServerMethodResponses.RegisterUserResponse;
import shared.ServerMethodResponses.ResetGameResponse;
import shared.ServerMethodResponses.SaveGameResponse;
import shared.definitions.CatanColor;
import shared.definitions.ResourceHand;
import shared.definitions.ResourceType;
import shared.definitions.ServerLogLevel;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.Log;

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
	 *	      <li>cookie = the player's catan cookie</li>
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
	 */
	public LoginUserResponse loginUser(String username, String password);
	
	
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
	 *	       <li>cookie = the player's catan cookie</li>
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
	 * 
	 */
	public RegisterUserResponse registerUser(String username, String password);
	
	
	/**
	 * This call returns information about all of the current games
	 * 
	 * @pre
	 * 	Valid login cookie
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
	 * @param cookie : catan cookie as set by server
	 */
	public ListGamesResponse listGames(String cookie);
	
	
	/**
	 * Creates an empty game on the server
	 * 
	 * @pre
	 * 	Valid login cookie
	 * 
	 * @post
	 * <ol>
	 *   <li>creates an empty game on server according to parameter booleans "randomTiles", "randomNumbers", and "Random Ports"; the parameter name settings are default if the given parameter is false otherwise the setting (i.e. ports) is randomized</li>
	 * 	 <li>returns a CreateGameResponse Object with the following field settings (See CreateGameResponse class for more details):
	 * 	   <ol>
	 * 		 <li>successful = true</li>
	 * 	     <li>gameDescription = a game description for the just created game ("title" field is set to provided name parameter and "id"
	 * 							field is set to the server assigned id for the game)</li>
	 * 	   </ol>
	 *   </li>
	 * </ol>
	 * 
	 *  @param randomTiles : create game with randomized game tiles
	 *  @param randomNumbers : create game with randomized numbers ("chits")
	 *  @param randomPorts : create game with randomized ports
	 *  @param name : the name which the created game will have
	 *  @param cookie : catan cookie as set by server
	 */
	public CreateGameResponse createGame(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name, String cookie);
	
	
	/**
	 * 
	 * @param gameID
	 * @param name
	 * @param cookie
	 * @return
	 */
	public SaveGameResponse saveGame(int gameID, String name, String cookie);
	
	/**
	 * 
	 * @param name
	 * @param cookie
	 * @return
	 */
	public LoadGameResponse loadGame(String name, String cookie);
	
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
	 * 	    <ol>
	 * 		  <li>successful = true</li>
	 * 	      <li>cookie = player.game cookie</li>
	 * 	    </ol>
	 *    </li>
	 *  </ol>
	 * 
	 * @param color: the available color the player wishes to use
	 * @param gameID: the ID for the game which the player wishes to join
	 * @param cookie: catan cookie as set by server
	 */
	public JoinGameResponse joinGame(CatanColor color, int gameID, String cookie);
	
	
	/**
	 * Fetches the JSON for the model of the current game.
	 * 
	 * @pre
	 *  <ol>
	 * 	  <li>Requester has a valid catan.user and catan.game id set in cookie</li>
	 * 
	 * @post
	 *  if the server catan model is the same as the client model returns GetGameModelResponse as follows:
	 *    <ol>
	 *       <li>needToUPdate = false</li>
	 *       <li>successful = true</li>
	 *       <li>gameModel = null</li>
	 *    </ol>
	 *  else returns GetGameModelResponse as follows:
	 *    <ol>
	 *      <li>needToUPdate = true</li>
	 *      <li>successful = true</li>
	 *      <li>gameModel = current server game model (already translated to Java from JSON response)</li>
	 *    </ol>
	 * 
	 *  @param version : current game version number for client game model
	 *  @param cookie : catan cookie as set by server
	 */
	public GetGameModelResponse getGameModel(int version, String cookie);
	
	
	/**
	 * Resets the game to how it was after all the players joined
	 * 
	 * @pre
	 *  <ol>
	 *    <li>Requester has a valid catan.user and catan.game id set in cookie</li>
	 *  </ol>
	 * 
	 * @post
	 * 	resets server game and returns ResetGameResponse as follows:
	 * 	  <ol>
	 *      <li>successful = true</li>
	 *      <li>gameModel = reset server game model</li>
	 *    </ol>
	 * 
	 * @param cookie: catan cookie as set by server
	 * 
	 */
	public ResetGameResponse resetGame(String cookie);
	
	
	/**
	 * Gets a list of all the commands played on a game
	 * 
	 * @pre
	 *  <ol>
	 *    <li>Requester has a valid catan.user and catan.game id set in cookie</li>
	 *  </ol>
	 *  
	 * @post
	 *   returns a GetGameCommandsResponse as follows:
	 *   <ol>
	 *     <li>successful = true</li>
	 *     <li>commands = server log of commands executed so far in game</li>
	 *   </ol>
	 * 
	 * @return string: see post for possible values
	 * 
	 * @param cookie : catan cookie as set by server
	 */
	public GetGameCommandsResponse getGameCommands(String cookie);
	
	
	/**
	 * Applies a list of commands to the current game.
	 * 
	 * @pre
	 *  <ol>
	 *    <li>Requester has a valid catan.user and catan.game id set in cookie</li>
	 *  </ol>
	 *   
	 * 
	 * @post
	 *  if the content could not be deserialized, or an error in applying the commands:
	 *     1) returns a string starting with "BAD" and followed by an error message detailing failure
	 *  if the commands were successfully deserialized:
	 *     1) returns a game model
	 * 
	 *  @param commands: list of valid game commands ordered chronologically from earliest to most recent
	 *  @param cookie: catan cookie as set by server
	 */
	public PostGameCommandsResponse postGameCommands(Log commands, String cookie);
	
	
	/**
	 * Lists the available AI types that may be added to a game
	 * 
	 * @pre
	 *  None
	 * 
	 * @post
	 *   returns a listAIResponse as follows:
	 *   <ol>
	 *     <li>successful = true</li>
	 *     <li>aiTypes = server provided list of available ai types to add to game</li>
	 *   </ol>
	 * 
	 * @param cookie: catan cookie as set by server
	 * 
	 */
	public ListAIResponse listAI(String cookie);
	
	
	/**
	 * Adds an AI to the game
	 * 
	 * @pre
	 * <ol>
	 *  <li> The player has a valid catan.user and catan.game id</li>
	 *  <li> There is space in the game for an AI player</li>
	 * </ol>
	 *  
	 * @post
	 * <ol>
	 *  <li> The AI player is added to the next open spot in the game associated with the poster’s catan.game cookie</li>
	 *  <li> The AI player uses a color not taken by any other player in the game</li>
	 *  <li> Returns an AddAIResponse object with successful set to true</li>
	 * </ol>
	 * 
	 * @param aiToAdd string from list<string> provided by listAI() command
	 * @param cookie: catan cookie as set by server
	 * 
	 */
	public AddAIResponse addAI(String aiToAdd, String cookie);
	
	
	/**
	 * Sets the server’s logging level
	 * 
	 * @pre
	 *  <ol>
	 *    <li>The poster specifies a valid logging level. Valid values include:
	 *      <ul>
	 *        <li>SEVERE,</li> 
	 *        <li>WARNING,</li> 
	 *        <li>INFO,</li> 
	 *        <li>CONFIG,</li> 
	 *        <li>FINE,</li>
	 *        <li>FINER,</li>
	 *        <li>FINEST</li>
	 *      </ul></li>
	 *  </ol>
	 * 
	 * @post
	 *  <ol>
	 *    <li>The Server logging level is set to provided parameter</li>
	 *    <li>Returns a ChangeLogLevelResponse with successful set to true</li>
	 *  </ol>
	 *  
	 *  @param logLevel: desired logging level for server
	 *  @param cookie: catan cookie as set by server
	 *  
	 */
	public ChangeLogLevelResponse changeLogLevel(ServerLogLevel logLevel, String cookie);
	
	
	/**
	 * Sends chat message to game chat log
	 * 
	 * @pre
	 *  None
	 * 
	 * @post
	 *   <ol>
	 *     <li>game chat log contains message</li>
	 *     <li>returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 *   </ol>
	 * 
	 * @param playerIndex: player index in current game
	 * @param message: message player wishes to post to chat log
	 * @param cookie: catan cookie as set by server 
	 * 
	 */
	public MoveResponse sendChat(int playerIndex, String message, String cookie);
	
	
	/**
	 * Accept another player's trade offer
	 * 
	 * @pre
	 * 	<ol>
	 *    <li>player has been offered a domestic trade</li>
	 *    <li>if willAccept = true
	 *      <ol>
	 *        <li>player has resources offered on his/her part in trade</li>
	 *      </ol>
	 *    </li>
	 *  </ol>
	 *     
	 * @post
	 *  <ol>
	 *    <li>
	 * 	    <ol>
	 *        <li>if willAccept = true:
	 *          <ol>
	 *            <li>player accepting and player who offered exchange resources specified in trade</li>
	 *          </ol>
	 *        <li>if willAccept = false:
	 *          <ol>
	 *            <li>no resources are exchanged</li>
	 *          </ol>
	 *        </li>
	 *      </ol>
	 *    </li>
	 *    <li>trade offer is removed from game model</li>
	 *    <li>returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 *  </ol>
	 *     
	 * 
	 * @param playerIndex: player index in current game
	 * @param willAccept: boolean value of whether or not player accepts proposed trade
	 * @param cookie: catan cookie as set by server 
	 *  
	 */
	public MoveResponse acceptTrade(int playerIndex, boolean willAccept, String cookie);
	
	
	/**
	 * Discards cards from players hand
	 * 
	 * @pre
	 * <ol>
	 *  <li> Player is in 'Discarding' state</li>
	 *  <li> Player has over 7 cards</li>
	 *  <li> Player has the cards player is choosing to discard.</li>
	 * </ol>
	 * 
	 * @post
	 * <ol>
	 *  <li> Player client now enters 'Robbing' state</li>
	 *  <li> Player gives up specified cards</li>
	 *  <li> returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 * </ol>
	 *  
	 *  @param playerIndex: player index in current game
	 *  @param resources: list of valid resource types
	 *  @param resourceHand: contains map of each resource type and the associated number of cards for the type the player is dropping
	 *  @param cookie: catan cookie as set by server
	 */
	public MoveResponse discardCards(int playerIndex, ResourceHand resourceHand, String cookie);

	/**
	 * Specifies to the server the number that was rolled by the player
	 * @pre
	 * <ol>
	 *  <li> The client model's status is "rolling"</li>
	 *  <li> It is the turn of the player trying to roll the dice</li>
	 * </ol>
	 *  
	 * @post
	 * <ol>
	 *  <li> An integer from 2 to 12 is returned that represents the that was rolled</li>
	 *  <li> The client model's status is now in "discarding" or "robbing" or "playing"</li>
	 *  <li>returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 * </ol>
	 * 
	 *  @param playerIndex: player index in current game
	 *  @param number: an integer from 2 to 12 that represents the number that was rolled.
	 *  @param cookie: catan cookie as set by server
	 */
	public MoveResponse rollNumber(int playerIndex, int number, String cookie);
	
	/**
	 * Builds a road of the same color as the player building the road and at the specified location on the map
	 * 
	 * @pre
	 * <ol>
	 * 	<li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The road location is open</li>
	 *  <li> The road location is connected to another road</li>
	 *  <li> The road location is not on water</li>
	 *  <li> The player has the necessary resources to build a road (1 wood, 1 brick)</li>
	 * </ol>
	 *  
	 * @post
	 * <ol>
	 *  <li> Player expends the resources to play the road (1 wood, 1 brick)</li>
	 *  <li> The map lists the road correctly</li>
	 *  <li> returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 * </ol>
	 * 
	 * @param playerIndex: player index in current game
	 * @param roadLocation: An EdgeLocation that declares where on the map the road will be built. 
	 * @param free: a boolean that specifies whether or not the road piece has been given to the player for free.
	 * @param cookie: catan cookie as set by server
	 */
	public MoveResponse buildRoad(int playerIndex, EdgeLocation roadLocation, boolean free, String cookie);
	
	/**
	 * Builds a settlement of the same color as the player building the road and at the specified location on the map
	 * 
	 * @pre
	 * <ol>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The settlement location is open</li>
	 *  <li> The settlement location is not on water</li>
	 *  <li> The settlement location is connected to one of your roads</li>
	 *  <li> The player has the necessary resources to build a settlement (1 wood, 1 brick, 1 wheat, 1 sheep)</li>
	 * </ol>
	 *  
	 * @post
	 * <ol>
	 *  <li> The player expends the resources necessary to play the settlement (1 wood, 1 brick, 1 wheat, 1 sheep)</li>
	 *  <li> The map lists the settlement location correctly</li>
	 *  <li> returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 * </ol>
	 * 
	 * @param playerIndex: player index in current game
	 * @param free: a boolean that specifies whether or not the settlement piece has been given to the player for free.
	 * @param cookie: catan cookie as set by server
	 * 
	 */
	public MoveResponse buildSettlement(int playerIndex, VertexLocation location, boolean free, String cookie);
	
	/**
	 * Builds a city of the same color as the player building the road and at the specified location on the map
	 * 
	 * @pre
	 * <ol>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The city location is where the player currently has a settlement</li>
	 *  <li> The player has the necessary resources to build a city (2 wheat, 3 ore)</li>
	 * </ol>
	 * 
	 * @post
	 * <ol> 
	 *  <li> The player expends the resources necessary to play the settlement (2 wheat, 3 ore)</li>
	 *  <li> The player gets a settlement back</li>
	 *  <li> The map lists the city location correctly</li>
	 *  <li> returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 * </ol>
	 * 
	 *  @param playerIndex: player index in current game
	 *  @param location: location of where city will be built
	 *  @param cookie: catan cookie as set by server
	 *  
	 */
	public MoveResponse buildCity(int playerIndex, VertexLocation location, String cookie);
	
	/**
	 * The player offers a trade of certain resources to a another player in the game in return for different resource.
	 * 
	 * @pre
	 * <ol>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The player owns the designated resources that want to be traded</li>
	 * </ol>
	 * 
	 * @post
	 * <ol>
	 *  <li> The trade is offered to the specified player and stored in the model</li>
	 *  <li> returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 * </ol>
	 * 
	 * @param playerIndex: player index in current game
	 * @param offer: ResourceHand indicating the cards to be given and received.  Negative numbers mean the players is receiving cards of that resource in the trade.
	 * @param receiver: playerIndex which specifies the recipient whom the player is trading with
	 * @param cookie: catan cookie as set by server 
	 * 
	 */
	public MoveResponse offerTrade(int playerIndex, ResourceHand offer, int receiver, String cookie);
	
	/**
	 * The player offers a trade of certain resources to the bank at a specified ratio depending on the port the player has a settlement on.
	 * 
	 * @pre
	 * <ol>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The player owns the designated resources that want to be traded</li>
	 * </ol>
	 * 
	 * @post
	 * <ol>
	 *  <li> The trade is offered to the specified player and stored in the model</li>
	 *  <li> returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 * </ol
	 * 
	 * @param playerIndex: player index in current game
	 * @param ratio: an integer of value 2, 3, or 4
	 * @param inputResource: Resource - The resource the player is giving
	 * @param outputResource: Resource - The resoure the player is receiving
	 * @param cookie: catan cookie as set by server
	 */
	public MoveResponse maritimeTrade(int playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource, String cookie);
	
	/**
	 * The player finishes his/her turn.
	 * 
	 * @pre
	 * <ol>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 * </ol>
	 *  
	 * @post
	 *  <ol> 
	 *   <li>It is the next players turn</li>
	 *   <li>returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 *  </ol>
	 *  
	 *  @param playerIndex: player index in current game
	 *  @param cookie: catan cookie as set by server
	 */
	public MoveResponse finishTurn(int playerIndex, String cookie);
	
	/**
	 * The player buys a development card from the bank.
	 * 
	 * @pre
	 * <ol>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The player has the necessary resources (1 ore, 1 wheat, 1 sheep)</li>
	 *  <li> There are development cards left in the deck.</li>
	 * </ol>
	 * @post
	 * <ol>
	 *  <li> The player has the new card</li>
	 *    <ol>
	 *    	<li>If it is a monument card, it goes into the old devcard hand</li>
	 *      <li>If it is any other card, it goes into the new devcard hand (unplayable this turn)</li>
	 *    </ol>
	 *  <li> returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 * </ol>
	 *  
	 *  @param playerIndex: player index in current game
	 *  @param cookie: catan cookie as set by server
	 */
	public MoveResponse buyDevCard(int playerIndex, String cookie);
	
	/**
	 * The player can play the "Year of Plenty Card" and gain any two resource from the bank if the preconditions are satisfied.
	 * @pre
	 * <ol>
	 *  <li> The player has the year of plenty card in their "old dev card hand"</li>
	 *  <li> The player hasn't played a devlopment card this turn yet</li>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The two resources the player specifies are in the bank</li>
	 * </ol>
	 * @post
	 * <ol>
	 *  <li>The player gains one each of the two resources specified</li>
	 *  <li> returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 * </ol>
	 * 
	 * @param playerIndex: player index in current game
	 * @param ResourceOne: The first of the two resources that the player desires to receive from the bank
	 * @param ResourceTwo: The second of the two resources that the player desires to receive from the bank
	 * @param cookie: catan cookie as set by server
	 * 
	 */
	public MoveResponse playYearOfPlentyCard(int playerIndex, ResourceType resource1, ResourceType resource2, String cookie);
	
	/**
	 * The player can play the "Road Buidling Card" and build two new roads at no charge if the preconditions are satisfied.
	 * 
	 * @pre
	 * <ol>
	 *  <li> The player has the road building card in their "old dev card hand"</li>
	 *  <li> The player hasn't played a devlopment card this turn yet</li>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The first road location is connected to one of the players existing roads</li>
	 *  <li> The second road location is connected to one of the players existing roads</li>
	 *  <li> Neither location of the two new roads is on water</li>
	 *  <li> The player has to roads</li>
	 * </ol>
	 * 
	 *  @post
	 *  <ol>
	 *    <li>The player uses two roads</li>
	 *    <li>The map lists the placement of the roads correctly</li>
	 *    <li> returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 *  </ol>
	 *  
	 *  @param playerIndex: player index in current game
	 *  @param spot1: an EdgeLocation that specifies the location of the first road to be built
	 *  @param spot2: an EdgeLocation that specifies the location of the second road to be built
	 *  @param cookie: catan cookie as set by server
	 */
	public MoveResponse playRoadBuildingCard(int playerIndex, EdgeLocation spot1, EdgeLocation spot2, String cookie);
	
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
	 * 
	 * @param playerIndex: player index in current game
	 * @param resource: The specified Resource that the player wants to receive
	 * @param cookie: catan cookie as set by server
	 * 
	 */
	public MoveResponse playMonopolyCard(int playerIndex, ResourceType resource, String cookie);

	/**
	 * The player can play the "Soldier Card" and he/she now have to move the robber to a new location on the map and steal two resources from a player that has a settlement touching the robbers new location.
	 * 
	 * @pre
	 * <ol>
	 *  <li> The player has the soldier plenty card in their "old dev card hand"</li>
	 *  <li> The player hasn't played a devlopment card this turn yet</li>
	 *  <li> It is the players turn</li>
	 *  <li> The client model status is "Playing"</li>
	 *  <li> The robber must move locations so the new location passed in cannot be the robbers current location</li>
	 *  <li> The player to rob has cards</li>
	 * </ol>
	 *  
	 * @post
	 * <ol>
	 *  <li> The robber is in the new location</li>
	 *  <li> The player to rob gives one random resource card to the player playing the soldier card</li>
	 *  <li> returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 * </ol>
	 *  
	 * @param playerIndex: player index in current game 
	 * @param location: Hexlocation that specifies the robbers new location on the map
	 * @param victomIndex: playerIndex of whom is getting robbed
	 * @param cookie: catan cookie as set by server
	 * 
	 */
	public MoveResponse playSoldierCard(int playerIndex, int victimIndex, HexLocation location, String cookie);
	
	/**
	 * The player can play the "Monument Card" and he/she will gain one victory point
	 * 
	 * @pre
	 *  <ol>
	 *    <li> The player hasn't played a devlopment card this turn yet</li>
	 *    <li> It is the players turn</li>
	 *    <li> The client model status is "Playing"</li>
	 *  </ol>
	 * @post
	 *  <ol>
	 *    <li> The player gains a victory point</li>
	 *    <li> returns MoveResponse with successful set to true and gameModel set to updated server game model<li>
	 *  <ol>
	 *  
	 *  @param playerIndex: player index in current game
	 *  @param cookie: catan cookie as set by server
	 */
	public MoveResponse playMonumentCard(int playerIndex, String cookie);


	public MoveResponse robPlayer(int playerIndex, int victimIndex,
			HexLocation location, String cookie);
}
