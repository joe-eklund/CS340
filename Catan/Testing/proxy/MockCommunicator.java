package proxy;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import shared.ServerMethodRequests.AcceptTradeRequest;
import shared.ServerMethodRequests.AddAIRequest;
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
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.Log;
import shared.model.LogEntry;
import static shared.definitions.TestingConstants.*;

/**This class will contain some hard code data for the use of testing.
 * @author Chad
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
			commandName = "game/model";
		}
		switch(commandName) {
		case "game/listAI":
			result = new CommandResponse(null, 200, MOCK_AIS, null);
			break;
		case "game/commands": 
			LogEntry[] messages = new LogEntry[getCommandsLog().getLogMessages().size()];
			messages = getCommandsLog().getLogMessages().toArray(messages);
			result = new CommandResponse(null, 200, messages, null);
			break;
		case "game/model":
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue())) {
				if(clientVersion != getServerModel().getVersion()) {
					result = new CommandResponse(null, 200, getServerModel(), null);
				}
				else {
					result = new CommandResponse(null, 200, null, null);
				}
			}
			else {
				result = new CommandResponse(null, 404, null, null);
			}
			break;
		case "games/list":
			result = new CommandResponse(null, 200, GAMES_ARRAY, null);
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
		int x;
		int y;
		switch(commandName) {
		case "user/login":
			UserRequest loginRequest = (UserRequest) commandParameters;
			if(loginRequest.getUsername().equals(VALID_USERNAME) && loginRequest.getPassword().equals(VALID_PASSWORD)) {
				
				result = new CommandResponse(SUCCESSFUL_LOGIN_HEADERS, 200, null, null);
			}
			else {
				result = new CommandResponse(null, 400, null, LOGIN_FAIL_MESSAGE);
			}
			break;
		case "user/register":
			UserRequest registerRequest = (UserRequest) commandParameters;
			if(registerRequest.getUsername().equals(VALID_USERNAME)) {
				result = new CommandResponse(null, 400, null, REGISTER_FAIL_MESSAGE);
			}
			else {
				result = new CommandResponse(SUCCESSFUL_LOGIN_HEADERS, 200, null, null);
			}
			break;
		case "games/create":
			CreateGameRequest createGameRequest = (CreateGameRequest) commandParameters;
			GameDescription newGame = new GameDescription(createGameRequest.getName(), 0, new ArrayList<PlayerDescription>());
			if(createGameRequest.isRandomNumbers() && createGameRequest.isRandomPorts() && createGameRequest.isRandomTiles()) {
				result = new CommandResponse(null, 200, newGame, null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "games/join":
			JoinGameRequest joinGameRequest = (JoinGameRequest) commandParameters;
			if(joinGameRequest.getColor().equals(JOIN_GAME_COLOR) && joinGameRequest.getID() == VALID_JOIN_GAME_INDEX && headers.get(0).getValue() == VALID_LOGIN_COOKIE_CLIENT) {
				result = new CommandResponse(SUCCESSFUL_JOIN_HEADERS, 200, null, null);
			}
			else {
				result = new CommandResponse(null, 404, null, null);
			}
			break;
		case "game/reset":
			result = new CommandResponse(null, 200, getServerModel(), null);
			break;
		case "game/commands":
			Log postCommandsRequest = (Log) commandParameters;
			if(postCommandsRequest.getLogMessages().equals(getCommandsLog().getLogMessages())) {
				result = new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, getServerModel(), INVALID_COMMANDS_MESSAGE);
			}
			break;
		case "game/addAI":
			AddAIRequest addAIRequest = (AddAIRequest) commandParameters;
			if(MOCK_AIS_LIST.contains(addAIRequest.getAIType())) {
				result = new CommandResponse(null, 200, null, null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "util/changeLogLevel":
			ChangeLogLevelRequest logRequest = (ChangeLogLevelRequest) commandParameters;
			if(logRequest.getlogLevel().equals(ServerLogLevel.SEVERE.name().toLowerCase())) {
				result = new CommandResponse(null, 200, null, null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "moves/sendChat":
			SendChatRequest chatRequest = (SendChatRequest) commandParameters;
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && chatRequest.getPlayerIndex() == PLAYER_INDEX && chatRequest.getContent().equals(CHAT_CONTENT)) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "moves/acceptTrade":
			AcceptTradeRequest acceptTradeRequest = (AcceptTradeRequest) commandParameters;
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && acceptTradeRequest.getPlayerIndex() == PLAYER_INDEX && acceptTradeRequest.isWillAccept()) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "moves/discardCards":
			DiscardCardsRequest discardRequest = (DiscardCardsRequest) commandParameters;
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && discardRequest.getPlayerIndex() == PLAYER_INDEX && discardRequest.getDiscardedCards().equals(RESOURCE_HAND)) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "moves/rollNumber":
			RollNumberRequest rollRequest = (RollNumberRequest) commandParameters;
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && rollRequest.getPlayerIndex() == PLAYER_INDEX && rollRequest.getNumber() == ROLL_NUMBER) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "moves/buildRoad":
			BuildRoadRequest buildRoadRequest = (BuildRoadRequest) commandParameters;
			x = buildRoadRequest.getRoadLocation().getX();
			y = buildRoadRequest.getRoadLocation().getY();
			EdgeDirection eDir = buildRoadRequest.getRoadLocation().getDirection();
			EdgeLocation roadLocation= new EdgeLocation(new HexLocation(x,y), eDir);
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && buildRoadRequest.getPlayerIndex() == PLAYER_INDEX && roadLocation.equals(EDGE_LOCATION) && buildRoadRequest.isFree()) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "moves/buildSettlement":
			BuildSettlementRequest buildSettlementRequest = (BuildSettlementRequest) commandParameters;
			x = buildSettlementRequest.getVertexLocation().getX();
			y = buildSettlementRequest.getVertexLocation().getY();
			VertexDirection vDirSettle = buildSettlementRequest.getVertexLocation().getDirection();
			VertexLocation settleVertex = new VertexLocation(new HexLocation(x,y), vDirSettle);
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && buildSettlementRequest.getPlayerIndex() == PLAYER_INDEX && settleVertex.equals(VERTEX_LOCATION) && buildSettlementRequest.isFree()) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "moves/buildCity":
			BuildCityRequest buildCityRequest = (BuildCityRequest) commandParameters;
			x = buildCityRequest.getCityLocation().getX();
			y = buildCityRequest.getCityLocation().getY();
			VertexDirection vDirCity = buildCityRequest.getCityLocation().getDirection();
			VertexLocation cityVertex = new VertexLocation(new HexLocation(x,y), vDirCity);
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && buildCityRequest.getPlayerIndex() == PLAYER_INDEX && cityVertex.equals(VERTEX_LOCATION)) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "moves/offerTrade":
			OfferTradeRequest offerTradeRequest = (OfferTradeRequest) commandParameters;
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && offerTradeRequest.getPlayerIndex() == PLAYER_INDEX && offerTradeRequest.getOffer().equals(RESOURCE_HAND) && offerTradeRequest.getReceiver() == ANOTHER_PLAYER_INDEX) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "moves/maritimeTrade":
			MaritimeTradeRequest maritimeRequest = (MaritimeTradeRequest) commandParameters;
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && maritimeRequest.getPlayerIndex() == PLAYER_INDEX && maritimeRequest.getInputResource().equals(RESOURCE_TYPE.name().toLowerCase()) && maritimeRequest.getInputResource().equals(RESOURCE_TYPE.name().toLowerCase()) && maritimeRequest.getRatio() == MARITIME_RATIO) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "moves/finishTurn":
			FinishTurnRequest finishRequest = (FinishTurnRequest) commandParameters;
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && finishRequest.getPlayerIndex() == PLAYER_INDEX) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "moves/buyDevCard":
			BuyDevCardRequest buyDevRequest = (BuyDevCardRequest) commandParameters;
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && buyDevRequest.getPlayerIndex() == PLAYER_INDEX) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "moves/Year_of_Plenty":
			YearOfPlentyDevRequest yearPlentyRequest = (YearOfPlentyDevRequest) commandParameters;
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && yearPlentyRequest.getPlayerIndex() == PLAYER_INDEX && yearPlentyRequest.getResource1().equals(RESOURCE_TYPE.name().toLowerCase()) && yearPlentyRequest.getResource2().equals(OTHER_RESOURCE_TYPE.name().toLowerCase())) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}
			break;
		case "moves/Road_Building":
			RoadBuildingDevRequest roadBuildRequest = (RoadBuildingDevRequest) commandParameters;
			x = roadBuildRequest.getSpot1().getX();
			y = roadBuildRequest.getSpot1().getY();
			EdgeDirection eDir1 = roadBuildRequest.getSpot1().getDirection();
			EdgeLocation spot1 = new EdgeLocation(new HexLocation(x,y), eDir1);
			x = roadBuildRequest.getSpot2().getX();
			y = roadBuildRequest.getSpot2().getY();
			EdgeDirection eDir2 = roadBuildRequest.getSpot2().getDirection();
			EdgeLocation spot2 = new EdgeLocation(new HexLocation(x,y), eDir2);
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && roadBuildRequest.getPlayerIndex() == PLAYER_INDEX && spot1.equals(EDGE_LOCATION) && spot2.equals(ANOTHER_EDGE)) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}			
			break;
		case "moves/Monopoly":
			MonopolyDevRequest monopolyRequest = (MonopolyDevRequest) commandParameters;
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && monopolyRequest.getPlayerIndex() == PLAYER_INDEX && monopolyRequest.getResource().equals(RESOURCE_TYPE.name().toLowerCase())) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}			
			break;
		case "moves/Soldier":
			SoldierDevRequest soldierRequest = (SoldierDevRequest) commandParameters;
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && soldierRequest.getPlayerIndex() == PLAYER_INDEX && soldierRequest.getVictimIndex() == ANOTHER_PLAYER_INDEX && soldierRequest.getLocation().equals(HEX_LOCATION)) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
			}
			else {
				result = new CommandResponse(null, 400, null, null);
			}			
			break;
		case "moves/Monument":
			MonumentDevRequest monumentRequest = (MonumentDevRequest) commandParameters;
			if(VALID_JOINED_GAME_COOKIE.equals(headers.get(0).getValue()) && monumentRequest.getPlayerIndex() == PLAYER_INDEX) {
				result =  new CommandResponse(null, 200, getServerModel(), null);
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
