package proxy;

import java.awt.List;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;

import shared.ServerMethodRequests.*;
import shared.ServerMethodResponses.*;
import shared.definitions.*;
import shared.locations.*;

public class ProxyServer implements IServer{
	private ICommunicator clientCommunicator;
	private ITranslator cookieTranslator;
	private String cookieEncoding;
	
	public ProxyServer(ICommunicator clientCommunicator, ITranslator cookieTranslator, String cookieEncoding) {
		this.clientCommunicator = clientCommunicator;
		this.cookieTranslator = cookieTranslator;
		this.cookieEncoding = cookieEncoding;
	}

	@Override
	public ILoginUserResponse loginUser(String username, String password) throws UnsupportedEncodingException {
		UserRequest loginRequest = new UserRequest(username, password);
		ICommandResponse loginResponse = this.clientCommunicator.executeCommand(RequestType.POST, new ArrayList<Pair<String,String>>(), "user/login", loginRequest, null);
		String cookie = loginResponse.getResponseHeaders().get("Set-cookie").get(0);
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
		String cookie = loginResponse.getResponseHeaders().get("Set-cookie").get(0);
		cookie = cookie.replaceFirst("catan.user=", "");
		cookie = cookie.substring(0, cookie.lastIndexOf(";Path=/;"));
		String cookieJSON = URLDecoder.decode(cookie, this.cookieEncoding);
		PlayerCookie playerCookie = (PlayerCookie) this.cookieTranslator.translateFrom(cookieJSON, PlayerCookie.class);
		return new RegisterUserResponse(loginResponse.getResponseCode() == 200, loginResponse.getResponseMessage(), playerCookie.getName(), cookie, playerCookie.getPlayerID());
	}
	
	@Override
	public IListGamesResponse listGames() {
		Class<?> arrayGameDescriptionType = GameDescription[].class;
		ICommandResponse listGamesResponse = this.clientCommunicator.executeCommand(RequestType.GET, new ArrayList<Pair<String,String>>(), "/games/list", null, arrayGameDescriptionType);
		return new ListGamesResponse(listGamesResponse.getResponseCode() == 200, Arrays.asList((GameDescription[])listGamesResponse.getResponseObject()));
	}

	@Override
	public ICreateGameResponse createGame(String name) {
		CreateGameRequest createGameRequest = new CreateGameRequest(name);
		ICommandResponse createGameResponse = this.clientCommunicator.executeCommand(RequestType.POST, new ArrayList<Pair<String,String>>(), "/games/create", createGameRequest, GameDescription.class);
		return new CreateGameResponse(createGameResponse.getResponseCode() == 200, (GameDescription)createGameResponse.getResponseObject());
	}

	@Override
	public IJoinGameResponse joinGame(CatanColor color, int gameID, String cookie) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResetGameResponse resetGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetGameCommandsResponse getGameCommands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostGameCommandsResponse postGameCommands(Log commands) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListAIResponse listAI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddAIResponse addAI(String aiToAdd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChangeLogLevelResponse changeLogLevel(ServerLogLevel logLevel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendChat(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void discardCards(ResourceHand resourceHand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollNumber(DiceRoll number) {
		// TODO Auto-generated method stub
		
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
