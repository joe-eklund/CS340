package client.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import proxy.ITranslator;
import proxy.TranslatorJSON;
import shared.definitions.GameDescription;
import shared.definitions.ServerModel;
import shared.definitions.PlayerIndex;
import shared.model.Bank;
import shared.model.Chat;
import shared.model.Log;
import shared.model.Map;
import shared.model.Player;
import shared.model.TradeOffer;
import shared.model.TurnTracker;
/**
 * 
 * @author Epper Marshall
 * Tests the translation of a ServerModel to JSON and from JSON to a ServerModel.
 */
public class TranslatorTest {
	private ITranslator trans; 
	
	@Before 
	public void setUp() { 
		trans = new TranslatorJSON(); 
	}
	
	@Test
	public void testTranslateToJSON() {		
		List<Player> players=new ArrayList();
		players.add(new Player("Blue", "Ender", 0));
		players.add(new Player("Orange", "Ralph", 1));
		players.add(new Player("Red", "Santa", 2));
		players.add(new Player("Brown", "Frodo", 3));
		
		ServerModel Server=new ServerModel(new Bank(),new Chat(),new Log(),new Map(),players,null,new TurnTracker(),0,-1);
		String translation=trans.translateTo(Server);
		//System.out.println(translation);
		assertEquals("JSON should match",translation,"{\"chat\":{\"lines\":[]},\"bank\":{\"brick\":19,\"ore\":19,\"sheep\":19,\"wheat\":19,\"wood\":19},\"log\":{\"lines\":[]},\"map\":{\"radius\":3},\"players\":[{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Blue\",\"discarded\":false,\"monuments\":0,\"name\":\"Ender\",\"playerIndex\":0,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":0},{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Orange\",\"discarded\":false,\"monuments\":0,\"name\":\"Ralph\",\"playerIndex\":1,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":0},{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Red\",\"discarded\":false,\"monuments\":0,\"name\":\"Santa\",\"playerIndex\":2,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":0},{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Brown\",\"discarded\":false,\"monuments\":0,\"name\":\"Frodo\",\"playerIndex\":3,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":0}],\"turnTracker\":{\"currentTurn\":0,\"status\":\"Playing\",\"longestRoad\":-1,\"largestArmy\":-1},\"winner\":-1,\"deck\":{\"monopoly\":2,\"monument\":5,\"roadBuilding\":2,\"soldier\":14,\"yearOfPlenty\":2}}");
	}
	@Test
	public void testTranslateFromArray()
	{
		String mssg = "[{\"title\":\"Default Game\",\"id\":0,\"players\":[{\"color\":\"orange\",\"name\":\"Sam\",\"id\":0},{\"color\":\"blue\",\"name\":\"Brooke\",\"id\":1},{\"color\":\"red\",\"name\":\"Pete\",\"id\":10},{\"color\":\"green\",\"name\":\"Mark\",\"id\":11}]},{\"title\":\"AI Game\",\"id\":1,\"players\":[{\"color\":\"orange\",\"name\":\"Pete\",\"id\":10},{\"color\":\"red\",\"name\":\"Squall\",\"id\":-2},{\"color\":\"green\",\"name\":\"Steve\",\"id\":-2},{\"color\":\"white\",\"name\":\"Scott\",\"id\":-2}]},{\"title\":\"Empty Game\",\"id\":2,\"players\":[{\"color\":\"orange\",\"name\":\"Sam\",\"id\":0},{\"color\":\"blue\",\"name\":\"Brooke\",\"id\":1},{\"color\":\"red\",\"name\":\"Pete\",\"id\":10},{\"color\":\"green\",\"name\":\"Mark\",\"id\":11}]}]";
		GameDescription[] games= (GameDescription[]) trans.translateFrom(mssg, GameDescription[].class);
		System.out.println(games.length);
		
	}
	@Test
	public void testTranslateFrom() {		
		String message = "{\"chat\":{\"lines\":[]},"
				+ "\"bank\":{\"brick\":10,\"ore\":19,\"sheep\":9,\"wheat\":19,\"wood\":19},"
				+ "\"log\":{\"lines\":[]},\"map\":{\"radius\":3},"
				+ "\"players\":[{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Blue\",\"discarded\":false,\"monuments\":0,\"name\":\"Ender\",\"playerIndex\":0,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
				+ "{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Orange\",\"discarded\":false,\"monuments\":0,\"name\":\"Ralph\",\"playerIndex\":1,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
				+ "{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Red\",\"discarded\":false,\"monuments\":0,\"name\":\"Henry\",\"playerIndex\":2,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
				+ "{\"cities\":4,\"settlements\":5,\"roads\":10,\"color\":\"Brown\",\"discarded\":false,\"monuments\":0,\"name\":\"Frodo\",\"playerIndex\":3,\"playedDevCard\":true,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2}],"
				+ "\"turnTracker\":{\"currentTurn\":0,\"status\":\"Playing\",\"longestRoad\":-1,\"largestArmy\":-1},"
				+ "\"winner\":-1,"
				+ "\"deck\":{\"monopoly\":2,\"monument\":5,\"roadBuilding\":2,\"soldier\":14,\"yearOfPlenty\":2}}";
		ServerModel Server = (ServerModel) trans.translateFrom(message, ServerModel.class);
		
		assertEquals("Bank bricks should match(10)",Server.getBank().getBrick(),10);
		assertEquals("Bank sheeps should match(9)",Server.getBank().getSheep(),9);
		assertEquals("Player at (0) should be named Ender",Server.getPlayers().get(0).getName(),"Ender");
		assertEquals("Player at (2) should be named Henry",Server.getPlayers().get(2).getName(),"Henry");
		assertEquals("Player at (3) should have 10 roads",Server.getPlayers().get(3).getRoads(),10);
		assertEquals("Player at (3) should have played dev card",Server.getPlayers().get(3).hasPlayedDevCard(),true);
		assertEquals("Map should have a radius of 3",Server.getMap().getRadius(),3);
		
		String swagger="{\r\n" + 
				"  \"deck\": {\r\n" + 
				"    \"yearOfPlenty\": 2,\r\n" + 
				"    \"monopoly\": 2,\r\n" + 
				"    \"soldier\": 14,\r\n" + 
				"    \"roadBuilding\": 2,\r\n" + 
				"    \"monument\": 5\r\n" + 
				"  },\r\n" + 
				"  \"map\": {\r\n" + 
				"    \"hexes\": [\r\n" + 
				"      {\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"brick\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 4\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 11\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"brick\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 8\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 3\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"ore\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 9\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 12\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"ore\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -2,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 5\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 10\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 11\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"brick\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 5\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 6\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -2,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 2\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 9\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 4\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 10\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -2,\r\n" + 
				"          \"y\": 2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 6\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"ore\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": 2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 3\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 8\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"roads\": [\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 1,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"S\",\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 3,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 3,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 2,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"S\",\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 0,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"S\",\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 2,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"S\",\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 1,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -2,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 0,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"cities\": [],\r\n" + 
				"    \"settlements\": [\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 3,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 3,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SE\",\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 2,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 2,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 1,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -2,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 0,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SE\",\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 1,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 0,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"radius\": 3,\r\n" + 
				"    \"ports\": [\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"brick\",\r\n" + 
				"        \"direction\": \"NE\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -2,\r\n" + 
				"          \"y\": 3\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 3,\r\n" + 
				"        \"direction\": \"SW\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 3,\r\n" + 
				"          \"y\": -3\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"ore\",\r\n" + 
				"        \"direction\": \"S\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -3\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"direction\": \"NW\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 3,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 3,\r\n" + 
				"        \"direction\": \"N\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 3\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"direction\": \"S\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 3,\r\n" + 
				"        \"direction\": \"SE\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -3,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 3,\r\n" + 
				"        \"direction\": \"NW\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"direction\": \"NE\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -3,\r\n" + 
				"          \"y\": 2\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"robber\": {\r\n" + 
				"      \"x\": 0,\r\n" + 
				"      \"y\": -2\r\n" + 
				"    }\r\n" + 
				"  },\r\n" + 
				"  \"players\": [\r\n" + 
				"    {\r\n" + 
				"      \"resources\": {\r\n" + 
				"        \"brick\": 0,\r\n" + 
				"        \"wood\": 1,\r\n" + 
				"        \"sheep\": 1,\r\n" + 
				"        \"wheat\": 1,\r\n" + 
				"        \"ore\": 0\r\n" + 
				"      },\r\n" + 
				"      \"oldDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"newDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"roads\": 13,\r\n" + 
				"      \"cities\": 4,\r\n" + 
				"      \"settlements\": 3,\r\n" + 
				"      \"soldiers\": 0,\r\n" + 
				"      \"victoryPoints\": 2,\r\n" + 
				"      \"monuments\": 0,\r\n" + 
				"      \"playedDevCard\": false,\r\n" + 
				"      \"discarded\": false,\r\n" + 
				"      \"playerID\": 0,\r\n" + 
				"      \"playerIndex\": 0,\r\n" + 
				"      \"name\": \"Sam\",\r\n" + 
				"      \"color\": \"orange\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"resources\": {\r\n" + 
				"        \"brick\": 1,\r\n" + 
				"        \"wood\": 0,\r\n" + 
				"        \"sheep\": 1,\r\n" + 
				"        \"wheat\": 0,\r\n" + 
				"        \"ore\": 1\r\n" + 
				"      },\r\n" + 
				"      \"oldDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"newDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"roads\": 13,\r\n" + 
				"      \"cities\": 4,\r\n" + 
				"      \"settlements\": 3,\r\n" + 
				"      \"soldiers\": 0,\r\n" + 
				"      \"victoryPoints\": 2,\r\n" + 
				"      \"monuments\": 0,\r\n" + 
				"      \"playedDevCard\": false,\r\n" + 
				"      \"discarded\": false,\r\n" + 
				"      \"playerID\": 1,\r\n" + 
				"      \"playerIndex\": 1,\r\n" + 
				"      \"name\": \"Brooke\",\r\n" + 
				"      \"color\": \"green\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"resources\": {\r\n" + 
				"        \"brick\": 0,\r\n" + 
				"        \"wood\": 1,\r\n" + 
				"        \"sheep\": 1,\r\n" + 
				"        \"wheat\": 1,\r\n" + 
				"        \"ore\": 0\r\n" + 
				"      },\r\n" + 
				"      \"oldDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"newDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"roads\": 13,\r\n" + 
				"      \"cities\": 4,\r\n" + 
				"      \"settlements\": 3,\r\n" + 
				"      \"soldiers\": 0,\r\n" + 
				"      \"victoryPoints\": 2,\r\n" + 
				"      \"monuments\": 0,\r\n" + 
				"      \"playedDevCard\": false,\r\n" + 
				"      \"discarded\": false,\r\n" + 
				"      \"playerID\": 10,\r\n" + 
				"      \"playerIndex\": 2,\r\n" + 
				"      \"name\": \"Pete\",\r\n" + 
				"      \"color\": \"red\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"resources\": {\r\n" + 
				"        \"brick\": 0,\r\n" + 
				"        \"wood\": 1,\r\n" + 
				"        \"sheep\": 1,\r\n" + 
				"        \"wheat\": 0,\r\n" + 
				"        \"ore\": 1\r\n" + 
				"      },\r\n" + 
				"      \"oldDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"newDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"roads\": 13,\r\n" + 
				"      \"cities\": 4,\r\n" + 
				"      \"settlements\": 3,\r\n" + 
				"      \"soldiers\": 0,\r\n" + 
				"      \"victoryPoints\": 2,\r\n" + 
				"      \"monuments\": 0,\r\n" + 
				"      \"playedDevCard\": false,\r\n" + 
				"      \"discarded\": false,\r\n" + 
				"      \"playerID\": 11,\r\n" + 
				"      \"playerIndex\": 3,\r\n" + 
				"      \"name\": \"Mark\",\r\n" + 
				"      \"color\": \"green\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"log\": {\r\n" + 
				"    \"lines\": [\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Sam\",\r\n" + 
				"        \"message\": \"Sam built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Sam\",\r\n" + 
				"        \"message\": \"Sam built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Sam\",\r\n" + 
				"        \"message\": \"Sam's turn just ended\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Brooke\",\r\n" + 
				"        \"message\": \"Brooke built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Brooke\",\r\n" + 
				"        \"message\": \"Brooke built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Brooke\",\r\n" + 
				"        \"message\": \"Brooke's turn just ended\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Pete\",\r\n" + 
				"        \"message\": \"Pete built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Pete\",\r\n" + 
				"        \"message\": \"Pete built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Pete\",\r\n" + 
				"        \"message\": \"Pete's turn just ended\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Mark\",\r\n" + 
				"        \"message\": \"Mark built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Mark\",\r\n" + 
				"        \"message\": \"Mark built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Mark\",\r\n" + 
				"        \"message\": \"Mark's turn just ended\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Mark\",\r\n" + 
				"        \"message\": \"Mark built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Mark\",\r\n" + 
				"        \"message\": \"Mark built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Mark\",\r\n" + 
				"        \"message\": \"Mark's turn just ended\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Pete\",\r\n" + 
				"        \"message\": \"Pete built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Pete\",\r\n" + 
				"        \"message\": \"Pete built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Pete\",\r\n" + 
				"        \"message\": \"Pete's turn just ended\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Brooke\",\r\n" + 
				"        \"message\": \"Brooke built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Brooke\",\r\n" + 
				"        \"message\": \"Brooke built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Brooke\",\r\n" + 
				"        \"message\": \"Brooke's turn just ended\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Sam\",\r\n" + 
				"        \"message\": \"Sam built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Sam\",\r\n" + 
				"        \"message\": \"Sam built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Sam\",\r\n" + 
				"        \"message\": \"Sam's turn just ended\"\r\n" + 
				"      }\r\n" + 
				"    ]\r\n" + 
				"  },\r\n" + 
				"  \"chat\": {\r\n" + 
				"    \"lines\": []\r\n" + 
				"  },\r\n" + 
				"  \"bank\": {\r\n" + 
				"    \"brick\": 23,\r\n" + 
				"    \"wood\": 21,\r\n" + 
				"    \"sheep\": 20,\r\n" + 
				"    \"wheat\": 22,\r\n" + 
				"    \"ore\": 22\r\n" + 
				"  },\r\n" + 
				"  \"turnTracker\": {\r\n" + 
				"    \"status\": \"Rolling\",\r\n" + 
				"    \"currentTurn\": 0,\r\n" + 
				"    \"longestRoad\": -1,\r\n" + 
				"    \"largestArmy\": -1\r\n" + 
				"  },\r\n" + 
				"  \"winner\": -1,\r\n" + 
				"  \"version\": 0\r\n" + 
				"}";
		Server = (ServerModel) trans.translateFrom(swagger, ServerModel.class);
		assertEquals("Player at (1) should be named Brooke",Server.getPlayers().get(1).getName(),"Brooke");
		assertEquals("Player at (3) should be named Mark",Server.getPlayers().get(3).getName(),"Mark");
		assertEquals("Status should be Rolling",Server.getTurnTracker().getStatus(),"Rolling");
		assertEquals("Bank should have 20 sheep",Server.getBank().getSheep(),20);
	}
}
