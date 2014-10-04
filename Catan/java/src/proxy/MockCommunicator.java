package proxy;
import java.net.MalformedURLException;
import java.util.List;

import junit.TestingConstants;
import shared.ServerMethodRequests.AcceptTradeRequest;
import shared.ServerMethodRequests.BuildCityRequest;
import shared.ServerMethodRequests.BuildRoadRequest;
import shared.ServerMethodRequests.BuildSettlementRequest;
import shared.ServerMethodRequests.BuyDevCardRequest;
import shared.ServerMethodRequests.ChangeLogLevelRequest;
import shared.ServerMethodRequests.CreateGameRequest;
import shared.ServerMethodRequests.DiscardCardsRequest;
import shared.ServerMethodRequests.FinishTurnRequest;
import shared.ServerMethodRequests.JoinGameRequest;
import shared.ServerMethodRequests.MaritimeTradeRequest;
import shared.ServerMethodRequests.MonopolyDevRequest;
import shared.ServerMethodRequests.MonumentDevRequest;
import shared.ServerMethodRequests.OfferTradeRequest;
import shared.ServerMethodRequests.RoadBuildingDevRequest;
import shared.ServerMethodRequests.RollNumberRequest;
import shared.ServerMethodRequests.SendChatRequest;
import shared.ServerMethodRequests.SoldierDevRequest;
import shared.ServerMethodRequests.UserRequest;
import shared.ServerMethodRequests.YearOfPlentyDevRequest;
import shared.definitions.GameDescription;
import shared.definitions.PlayerDescription;
import shared.definitions.ServerLogLevel;
import client.model.Log;

/**This class will contain some hard code data for the use of testing.
 * 
 * @author Chad
 *
 */
