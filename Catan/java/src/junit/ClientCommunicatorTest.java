package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import proxy.ClientCommunicator;
import proxy.CommandResponse;
import proxy.Pair;
import proxy.RequestType;
import proxy.TranslatorJSON;
import shared.ServerMethodRequests.*;
import shared.definitions.ResourceHand;
import shared.definitions.User;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

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
		Pair<String, String> mockPair = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		headers.add(mockPair);
		mockUser = new User("Brooke", "brooke");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "user/login", mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
//	@Test
	public void testRegisterUser() {
		Pair<String, String> mockPair = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Bob%22%2C%22password%22%3A%22bob%22%2C%22playerID%22%3A0%7D");
		headers.add(mockPair);
		Random r = new Random();
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<5; i++)
		{
			builder.append((char)r.nextInt(255));
		}
		System.out.print(builder.toString());
		mockUser = new User(builder.toString(), "TEST");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "user/register", mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testGetGamesList() {
		Pair<String, String> mockPair = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		headers.add(mockPair);
		mockUser = new User("Brooke", "brooke");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.GET, headers, "games/list", mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testCreateGame() {
		Pair<String, String> mockPair = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		headers.add(mockPair);
		CreateGameRequest data = new CreateGameRequest(true,true,true, "GAME OF DOOM!!");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "games/create", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testJoinGame() {
		Pair<String, String> mockPair = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		headers.add(mockPair);
		JoinGameRequest data = new JoinGameRequest(4, "green");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "games/join", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testGetGameModel() {
		Pair<String, String> mockPair = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		headers.add(mockPair);
		mockUser = new User("Brooke", "brooke");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.GET, headers, "games/model", mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testGameReset() {
		Pair<String, String> mockPair = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		headers.add(mockPair);
		ResetGameRequest data = new ResetGameRequest();
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "games/reset", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testGetGameCommands() {
		Pair<String, String> mockPair = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		headers.add(mockPair);
		mockUser = new User("Brooke", "brooke");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.GET, headers, "games/commands", mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testPostGameCommands() {
		//Can be used for various game command calls
	}
	
	@Test
	public void testGetGameListAI() {
		Pair<String, String> mockPair = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		headers.add(mockPair);
		mockUser = new User("Brooke", "brooke");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.GET, headers, "games/listAI", mockUser, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testAddAI() { 
		Pair<String, String> mockPair = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		headers.add(mockPair);
		AddAIRequest data = new AddAIRequest("Squall");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "games/commands", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testUtilChangeLogLevel() {
		Pair<String, String> mockPair = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		headers.add(mockPair);
		ChangeLogLevelRequest data = new ChangeLogLevelRequest("SEVERE");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "games/listAI", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	////Move Command Tests*****************
	
	@Test
	public void testSendChat() {
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		SendChatRequest mockChatRequest = new SendChatRequest(1, "Sup duuuuuuuude!!!!");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/sendChat", mockChatRequest, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testRollNumber(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		RollNumberRequest data = new RollNumberRequest(5, 1);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/rollNumber", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test 
	public void testRobPlayer(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		SoldierDevRequest data = new SoldierDevRequest(1,11, new HexLocation(5,5));
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/robPlayer", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testFinishTurn(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		FinishTurnRequest data = new FinishTurnRequest(1);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/finishTurn", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testBuyDevCard(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		BuyDevCardRequest data = new BuyDevCardRequest(1);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/buyDevCard", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testYearOfPlenty(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		YearOfPlentyDevRequest data = new YearOfPlentyDevRequest(1, "sheep", "wood");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/Year_of_Plenty", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testRoadBuilding(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		RoadBuildingDevRequest data = new RoadBuildingDevRequest(1, null, null);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/Road_Building", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testSoldier(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		SoldierDevRequest data = new SoldierDevRequest(1,11, new HexLocation(5,5));
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/Soldier", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testMonopoly(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		MonopolyDevRequest data = new MonopolyDevRequest(1, "sheep");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/Monopoly", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testMonument() {
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		MonumentDevRequest data = new MonumentDevRequest(1);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/Monument", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testBuildRoad(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		BuildRoadRequest data = new BuildRoadRequest(1, new EdgeLocation(new HexLocation(0, 0), EdgeDirection.SouthEast), true);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/buildRoad", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testBuildSettlement(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		VertexLocation vertex = new VertexLocation(new HexLocation(1,1),VertexDirection.NorthWest);
		BuildSettlementRequest data = new BuildSettlementRequest(1 ,vertex, true);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/buildSettlement", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testBuildCity(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		VertexLocation vertex = new VertexLocation(new HexLocation(1,1),VertexDirection.NorthWest);

		BuildCityRequest data = new BuildCityRequest(1, vertex);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/buildCity", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testOfferTrade(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		OfferTradeRequest data = new OfferTradeRequest(11, new ResourceHand(0,0,0,0,0), 1);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/offerTrade", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testAcceptTrade(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		AcceptTradeRequest data = new AcceptTradeRequest(1, true);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/acceptTrade", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testMaritimeTrade(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		MaritimeTradeRequest data = new MaritimeTradeRequest(1,2,"sheep", "wood");
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/maritimeTrade", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
	
	@Test
	public void testDiscardCards(){
		Pair<String, String> user = new Pair<String, String>("Cookie", "catan.user=%7B%22name%22%3A%22Brooke%22%2C%22password%22%3A%22brooke%22%2C%22playerID%22%3A0%7D");
		Pair<String, String> game = new Pair<String, String>("Cookie", "catan.game=2");
		headers.add(user);
		headers.add(game);
		DiscardCardsRequest data = new DiscardCardsRequest(new ResourceHand(0,-1,-1,0,0),1);
		CommandResponse mockResponse = CCTestor.executeCommand(RequestType.POST, headers, "moves/discardCards", data, RequestType.class);
		assertTrue(mockResponse.getResponseCode() == 200);
	}
}