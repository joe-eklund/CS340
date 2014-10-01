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

public class TranslatorTest {
	
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
		//System.out.println(translation);
		assertEquals("JSON should match",translation,"{\"chat\":{\"lines\":[]},\"bank\":{\"resources\":{\"bricks\":19,\"ores\":19,\"sheeps\":19,\"wheats\":19,\"woods\":19}},\"log\":{\"lines\":[]},\"map\":{\"radius\":0},\"players\":[{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Blue\",\"discarded\":false,\"monuments\":0,\"name\":\"Ender\",\"playerIndex\":0,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"bricks\":19,\"ores\":19,\"sheeps\":19,\"wheats\":19,\"woods\":19},\"soldiers\":0,\"victoryPoints\":2},{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Orange\",\"discarded\":false,\"monuments\":0,\"name\":\"Ralph\",\"playerIndex\":1,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"bricks\":19,\"ores\":19,\"sheeps\":19,\"wheats\":19,\"woods\":19},\"soldiers\":0,\"victoryPoints\":2},{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Red\",\"discarded\":false,\"monuments\":0,\"name\":\"Santa\",\"playerIndex\":2,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"bricks\":19,\"ores\":19,\"sheeps\":19,\"wheats\":19,\"woods\":19},\"soldiers\":0,\"victoryPoints\":2},{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Brown\",\"discarded\":false,\"monuments\":0,\"name\":\"Frodo\",\"playerIndex\":3,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"bricks\":19,\"ores\":19,\"sheeps\":19,\"wheats\":19,\"woods\":19},\"soldiers\":0,\"victoryPoints\":2}],\"turnTracker\":{\"currentTurn\":0,\"longestRoad\":0,\"largestArmy\":0},\"version\":0,\"winner\":-1}");
	}
}
