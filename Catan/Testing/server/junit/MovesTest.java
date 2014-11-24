package server.junit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import proxy.TranslatorJSON;
import server.commands.users.UsersCommandLog;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
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
	
	@Before 
	public void setUp() { 
		moves = new MovesFacadeStub();
		jsonTrans = new TranslatorJSON();
		cookie=new CookieParams("Bobby", "bobby", 0, 1);
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

		YearOfPlentyHandler yopHandle;
		YearOfPlentyDevRequest request=new YearOfPlentyDevRequest(0, "wood", "sheep");
		ServerModel preGame,postGame;
		
		try {
			postGame=moves.yearOfPlenty(request, cookie);
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
