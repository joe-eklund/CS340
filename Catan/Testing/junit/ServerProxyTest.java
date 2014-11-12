package junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import proxy.ICommunicator;
import proxy.IServer;
import proxy.MockCommunicator;
import proxy.ProxyServer;
import proxy.TranslatorJSON;
import shared.ServerMethodResponses.AddAIResponse;
import shared.ServerMethodResponses.ChangeLogLevelResponse;
import shared.ServerMethodResponses.CreateGameResponse;
import shared.ServerMethodResponses.GetGameCommandsResponse;
import shared.ServerMethodResponses.GetGameModelResponse;
import shared.ServerMethodResponses.JoinGameResponse;
import shared.ServerMethodResponses.ListAIResponse;
import shared.ServerMethodResponses.ListGamesResponse;
import shared.ServerMethodResponses.MoveResponse;
import shared.ServerMethodResponses.PostGameCommandsResponse;
import shared.ServerMethodResponses.ResetGameResponse;
import shared.ServerMethodResponses.UserResponse;
import shared.definitions.CatanColor;
import shared.definitions.ServerLogLevel;
import shared.model.Log;
import static shared.definitions.TestingConstants.*;
/**
 * A class for testin ability of proxy to transmit parameter information to the client communicator and return response information back to presenter
 * -the mock communicator is used to verify that testing constants are send and received correctly
 * -tests verify proxy logic in terms of its ability to set up response objects, parse cookies, etc
 */
public class ServerProxyTest {
	private IServer proxy; 
	
	@Before 
	public void setUp() { 
		TranslatorJSON translator = new TranslatorJSON();
		ICommunicator mockCommunicator = new MockCommunicator();
		ProxyServer.setSingleton(mockCommunicator, translator, "UTF-8");
		proxy = ProxyServer.getSingleton();
	}
	
	/*
	 * basic test of proxy server login
	 * -verifies ability to send user prompt message for invalid credentials
	 * -verifies ability to send 'ok' message for valid credentials and user login cookie
	 */
	@Test
	public void testLoginUser() {
		UserResponse response;
		
		//invalid login attempt
		response = proxy.loginUser(VALID_USERNAME, INVALID_PASSWORD);
		assertEquals("Login response code for unsuccessful login attempt", false, response.isSuccessful());
		assertEquals("Login response message for unsuccessful login attempt", LOGIN_FAIL_MESSAGE, response.getMessage()); 
		
		//valid login attempt
		response = proxy.loginUser(VALID_USERNAME,VALID_PASSWORD);
		assertEquals("Login response code for successful login attempt",true, response.isSuccessful());
		assertEquals("Login response cookie for successful login attempt for Sam", VALID_LOGIN_COOKIE_CLIENT, response.getCookie());
	}
	
	/*
	 * basic test of proxy register new user
	 * -verifies ability to return message when user fails registrations
	 * -verifies ability to return 'ok' message for successful registration and user login cookie
	 */
	@Test
	public void testRegisterUser() {
		UserResponse response;
		
		//register with name already in use
		response = proxy.registerUser(INVALID_REGISTER_USERNAME, VALID_PASSWORD);
		assertEquals("Register response code for register duplicate username attempt", false, response.isSuccessful());
		assertEquals("Register response message for unsuccessful login attempt", REGISTER_FAIL_MESSAGE, response.getMessage());
		
		//register with unique name and valid password
		response = proxy.registerUser(VALID_REGISTER_USERNAME, VALID_REGISTER_PASSWORD);
		assertEquals("Register response code for successful register attempt",true, response.isSuccessful());
		assertEquals("Register response cookie for successful register attempt", VALID_LOGIN_COOKIE_CLIENT, response.getCookie());
	}
	
	/*
	 * basic test of listGames request
	 * -verifies ability to return games list of available games 
	 */
	@Test
	public void testListGames() {
		//test list games
		ListGamesResponse response = proxy.listGames(null);
		assertEquals("Response code for successful listGames attempt",true, response.isSuccessful());
		assertEquals("Response object for successful listGames attempt", GAMES_LIST, response.getGameDescriptions());
	}
	
	/*
	 * basic test of createGame request
	 * -verifies ability to send random game setting bools
	 * -verifies ability to return new game description
	 */
	@Test
	public void testCreateGame() {
		//test create game
		CreateGameResponse response = proxy.createGame(true, true, true, NEW_GAME_NAME, null);
		assertEquals("Response code for successful createGames attempt",true, response.isSuccessful());
		assertEquals("Response object for successful createGames attempt", NEW_GAME, response.getGameDescription());
	}
	
