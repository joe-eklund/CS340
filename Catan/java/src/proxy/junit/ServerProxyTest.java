package proxy.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import client.model.*;
import proxy.*;
import shared.ServerMethodResponses.UserResponse;
import shared.definitions.GameModel;
import shared.definitions.PlayerIndex;
/**
 * 
 * @author Epper Marshall
 * Tests the translation of a GameModel to JSON and from JSON to a GameModel.
 */
public class ServerProxyTest {
	private IServer proxy; 
	
	@Before 
	public void setUp() { 
		ITranslator translator = new TranslatorJSON();
		ICommunicator mockCommunicator = new MockCommunicator(translator);
		ProxyServer.setSingleton(mockCommunicator, translator, "UTF-8");
		proxy = ProxyServer.getSingleton();
	}
	
	@Test
	public void testLoginUser() {
		String user;
		String pass;
		
		//invalid login attempt
		String message = "Login failed - bad password or username";
		String cookie = "";
		user = "Brooke";
		pass = "badpassword";
		UserResponse response = proxy.loginUser(user, pass);
		assertEquals("Login response code for unsuccessful login attempt should be false",response.isSuccessful(),false);
		assertEquals("Login response message for unsuccessful login attempt should be \"" + message + "\"", response.getMessage(), message); 
		
		//valid login attempt
		user = "Brooke";
		pass = "brooke";
		assertEquals("Login response code for successful login attempt should be true",response.isSuccessful(),true);
		assertEquals("Login response cookie for successful login attempt for Brooke should be \"" + cookie + "\"", response.getCookie(), cookie);
	}
	
	@Test
	public void testRegisterUser() {
		//register with username with less than 3 characters
		
		//register with username with more than 7 characters
		
		//register with username containing invalid character
		
		//register with name already in use
		
		//register with valid username and password of less than 5 characters
		
		//register with valid username and password of illegal characters
		
		//register with unique name and valid password
	}
	
	@Test
	public void testListGames() {
		//test list games
	}
	
	@Test
	public void testCreateGame() {
		//test create game
		
	}
	
	@Test
	public void testJoinGame() {
		//join game that is already full
		
		//invalid cookie
		
		//duplicate color
		
		//change color for game already joined
		
		//join game with room
		
	}
	
	@Test
	public void testSaveGame() {
		
	}
	
	@Test
	public void testLoadGame() {
		
	}
	
	@Test
	public void testGetGameModel() {
		//invalid cookie
		
		//valid
	}
	
	@Test
	public void testResetGame() {
		//invalid cookie
		
		//valid
	}
	
	@Test
	public void testGetGameCommands() {
		//invalid cookie
		
		//valid
	}
	
	@Test
	public void testPostGameCommands() {
		//invalid command format
		
		//invalid cookie
		
		//valid command format
		
	}
	
	@Test
	public void testListAI() {
		//valid
	}
	
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
}
