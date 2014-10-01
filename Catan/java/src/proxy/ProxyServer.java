package proxy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;

import client.model.Log;
import shared.ServerMethodRequests.*;
import shared.ServerMethodResponses.*;
import shared.definitions.*;
import shared.locations.*;

public class ProxyServer implements IServer{
	private ICommunicator clientCommunicator;
	private ITranslator cookieTranslator;
	private String cookieEncoding;
	private String cookie;
	
	public ProxyServer(ICommunicator clientCommunicator, ITranslator cookieTranslator, String cookieEncoding) {
		this.clientCommunicator = clientCommunicator;
		this.cookieTranslator = cookieTranslator;
		this.cookieEncoding = cookieEncoding;
	}

	@Override
	public ILoginUserResponse loginUser(String username, String password) throws UnsupportedEncodingException {
		UserRequest loginRequest = new UserRequest(username, password);
		ICommandResponse loginResponse = this.clientCommunicator.executeCommand(RequestType.POST, new ArrayList<Pair<String,String>>(), "user/login", loginRequest, null);
		cookie = loginResponse.getResponseHeaders().get("Set-cookie").get(0);
		cookie = cookie.replaceFirst("catan.user=", "");
		cookie = cookie.substring(0, cookie.lastIndexOf(";Path=/;"));
		String cookieJSON = URLDecoder.decode(cookie, this.cookieEncoding);
		PlayerCookie playerCookie = (PlayerCookie) this.cookieTranslator.translateFrom(cookieJSON, PlayerCookie.class);
		return new LoginUserResponse(loginResponse.getResponseCode() == 200, loginResponse.getResponseMessage(), playerCookie.getName(), cookie, playerCookie.getPlayerID());
	}

	@Override
	public IRegisterUserResponse registerUser(String username, String password) throws UnsupportedEncodingException {
		UserRequest loginRequest = new UserRequest(username, password);
		ICommandResponse loginResponse = this.clientCommunicator.executeCommand(RequestType.POST, new ArrayList<Pair<String,String>>(),"user/login", loginRequest, null);
		cookie = loginResponse.getResponseHeaders().get("Set-cookie").get(0);
		cookie = cookie.replaceFirst("catan.user=", "");
		cookie = cookie.substring(0, cookie.lastIndexOf(";Path=/;"));
		String cookieJSON = URLDecoder.decode(cookie, this.cookieEncoding);
		PlayerCookie playerCookie = (PlayerCookie) this.cookieTranslator.translateFrom(cookieJSON, PlayerCookie.class);
		return new RegisterUserResponse(loginResponse.getResponseCode() == 200, loginResponse.getResponseMessage(), playerCookie.getName(), cookie, playerCookie.getPlayerID());
	}
	
	@Override
	public IListGamesResponse listGames() {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse listGamesResponse = this.clientCommunicator.executeCommand(RequestType.GET, requestHeaders, "/games/list", null, GameDescription[].class);
		return new ListGamesResponse(listGamesResponse.getResponseCode() == 200, Arrays.asList((GameDescription[])listGamesResponse.getResponseObject()));
	}

	@Override
	public ICreateGameResponse createGame(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name) {
		CreateGameRequest createGameRequest = new CreateGameRequest(randomTiles, randomNumbers, randomPorts, name);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse createGameResponse = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/games/create", createGameRequest, GameDescription.class);
		return new CreateGameResponse(createGameResponse.getResponseCode() == 200, (GameDescription)createGameResponse.getResponseObject());
	}
	
