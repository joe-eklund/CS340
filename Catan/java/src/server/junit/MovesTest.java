package server.junit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import proxy.TranslatorJSON;
import server.serverCommunicator.LoginUserHandler;
import server.serverCommunicator.RegisterUserHandler;
import server.users.UsersFacadeStub;
import shared.ServerMethodRequests.UserRequest;

public class MovesTest {
	@Before 
	public void setUp() { 
		
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
		
	}
	@Test
	public void testMonument() {
		
	}
	@Test
	public void testMonopoly() {
		
	}
}
