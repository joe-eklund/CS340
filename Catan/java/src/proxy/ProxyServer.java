package proxy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import shared.ServerMethodRequests.*;
import shared.ServerMethodResponses.*;
import shared.definitions.CatanColor;
import shared.definitions.DiceRoll;
import shared.definitions.Log;
import shared.definitions.PlayerIndex;
import shared.definitions.ResourceHand;
import shared.definitions.ResourceType;
import shared.definitions.ServerLogLevel;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

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
		LoginRequest loginRequest = new LoginRequest(username, password);
		ICommandResponse loginResponse = this.clientCommunicator.executeCommand("user/login", loginRequest, null);
		String cookie = loginResponse.getCookieResponseHeader();
		String cookieJSON = URLDecoder.decode(cookie, this.cookieEncoding);
		PlayerCookie playerCookie = (PlayerCookie) this.cookieTranslator.translateFrom(cookieJSON/*,PlayerCookie.class*/);
		return new LoginUserResponse(loginResponse.getResponseCode() == 200, loginResponse.getResponseMessage(), playerCookie.getName(), cookie, playerCookie.getPlayerID());
	}

	@Override
	public IRegisterUserResponse registerUser(String username, String password) throws UnsupportedEncodingException {
		RegisterRequest loginRequest = new RegisterRequest(username, password);
		ICommandResponse loginResponse = this.clientCommunicator.executeCommand("user/login", loginRequest, null);
		String cookie = loginResponse.getCookieResponseHeader();
		String cookieJSON = URLDecoder.decode(cookie, this.cookieEncoding);
		PlayerCookie playerCookie = (PlayerCookie) this.cookieTranslator.translateFrom(cookieJSON/*,PlayerCookie.class*/);
		return new RegisterUserResponse(loginResponse.getResponseCode() == 200, loginResponse.getResponseMessage(), playerCookie.getName(), cookie, playerCookie.getPlayerID());
	}
	
	@Override
	public ListGamesResponse listGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateGameResponse createGame(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JoinGameResponse joinGame(CatanColor color, int gameID) {
		// TODO Auto-generated method stub
		return null;
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