public class MockCommunicator implements ICommunicator {
	
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
		int clientVersion = 0;
		if(commandName.contains("game/model")) {
			int modelIndex = commandName.indexOf("=") + 1;
			if(modelIndex > -1) {
				clientVersion = Integer.parseInt(commandName.substring(modelIndex));
			}
			commandName = "/game/model";
		}
		switch(commandName) {
		case "/game/listAI":
			result = new CommandResponse(null, 200, TestingConstants.MOCK_AIS, null);
			break;
		case "/game/commands":
			result = new CommandResponse(null, 200, TestingConstants.getCommandsLog(), null);
			break;
		case "/game/model":
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue())) {
				if(clientVersion < TestingConstants.getServerModel().getVersion()) {
					result = new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
				}
				else {
					result = new CommandResponse(null, 200, null, null);
				}
			}
			else {
				result = new CommandResponse(null, 404, null, null);
			}
			break;
		case "/games/list":
			result = new CommandResponse(null, 200, TestingConstants.GAMES_ARRAY, null);
			break;
		default:
			result = new CommandResponse(null, 400, "default case", "Error: Unhandled Get Case Reached!");
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
			if(createGameRequest.isRandomNumbers() && createGameRequest.isRandomPorts() && createGameRequest.isRandomTiles()) {
				result = new CommandResponse(null, 200, newGame, null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
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
			if(postCommandsRequest.getLogMessages().equals(TestingConstants.getCommandsLog().getLogMessages())) {
				result = new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, TestingConstants.getServerModel(), TestingConstants.INVALID_COMMANDS_MESSAGE);
			}
			break;
		case "/game/addAI":
			String addAIRequest = (String) commandParameters;
			if(TestingConstants.MOCK_AIS_LIST.contains(addAIRequest)) {
				result = new CommandResponse(null, 200, null, null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "/util/changeLogLevel":
			ChangeLogLevelRequest logRequest = (ChangeLogLevelRequest) commandParameters;
			if(logRequest.getlogLevel().equals(ServerLogLevel.SEVERE.name().toLowerCase())) {
				result = new CommandResponse(null, 200, null, null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "/games/sendChat":
			SendChatRequest chatRequest = (SendChatRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && chatRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX && chatRequest.getContent().equals(TestingConstants.CHAT_CONTENT)) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "/moves/acceptTrade":
			AcceptTradeRequest acceptTradeRequest = (AcceptTradeRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && acceptTradeRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX && acceptTradeRequest.isWillAccept()) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "/moves/discardCards":
			DiscardCardsRequest discardRequest = (DiscardCardsRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && discardRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX && discardRequest.getDiscardedCards().equals(TestingConstants.RESOURCE_HAND)) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "/moves/rollNumber":
			RollNumberRequest rollRequest = (RollNumberRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && rollRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX && rollRequest.getNumber() == TestingConstants.ROLL_NUMBER) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "/moves/buildRoad":
			BuildRoadRequest buildRoadRequest = (BuildRoadRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && buildRoadRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX && buildRoadRequest.getRoadLocation().equals(TestingConstants.EDGE_LOCATION) && buildRoadRequest.isFree()) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "/moves/buildSettlement":
			BuildSettlementRequest buildSettlementRequest = (BuildSettlementRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && buildSettlementRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX && buildSettlementRequest.getVertexLocation().equals(TestingConstants.VERTEX_LOCATION) && buildSettlementRequest.isFree()) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "/moves/buildCity":
			BuildCityRequest buildCityRequest = (BuildCityRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && buildCityRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX && buildCityRequest.getCityLocation().equals(TestingConstants.VERTEX_LOCATION)) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "/moves/offerTrade":
			OfferTradeRequest offerTradeRequest = (OfferTradeRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && offerTradeRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX && offerTradeRequest.getOffer().equals(TestingConstants.RESOURCE_HAND) && offerTradeRequest.getReceiver() == TestingConstants.ANOTHER_PLAYER_INDEX) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "/moves/maritimeTrade":
			MaritimeTradeRequest maritimeRequest = (MaritimeTradeRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && maritimeRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX && maritimeRequest.getInputResource().equals(TestingConstants.RESOURCE_TYPE.name()) && maritimeRequest.getInputResource().equals(TestingConstants.RESOURCE_TYPE.name()) && maritimeRequest.getRatio() == TestingConstants.MARITIME_RATIO) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "/moves/finishTurn":
			FinishTurnRequest finishRequest = (FinishTurnRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && finishRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "/moves/buyDevCard":
			BuyDevCardRequest buyDevRequest = (BuyDevCardRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && buyDevRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "/moves/Year_of_Plenty":
			YearOfPlentyDevRequest yearPlentyRequest = (YearOfPlentyDevRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && yearPlentyRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX && yearPlentyRequest.getResource1().equals(TestingConstants.RESOURCE_TYPE.name()) && yearPlentyRequest.getResource2().equals(TestingConstants.OTHER_RESOURCE_TYPE.name())) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "/moves/Road_Building":
			RoadBuildingDevRequest roadBuildRequest = (RoadBuildingDevRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && roadBuildRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX && roadBuildRequest.getSpot1().equals(TestingConstants.EDGE_LOCATION) && roadBuildRequest.getSpot2().equals(TestingConstants.ANOTHER_EDGE)) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}			
			break;
		case "/moves/Monopoly":
			MonopolyDevRequest monopolyRequest = (MonopolyDevRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && monopolyRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX && monopolyRequest.getResource().equals(TestingConstants.RESOURCE_TYPE.name())) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}			
			break;
		case "/moves/Soldier":
			SoldierDevRequest soldierRequest = (SoldierDevRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && soldierRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX && soldierRequest.getVictimIndex() == TestingConstants.ANOTHER_PLAYER_INDEX && soldierRequest.getLocation().equals(TestingConstants.HEX_LOCATION)) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}			
			break;
		case "/moves/Monument":
			MonumentDevRequest monumentRequest = (MonumentDevRequest) commandParameters;
			if(TestingConstants.VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && monumentRequest.getPlayerIndex() == TestingConstants.PLAYER_INDEX) {
				result =  new CommandResponse(null, 200, TestingConstants.getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		default:
			result = new CommandResponse(null, 400, "default case", "Error: Unhandled Post Case Reached!" + "Command provided: " + commandName );
		}
		return result;
	}
}
