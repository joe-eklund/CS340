package shared.definitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import proxy.ITranslator;
import proxy.TranslatorJSON;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.Log;

/**
 * A class made for testing constraints on the proxy, client communicator, translator, and server
 *
 */
public final class TestingConstants {
	
	public static final String VALID_LOGIN_COOKIE_CLIENT = "catan.user=%7B%22name%22%3A%22Sam%22%2C%22password%22%3A%22sam%22%2C%22playerID%22%3A0%7D";
	public static final String VALID_LOGIN_COOKIE = "catan.user=%7B%22name%22%3A%22Sam%22%2C%22password%22%3A%22sam%22%2C%22playerID%22%3A0%7D;Path=/;";
	private static final List<String> COOKIE_VALUE_LIST = new ArrayList<String>() {{
		add(TestingConstants.VALID_LOGIN_COOKIE);
	}};
	public static final HashMap<String, List<String>> SUCCESSFUL_LOGIN_HEADERS = new HashMap<String, List<String>>() {{
		put("Set-cookie", COOKIE_VALUE_LIST);
	}};
	
	public static final String VALID_USERNAME = "Sam";
	public static final String VALID_PASSWORD = "sam";
	public static final String INVALID_PASSWORD = "badpass";
	public static final String LOGIN_FAIL_MESSAGE = "Login failed - bad password or username";
	public static final String REGISTER_FAIL_MESSAGE = "Registration failed - username is already taken";
	public static final String INVALID_REGISTER_USERNAME = "Sam";
	public static final String VALID_REGISTER_USERNAME = "Brooke";
	public static final String VALID_REGISTER_PASSWORD = "brooke";
	public static final PlayerDescription[] PLAYER_SET_1 = {
//			new PlayerDescription(CatanColor.BLUE, 0, "Bill"),
//			new PlayerDescription(CatanColor.BROWN, 1, "Fred"),
//			new PlayerDescription(CatanColor.GREEN, 2, "Sam"),
//			new PlayerDescription(CatanColor.ORANGE, 3, "May"),
	};
	public static final GameDescription GAME_DESCRIPTION_1 = new GameDescription("Game1", 0, Arrays.asList(PLAYER_SET_1));
	
	public static final PlayerDescription[] PLAYER_SET_2 = {
//			new PlayerDescription(CatanColor.BLUE, 0, "June"),
//			new PlayerDescription(CatanColor.BROWN, 1, "Tom"),
//			new PlayerDescription(CatanColor.GREEN, 2, "Sam"),
//			new PlayerDescription(CatanColor.ORANGE, 3, "Nick"),
	};
	public static final GameDescription GAME_DESCRIPTION_2 = new GameDescription("Game2", 1, Arrays.asList(PLAYER_SET_2));
	
	public static final List<GameDescription> GAMES_LIST = new ArrayList<GameDescription>() {{
		add(GAME_DESCRIPTION_1);
		add(GAME_DESCRIPTION_2);
	}};
	
	public static final GameDescription[] GAMES_ARRAY = { GAME_DESCRIPTION_1, GAME_DESCRIPTION_2};
	
	public static final String NEW_GAME_NAME = "New Game";
	public static final GameDescription NEW_GAME = new GameDescription(NEW_GAME_NAME, 0, new ArrayList<PlayerDescription>());

	public static final String MOCK_AIS[] = new String[] {"Bill","Fred","Tom","Jim"};
	public static final List<String> MOCK_AIS_LIST = Arrays.asList(MOCK_AIS);
	
	public static final int VALID_JOIN_GAME_INDEX = 0;
	public static final String INVALID_LOGIN_COOKIE = "invalid login cookie";
	public static final String JOIN_GAME_COLOR = CatanColor.BLUE.name().toLowerCase();
	public static final String VALID_JOIN_RETURN_COOKIE = "catan.game=0;Path=/;";
	public static final String VALID_JOINED_GAME_COOKIE = VALID_LOGIN_COOKIE_CLIENT + "; catan.game=0";
	
