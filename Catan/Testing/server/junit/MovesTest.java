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
import shared.ServerMethodRequests.RollNumberRequest;
import shared.ServerMethodRequests.SendChatRequest;
import shared.ServerMethodRequests.UserRequest;
import shared.ServerMethodRequests.YearOfPlentyDevRequest;
import shared.definitions.ServerModel;
import shared.model.Chat;

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
		int player0Resources=aGame.getPlayers().get(0).getResources().totalResourcesCount();
		int player1Resources=aGame.getPlayers().get(1).getResources().totalResourcesCount();
		int player2Resources=aGame.getPlayers().get(2).getResources().totalResourcesCount();
		int player3Resources=aGame.getPlayers().get(3).getResources().totalResourcesCount();
		try {
			moves.rollNumber(request, cookie);
			assertEquals("Player 0 should have more resources.",player0Resources+1,aGame.getPlayers().get(0).getResources().totalResourcesCount());
			assertEquals("Player 1 should have more resources.",player1Resources+0,aGame.getPlayers().get(1).getResources().totalResourcesCount());
			assertEquals("Player 2 should have more resources.",player2Resources+0,aGame.getPlayers().get(2).getResources().totalResourcesCount());
			assertEquals("Player 3 should have more resources.",player3Resources+0,aGame.getPlayers().get(3).getResources().totalResourcesCount());
			request=new RollNumberRequest(10,0);
			player0Resources=aGame.getPlayers().get(0).getResources().totalResourcesCount();
			player1Resources=aGame.getPlayers().get(1).getResources().totalResourcesCount();
			player2Resources=aGame.getPlayers().get(2).getResources().totalResourcesCount();
			player3Resources=aGame.getPlayers().get(3).getResources().totalResourcesCount();
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
