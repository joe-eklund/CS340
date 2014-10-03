/**
 * 
 */
package proxy.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import proxy.ClientCommunicator;
import proxy.CommandResponse;
import proxy.Pair;
import proxy.RequestType;
import proxy.TranslatorJSON;
import shared.definitions.User;

/**
 * @author Chad
 *
 */
public class ClientCommunicatorTest {

	ClientCommunicator CCTestor;
	TranslatorJSON jsonTrans;
	String mockCommandName = "user/login";
	List<Pair<String,String>> headers;
	RequestType mockRequest;
	User mockUser;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		jsonTrans = new TranslatorJSON();
		CCTestor = new ClientCommunicator("localhost", 8081, jsonTrans);
		headers = new ArrayList<Pair<String,String>>();
		Pair mockPair = new Pair("SAM", "sam");
		headers.add(mockPair);
		mockUser = new User("Brooke", "brooke");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test method for {@link proxy.ClientCommunicator#executeCommand(proxy.RequestType, java.util.List, java.lang.String, java.lang.Object, java.lang.Class)}.
	 */
	@Test
	public void connectToServerTest() {
		mockRequest = RequestType.POST;
		CommandResponse mockResponse = CCTestor.executeCommand(mockRequest, headers, mockCommandName, mockUser, RequestType.class);
		//assertNotNull(mockResponse.getResponseCode() == 200);
		System.out.print(mockResponse);
	}
	
	final String mockData2 = "{\"chat\":{\"lines\":[]},"
			+ "\"bank\":{\"brick\":10,\"ore\":19,\"sheep\":9,\"wheat\":19,\"wood\":19},"
			+ "\"log\":{\"lines\":[]},\"map\":{\"radius\":3},"
			+ "\"players\":[{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Blue\",\"discarded\":false,\"monuments\":0,\"name\":\"Ender\",\"playerIndex\":0,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
			+ "{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Orange\",\"discarded\":false,\"monuments\":0,\"name\":\"Ralph\",\"playerIndex\":1,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
			+ "{\"cities\":4,\"settlements\":5,\"roads\":15,\"color\":\"Red\",\"discarded\":false,\"monuments\":0,\"name\":\"Henry\",\"playerIndex\":2,\"playedDevCard\":false,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2},"
			+ "{\"cities\":4,\"settlements\":5,\"roads\":10,\"color\":\"Brown\",\"discarded\":false,\"monuments\":0,\"name\":\"Frodo\",\"playerIndex\":3,\"playedDevCard\":true,\"playerID\":0,\"resources\":{\"brick\":0,\"ore\":0,\"sheep\":0,\"wheat\":0,\"wood\":0},\"soldiers\":0,\"victoryPoints\":2}],"
			+ "\"turnTracker\":{\"currentTurn\":0,\"status\":\"Playing\",\"longestRoad\":-1,\"largestArmy\":-1},"
			+ "\"version\":0,\"winner\":-1,"
			+ "\"deck\":{\"monopoly\":2,\"monument\":5,\"roadBuilding\":2,\"soldier\":14,\"yearOfPlenty\":2}}";
				
	final String mockData = "{\r\n" + 
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
			"        \"owner\": 2,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"S\",\r\n" + 
			"          \"x\": 1,\r\n" + 
			"          \"y\": -1\r\n" + 
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
			"        \"owner\": 0,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"S\",\r\n" + 
			"          \"x\": 0,\r\n" + 
			"          \"y\": 1\r\n" + 
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
			"        \"owner\": 2,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"S\",\r\n" + 
			"          \"x\": 0,\r\n" + 
			"          \"y\": 0\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 0,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"SW\",\r\n" + 
			"          \"x\": 2,\r\n" + 
			"          \"y\": 0\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
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
			"      }\r\n" + 
			"    ],\r\n" + 
			"    \"cities\": [],\r\n" + 
			"    \"settlements\": [\r\n" + 
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
			"          \"x\": -1,\r\n" + 
			"          \"y\": -1\r\n" + 
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
			"      },\r\n" + 
			"      {\r\n" + 
			"        \"owner\": 3,\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"direction\": \"SW\",\r\n" + 
			"          \"x\": -1,\r\n" + 
			"          \"y\": 1\r\n" + 
			"        }\r\n" + 
			"      }\r\n" + 
			"    ],\r\n" + 
			"    \"radius\": 3,\r\n" + 
			"    \"ports\": [\r\n" + 
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
			"        \"direction\": \"NW\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": 2,\r\n" + 
			"          \"y\": 1\r\n" + 
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
			"        \"ratio\": 2,\r\n" + 
			"        \"resource\": \"wood\",\r\n" + 
			"        \"direction\": \"NE\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": -3,\r\n" + 
			"          \"y\": 2\r\n" + 
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
			"        \"ratio\": 3,\r\n" + 
			"        \"direction\": \"SE\",\r\n" + 
			"        \"location\": {\r\n" + 
			"          \"x\": -3,\r\n" + 
			"          \"y\": 0\r\n" + 
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
			"      \"color\": \"red\"\r\n" + 
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
}