	private static final List<String> COOKIE_VALUE_LIST_JOIN = new ArrayList<String>() {{
		add(TestingConstants.VALID_JOIN_RETURN_COOKIE);
	}};
	public static final HashMap<String, List<String>> SUCCESSFUL_JOIN_HEADERS = new HashMap<String, List<String>>() {{
		put("Set-cookie", COOKIE_VALUE_LIST_JOIN);
	}};
	
	public static final int CLIENT_GAME_VERSION = 0;
	
	public static final String INVALID_COMMANDS_MESSAGE = "Error: invalid command log syntax";
	
	public static final String INVALID_AI = "invalid AI";
	public static final int PLAYER_INDEX = 27;
	public static final String CHAT_CONTENT = "Hello World!";
	
	public static final ResourceHand RESOURCE_HAND = new ResourceHand(3, 1, 0, 0, -2);
	public static final int ROLL_NUMBER = 5;
	public static final int ANOTHER_PLAYER_INDEX = 17;
	public static final HexLocation HEX_LOCATION = new HexLocation(1, 3);
	public static final EdgeLocation EDGE_LOCATION = new EdgeLocation(HEX_LOCATION, EdgeDirection.SouthEast);
	public static final EdgeLocation ANOTHER_EDGE = new EdgeLocation(new HexLocation(4, 7), EdgeDirection.NorthEast);
	public static final VertexLocation VERTEX_LOCATION = new VertexLocation(new HexLocation(1, 3), VertexDirection.West);
	public static final int MARITIME_RATIO = 3;
	public static final ResourceType RESOURCE_TYPE = ResourceType.ORE;
	public static final ResourceType OTHER_RESOURCE_TYPE = ResourceType.WOOD;
	
	public static Log getCommandsLog() {
		ITranslator translator = new TranslatorJSON();
		return (Log)translator.translateFrom(MOCK_COMMANDS_JSON, Log.class);
	}
	private static final String MOCK_COMMANDS_JSON  = "{\n" + 
			"    \"lines\": [\n" + 
			"      {\n" + 
			"        \"source\": \"Sam\",\n" + 
			"        \"message\": \"Sam built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Sam\",\n" + 
			"        \"message\": \"Sam built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Sam\",\n" + 
			"        \"message\": \"Sam's turn just ended\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Brooke\",\n" + 
			"        \"message\": \"Brooke built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Brooke\",\n" + 
			"        \"message\": \"Brooke built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Brooke\",\n" + 
			"        \"message\": \"Brooke's turn just ended\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Pete\",\n" + 
			"        \"message\": \"Pete built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Pete\",\n" + 
			"        \"message\": \"Pete built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Pete\",\n" + 
			"        \"message\": \"Pete's turn just ended\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Mark\",\n" + 
			"        \"message\": \"Mark built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Mark\",\n" + 
			"        \"message\": \"Mark built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Mark\",\n" + 
			"        \"message\": \"Mark's turn just ended\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Mark\",\n" + 
			"        \"message\": \"Mark built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Mark\",\n" + 
			"        \"message\": \"Mark built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Mark\",\n" + 
			"        \"message\": \"Mark's turn just ended\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Pete\",\n" + 
			"        \"message\": \"Pete built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Pete\",\n" + 
			"        \"message\": \"Pete built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Pete\",\n" + 
			"        \"message\": \"Pete's turn just ended\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Brooke\",\n" + 
			"        \"message\": \"Brooke built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Brooke\",\n" + 
			"        \"message\": \"Brooke built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Brooke\",\n" + 
			"        \"message\": \"Brooke's turn just ended\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Sam\",\n" + 
			"        \"message\": \"Sam built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Sam\",\n" + 
			"        \"message\": \"Sam built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Sam\",\n" + 
			"        \"message\": \"Sam's turn just ended\"\n" + 
			"      }\n" + 
			"    ]\n" + 
			"  }";
	
	public static ServerModel getServerModel() {
		ITranslator translator = new TranslatorJSON();
		return (ServerModel)translator.translateFrom(MOCK_MODEL_JSON, ServerModel.class);
	}
	private static final String MOCK_MODEL_JSON = "{\r\n" + 
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
