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
import client.model.Log;
/**
 * 
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
	
	@Test
	public void testLoginUser() {
		UserResponse response;
		
		//invalid login attempt
		response = proxy.loginUser(TestingConstants.VALID_USERNAME, TestingConstants.INVALID_PASSWORD);
		assertEquals("Login response code for unsuccessful login attempt", false, response.isSuccessful());
		assertEquals("Login response message for unsuccessful login attempt", TestingConstants.LOGIN_FAIL_MESSAGE, response.getMessage()); 
		
		//valid login attempt
		response = proxy.loginUser(TestingConstants.VALID_USERNAME,TestingConstants.VALID_PASSWORD);
		assertEquals("Login response code for successful login attempt",true, response.isSuccessful());
		assertEquals("Login response cookie for successful login attempt for Sam", TestingConstants.VALID_LOGIN_COOKIE_CLIENT, response.getCookie());
	}
	
	@Test
	public void testRegisterUser() {
		UserResponse response;
		
		//register with name already in use
		response = proxy.registerUser(TestingConstants.INVALID_REGISTER_USERNAME, TestingConstants.VALID_PASSWORD);
		assertEquals("Register response code for register duplicate username attempt", false, response.isSuccessful());
		assertEquals("Register response message for unsuccessful login attempt", TestingConstants.REGISTER_FAIL_MESSAGE, response.getMessage());
		
		//register with unique name and valid password
		response = proxy.registerUser(TestingConstants.VALID_REGISTER_USERNAME, TestingConstants.VALID_REGISTER_PASSWORD);
		assertEquals("Register response code for successful register attempt",true, response.isSuccessful());
		assertEquals("Register response cookie for successful register attempt", TestingConstants.VALID_LOGIN_COOKIE_CLIENT, response.getCookie());
	}
	
	@Test
	public void testListGames() {
		//test list games
		ListGamesResponse response = proxy.listGames(null);
		assertEquals("Response code for successful listGames attempt",true, response.isSuccessful());
		assertEquals("Response object for successful listGames attempt", TestingConstants.GAMES_LIST, response.getGameDescriptions());
	}
	
	@Test
	public void testCreateGame() {
		//test create game
		CreateGameResponse response = proxy.createGame(false, false, false, TestingConstants.NEW_GAME_NAME, null);
		assertEquals("Response code for successful createGames attempt",true, response.isSuccessful());
		assertEquals("Response object for successful createGames attempt", TestingConstants.NEW_GAME, response.getGameDescription());
	}
	
	@Test
	public void testJoinGame() {
		//test join game invalid login cookie
		JoinGameResponse response = proxy.joinGame(CatanColor.BLUE, TestingConstants.VALID_JOIN_GAME_INDEX, TestingConstants.INVALID_LOGIN_COOKIE);
		assertEquals("Response code for unsuccessful joinGame attempt", false, response.isSuccessful());
		
		//test join game valid login cookie
		response = proxy.joinGame(CatanColor.BLUE, TestingConstants.VALID_JOIN_GAME_INDEX, TestingConstants.VALID_LOGIN_COOKIE_CLIENT);
		assertEquals("Response code for successful joinGame attempt",true, response.isSuccessful());
		assertEquals("Response object for successful joinGame attempt", TestingConstants.VALID_JOINED_GAME_COOKIE, response.getCookie());
	}
		
	@Test
	public void testGetGameModel() {
		//invalid cookie
		GetGameModelResponse response = proxy.getGameModel(TestingConstants.CLIENT_GAME_VERSION, TestingConstants.INVALID_LOGIN_COOKIE);
		assertEquals("Response code for unsuccessful getGameModel attempt (invalid cookie)", false, response.isSuccessful());
		
		//valid
		response = proxy.getGameModel(TestingConstants.CLIENT_GAME_VERSION, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful getGameModel attempt", true, response.isSuccessful());
		assertEquals("Game version for successful getGameModel attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testResetGame() {
		//valid
		ResetGameResponse response = proxy.resetGame(TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful resetGame attempt", true, response.isSuccessful());
		assertEquals("Game version for successful resetGame attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	

	@Test
	public void testGetGameCommands() {
		//valid
		GetGameCommandsResponse response = proxy.getGameCommands(TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful getGameCommands attempt", true, response.isSuccessful());
		assertEquals("Command log is valid for successful getGameCommands attempt", TestingConstants.getCommandsLog().getLogMessages(), response.getCommands().getLogMessages());
	}
	
	@Test
	public void testPostGameCommands() {
		//invalid command format
		PostGameCommandsResponse response;
		response = proxy.postGameCommands(new Log(), TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful postGameCommands attempt", false, response.isSuccessful());
		assertEquals("Game version for successful postGameCommands attempt", TestingConstants.INVALID_COMMANDS_MESSAGE, response.getErrorMessage());
		
		//valid command format
		response = proxy.postGameCommands(TestingConstants.getCommandsLog(), TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful postGameCommands attempt", true, response.isSuccessful());
		assertEquals("Game version for successful postGameCommands attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testListAI() {
		//valid
		ListAIResponse response = proxy.listAI(null);
		assertEquals("Response code for successful listAIs attempt",true, response.isSuccessful());
		assertEquals("List AIs available to add to game", TestingConstants.MOCK_AIS_LIST, response.getAiTypes());
	}
	
	@Test
	public void testAddAI() {
		//invalid
		AddAIResponse response = proxy.addAI(TestingConstants.INVALID_AI, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for unsuccessful addAIs attempt",false, response.isSuccessful());
		
		//valid
		response = proxy.addAI(TestingConstants.MOCK_AIS_LIST.get(0), TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful addAIs attempt",true, response.isSuccessful());
	}
	
	@Test
	public void testChangeLogLevel() {
		//valid
		ChangeLogLevelResponse response = proxy.changeLogLevel(ServerLogLevel.SEVERE, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful change server log to severe level",true, response.isSuccessful());
	}
	
	@Test
	public void testSendChat() {
		//invalid cookie
		MoveResponse response = proxy.sendChat(TestingConstants.PLAYER_INDEX, "hello world", TestingConstants.INVALID_LOGIN_COOKIE);
		assertEquals("Response code for unsuccessful send chat attempt",false, response.isSuccessful());
		
		//valid send "hello world"
		response = proxy.sendChat(TestingConstants.PLAYER_INDEX, TestingConstants.CHAT_CONTENT, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for unsuccessful send chat attempt", true, response.isSuccessful());
		assertEquals("Response game object for unsuccessful send chat attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testAcceptTrade() {
		//valid
		MoveResponse response = proxy.acceptTrade(TestingConstants.PLAYER_INDEX, true, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful accept trade attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful accept trade attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testDiscardCards() {
		//valid
		MoveResponse response = proxy.discardCards(TestingConstants.PLAYER_INDEX, TestingConstants.RESOURCE_HAND, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful discard attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful discard attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testRollNumber() {
		//valid
		MoveResponse response = proxy.rollNumber(TestingConstants.PLAYER_INDEX, TestingConstants.ROLL_NUMBER, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful roll number attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful roll number attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void  testBuildRoad() {
		//valid
		MoveResponse response = proxy.buildRoad(TestingConstants.PLAYER_INDEX, TestingConstants.EDGE_LOCATION, true, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful build road attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful build road attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testBuildSettlement() {
		//valid
		MoveResponse response = proxy.buildSettlement(TestingConstants.PLAYER_INDEX, TestingConstants.VERTEX_LOCATION, true, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful build settlement attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful build settlement attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testBuildCity() {
		//valid
		MoveResponse response = proxy.buildCity(TestingConstants.PLAYER_INDEX, TestingConstants.VERTEX_LOCATION, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful build city attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful build city attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testOfferTrade() {
		//valid
		MoveResponse response = proxy.offerTrade(TestingConstants.PLAYER_INDEX, TestingConstants.RESOURCE_HAND, TestingConstants.ANOTHER_PLAYER_INDEX, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful trade offer attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful trade offer attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testOfferMaritimeTrade() {
		//valid
		MoveResponse response = proxy.maritimeTrade(TestingConstants.PLAYER_INDEX, TestingConstants.MARITIME_RATIO, TestingConstants.RESOURCE_TYPE, TestingConstants.OTHER_RESOURCE_TYPE, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful maritime trade attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful maritime trade attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testFinishTurn() {
		//valid
		MoveResponse response = proxy.finishTurn(TestingConstants.PLAYER_INDEX, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful finish turn attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful finish turn attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testBuyDevCard() {
		//valid
		MoveResponse response = proxy.buyDevCard(TestingConstants.PLAYER_INDEX, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful buy dev card attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful buy dev card attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testPlayYearOfPlenty() {
		MoveResponse response = proxy.playYearOfPlentyCard(TestingConstants.PLAYER_INDEX, TestingConstants.RESOURCE_TYPE, TestingConstants.OTHER_RESOURCE_TYPE, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful play year of plenty attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful play year of plenty attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testPlayRoadBuilding() {
		MoveResponse response = proxy.playRoadBuildingCard(TestingConstants.PLAYER_INDEX, TestingConstants.EDGE_LOCATION, TestingConstants.ANOTHER_EDGE, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful dev road building attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful dev road building attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testPlayMonopoly() {
		MoveResponse response = proxy.playMonopolyCard(TestingConstants.PLAYER_INDEX, TestingConstants.RESOURCE_TYPE, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful play monopoly attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful play monopoly card attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testPlaySoldier() {
		MoveResponse response = proxy.playSoldierCard(TestingConstants.PLAYER_INDEX, TestingConstants.ANOTHER_PLAYER_INDEX, TestingConstants.HEX_LOCATION, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful play soldier attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful play soldier attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
	
	@Test
	public void testPlayMonument() {
		MoveResponse response = proxy.playMonumentCard(TestingConstants.PLAYER_INDEX, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Response code for successful play monument attempt", true, response.isSuccessful());
		assertEquals("Response game object for successful play monument attempt", TestingConstants.getServerModel().getVersion(), response.getGameModel().getVersion());
	}
}
