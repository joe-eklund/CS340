package proxy.junit;

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
import proxy.ITranslator;
import proxy.TranslatorJSON;
import shared.definitions.GameModel;
import shared.definitions.PlayerIndex;

public class translatorTest {
	
	@Test
	public void testTranslateToJSON() {
		ITranslator trans=new TranslatorJSON();
		
		List<Player> players=new ArrayList();
		players.add(new Player("Blue", "Ender", 0));
		players.add(new Player("Orange", "Ralph", 1));
		players.add(new Player("Red", "Santa", 2));
		players.add(new Player("Brown", "Frodo", 3));
		
		GameModel game=new GameModel(new Bank(),new Chat(),new Log(),new Map(),players,null,new TurnTracker(),0,-1);
		String translation=trans.translateTo(game);
		/*assertEquals("Name should be George", "George", test.getName());
		test.setName("Henry");
		assertEquals("Name should be Henry", "Henry", test.getName());*/
	}
}