	@Override
	public IJoinGameResponse joinGame(CatanColor color, int gameID) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		JoinGameRequest joinGameRequest = new JoinGameRequest(gameID, color.name().toString());
		ICommandResponse joinGameResponse = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/games/join", joinGameRequest, null);
		String gameCookieExtension = joinGameResponse.getResponseHeaders().get("Set-cookie").get(0);
		gameCookieExtension = gameCookieExtension.replaceFirst("catan.game=", "");
		gameCookieExtension = gameCookieExtension.substring(0, gameCookieExtension.lastIndexOf(";Path=/;"));
		cookie = cookie + ";" + gameCookieExtension;
		return new JoinGameResponse(joinGameResponse.getResponseCode() == 200);
	}
	
	@Override
	public SaveGameResponse saveGame(int gameID, String name) {
		SaveGameRequest request = new SaveGameRequest(gameID, name);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/games/save", request, null);
		return new SaveGameResponse(response.getResponseCode() == 200);
	}
	
	public LoadGameResponse loadGame(String name) {
		LoadGameRequest request = new LoadGameRequest(name);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/games/load", request, null);
		return new LoadGameResponse(response.getResponseCode() == 200);
	}

	@Override
	public GetGameModelResponse getGameModel(int version) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse getGameModelResponse = this.clientCommunicator.executeCommand(RequestType.GET, requestHeaders, "game/model?version=" + Integer.toString(version), null, GameModel.class);
		boolean needToUpdate = true;
		if(getGameModelResponse.getResponseObject() == null) {
			needToUpdate = false;
		}
		return new GetGameModelResponse(getGameModelResponse.getResponseCode() == 200, (GameModel)getGameModelResponse.getResponseObject(), needToUpdate);
	}

	@Override
	public ResetGameResponse resetGame() {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse resetGameResponse = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "game/reset", null, GameModel.class);
		return new ResetGameResponse(resetGameResponse.getResponseCode() == 200, (GameModel) resetGameResponse.getResponseObject());
	}

	@Override
	public GetGameCommandsResponse getGameCommands() {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.GET, requestHeaders, "game/commands", null, Log.class);
		return new GetGameCommandsResponse(response.getResponseCode() == 200, (Log)response.getResponseObject());
	}

	@Override
	public PostGameCommandsResponse postGameCommands(Log commands) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "game/commands", commands, Log.class);
		PostGameCommandsResponse result;
		if(response.getResponseCode() == 200) {
			result = new PostGameCommandsResponse(true, (GameModel)response.getResponseObject(), null);
		}
		else {
			result = new PostGameCommandsResponse(false, null, response.getResponseMessage());
		}
		return result;
	}

	@Override
	public ListAIResponse listAI() {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.GET, requestHeaders, "/game/listAI", null, String[].class);
		return new ListAIResponse(response.getResponseCode() == 200, Arrays.asList((String[])response.getResponseObject()));
	}

	@Override
	public AddAIResponse addAI(String aiToAdd) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/game/addAI", aiToAdd, null);
		return new AddAIResponse(response.getResponseCode() == 200);
	}

	@Override
	public ChangeLogLevelResponse changeLogLevel(ServerLogLevel logLevel) {
		ChangeLogLevelRequest request = new ChangeLogLevelRequest(logLevel.name());
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/util/changeLogLevel", request, null);
		return new ChangeLogLevelResponse(response.getResponseCode() == 200);
	}

	@Override
	public MoveResponse sendChat(int playerIndex, String message) {
		SendChatRequest request = new SendChatRequest(playerIndex, message);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/games/sendChat", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse acceptTrade(boolean willAccept, int playerIndex) {
		AcceptTradeRequest request = new AcceptTradeRequest(playerIndex, willAccept);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/acceptTrade", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse discardCards(ResourceHand resourceHand, int playerIndex) {
		DiscardCardsRequest request = new DiscardCardsRequest(resourceHand, playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/discardCards", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse rollNumber(int number, int playerIndex) {
		RollNumberRequest request = new RollNumberRequest(number, playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/rollNumber", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse buildRoad(int playerIndex, EdgeLocation roadLocation, boolean free) {
		BuildRoadRequest request = new BuildRoadRequest(playerIndex, roadLocation, free);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/buildRoad", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse buildSettlement(int playerIndex, VertexLocation vertexLocation, boolean free) {
		BuildSettlementRequest request = new BuildSettlementRequest(playerIndex, vertexLocation, free);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/buildSettlement", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse buildCity(int playerIndex, VertexLocation vertexLocation) {
		BuildCityRequest request = new BuildCityRequest(playerIndex, vertexLocation);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/buildSettlement", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse offerTrade(int playerIndex, ResourceHand offer, int receiver) {
		OfferTradeRequest request = new OfferTradeRequest(playerIndex, offer, receiver);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/offerTrade", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse maritimeTrade(int playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource) {
		MaritimeTradeRequest request = new MaritimeTradeRequest(playerIndex, ratio, inputResource.name(), outputResource.name());
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/offerTrade", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse finishTurn(int playerIndex) {
		FinishTurnRequest request = new FinishTurnRequest(playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/offerTrade", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse buyDevCard(int playerIndex) {
		BuyDevCardRequest request = new BuyDevCardRequest(playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/offerTrade", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse playYearOfPlentyCard(int playerIndex, ResourceType resource1,
			ResourceType resource2) {
		YearOfPlentyDevRequest request = new YearOfPlentyDevRequest(playerIndex, resource1.name(), resource2.name());
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/offerTrade", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse playRoadBuildingCard(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {
		RoadBuildingDevRequest request = new RoadBuildingDevRequest(playerIndex, spot1, spot2);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/offerTrade", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse playMonopolyCard(int playerIndex, ResourceType resource) {
		MonopolyDevRequest request = new MonopolyDevRequest(playerIndex, resource.name());
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/offerTrade", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse playSoldierCard(int playerIndex, PlayerIndex victimIndex, HexLocation location) {
		SoldierDevRequest request = new SoldierDevRequest(playerIndex, victimIndex, location);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/offerTrade", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

	@Override
	public MoveResponse playMonumentCard(int playerIndex) {
		MonumentDevRequest request = new MonumentDevRequest(playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/offerTrade", request, GameModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (GameModel) response.getResponseObject());
	}

}
