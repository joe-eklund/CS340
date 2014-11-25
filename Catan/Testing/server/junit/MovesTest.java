package server.junit;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import client.exceptions.ClientModelException;
import proxy.TranslatorJSON;
import server.cookie.CookieParams;
import server.games.GamesFacadeStub;
import server.games.IGamesFacade;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import server.moves.MovesFacade;
import shared.ServerMethodRequests.BuildSettlementRequest;
import shared.ServerMethodRequests.BuyDevCardRequest;
import shared.ServerMethodRequests.FinishTurnRequest;
import shared.ServerMethodRequests.MonopolyDevRequest;
import shared.ServerMethodRequests.MonumentDevRequest;
import shared.ServerMethodRequests.RoadBuildingDevRequest;
import shared.ServerMethodRequests.RobPlayerRequest;
import shared.ServerMethodRequests.RollNumberRequest;
import shared.ServerMethodRequests.SendChatRequest;
import shared.ServerMethodRequests.SoldierDevRequest;
import shared.ServerMethodRequests.YearOfPlentyDevRequest;
import shared.definitions.RoadLocation;
import shared.definitions.ServerModel;
import shared.definitions.VertexLocationRequest;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.model.Player;
import shared.model.Road;

public class MovesTest {
	private IMovesFacade moves;
	private TranslatorJSON jsonTrans;
	private CookieParams cookie;
	
	private IGamesFacade games;
	private ArrayList<ServerModel> gamesList;
	
	@Before 
	public void setUp() { 
		//moves = new MovesFacadeStub();
		jsonTrans = new TranslatorJSON();
		cookie = new CookieParams("Bobby", "bobby", 0, 1);
		
		gamesList=new ArrayList<ServerModel>();
		games=new GamesFacadeStub(gamesList);
		moves=new MovesFacade(gamesList);
	}
	
