package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import client.model.Bank;
import client.model.Chat;
import client.model.Log;
import client.model.Map;
import client.model.Player;
import client.model.TradeOffer;
import client.model.TurnTracker;
import proxy.ICommunicator;
import proxy.IServer;
import proxy.ITranslator;
import proxy.MockCommunicator;
import proxy.ProxyServer;
import proxy.TranslatorJSON;
import shared.ServerMethodResponses.*;
import shared.definitions.CatanColor;
import shared.definitions.GameDescription;
import shared.definitions.GameModel;
import shared.definitions.PlayerDescription;
import shared.definitions.PlayerIndex;
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
	
	/*
	@Test
	public void testSaveGame() {
		
	}
	
	@Test
	public void testLoadGame() {
		
	}
	*/
	
	
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
		assertEquals("Game version for successful getGameCommands attempt", TestingConstants.getCommandsLog().getLogMessages().size(), response.getCommands());
	}
	
	/*
	@Test
	public void testPostGameCommands() {
		//invalid command format
		
		//invalid cookie
		
		//valid command format
		
	}
	*/
	@Test
	public void testListAI() {
		//valid
		
		ListAIResponse response = proxy.listAI(null);
		assertEquals("Response code for successful listAIs attempt",true, response.isSuccessful());
		assertEquals("List AIs available to add to game", TestingConstants.MOCK_AIS_LIST, response.getAiTypes());
	}
	
	/*
	@Test
	public void testAddAI() {
		//invalid cookie
		
		//no space to add ai
		
		//valid
	}
	
	@Test
	public void testChangeLogLevel() {
		//valid (change log level to sever)
		
	}
	
	@Test
	public void testSendChat() {
		//invalid cookie
		
		//valid send "hello world"
	}
	
	@Test
	public void testAcceptTrade() {
		
	}
	
	@Test
	public void testDiscardCards() {
		
	}
	
	@Test
	public void testRollNumber() {
		
	}
	
	@Test
	public void  testBuildRoad() {
		
	}
	
	@Test
	public void testBuildSettlement() {
		
	}
	
	@Test
	public void testBuildCity() {
		
	}
	
	@Test
	public void testOfferTrade() {
		
	}
	
	@Test
	public void testOfferMaritimeTrade() {
		
	}
	
	@Test
	public void testFinishTurn() {
		
	}
	
	@Test
	public void testBuyDevCard() {
		
	}
	
	@Test
	public void testPlayYearOfPlenty() {
		
	}
	
	@Test
	public void testPlayRoadBuilding() {
		
	}
	
	@Test
	public void testPlayMonopoly() {
		
	}
	
	@Test
	public void testPlaySoldier() {
		
	}
	
	@Test
	public void testPlayMonument() {
		
	}
	
	
	
	
	
	@Test
	public void testTranslateToJSON() {		
		List<Player> players=new ArrayList();
		players.add(new Player("Blue", "Ender", 0));
		players.add(new Player("Orange", "Ralph", 1));
		players.add(new Player("Red", "Santa", 2));
		players.add(new Player("Brown", "Frodo", 3));
		
		GameModel game=new GameModel(new Bank(),new Chat(),new Log(),new Map(),players,null,new TurnTracker(),0,-1);
		String translation=trans.translateTo(game);
		//System.out.println(translation);
		assertEquals("JSON should match",translation,"{\"chat\":{\"lines\":[]},\"bank\":{\"brick\":19,\"ore\":19,\"sheep\":19,\"wheat\":19,\"wood\":19},\"log\":{\"lines\":[]},\"map\":{\"radius\":3},\"players\":[{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Blue\",\"discarded\":false,\"monuments\":0,\"name\":\"Ender\",\"playerIndex\":0,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":0},{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Orange\",\"discarded\":false,\"monuments\":0,\"name\":\"Ralph\",\"playerIndex\":1,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":0},{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Red\",\"discarded\":false,\"monuments\":0,\"name\":\"Santa\",\"playerIndex\":2,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":0},{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Brown\",\"discarded\":false,\"monuments\":0,\"name\":\"Frodo\",\"playerIndex\":3,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":0}],\"turnTracker\":{\"currentTurn\":0,\"status\":\"Playing\",\"longestRoad\":-1,\"largestArmy\":-1},\"version\":0,\"winner\":-1,\"deck\":{\"monopoly\":2,\"monument\":5,\"roadBuilding\":2,\"soldier\":14,\"yearOfPlenty\":2}}");
	}
	@Test
	public void testTranslateFrom() {		
		String message = "{\"chat\":{\"lines\":[]},"
				+ "\"bank\":{\"brick\":10,\"ore\":19,\"sheep\":9,\"wheat\":19,\"wood\":19},"
				+ "\"log\":{\"lines\":[]},\"map\":{\"radius\":3},"
				+ "\"players\":[{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Blue\",\"discarded\":false,\"monuments\":0,\"name\":\"Ender\",\"playerIndex\":0,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
				+ "{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Orange\",\"discarded\":false,\"monuments\":0,\"name\":\"Ralph\",\"playerIndex\":1,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
				+ "{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Red\",\"discarded\":false,\"monuments\":0,\"name\":\"Henry\",\"playerIndex\":2,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
				+ "{\"cities\":4,\"settlements\":5,\"roads\":10,\"color\":\"Brown\",\"discarded\":false,\"monuments\":0,\"name\":\"Frodo\",\"playerIndex\":3,\"playedDevCard\":true,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2}],"
				+ "\"turnTracker\":{\"currentTurn\":0,\"status\":\"Playing\",\"longestRoad\":-1,\"largestArmy\":-1},"
				+ "\"version\":0,\"winner\":-1,"
				+ "\"deck\":{\"monopoly\":2,\"monument\":5,\"roadBuilding\":2,\"soldier\":14,\"yearOfPlenty\":2}}";
		GameModel game = (GameModel) trans.translateFrom(message, GameModel.class);
		
		assertEquals("Bank bricks should match(10)",game.getBank().getBrick(),10);
		assertEquals("Bank sheeps should match(9)",game.getBank().getSheep(),9);
		assertEquals("Player at (0) should be named Ender",game.getPlayers().get(0).getName(),"Ender");
		assertEquals("Player at (2) should be named Henry",game.getPlayers().get(2).getName(),"Henry");
		assertEquals("Player at (3) should have 10 roads",game.getPlayers().get(3).getRoads(),10);
		assertEquals("Player at (3) should have played dev card",game.getPlayers().get(3).hasPlayedDevCard(),true);
		assertEquals("Map should have a radius of 3",game.getMap().getRadius(),3);
		
	}
	*/
}
