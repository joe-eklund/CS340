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
		assertEquals("JSON should match",translation,"{\"chat\":{\"lines\":[]},\"bank\":{\"brick\":19,\"ore\":19,\"sheep\":19,\"wheat\":19,\"wood\":19},\"log\":{\"lines\":[]},\"map\":{\"radius\":3},\"players\":[{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Blue\",\"discarded\":false,\"monuments\":0,\"name\":\"Ender\",\"playerIndex\":0,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Orange\",\"discarded\":false,\"monuments\":0,\"name\":\"Ralph\",\"playerIndex\":1,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Red\",\"discarded\":false,\"monuments\":0,\"name\":\"Santa\",\"playerIndex\":2,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Brown\",\"discarded\":false,\"monuments\":0,\"name\":\"Frodo\",\"playerIndex\":3,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2}],\"turnTracker\":{\"currentTurn\":0,\"status\":\"Playing\",\"longestRoad\":-1,\"largestArmy\":-1},\"version\":0,\"winner\":-1,\"deck\":{\"monopoly\":2,\"monument\":5,\"roadBuilding\":2,\"soldier\":14,\"yearOfPlenty\":2}}");
	}
	@Test
	public void testTranslateFrom() {
		ITranslator trans=new TranslatorJSON();
		
		String message = "{\"chat\":{\"lines\":[]},"
				+ "\"bank\":{\"brick\":10,\"ore\":19,\"sheep\":9,\"wheat\":19,\"wood\":19},"
				+ "\"log\":{\"lines\":[]},\"map\":{\"radius\":3},"
				+ "\"players\":[{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Blue\",\"discarded\":false,\"monuments\":0,\"name\":\"Ender\",\"playerIndex\":0,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
				+ "{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Orange\",\"discarded\":false,\"monuments\":0,\"name\":\"Ralph\",\"playerIndex\":1,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
				+ "{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Red\",\"discarded\":false,\"monuments\":0,\"name\":\"Henry\",\"playerIndex\":2,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
				+ "{\"cities\":4,\"settlements\":5,\"roads\":10,\"color\":\"Brown\",\"discarded\":false,\"monuments\":0,\"name\":\"Frodo\",\"playerIndex\":3,\"playedDevCard\":true,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2}],"
				+ "\"turnTracker\":{\"currentTurn\":0,\"status\":\"Playing\",\"longestRoad\":-1,\"largestArmy\":-1},"
				+ "\"version\":0,\"winner\":-1,"
				+ "\"deck\":{\"monopoly\":2,\"monument\":5,\"roadBuilding\":2,\"soldier\":14,\"yearOfPlenty\":2}}";
		GameModel game = (GameModel) trans.translateFrom(message, GameModel.class);
		
		assertEquals("Bank bricks should match(10)",game.getBank().getBrick(),10);
		assertEquals("Bank sheeps should match(9)",game.getBank().getSheep(),9);
		assertEquals("Player at (0) should be named Ender",game.getPlayers().get(0).getName(),"Ender");
		assertEquals("Player at (2) should be named Henry",game.getPlayers().get(2).getName(),"Henry");
		assertEquals("Player at (3) should have 10 roads",game.getPlayers().get(3).getRoads(),10);
		assertEquals("Player at (3) should have played dev card",game.getPlayers().get(3).hasPlayedDevCard(),true);
		assertEquals("Map should have a radius of 3",game.getMap().getRadius(),3);
	}
}
