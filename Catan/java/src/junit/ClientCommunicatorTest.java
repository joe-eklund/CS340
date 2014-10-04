package junit;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import client.model.Message;
import proxy.ClientCommunicator;
import proxy.CommandResponse;
import proxy.Pair;
import proxy.RequestType;
import proxy.TranslatorJSON;
import shared.ServerMethodRequests.*;
import shared.definitions.ResourceHand;
import shared.definitions.User;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

/**
 * @author Chad
 *
 */
public class ClientCommunicatorTest {

	ClientCommunicator CCTestor;
	TranslatorJSON jsonTrans;
	
	List<Pair<String,String>> headers;
	User mockUser;
	String mockCommandName;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		jsonTrans = new TranslatorJSON();
		CCTestor = new ClientCommunicator("localhost", 8081, jsonTrans);
		headers = new ArrayList<Pair<String,String>>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test method for {@link proxy.ClientCommunicator#executeCommand(proxy.RequestType, java.util.List, java.lang.String, java.lang.Object, java.lang.Class)}.
	 */
//	@Test
	public void connectUserLoginTest() {
		Pair mockPair = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		headers.add(mockPair);
		mockUser = new User("Brooke", "brooke");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "user/login", mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
//	@Test
	public void testRegisterUser() {
		Pair mockPair = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Bob%22%2C%22password%22%3A%22bob%22%2C%22playerID%22%3A0%7D");
		headers.add(mockPair);
		mockUser = new User("Bob", "bob");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "user/register", mockUser, RequestType.class);
//		assertTrue(mockResponse.getResponseCode() == 200);
		System.out.print(mockResponse);
	}
	
//	@Test
	public void testGetGamesList() {
		Pair mockPair = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		headers.add(mockPair);
		mockUser = new User("Brooke", "brooke");
		mockCommandName = "games/list";
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.GET, headers, "games/list", mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
//		System.out.print(mockResponse.);
	}
	
//	@Test
	public void testCreateGame() {
		mockCommandName = "games/create";
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, mockCommandName, mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
		System.out.print(mockResponse);
	}
	
//	@Test
	public void testJoinGame() {
		mockCommandName = "games/join";
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, mockCommandName, mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
		System.out.print(mockResponse);
	}
	
//	@Test
	public void testGetGameModel() {
		mockCommandName = "game/model?version=x";
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, mockCommandName, mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
		System.out.print(mockResponse);
	}
	
//	@Test
	public void testGameReset() {
		mockCommandName = "games/reset";
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, mockCommandName, mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
		System.out.print(mockResponse);
	}
	
//	@Test
	public void testGetGameCommands() {
		mockCommandName = "games/commands";
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, mockCommandName, mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
		System.out.print(mockResponse);
	}
	
//	@Test
	public void testPostGameCommands() {
		mockCommandName = "games/commands";
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, mockCommandName, mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
		System.out.print(mockResponse);
	}
	
//	@Test
	public void testGetGameListAI() {
		mockCommandName = "games/listAI";
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, mockCommandName, mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
		System.out.print(mockResponse);
	}
	
//	@Test
	public void testAddAI() { 
		mockCommandName = "games/addAI";
//		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, mockCommandName, mockUser, RequestType.class);
//		assertTrue(mockResponse.getResponseCode() == 200);
//		System.out.print(mockResponse);
	}
	
//	@Test
	public void testUtilChangeLogLevel() {
		mockCommandName = "util/changeLogLevel";
//		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, mockCommandName, mockUser, RequestType.class);
//		assertTrue(mockResponse.getResponseCode() == 200);
//		System.out.print(mockResponse);
	}
	
	////Move Command Tests*****************
	
	@Test
	public void testSendChat() {
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		SendChatRequest mockChatRequest = new SendChatRequest(1, "Sup duuuuuuuude!!!!");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/sendChat", mockChatRequest, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testRollNumber(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		RollNumberRequest data = new RollNumberRequest(5, 1);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/rollNumber", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test 
	public void testRobPlayer(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		SoldierDevRequest data = new SoldierDevRequest(1,11, new HexLocation(5,5));
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/robPlayer", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testFinishTurn(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		FinishTurnRequest data = new FinishTurnRequest(1);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/finishTurn", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testBuyDevCard(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		BuyDevCardRequest data = new BuyDevCardRequest(1);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/buyDevCard", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testYearOfPlenty(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		YearOfPlentyDevRequest data = new YearOfPlentyDevRequest(1, "sheep", "wood");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/Year_of_Plenty", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testRoadBuilding(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		RoadBuildingDevRequest data = new RoadBuildingDevRequest(1, null, null);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/Road_Building", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testSoldier(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		SoldierDevRequest data = new SoldierDevRequest(1,11, new HexLocation(5,5));
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/Soldier", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testMonopoly(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		MonopolyDevRequest data = new MonopolyDevRequest(1, "sheep");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/Monopoly", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testMonument() {
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		MonumentDevRequest data = new MonumentDevRequest(1);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/Monument", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testBuildRoad(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		BuildRoadRequest data = new BuildRoadRequest(1 ,EdgeLocation, 9);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/buildRoad", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testBuildSettlement(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		BuildSettlementRequest data = new BuildSettlementRequest(1 ,EdgeLocation, 9);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/buildSettlement", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testBuildCity(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		BuildCityRequest data = new BuildCityRequest(1 ,EdgeLocation, 9);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/buildCity", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testOfferTrade(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		OfferTradeRequest data = new OfferTradeRequest(1, new ResourceHand(0,0,-1,0,1), 11);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/offerTrade", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testAcceptTrade(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		AcceptTradeRequest data = new AcceptTradeRequest(1, false);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/acceptTrade", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testMaritimeTrade(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		MaritimeTradeRequest data = new MaritimeTradeRequest(1,2,"sheep", "wood");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/maritimeTrade", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testDiscardCards(){
		Pair user = new Pair("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair game = new Pair("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		DiscardCardsRequest data = new DiscardCardsRequest(new ResourceHand(0,-1,-1,0,0),1);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/discardCards", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	
	
	
	
	
	final String mockData2 = "{\"chat\":{\"lines\":[]},"
			+ "\"bank\":{\"brick\":10,\"ore\":19,\"sheep\":9,\"wheat\":19,\"wood\":19},"
			+ "\"log\":{\"lines\":[]},\"map\":{\"radius\":3},"
			+ "\"players\":[{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Blue\",\"discarded\":false,\"monuments\":0,\"name\":\"Ender\",\"playerIndex\":0,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
			+ "{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Orange\",\"discarded\":false,\"monuments\":0,\"name\":\"Ralph\",\"playerIndex\":1,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
			+ "{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Red\",\"discarded\":false,\"monuments\":0,\"name\":\"Henry\",\"playerIndex\":2,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
			+ "{\"cities\":4,\"settlements\":5,\"roads\":10,\"color\":\"Brown\",\"discarded\":false,\"monuments\":0,\"name\":\"Frodo\",\"playerIndex\":3,\"playedDevCard\":true,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2}],"
			+ "\"turnTracker\":{\"currentTurn\":0,\"status\":\"Playing\",\"longestRoad\":-1,\"largestArmy\":-1},"
			+ "\"version\":0,\"winner\":-1,"
			+ "\"deck\":{\"monopoly\":2,\"monument\":5,\"roadBuilding\":2,\"soldier\":14,\"yearOfPlenty\":2}}";
				
	final String mockData = "{\r\n" + 
			"  \"deck\": {\r\n" + 
			"    \"yearOfPlenty\": 2,\r\n" + 
			"    \"monopoly\": 2,\r\n" + 
			"    \"soldier\": 14,\r\n" + 
			"    \"roadBuilding\": 2,\r\n" + 
			"    \"monument\": 5\r\n" + 
			"  },\r\n" + 
			"  \"map\": {\r\n" + 
			"    \"hexes\": [\r\n" + 
			"      {\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 0,\r\n" + 
			"          \"y\": -2\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"brick\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 1,\r\n" + 
			"          \"y\": -2\r\n" + 
			"        },\r\n" + 
			"        \"number\": 4\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"wood\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 2,\r\n" + 
			"          \"y\": -2\r\n" + 
			"        },\r\n" + 
			"        \"number\": 11\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"brick\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": -1,\r\n" + 
			"          \"y\": -1\r\n" + 
			"        },\r\n" + 
			"        \"number\": 8\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"wood\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 0,\r\n" + 
			"          \"y\": -1\r\n" + 
			"        },\r\n" + 
			"        \"number\": 3\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"ore\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 1,\r\n" + 
			"          \"y\": -1\r\n" + 
			"        },\r\n" + 
			"        \"number\": 9\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"sheep\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 2,\r\n" + 
			"          \"y\": -1\r\n" + 
			"        },\r\n" + 
			"        \"number\": 12\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"ore\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": -2,\r\n" + 
			"          \"y\": 0\r\n" + 
			"        },\r\n" + 
			"        \"number\": 5\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"sheep\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": -1,\r\n" + 
			"          \"y\": 0\r\n" + 
			"        },\r\n" + 
			"        \"number\": 10\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"wheat\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 0,\r\n" + 
			"          \"y\": 0\r\n" + 
			"        },\r\n" + 
			"        \"number\": 11\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"brick\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 1,\r\n" + 
			"          \"y\": 0\r\n" + 
			"        },\r\n" + 
			"        \"number\": 5\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"wheat\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 2,\r\n" + 
			"          \"y\": 0\r\n" + 
			"        },\r\n" + 
			"        \"number\": 6\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"wheat\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": -2,\r\n" + 
			"          \"y\": 1\r\n" + 
			"        },\r\n" + 
			"        \"number\": 2\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"sheep\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": -1,\r\n" + 
			"          \"y\": 1\r\n" + 
			"        },\r\n" + 
			"        \"number\": 9\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"wood\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 0,\r\n" + 
			"          \"y\": 1\r\n" + 
			"        },\r\n" + 
			"        \"number\": 4\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"sheep\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 1,\r\n" + 
			"          \"y\": 1\r\n" + 
			"        },\r\n" + 
			"        \"number\": 10\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"wood\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": -2,\r\n" + 
			"          \"y\": 2\r\n" + 
			"        },\r\n" + 
			"        \"number\": 6\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"ore\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": -1,\r\n" + 
			"          \"y\": 2\r\n" + 
			"        },\r\n" + 
			"        \"number\": 3\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"resource\": \"wheat\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 0,\r\n" + 
			"          \"y\": 2\r\n" + 
			"        },\r\n" + 
			"        \"number\": 8\r\n" + 
			"      }\r\n" + 
			"    ],\r\n" + 
			"    \"roads\": [\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 2,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"S\",\r\n" + 
			"          \"x\": 1,\r\n" + 
			"          \"y\": -1\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 3,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"SW\",\r\n" + 
			"          \"x\": 2,\r\n" + 
			"          \"y\": -2\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 0,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"S\",\r\n" + 
			"          \"x\": 0,\r\n" + 
			"          \"y\": 1\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 1,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"SW\",\r\n" + 
			"          \"x\": -2,\r\n" + 
			"          \"y\": 1\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 2,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"S\",\r\n" + 
			"          \"x\": 0,\r\n" + 
			"          \"y\": 0\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 0,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"SW\",\r\n" + 
			"          \"x\": 2,\r\n" + 
			"          \"y\": 0\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 1,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"S\",\r\n" + 
			"          \"x\": -1,\r\n" + 
			"          \"y\": -1\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 3,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"SW\",\r\n" + 
			"          \"x\": -1,\r\n" + 
			"          \"y\": 1\r\n" + 
			"        }\r\n" + 
			"      }\r\n" + 
			"    ],\r\n" + 
			"    \"cities\": [],\r\n" + 
			"    \"settlements\": [\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 3,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"SE\",\r\n" + 
			"          \"x\": 1,\r\n" + 
			"          \"y\": -2\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 2,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"SW\",\r\n" + 
			"          \"x\": 0,\r\n" + 
			"          \"y\": 0\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 2,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"SW\",\r\n" + 
			"          \"x\": 1,\r\n" + 
			"          \"y\": -1\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 1,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"SW\",\r\n" + 
			"          \"x\": -1,\r\n" + 
			"          \"y\": -1\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 0,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"SE\",\r\n" + 
			"          \"x\": 0,\r\n" + 
			"          \"y\": 1\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 1,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"SW\",\r\n" + 
			"          \"x\": -2,\r\n" + 
			"          \"y\": 1\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 0,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"SW\",\r\n" + 
			"          \"x\": 2,\r\n" + 
			"          \"y\": 0\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 3,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"SW\",\r\n" + 
			"          \"x\": -1,\r\n" + 
			"          \"y\": 1\r\n" + 
			"        }\r\n" + 
			"      }\r\n" + 
			"    ],\r\n" + 
			"    \"radius\": 3,\r\n" + 
			"    \"ports\": [\r\n" + 
			"      {\r\n" + 
			"        \"ratio\": 2,\r\n" + 
			"        \"resource\": \"wheat\",\r\n" + 
			"        \"direction\": \"S\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": -1,\r\n" + 
			"          \"y\": -2\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"ratio\": 3,\r\n" + 
			"        \"direction\": \"NW\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 2,\r\n" + 
			"          \"y\": 1\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"ratio\": 2,\r\n" + 
			"        \"resource\": \"ore\",\r\n" + 
			"        \"direction\": \"S\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 1,\r\n" + 
			"          \"y\": -3\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"ratio\": 2,\r\n" + 
			"        \"resource\": \"sheep\",\r\n" + 
			"        \"direction\": \"NW\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 3,\r\n" + 
			"          \"y\": -1\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"ratio\": 2,\r\n" + 
			"        \"resource\": \"wood\",\r\n" + 
			"        \"direction\": \"NE\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": -3,\r\n" + 
			"          \"y\": 2\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"ratio\": 3,\r\n" + 
			"        \"direction\": \"N\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 0,\r\n" + 
			"          \"y\": 3\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"ratio\": 2,\r\n" + 
			"        \"resource\": \"brick\",\r\n" + 
			"        \"direction\": \"NE\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": -2,\r\n" + 
			"          \"y\": 3\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"ratio\": 3,\r\n" + 
			"        \"direction\": \"SW\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 3,\r\n" + 
			"          \"y\": -3\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"ratio\": 3,\r\n" + 
			"        \"direction\": \"SE\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": -3,\r\n" + 
			"          \"y\": 0\r\n" + 
			"        }\r\n" + 
			"      }\r\n" + 
			"    ],\r\n" + 
			"    \"robber\": {\r\n" + 
			"      \"x\": 0,\r\n" + 
			"      \"y\": -2\r\n" + 
			"    }\r\n" + 
			"  },\r\n" + 
			"  \"players\": [\r\n" + 
			"    {\r\n" + 
			"      \"resources\": {\r\n" + 
			"        \"brick\": 0,\r\n" + 
			"        \"wood\": 1,\r\n" + 
			"        \"sheep\": 1,\r\n" + 
			"        \"wheat\": 1,\r\n" + 
			"        \"ore\": 0\r\n" + 
			"      },\r\n" + 
			"      \"oldDevCards\": {\r\n" + 
			"        \"yearOfPlenty\": 0,\r\n" + 
			"        \"monopoly\": 0,\r\n" + 
			"        \"soldier\": 0,\r\n" + 
			"        \"roadBuilding\": 0,\r\n" + 
			"        \"monument\": 0\r\n" + 
			"      },\r\n" + 
			"      \"newDevCards\": {\r\n" + 
			"        \"yearOfPlenty\": 0,\r\n" + 
			"        \"monopoly\": 0,\r\n" + 
			"        \"soldier\": 0,\r\n" + 
			"        \"roadBuilding\": 0,\r\n" + 
			"        \"monument\": 0\r\n" + 
			"      },\r\n" + 
			"      \"roads\": 13,\r\n" + 
			"      \"cities\": 4,\r\n" + 
			"      \"settlements\": 3,\r\n" + 
			"      \"soldiers\": 0,\r\n" + 
			"      \"victoryPoints\": 2,\r\n" + 
			"      \"monuments\": 0,\r\n" + 
			"      \"playedDevCard\": false,\r\n" + 
			"      \"discarded\": false,\r\n" + 
			"      \"playerID\": 0,\r\n" + 
			"      \"playerIndex\": 0,\r\n" + 
			"      \"name\": \"Sam\",\r\n" + 
			"      \"color\": \"orange\"\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"resources\": {\r\n" + 
			"        \"brick\": 1,\r\n" + 
			"        \"wood\": 0,\r\n" + 
			"        \"sheep\": 1,\r\n" + 
			"        \"wheat\": 0,\r\n" + 
			"        \"ore\": 1\r\n" + 
			"      },\r\n" + 
			"      \"oldDevCards\": {\r\n" + 
			"        \"yearOfPlenty\": 0,\r\n" + 
			"        \"monopoly\": 0,\r\n" + 
			"        \"soldier\": 0,\r\n" + 
			"        \"roadBuilding\": 0,\r\n" + 
			"        \"monument\": 0\r\n" + 
			"      },\r\n" + 
			"      \"newDevCards\": {\r\n" + 
			"        \"yearOfPlenty\": 0,\r\n" + 
			"        \"monopoly\": 0,\r\n" + 
			"        \"soldier\": 0,\r\n" + 
			"        \"roadBuilding\": 0,\r\n" + 
			"        \"monument\": 0\r\n" + 
			"      },\r\n" + 
			"      \"roads\": 13,\r\n" + 
			"      \"cities\": 4,\r\n" + 
			"      \"settlements\": 3,\r\n" + 
			"      \"soldiers\": 0,\r\n" + 
			"      \"victoryPoints\": 2,\r\n" + 
			"      \"monuments\": 0,\r\n" + 
			"      \"playedDevCard\": false,\r\n" + 
			"      \"discarded\": false,\r\n" + 
			"      \"playerID\": 1,\r\n" + 
			"      \"playerIndex\": 1,\r\n" + 
			"      \"name\": \"Brooke\",\r\n" + 
			"      \"color\": \"red\"\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"resources\": {\r\n" + 
			"        \"brick\": 0,\r\n" + 
			"        \"wood\": 1,\r\n" + 
			"        \"sheep\": 1,\r\n" + 
			"        \"wheat\": 1,\r\n" + 
			"        \"ore\": 0\r\n" + 
			"      },\r\n" + 
			"      \"oldDevCards\": {\r\n" + 
			"        \"yearOfPlenty\": 0,\r\n" + 
			"        \"monopoly\": 0,\r\n" + 
			"        \"soldier\": 0,\r\n" + 
			"        \"roadBuilding\": 0,\r\n" + 
			"        \"monument\": 0\r\n" + 
			"      },\r\n" + 
			"      \"newDevCards\": {\r\n" + 
			"        \"yearOfPlenty\": 0,\r\n" + 
			"        \"monopoly\": 0,\r\n" + 
			"        \"soldier\": 0,\r\n" + 
			"        \"roadBuilding\": 0,\r\n" + 
			"        \"monument\": 0\r\n" + 
			"      },\r\n" + 
			"      \"roads\": 13,\r\n" + 
			"      \"cities\": 4,\r\n" + 
			"      \"settlements\": 3,\r\n" + 
			"      \"soldiers\": 0,\r\n" + 
			"      \"victoryPoints\": 2,\r\n" + 
			"      \"monuments\": 0,\r\n" + 
			"      \"playedDevCard\": false,\r\n" + 
			"      \"discarded\": false,\r\n" + 
			"      \"playerID\": 10,\r\n" + 
			"      \"playerIndex\": 2,\r\n" + 
			"      \"name\": \"Pete\",\r\n" + 
			"      \"color\": \"red\"\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"resources\": {\r\n" + 
			"        \"brick\": 0,\r\n" + 
			"        \"wood\": 1,\r\n" + 
			"        \"sheep\": 1,\r\n" + 
			"        \"wheat\": 0,\r\n" + 
			"        \"ore\": 1\r\n" + 
			"      },\r\n" + 
			"      \"oldDevCards\": {\r\n" + 
			"        \"yearOfPlenty\": 0,\r\n" + 
			"        \"monopoly\": 0,\r\n" + 
			"        \"soldier\": 0,\r\n" + 
			"        \"roadBuilding\": 0,\r\n" + 
			"        \"monument\": 0\r\n" + 
			"      },\r\n" + 
			"      \"newDevCards\": {\r\n" + 
			"        \"yearOfPlenty\": 0,\r\n" + 
			"        \"monopoly\": 0,\r\n" + 
			"        \"soldier\": 0,\r\n" + 
			"        \"roadBuilding\": 0,\r\n" + 
			"        \"monument\": 0\r\n" + 
			"      },\r\n" + 
			"      \"roads\": 13,\r\n" + 
			"      \"cities\": 4,\r\n" + 
			"      \"settlements\": 3,\r\n" + 
			"      \"soldiers\": 0,\r\n" + 
			"      \"victoryPoints\": 2,\r\n" + 
			"      \"monuments\": 0,\r\n" + 
			"      \"playedDevCard\": false,\r\n" + 
			"      \"discarded\": false,\r\n" + 
			"      \"playerID\": 11,\r\n" + 
			"      \"playerIndex\": 3,\r\n" + 
			"      \"name\": \"Mark\",\r\n" + 
			"      \"color\": \"green\"\r\n" + 
			"    }\r\n" + 
			"  ],\r\n" + 
			"  \"log\": {\r\n" + 
			"    \"lines\": [\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Sam\",\r\n" + 
			"        \"message\": \"Sam built a road\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Sam\",\r\n" + 
			"        \"message\": \"Sam built a settlement\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Sam\",\r\n" + 
			"        \"message\": \"Sam's turn just ended\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Brooke\",\r\n" + 
			"        \"message\": \"Brooke built a road\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Brooke\",\r\n" + 
			"        \"message\": \"Brooke built a settlement\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Brooke\",\r\n" + 
			"        \"message\": \"Brooke's turn just ended\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Pete\",\r\n" + 
			"        \"message\": \"Pete built a road\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Pete\",\r\n" + 
			"        \"message\": \"Pete built a settlement\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Pete\",\r\n" + 
			"        \"message\": \"Pete's turn just ended\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Mark\",\r\n" + 
			"        \"message\": \"Mark built a road\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Mark\",\r\n" + 
			"        \"message\": \"Mark built a settlement\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Mark\",\r\n" + 
			"        \"message\": \"Mark's turn just ended\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Mark\",\r\n" + 
			"        \"message\": \"Mark built a road\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Mark\",\r\n" + 
			"        \"message\": \"Mark built a settlement\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Mark\",\r\n" + 
			"        \"message\": \"Mark's turn just ended\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Pete\",\r\n" + 
			"        \"message\": \"Pete built a road\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Pete\",\r\n" + 
			"        \"message\": \"Pete built a settlement\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Pete\",\r\n" + 
			"        \"message\": \"Pete's turn just ended\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Brooke\",\r\n" + 
			"        \"message\": \"Brooke built a road\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Brooke\",\r\n" + 
			"        \"message\": \"Brooke built a settlement\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Brooke\",\r\n" + 
			"        \"message\": \"Brooke's turn just ended\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Sam\",\r\n" + 
			"        \"message\": \"Sam built a road\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Sam\",\r\n" + 
			"        \"message\": \"Sam built a settlement\"\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"source\": \"Sam\",\r\n" + 
			"        \"message\": \"Sam's turn just ended\"\r\n" + 
			"      }\r\n" + 
			"    ]\r\n" + 
			"  },\r\n" + 
			"  \"chat\": {\r\n" + 
			"    \"lines\": []\r\n" + 
			"  },\r\n" + 
			"  \"bank\": {\r\n" + 
			"    \"brick\": 23,\r\n" + 
			"    \"wood\": 21,\r\n" + 
			"    \"sheep\": 20,\r\n" + 
			"    \"wheat\": 22,\r\n" + 
			"    \"ore\": 22\r\n" + 
			"  },\r\n" + 
			"  \"turnTracker\": {\r\n" + 
			"    \"status\": \"Rolling\",\r\n" + 
			"    \"currentTurn\": 0,\r\n" + 
			"    \"longestRoad\": -1,\r\n" + 
			"    \"largestArmy\": -1\r\n" + 
			"  },\r\n" + 
			"  \"winner\": -1,\r\n" + 
			"  \"version\": 0\r\n" + 
			"}";
}
