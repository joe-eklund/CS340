package server.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import proxy.TranslatorJSON;
import server.commands.users.UsersCommandLog;
import server.cookie.CookieParams;
import server.game.GameFacadeStub;
import server.game.IGameFacade;
import server.games.GamesFacadeStub;
import server.games.IGamesFacade;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import server.moves.MovesFacade;
import server.moves.MovesFacadeStub;
import server.serverCommunicator.LoginUserHandler;
import server.serverCommunicator.RegisterUserHandler;
import server.serverCommunicator.YearOfPlentyHandler;
import server.users.IUsersFacade;
import server.users.UsersFacadeStub;
import shared.ServerMethodRequests.UserRequest;
import shared.ServerMethodRequests.YearOfPlentyDevRequest;
import shared.definitions.ServerModel;

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
		//player sends chat message-check appends
	}
	
	@Test
	public void testRollNumber() {
		//player rolls number-check each player gets correct amount of resources
	}
	
	@Test
	public void testBuyDevCard() {
		
	}
	
	@Test
	public void testFinishTurn() {
		
	}
	
	@Test
	public void testBuildRoad() {
		
	}
	
	@Test
	public void testBuildCity() {
		
	}
	
	@Test
	public void testBuildSettlement() {
		
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
		
	}
	
	@Test
	public void testRoadBuilding() {
		
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
			assertEquals("Bobby should have an additional wood.",sheep+1,aGame.getPlayers().get(0).getSheep());
		} catch (InvalidMovesRequest e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testMonument() {
		
	}
	
	@Test
	public void testMonopoly() {
		
	}
}
