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
	private static ProxyServer singleton;
	private ICommunicator clientCommunicator;
	private ITranslator cookieTranslator;
	private String cookieEncoding;
	
	/**
	 * **Private Instance Constructor**
	 * 
	 * @pre
	 * 	none
	 * 
	 * @post
	 * 	initialized ProxyServer singleton instance
	 * 
	 * @param clientCommunicator
	 * @param cookieTranslator
	 * @param cookieEncoding
	 */
	private ProxyServer(ICommunicator clientCommunicator, ITranslator cookieTranslator, String cookieEncoding) {
		this.clientCommunicator = clientCommunicator;
		this.cookieTranslator = cookieTranslator;
		this.cookieEncoding = cookieEncoding;
	}
	
	/**
	 * Configures singleton instance of ProxyServer
	 * 
	 * @pre
	 *   none
	 *   
	 * @post
	 * 	 if no singleton instance has been created yet
	 * 		1) creates singleton instance of ProxyServer
	 * 	 Configures singleton according to provided parameters
	 * 		singleton.clientCommunicator = clientCommunicator;
	 *		singleton.cookieTranslator = cookieTranslator;
	 *		singleton.cookieEncoding = cookieEncoding;
	 * 
	 * @param clientCommunicator ::= client communicator object containing server connection/communication abilities including a translator
	 * @param cookieTranslator ::= translator for converting cookie headers into java PlayerCookie object
	 * @param cookieEncoding ::= encoding format of response cookie (should normally be "UTF-8"
	 */
	public static void setSingleton(ICommunicator clientCommunicator, ITranslator cookieTranslator, String cookieEncoding) {
		if(singleton == null) {
			singleton = new ProxyServer(clientCommunicator, cookieTranslator, cookieEncoding);
		}
		else {
			singleton.clientCommunicator = clientCommunicator;
			singleton.cookieTranslator = cookieTranslator;
			singleton.cookieEncoding = cookieEncoding;
		}
	}
	
	public static ProxyServer getSingleton() {
		return singleton;
	}

	@Override
	public LoginUserResponse loginUser(String username, String password) {
		UserRequest loginRequest = new UserRequest(username, password);
		ICommandResponse loginResponse = this.clientCommunicator.executeCommand(RequestType.POST, new ArrayList<Pair<String,String>>(), "user/login", loginRequest, null);
		String cookie = loginResponse.getResponseHeaders().get("Set-cookie").get(0);
		String subCookie = cookie.replaceFirst("catan.user=", "");
		subCookie = subCookie.substring(0, subCookie.lastIndexOf(";Path=/;"));
		PlayerCookie playerCookie = null;
		try {
			String cookieJSON = URLDecoder.decode(subCookie, this.cookieEncoding);
			playerCookie = (PlayerCookie) this.cookieTranslator.translateFrom(cookieJSON, PlayerCookie.class);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new LoginUserResponse(loginResponse.getResponseCode() == 200, loginResponse.getResponseMessage(), playerCookie.getName(), cookie, playerCookie.getPlayerID());
	}

	@Override
	public RegisterUserResponse registerUser(String username, String password) {
		UserRequest loginRequest = new UserRequest(username, password);
		ICommandResponse loginResponse = this.clientCommunicator.executeCommand(RequestType.POST, new ArrayList<Pair<String,String>>(),"user/login", loginRequest, null);
		String cookie = loginResponse.getResponseHeaders().get("Set-cookie").get(0);
		String subCookie = cookie.replaceFirst("catan.user=", "");
		subCookie = subCookie.substring(0, subCookie.lastIndexOf(";Path=/;"));
		PlayerCookie playerCookie = null;
		try {
			String cookieJSON = URLDecoder.decode(subCookie, this.cookieEncoding);
			playerCookie = (PlayerCookie) this.cookieTranslator.translateFrom(cookieJSON, PlayerCookie.class);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		return new RegisterUserResponse(loginResponse.getResponseCode() == 200, loginResponse.getResponseMessage(), playerCookie.getName(), cookie, playerCookie.getPlayerID());
	}
	
	@Override
	public ListGamesResponse listGames(String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse listGamesResponse = this.clientCommunicator.executeCommand(RequestType.GET, requestHeaders, "/games/list", null, GameDescription[].class);
		return new ListGamesResponse(listGamesResponse.getResponseCode() == 200, Arrays.asList((GameDescription[])listGamesResponse.getResponseObject()));
	}

	@Override
	public CreateGameResponse createGame(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name, String cookie) {
		CreateGameRequest createGameRequest = new CreateGameRequest(randomTiles, randomNumbers, randomPorts, name);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse createGameResponse = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/games/create", createGameRequest, GameDescription.class);
		return new CreateGameResponse(createGameResponse.getResponseCode() == 200, (GameDescription)createGameResponse.getResponseObject());
	}
	
	@Override
	public JoinGameResponse joinGame(CatanColor color, int gameID, String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		JoinGameRequest joinGameRequest = new JoinGameRequest(gameID, color.name().toString());
		ICommandResponse joinGameResponse = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/games/join", joinGameRequest, null);
		String gameCookieExtension = joinGameResponse.getResponseHeaders().get("Set-cookie").get(0);
		gameCookieExtension = gameCookieExtension.replaceFirst("catan.game=", "");
		gameCookieExtension = gameCookieExtension.substring(0, gameCookieExtension.lastIndexOf(";Path=/;"));
		cookie = cookie + ";" + gameCookieExtension;
		return new JoinGameResponse(joinGameResponse.getResponseCode() == 200, cookie);
	}
	
	@Override
	public SaveGameResponse saveGame(int gameID, String name, String cookie) {
		SaveGameRequest request = new SaveGameRequest(gameID, name);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/games/save", request, null);
		return new SaveGameResponse(response.getResponseCode() == 200);
	}
	
	public LoadGameResponse loadGame(String name, String cookie) {
		LoadGameRequest request = new LoadGameRequest(name);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/games/load", request, null);
		return new LoadGameResponse(response.getResponseCode() == 200);
	}

	@Override
	public GetGameModelResponse getGameModel(int version, String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse getServerModelResponse = this.clientCommunicator.executeCommand(RequestType.GET, requestHeaders, "game/model?version=" + Integer.toString(version), null, ServerModel.class);
		boolean needToUpdate = true;
		if(getServerModelResponse.getResponseObject() == null) {
			needToUpdate = false;
		}
		return new GetGameModelResponse(getServerModelResponse.getResponseCode() == 200, (ServerModel)getServerModelResponse.getResponseObject(), needToUpdate);
	}

	@Override
	public ResetGameResponse resetGame(String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse resetGameResponse = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "game/reset", null, ServerModel.class);
		return new ResetGameResponse(resetGameResponse.getResponseCode() == 200, (ServerModel) resetGameResponse.getResponseObject());
	}

	@Override
	public GetGameCommandsResponse getGameCommands(String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.GET, requestHeaders, "game/commands", null, Log.class);
		return new GetGameCommandsResponse(response.getResponseCode() == 200, (Log)response.getResponseObject());
	}

	@Override
	public PostGameCommandsResponse postGameCommands(Log commands, String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "game/commands", commands, Log.class);
		PostGameCommandsResponse result;
		if(response.getResponseCode() == 200) {
			result = new PostGameCommandsResponse(true, (ServerModel)response.getResponseObject(), null);
		}
		else {
			result = new PostGameCommandsResponse(false, null, response.getResponseMessage());
		}
		return result;
	}

	@Override
	public ListAIResponse listAI(String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.GET, requestHeaders, "/game/listAI", null, String[].class);
		return new ListAIResponse(response.getResponseCode() == 200, Arrays.asList((String[])response.getResponseObject()));
	}

	@Override
	public AddAIResponse addAI(String aiToAdd, String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/game/addAI", aiToAdd, null);
		return new AddAIResponse(response.getResponseCode() == 200);
	}

	@Override
	public ChangeLogLevelResponse changeLogLevel(ServerLogLevel logLevel, String cookie) {
		ChangeLogLevelRequest request = new ChangeLogLevelRequest(logLevel.name());
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/util/changeLogLevel", request, null);
		return new ChangeLogLevelResponse(response.getResponseCode() == 200);
	}

	@Override
	public MoveResponse sendChat(int playerIndex, String message, String cookie) {
		SendChatRequest request = new SendChatRequest(playerIndex, message);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/games/sendChat", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse acceptTrade(int playerIndex, boolean willAccept, String cookie) {
		AcceptTradeRequest request = new AcceptTradeRequest(playerIndex, willAccept);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/acceptTrade", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse discardCards(int playerIndex, ResourceHand resourceHand, String cookie) {
		DiscardCardsRequest request = new DiscardCardsRequest(resourceHand, playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/discardCards", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse rollNumber(int playerIndex, int number, String cookie) {
		RollNumberRequest request = new RollNumberRequest(number, playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/rollNumber", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse buildRoad(int playerIndex, EdgeLocation roadLocation, boolean free, String cookie) {
		BuildRoadRequest request = new BuildRoadRequest(playerIndex, roadLocation, free);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/buildRoad", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse buildSettlement(int playerIndex, VertexLocation vertexLocation, boolean free, String cookie) {
		BuildSettlementRequest request = new BuildSettlementRequest(playerIndex, vertexLocation, free);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/buildSettlement", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse buildCity(int playerIndex, VertexLocation vertexLocation, String cookie) {
		BuildCityRequest request = new BuildCityRequest(playerIndex, vertexLocation);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/buildSettlement", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse offerTrade(int playerIndex, ResourceHand offer, int receiver, String cookie) {
		OfferTradeRequest request = new OfferTradeRequest(playerIndex, offer, receiver);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/offerTrade", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse maritimeTrade(int playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource, String cookie) {
		MaritimeTradeRequest request = new MaritimeTradeRequest(playerIndex, ratio, inputResource.name(), outputResource.name());
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/maritimeTrade", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse finishTurn(int playerIndex, String cookie) {
		FinishTurnRequest request = new FinishTurnRequest(playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/finishTurn", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse buyDevCard(int playerIndex, String cookie) {
		BuyDevCardRequest request = new BuyDevCardRequest(playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/buyDevCard", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse playYearOfPlentyCard(int playerIndex, ResourceType resource1,
			ResourceType resource2, String cookie) {
		YearOfPlentyDevRequest request = new YearOfPlentyDevRequest(playerIndex, resource1.name(), resource2.name());
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/Year_of_Plenty", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse playRoadBuildingCard(int playerIndex, EdgeLocation spot1, EdgeLocation spot2, String cookie) {
		RoadBuildingDevRequest request = new RoadBuildingDevRequest(playerIndex, spot1, spot2);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/Road_Building", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse playMonopolyCard(int playerIndex, ResourceType resource, String cookie) {
		MonopolyDevRequest request = new MonopolyDevRequest(playerIndex, resource.name());
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/offerTrade", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse playSoldierCard(int playerIndex, PlayerIndex victimIndex, HexLocation location, String cookie) {
		SoldierDevRequest request = new SoldierDevRequest(playerIndex, victimIndex, location);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/Monopoly", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

	@Override
	public MoveResponse playMonumentCard(int playerIndex, String cookie) {
		MonumentDevRequest request = new MonumentDevRequest(playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>("Cookie", cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "/moves/Monument", request, ServerModel.class);
		return new MoveResponse(response.getResponseCode() == 200, (ServerModel) response.getResponseObject());
	}

}
