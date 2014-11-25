package server.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import proxy.TranslatorJSON;
import server.cookie.CookieParams;
import server.game.GameFacade;
import server.game.IGameFacade;
import server.moves.IMovesFacade;
import shared.ServerMethodRequests.AddAIRequest;
import shared.definitions.ServerModel;

public class GameTest {
	private TranslatorJSON jsonTrans;
	private CookieParams cookie;
	private IGameFacade game;
	private ArrayList<ServerModel> gamesList;
	
	@Before 
	public void setUp() { 
		jsonTrans = new TranslatorJSON();
		cookie = new CookieParams("Bobby", "bobby", 0, 0);
		
		gamesList=new ArrayList<ServerModel>();
		game=new GameFacade(gamesList);
	}
	@Test
	public void testGetGameModel(){ 
		
	}
	@Test
	public void testAddAI(){ 
		int ais=game.listAI().size();
		AddAIRequest request=new AddAIRequest("AI v4.9");
		game.addAI(request);
		assertEquals("There should be another ai.",ais+1,game.listAI().size());
		
	}
	@Test
	public void testListAI(){ 
		List<String> ais=game.listAI();
		assertEquals("There should be 3 ais",3,ais.size());
	}
}
