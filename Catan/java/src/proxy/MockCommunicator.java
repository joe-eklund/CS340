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
import junit.TestingConstants;

/**This class will contain some hard code data for the use of testing.
 * 
 * @author Chad
 *
 */
public class MockCommunicator implements ICommunicator {
	
	//private String samJoinReturnCookie = "catan.game=0";
	//private String samFakeCookieAfterJoin = samLoginCookie + " " + samJoinReturnCookie;
	private Map<String, List<String>> successJoinHeaders;
	private Map<String, List<String>> failHeaders;
	
	/**
	 * @param host
	 * @param port
	 * @param jsonTrans
	 */
	public MockCommunicator() {
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
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue())) {
				result = new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 404, null, null);
			}
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
			if(joinGameRequest.getColor().equals(TestingConstants.JOIN_GAME_COLOR) && joinGameRequest.getID() == TestingConstants.VALID_JOIN_GAME_INDEX && headers.get(0).getValue() == TestingConstants.VALID_LOGIN_COOKIE_CLIENT) {
				result = new CommandResponse(TestingConstants.SUCCESSFUL_JOIN_HEADERS, 200, null, null);
			}
			else {
				result = new CommandResponse(null, 404, null, null);
			}
			break;
		case "/game/reset":
			result = new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
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
}