	/*
	 * basic join game test
	 * -verifies ability to send cookie through proxy
	 * -verifies response code
	 * -verifies ability return cookie through proxy
	 */
	@Test
	public void testJoinGame() {
		//test join game invalid login cookie
		JoinGameResponse response = proxy.joinGame(CatanColor.BLUE, VALID_JOIN_GAME_INDEX, INVALID_LOGIN_COOKIE);
		assertEquals("Response code for unsuccessful joinGame attempt", false, response.isSuccessful());
		
		//test join game valid login cookie
		response = proxy.joinGame(CatanColor.BLUE, VALID_JOIN_GAME_INDEX, VALID_LOGIN_COOKIE_CLIENT);
		assertEquals("Response code for successful joinGame attempt",true, response.isSuccessful());
		assertEquals("Response object for successful joinGame attempt", VALID_JOINED_GAME_COOKIE, response.getCookie());
	}
	
	/*
	 * basic game update test
	 * -verifies ability to get updated game with valid cookie
	 * -verifies response code
	 */
	@Test
	public void testGetGameModel() {
		//invalid cookie
		GetGameModelResponse response = proxy.getGameModel(CLIENT_GAME_VERSION, INVALID_LOGIN_COOKIE);
		assertEquals("Response code for unsuccessful getGameModel attempt (invalid cookie)", false, response.isSuccessful());
		int version = 0;
		
		//valid
		response = proxy.getGameModel(version, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful getGameModel attempt", true, response.isSuccessful());
		assertEquals("Game version for successful getGameModel attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic reset game test
	 * -verifies ability to return response code
	 * -verifies ability to return reset game model
	 */
	@Test
	public void testResetGame() {
		//valid
		ResetGameResponse response = proxy.resetGame(VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful resetGame attempt", true, response.isSuccessful());
		assertEquals("Game version for successful resetGame attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	

	/*
	 * basic get game commands test
	 * -verifies ability to transmit commands via proxy
	 * -verifies proxy ability to return request success
	 */
	@Test
	public void testGetGameCommands() {
		//valid
		GetGameCommandsResponse response = proxy.getGameCommands(VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful getGameCommands attempt", true, response.isSuccessful());
		assertEquals("Command log is valid for successful getGameCommands attempt", getCommandsLog().getLogMessages(), response.getCommands().getLogMessages());
	}
	
	/*
	 * basic post commands test
	 * -verifies ability to transmit cookie through proxy code
	 * -verifies ability to return fail code and error message
	 * -verifies ability to return success code
	 * -verifies ability to return valid game
	 */
	@Test
	public void testPostGameCommands() {
		//invalid command format
		PostGameCommandsResponse response;
		response = proxy.postGameCommands(new Log(), VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful postGameCommands attempt", false, response.isSuccessful());
		assertEquals("Game version for successful postGameCommands attempt", INVALID_COMMANDS_MESSAGE, response.getErrorMessage());
		
		//valid command format
		response = proxy.postGameCommands(getCommandsLog(), VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful postGameCommands attempt", true, response.isSuccessful());
		assertEquals("Game version for successful postGameCommands attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	/*
	 * basic list ai test
	 * -verifies ability to return request success code
	 * -verifies ability to return string list of available ais
	 */
	@Test
	public void testListAI() {
		//valid
		ListAIResponse response = proxy.listAI(null);
		assertEquals("Response code for successful listAIs attempt",true, response.isSuccessful());
		assertEquals("List AIs available to add to game", MOCK_AIS_LIST, response.getAiTypes());
	}
	
	/*
	 * basic add ai test
	 * -verifies ability to return unsuccessful code for invalid ai type
	 * -verifies ability to return success code for valid ai type
	 */
	@Test
	public void testAddAI() {
		//invalid
		AddAIResponse response = proxy.addAI(INVALID_AI, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for unsuccessful addAIs attempt",false, response.isSuccessful());
		
		//valid
		response = proxy.addAI(MOCK_AIS_LIST.get(0), VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful addAIs attempt",true, response.isSuccessful());
	}
	
	/*
	 * basic change log level test
	 * -verifies proxy ability to correct transmit server log change parameter
	 * -verifies proxy ability to return success code
	 */
	@Test
	public void testChangeLogLevel() {
		//valid
		ChangeLogLevelResponse response = proxy.changeLogLevel(ServerLogLevel.SEVERE, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful change server log to severe level",true, response.isSuccessful());
	}
	
	/*
	 * test send chat
	 * -verifies ability to return unsuccessful code from proxy
	 * -verifies ability to send chat through proxy
	 * -verifies ability to return success code through proxy
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void testSendChat() {
		//invalid cookie
		MoveResponse response = proxy.sendChat(PLAYER_INDEX, "hello world", INVALID_LOGIN_COOKIE);
		assertEquals("Response code for unsuccessful send chat attempt",false, response.isSuccessful());
		
		//valid send "hello world"
		response = proxy.sendChat(PLAYER_INDEX, CHAT_CONTENT, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for unsuccessful send chat attempt", true, response.isSuccessful());
		assertEquals("Response game object for unsuccessful send chat attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic accept trade test
	 * -verifies ability to send paramaters (resource hand, sender & receiver index, cookie) through proxy
	 * -verifies ability for proxy to return successfullness of request
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void testAcceptTrade() {
		//valid
		MoveResponse response = proxy.acceptTrade(PLAYER_INDEX, true, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful accept trade attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful accept trade attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic discard cards test
	 * -verifies ability to send paramaters (discard hand, player index, cookie) through proxy
	 * -verifies ability for proxy to return successfullness of request
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void testDiscardCards() {
		//valid
		MoveResponse response = proxy.discardCards(PLAYER_INDEX, RESOURCE_HAND, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful discard attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful discard attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic roll number test
	 * -tests ability to send player index, roll number, and cookie through proxy
	 * -tests ability to return success of request
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void testRollNumber() {
		//valid
		MoveResponse response = proxy.rollNumber(PLAYER_INDEX, ROLL_NUMBER, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful roll number attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful roll number attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic build road test
	 * -verifies ability of proxy to send playerindex, edge location, freeness, cookie to communicator
	 * -verifies ability to return successfullness code
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void  testBuildRoad() {
		//valid
		MoveResponse response = proxy.buildRoad(PLAYER_INDEX, EDGE_LOCATION, true, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful build road attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful build road attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic build settlement request test
	 * -verifies ability to send player index, vertex location, freeness, and cookie through proxy
	 * -verifies ability to return response success
	 * -verifies ability to return updated game model
	 */
	@Test
	public void testBuildSettlement() {
		//valid
		MoveResponse response = proxy.buildSettlement(PLAYER_INDEX, VERTEX_LOCATION, true, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful build settlement attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful build settlement attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic build city test
	 * -verifies ability to send player index, vertex location and cookie
	 * -verifies ability to return success of request
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void testBuildCity() {
		//valid
		MoveResponse response = proxy.buildCity(PLAYER_INDEX, VERTEX_LOCATION, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful build city attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful build city attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic send offer test
	 * -verifies ability to send playerindex, resource hand (offer), receiver index, cookie
	 * -verifies ability to return success of request
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void testOfferTrade() {
		//valid
		MoveResponse response = proxy.offerTrade(PLAYER_INDEX, RESOURCE_HAND, ANOTHER_PLAYER_INDEX, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful trade offer attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful trade offer attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic maritime trade/exchange test
	 * -verifies ability to send playerindex, ratio, in resource type, out resource type, cookie
	 * -verifies ability to return success of request
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void testOfferMaritimeTrade() {
		//valid
		MoveResponse response = proxy.maritimeTrade(PLAYER_INDEX, MARITIME_RATIO, RESOURCE_TYPE, OTHER_RESOURCE_TYPE, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful maritime trade attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful maritime trade attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic end turn request test
	 * -verifies ability to send player index and cookie through proxy
	 * -verifies ability to return success of request
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void testFinishTurn() {
		//valid
		MoveResponse response = proxy.finishTurn(PLAYER_INDEX, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful finish turn attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful finish turn attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic buy dev card test
	 * -verifies ability to send player index and cookie via proxy
	 * -verifies ability to return success of request
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void testBuyDevCard() {
		//valid
		MoveResponse response = proxy.buyDevCard(PLAYER_INDEX, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful buy dev card attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful buy dev card attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic play year of plenty card request
	 * -verifies ability to send player index, resource type 1, resource type 2, and cookie
	 * -verifies ability to return success of request
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void testPlayYearOfPlenty() {
		MoveResponse response = proxy.playYearOfPlentyCard(PLAYER_INDEX, RESOURCE_TYPE, OTHER_RESOURCE_TYPE, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful play year of plenty attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful play year of plenty attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic play road building dev card request test
	 * -tests ability to send player index, edge location 1 (spot1), edge location 2 (spot2), and cookie
	 * -verifies ability to return success of request
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void testPlayRoadBuilding() {
		MoveResponse response = proxy.playRoadBuildingCard(PLAYER_INDEX, EDGE_LOCATION, ANOTHER_EDGE, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful dev road building attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful dev road building attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic play monopoly request test
	 * -verifies ability to send player index, resource type 1, and cookie
	 * -verifies ability to return success of request
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void testPlayMonopoly() {
		MoveResponse response = proxy.playMonopolyCard(PLAYER_INDEX, RESOURCE_TYPE, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful play monopoly attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful play monopoly card attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic play soldier request
	 * -verifies ability send playerindex, victim index, hex location, and cookie
	 * -verifies ability to return success of request
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void testPlaySoldier() {
		MoveResponse response = proxy.playSoldierCard(PLAYER_INDEX, ANOTHER_PLAYER_INDEX, HEX_LOCATION, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful play soldier attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful play soldier attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	/*
	 * basic play monument card test
	 * -verifies ability to send player index and cookie
	 * -verifies ability to return success of request
	 * -verifies ability of proxy to return updated game model
	 */
	@Test
	public void testPlayMonument() {
		MoveResponse response = proxy.playMonumentCard(PLAYER_INDEX, VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful play monument attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful play monument attempt", getServerModel().getVersion(), response.getGameModel().getVersion());
	}
}
