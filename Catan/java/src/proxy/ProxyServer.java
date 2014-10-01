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
	public ICreateGameResponse createGame(String name) {
		CreateGameRequest createGameRequest = new CreateGameRequest(name);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse createGameResponse = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/games/create", createGameRequest, GameDescription.class);
		return new CreateGameResponse(createGameResponse.getResponseCode() == 200, (GameDescription)createGameResponse.getResponseObject());
	}

	@Override
	public IJoinGameResponse joinGame(CatanColor color, int gameID) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		JoinGameRequest joinGameRequest = new JoinGameRequest(gameID, color);
		ICommandResponse joinGameResponse = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/games/join", joinGameRequest, null);
		String gameCookieExtension = joinGameResponse.getResponseHeaders().get("Set-cookie").get(0);
		gameCookieExtension = gameCookieExtension.replaceFirst("catan.game=", "");
		gameCookieExtension = gameCookieExtension.substring(0, gameCookieExtension.lastIndexOf(";Path=/;"));
		cookie = cookie + ";" + gameCookieExtension;
		return new JoinGameResponse(joinGameResponse.getResponseCode() == 200);
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
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/util/changeLogLevel", logLevel.toString(), null);
		return new ChangeLogLevelResponse(response.getResponseCode() == 200);
	}

	@Override
	public void sendChat(String message) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/games/sendChat", message, null);
	}

	@Override
	public void acceptTrade(boolean willAccept, int playerIndex) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		AcceptTradeRequest requestParams = new AcceptTradeRequest(willAccept, playerIndex);
		this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/acceptTrade", requestParams, null);
	}

	@Override
	public void discardCards(ResourceHand resourceHand, int playerIndex) {
		// TODO Auto-generated method stub
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		DiscardCardsRequest requestParams = new DiscardCardsRequest(resourceHand, playerIndex);
		this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/discardCards", requestParams, null);
	}

	@Override
	public void rollNumber(DiceRoll number, int playerIndex) {
		// TODO Auto-generated method stub
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		RollNumberRequest requestParams = new RollNumberRequest(number.getRollValue(), playerIndex);
		this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/rollNumber", requestParams, null);
	}

	@Override
	public void buildRoad(boolean free, EdgeLocation roadLocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildSettlement(boolean free, VertexLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildCity(VertexLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void offerTrade(ResourceHand offer, PlayerIndex receiver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void maritimeTrade(int ratio, ResourceType inputResource,
			ResourceType outputResource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buyDevCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resourceOne,
			ResourceType resourceTwo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playRoadBuildingCard(EdgeLocation spot1, EdgeLocation spot2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSoldierCard(HexLocation location, PlayerIndex victimIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playMonumentCard() {
		// TODO Auto-generated method stub
		
	}

}