	@Test
	public void testSendChat() {
		SendChatRequest request=new SendChatRequest(0, "Hello World");
		ServerModel aGame=gamesList.get(1);
		
		try {
			moves.sendChat(request, cookie);
			assertEquals("Last chat message should equal 'Hello World'","Hello World",aGame.getChat().getMessages().get(aGame.getChat().getMessages().size()-1).getMessage());
			assertEquals("Last chat message should have come from Bobby","Bobby",aGame.getChat().getMessages().get(aGame.getChat().getMessages().size()-1).getSource());
			request=new SendChatRequest(1, "Hello back");
			cookie = new CookieParams("Billy", "billy", 1, 1);
			moves.sendChat(request, cookie);
			assertEquals("Last chat message should equal 'Hello back'","Hello back",aGame.getChat().getMessages().get(aGame.getChat().getMessages().size()-1).getMessage());
			assertEquals("Last chat message should have come from Billy","Billy",aGame.getChat().getMessages().get(aGame.getChat().getMessages().size()-1).getSource());
		} catch (InvalidMovesRequest e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testRollNumber() {
		RollNumberRequest request=new RollNumberRequest(1, 0);
		ServerModel aGame=gamesList.get(1);
		
		try {
			moves.rollNumber(request, cookie);
		} catch (InvalidMovesRequest e) {
			assertEquals("Should have thrown 'Error: invalid roll number request'","Error: invalid roll number request",e.getMessage());
		}
		aGame=gamesList.get(2);
		cookie=new CookieParams("Bobby", "bobby", 0, 2);
		request=new RollNumberRequest(6,0);
		int player0Resources=aGame.getPlayers().get(0).getResourceCount();
		int player1Resources=aGame.getPlayers().get(1).getResourceCount();
		int player2Resources=aGame.getPlayers().get(2).getResourceCount();
		int player3Resources=aGame.getPlayers().get(3).getResourceCount();
		try {
			moves.rollNumber(request, cookie);
			assertEquals("Player 0 should have more resources.",player0Resources+1,aGame.getPlayers().get(0).getResources().totalResourcesCount());
			assertEquals("Player 1 should have more resources.",player1Resources+0,aGame.getPlayers().get(1).getResources().totalResourcesCount());
			assertEquals("Player 2 should have more resources.",player2Resources+0,aGame.getPlayers().get(2).getResources().totalResourcesCount());
			assertEquals("Player 3 should have more resources.",player3Resources+0,aGame.getPlayers().get(3).getResources().totalResourcesCount());
			request=new RollNumberRequest(10,0);
			player0Resources=aGame.getPlayers().get(0).getResourceCount();
			player1Resources=aGame.getPlayers().get(1).getResourceCount();
			player2Resources=aGame.getPlayers().get(2).getResourceCount();
			player3Resources=aGame.getPlayers().get(3).getResourceCount();
			moves.rollNumber(request, cookie);
			assertEquals("Player 0 should have more resources.",player0Resources+0,aGame.getPlayers().get(0).getResources().totalResourcesCount());
			assertEquals("Player 1 should have more resources.",player1Resources+1,aGame.getPlayers().get(1).getResources().totalResourcesCount());
			assertEquals("Player 2 should have more resources.",player2Resources+0,aGame.getPlayers().get(2).getResources().totalResourcesCount());
			assertEquals("Player 3 should have more resources.",player3Resources+1,aGame.getPlayers().get(3).getResources().totalResourcesCount());
		} catch (InvalidMovesRequest e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testRobPlayer() {
		RobPlayerRequest request=new RobPlayerRequest(0,1,new HexLocation(0,2));
		cookie=new CookieParams("Bobby", "bobby", 0, 2);
		ServerModel aGame=gamesList.get(2);
		int player=aGame.getPlayers().get(0).getResourceCount();
		int target=aGame.getPlayers().get(1).getResourceCount();
		int other1=aGame.getPlayers().get(2).getResourceCount();
		int other2=aGame.getPlayers().get(3).getResourceCount();
		try {
			moves.robPlayer(request, cookie);
			assertEquals("Bobby should have an addition resource.",player+1,aGame.getPlayers().get(0).getResourceCount());
			assertEquals("Billy should have one less resource.",target-1,aGame.getPlayers().get(1).getResourceCount());
			assertEquals("Sandy should have the same amount of resources.",other1,aGame.getPlayers().get(2).getResourceCount());
			assertEquals("Cathy should have the same amount of resources.",other2,aGame.getPlayers().get(3).getResourceCount());
		} catch (InvalidMovesRequest e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testBuyDevCard() {
		//me
		BuyDevCardRequest request=new BuyDevCardRequest(0);
		ServerModel aGame=gamesList.get(2);
		cookie=new CookieParams("Bobby", "bobby", 0, 2);
		int playerNewCards=aGame.getPlayers().get(0).getNewDevCards().getTotalDevCardCount();
		int playerOldCards=aGame.getPlayers().get(0).getOldDevCards().getTotalDevCardCount();
		int devCards=aGame.getDeck().getTotalDevCardCount();
		try {
			moves.buyDevCard(request, cookie);
			if(aGame.getPlayers().get(0).getNewDevCards().getTotalDevCardCount()!=playerNewCards){
				assertEquals("Bobby should have a new development card.",playerNewCards+1,aGame.getPlayers().get(0).getNewDevCards().getTotalDevCardCount());
			}
			else{
				assertEquals("Bobby should have a new development card.",playerOldCards+1,aGame.getPlayers().get(0).getOldDevCards().getTotalDevCardCount());
			}
			assertEquals("The deck should have one less card.",devCards-1,aGame.getDeck().getTotalDevCardCount());	
		} catch (InvalidMovesRequest e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testFinishTurn() {
		FinishTurnRequest request=new FinishTurnRequest(0);
		ServerModel aGame=gamesList.get(2);
		aGame.getTurnTracker().setStatus("Playing");
		cookie=new CookieParams("Bobby", "bobby", 0, 2);
		try {
			moves.finishTurn(request, cookie);
			assertEquals("The state should be Rolling.","Rolling",aGame.getTurnTracker().getStatus());
			assertEquals("It should be Billy's turn, ie 1.",1,aGame.getTurnTracker().getCurrentTurn());
		} catch (InvalidMovesRequest e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testBuildRoad() {
		EdgeDirection direction = EdgeDirection.North;
		EdgeDirection direction2 = EdgeDirection.NorthEast;
		RoadLocation location1 = new RoadLocation(0, 0, direction);
		RoadLocation location2 = new RoadLocation(0, 0, direction2);
		RoadBuildingDevRequest request = new RoadBuildingDevRequest(0, location1, location2);
		ServerModel aGame;
		
		aGame = gamesList.get(1);
		int wood = aGame.getPlayers().get(0).getWood();
		
		try {
			aGame=moves.roadBuilding(request, cookie);
			//Need to get the right roads not right right now
			Road road = aGame.getMap().getRoads().get(0);
			assertEquals("Bobby should have road at X1: 0",location1.getX(),road.location.getX());
			assertEquals("Bobby should have road at Y1: 0",location1.getY(),road.location.getY());

		} catch (InvalidMovesRequest e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testBuildCity() {
		
	}
	
	@Test
	public void testBuildSettlement() {
		VertexLocationRequest location1 = new VertexLocationRequest(0, 0, VertexDirection.NorthEast);
		BuildSettlementRequest request = new BuildSettlementRequest(0, location1, true);
		ServerModel aGame;
		
		aGame = gamesList.get(1);
		
		try {
			aGame = moves.buildSettlement(request, cookie);

			Road road = aGame.getMap().getRoads().get(0);
			Road road2 = aGame.getMap().getRoads().get(1);
			assertEquals("Bobby should have road at X1: 0",location1.getX(),road.location.getX());
			assertEquals("Bobby should have road at Y1: 0",location1.getY(),road.location.getY());

		} catch (InvalidMovesRequest e) {
			System.out.println(e.getMessage());
		} catch (ClientModelException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testTrade() {
		//offer trade
		//accept trade
		//maritime trade
	}
	
	@Test
	public void testDiscard() {
		
	}
	
	//Development Cards
	@Test
	public void testSoldier() {
		HexLocation location = new HexLocation(0, 0);
		SoldierDevRequest request = new SoldierDevRequest(0, 1, location);
		ServerModel aGame;
		
		aGame = gamesList.get(1);
		
		try {
			aGame = moves.soldier(request, cookie);
			assertEquals("Bobby should have road at X1: 0",location.getX(),aGame.getMap().getRobber().getX());
		} catch (InvalidMovesRequest e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testRoadBuilding() {
		EdgeDirection direction = EdgeDirection.North;
		EdgeDirection direction2 = EdgeDirection.NorthEast;
		RoadLocation location1 = new RoadLocation(0, 0, direction);
		RoadLocation location2 = new RoadLocation(0, 0, direction2);
		RoadBuildingDevRequest request = new RoadBuildingDevRequest(0, location1, location2);
		ServerModel aGame;
		
		aGame = gamesList.get(1);
		int wood = aGame.getPlayers().get(0).getWood();
		
		try {
			aGame=moves.roadBuilding(request, cookie);
			//Need to get the right roads not right right now
			Road road = aGame.getMap().getRoads().get(0);
			Road road2 = aGame.getMap().getRoads().get(1);
			assertEquals("Bobby should have road at X1: 0",location1.getX(),road.location.getX());
			assertEquals("Bobby should have road at Y1: 0",location1.getY(),road.location.getY());
			assertEquals("Bobby should have road at Y2: 0",location2.getX(),road2.location.getX());
			assertEquals("Bobby should have road at Y2: 0",location2.getY(),road2.location.getY());

		} catch (InvalidMovesRequest e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testYearOfPlenty() {
		YearOfPlentyDevRequest request=new YearOfPlentyDevRequest(0, "wood", "sheep");
		ServerModel aGame;
		
		aGame=gamesList.get(1);
		int wood=aGame.getPlayers().get(0).getWood();
		int sheep=aGame.getPlayers().get(0).getSheep();
		try {
			aGame=moves.yearOfPlenty(request, cookie);
			assertEquals("Bobby should have an additional wood.",wood+1,aGame.getPlayers().get(0).getWood());
			assertEquals("Bobby should have an additional sheep.",sheep+1,aGame.getPlayers().get(0).getSheep());
		} catch (InvalidMovesRequest e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testMonument() {
		MonumentDevRequest request = new MonumentDevRequest(0);
		ServerModel aGame;
		
		aGame = gamesList.get(1);
		int points = aGame.getPlayers().get(0).getMonuments();
		try {
			aGame = moves.monument(request, cookie);
			assertEquals("Bobby should have an additional point.",points+1,aGame.getPlayers().get(0).getMonuments());
		} catch (InvalidMovesRequest e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testMonopoly() {
		MonopolyDevRequest request = new MonopolyDevRequest(0, "wood");
		ServerModel aGame;
		
		aGame = gamesList.get(1);
		int totalWood = 0;
		for(Player player : aGame.getPlayers()){
			totalWood += aGame.getPlayers().get(player.getPlayerIndex()).getWood();
		}
		
		try {
			aGame = moves.monopoly(request, cookie);
			assertEquals("Bobby should have all the wood.",totalWood,aGame.getPlayers().get(0).getWood());
		} catch (InvalidMovesRequest e) {
			System.out.println(e.getMessage());
		}
	}
}
