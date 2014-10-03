package proxy;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.ServerMethodRequests.*;
import shared.ServerMethodResponses.*;
import shared.definitions.CatanColor;
import shared.definitions.GameDescription;
import shared.definitions.PlayerDescription;
import shared.definitions.ServerModel;
import client.model.Log;
import proxy.junit.TestingConstants;

/**This class will contain some hard code data for the use of testing.
 * 
 * @author Chad
 *
 */
public class MockCommunicator implements ICommunicator {
	
	private ServerModel mockServerModel;
	private ITranslator jsonTrans;
	//private String samJoinReturnCookie = "catan.game=0";
	//private String samFakeCookieAfterJoin = samLoginCookie + " " + samJoinReturnCookie;
	private Map<String, List<String>> successJoinHeaders;
	private Map<String, List<String>> failHeaders;
	
	/**
	 * @param host
	 * @param port
	 * @param jsonTrans
	 */
	public MockCommunicator(TranslatorJSON jsonTrans) {
		super();
		this.jsonTrans = jsonTrans;
		mockServerModel = (ServerModel)jsonTrans.translateFrom(mockData, ServerModel.class);
	}

	/**Starts the request from the server given the information from the proxy. Starts by packaging up the info and having the translator change it to json. Then takes the json object with the request type and sends it to the server. 
	 * 
	 * @param commandName
	 * @param commandParameters
	 * @return ICommandResponse from the server
	 * @throws MalformedURLException 
	 */
	@Override
	public CommandResponse executeCommand(RequestType requestType, List<Pair<String,String>> headers, String commandName, Object commandParameters, Class<?> responseCastClass){
		CommandResponse result;
		if(requestType.name().toLowerCase().equals("get")) {
			result = doGet(commandName, headers, commandParameters, responseCastClass);
		}
		else {
			result = doPost(commandName, headers, commandParameters, responseCastClass);
		}
		return result;
	}
	
	
	/**
	 * 
	 * @param headers 
	 * @param urlPath
	 * @throws ClientException
	 */
	private CommandResponse doGet(String commandName, List<Pair<String,String>> headers, Object commandParameters, Class<?> responseCastClass) //throws ClientException may need to add this in later
	{
		CommandResponse result = null;
		switch(commandName) {
		case "/game/listAI":
			result = new CommandResponse(null, 200, TestingConstants.MOCK_AIS, null);
			break;
		case "/game/commands":
			break;
		case "/game/model?version=0":
			break;
		case "/games/list":
			result = new CommandResponse(null, 200, TestingConstants.GAMES_ARRAY, null);
			break;
		default:
			result = new CommandResponse(failHeaders, 400, "default case", "Error: Unhandled Get Case Reached!");
		}
		return result;
	}
	
	
	/**Make HTTP POST request to the specified URL, 
	 *passing in the specified postData object
	 * @param urlPath
	 * @param postData
	 * @throws ClientException
	 */
	private CommandResponse doPost(String commandName, List<Pair<String,String>> headers, Object commandParameters, Class<?> responseCastClass){
		CommandResponse result = null;
		switch(commandName) {
		case "/user/login":
			UserRequest loginRequest = (UserRequest) commandParameters;
			if(loginRequest.getUsername().equals(TestingConstants.VALID_USERNAME) && loginRequest.getPassword().equals(TestingConstants.VALID_PASSWORD)) {
				
				result = new CommandResponse(TestingConstants.SUCCESSFUL_LOGIN_HEADERS, 200, null, null);
			}
			else {
				result = new CommandResponse(null, 400, null, TestingConstants.LOGIN_FAIL_MESSAGE);
			}
			break;
		case "/user/register":
			UserRequest registerRequest = (UserRequest) commandParameters;
			if(registerRequest.getUsername().equals(TestingConstants.VALID_USERNAME)) {
				result = new CommandResponse(null, 400, null, TestingConstants.REGISTER_FAIL_MESSAGE);
			}
			else {
				result = new CommandResponse(TestingConstants.SUCCESSFUL_LOGIN_HEADERS, 200, null, null);
			}
			break;
		case "/games/create":
			CreateGameRequest createGameRequest = (CreateGameRequest) commandParameters;
			GameDescription newGame = new GameDescription(createGameRequest.getName(), 0, new PlayerDescription[4]);
			result = new CommandResponse(null, 200, newGame, null);
			break;
		case "/games/join":
			JoinGameRequest joinGameRequest = (JoinGameRequest) commandParameters;
			break;
		case "/game/reset":
			ResetGameRequest resetGameRequest = (ResetGameRequest) commandParameters; 
			break;
		case "/game/commands":
			Log postCommandsRequest = (Log) commandParameters;
			break;
		case "/game/addAI":
			String addAIRequest = (String) commandParameters;
			break;
		case "/util/changeLogLevel":
			ChangeLogLevelRequest logRequest = (ChangeLogLevelRequest) commandParameters;
			break;
		case "/games/sendChat":
			SendChatRequest chatRequest = (SendChatRequest) commandParameters;
			break;
		case "/moves/acceptTrade":
			AcceptTradeRequest acceptTradeRequest = (AcceptTradeRequest) commandParameters;
			break;
		case "/moves/discardCards":
			DiscardCardsRequest discardRequest = (DiscardCardsRequest) commandParameters;
			break;
		case "/moves/rollNumber":
			RollNumberRequest rollRequest = (RollNumberRequest) commandParameters;
			break;
		case "/moves/buildRoad":
			BuildRoadRequest buildRoadRequest = (BuildRoadRequest) commandParameters;
			break;
		case "/moves/buildSettlement":
			BuildSettlementRequest buildSettlementRequest = (BuildSettlementRequest) commandParameters;
			break;
		case "/moves/buildCity":
			BuildCityRequest buildCityRequest = (BuildCityRequest) commandParameters;
			break;
		case "/moves/offerTrade":
			OfferTradeRequest offerTradeRequest = (OfferTradeRequest) commandParameters;
			break;
		case "/moves/maritimeTrade":
			MaritimeTradeRequest maritimeRequest = (MaritimeTradeRequest) commandParameters;
			break;
		case "/moves/finishTurn":
			FinishTurnRequest finishRequest = (FinishTurnRequest) commandParameters;
			break;
		case "/moves/buyDevCard":
			BuyDevCardRequest buyDevRequest = (BuyDevCardRequest) commandParameters;
			break;
		case "/moves/Year_of_Plenty":
			YearOfPlentyDevRequest yearPlentyRequest = (YearOfPlentyDevRequest) commandParameters;
			break;
		case "/moves/Road_Building":
			RoadBuildingDevRequest roadBuildRequest = (RoadBuildingDevRequest) commandParameters;
			break;
		case "/moves/Monopoly":
			MonopolyDevRequest monopolyRequest = (MonopolyDevRequest) commandParameters;
			break;
		case "/moves/Soldier":
			SoldierDevRequest soldierRequest = (SoldierDevRequest) commandParameters;
			break;
		case "/moves/Monument":
			MonumentDevRequest monumentRequest = (MonumentDevRequest) commandParameters;
			break;
		default:
			result = new CommandResponse(failHeaders, 400, "default case", "Error: Unhandled Post Case Reached!");
		}
		return result;
	}
		//Mock Hard coded data: The following data is used for JUnit testing. It contains mock data for testing various commands and the communication between the translator and the server.
	
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
