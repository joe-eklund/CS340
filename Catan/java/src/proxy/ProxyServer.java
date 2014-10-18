package proxy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.model.Log;
import client.model.Message;
import shared.ServerMethodRequests.*;
import shared.ServerMethodResponses.*;
import shared.definitions.*;
import shared.locations.*;

/**
 * A Server Proxy class that implements the Server Interface
 * 
 * <br><b>Domain</b>
 *  <ul>
 *    <li>singleton: singleton instance for proxy server; null until static setSingleton is used</li>
 *    <li>clientCommunicator: of interface ICommunicator and used to carry out http communcation with server</li>
 *    <li>cookieTranslator: of interface ITranslator and used to translate encoded cookie headers; must be set to correct type to translate cookie headers (should be TranslatorJSON for catan game)</li>
 *    <li>cookieEncoding: encoding used by cookie headers (should be "UTF-8" for catan game)</li>
 *
 */
public class ProxyServer implements IServer{
	private final String COOKIE_STR = "Cookie";
	private final String CONTENT_TYPE_STR = "Content-type";
	private final String APP_JSON_STR = "application/json";
	
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
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		UserRequest loginRequest = new UserRequest(username, password);
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "user/login", loginRequest, String.class);
		boolean successful = false;
		String cookie = "";
		String playerName = "";
		int playerID = -1;
		if(response.getResponseCode() == 200) {
			successful = true;
			cookie = response.getResponseHeaders().get("Set-cookie").get(0);
			int index = cookie.lastIndexOf(";Path=/");
			cookie = cookie.substring(0, index);
			String subCookie = cookie.replaceFirst("catan.user=", "");
			PlayerCookie playerCookie = null;
			try {
				String cookieJSON = URLDecoder.decode(subCookie, this.cookieEncoding);
				playerCookie = (PlayerCookie) this.cookieTranslator.translateFrom(cookieJSON, PlayerCookie.class);
				playerID = playerCookie.getPlayerID();
				playerName = playerCookie.getName();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return new LoginUserResponse(successful, response.getResponseMessage(), playerName, cookie, playerID);
	}

	@Override
	public RegisterUserResponse registerUser(String username, String password) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		UserRequest loginRequest = new UserRequest(username, password);
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders,"user/register", loginRequest, String.class);
		boolean successful = false;
		String cookie = "";
		String playerName = "";
		int playerID = -1;
		if(response.getResponseCode() == 200) {
			successful = true;
			cookie = response.getResponseHeaders().get("Set-cookie").get(0);
			int index = cookie.lastIndexOf(";Path=/");
			cookie = cookie.substring(0, index);
			String subCookie = cookie.replaceFirst("catan.user=", "");
			PlayerCookie playerCookie = null;
			try {
				String cookieJSON = URLDecoder.decode(subCookie, this.cookieEncoding);
				playerCookie = (PlayerCookie) this.cookieTranslator.translateFrom(cookieJSON, PlayerCookie.class);
				playerID = playerCookie.getPlayerID();
				playerName = playerCookie.getName();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}		
		return new RegisterUserResponse(successful, response.getResponseMessage(), playerName, cookie, playerID);
	}
	
	@Override
	public ListGamesResponse listGames(String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.GET, requestHeaders, "games/list", null, GameDescription[].class);
		boolean successful = response.getResponseCode() == 200;
		//return new ListGamesResponse(successful, successful ? Arrays.asList((GameDescription[])response.getResponseObject()) : null);
		List<GameDescription> games = null;
		if(successful) {
			games = new ArrayList<GameDescription>();
			GameDescription[] gamesArray = (GameDescription[])response.getResponseObject();
			for(GameDescription game : gamesArray) {
				games.add(new GameDescription(game));
			}
		}
		return new ListGamesResponse(successful, games);
	}

	@Override
	public CreateGameResponse createGame(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name, String cookie) {
		CreateGameRequest createGameRequest = new CreateGameRequest(randomTiles, randomNumbers, randomPorts, name);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "games/create", createGameRequest, GameDescription.class);
		boolean successful = response.getResponseCode() == 200;
		return new CreateGameResponse(successful, successful ? new GameDescription((GameDescription)response.getResponseObject()): null);
	}
	
	@Override
	public JoinGameResponse joinGame(CatanColor color, int gameID, String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		//requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		JoinGameRequest joinGameRequest = new JoinGameRequest(gameID, color.name().toString());
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "games/join", joinGameRequest, null);
		boolean successful = false;
		String resultCookie = "";
		if(response.getResponseCode() == 200) {
			successful = true;
			String gameCookieExtension = response.getResponseHeaders().get("Set-cookie").get(0);
			int index = gameCookieExtension.lastIndexOf(";Path=/;");
			gameCookieExtension = gameCookieExtension.substring(0, index);
			cookie = cookie + "; " + gameCookieExtension;
			resultCookie = cookie;
		}
		return new JoinGameResponse(successful, resultCookie);
	}
	
	@Override
	public SaveGameResponse saveGame(int gameID, String name, String cookie) {
		SaveGameRequest request = new SaveGameRequest(gameID, name);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "games/save", request, null);
		return new SaveGameResponse(response.getResponseCode() == 200);
	}
	
	public LoadGameResponse loadGame(String name, String cookie) {
		LoadGameRequest request = new LoadGameRequest(name);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "games/load", request, null);
		return new LoadGameResponse(response.getResponseCode() == 200);
	}

	@Override
	public GetGameModelResponse getGameModel(int version, String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.GET, requestHeaders, "game/model?version=" + Integer.toString(version), null, ServerModel.class);
		//ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.GET, requestHeaders, "game/model", null, ServerModel.class);
		boolean needToUpdate = true;
		if(response.getResponseObject() == null) {
			needToUpdate = false;
		}
		boolean successful = response.getResponseCode() == 200;
		return new GetGameModelResponse(successful, successful ? (ServerModel)response.getResponseObject() : null, needToUpdate);
	}

	@Override
	public ResetGameResponse resetGame(String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "game/reset", null, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new ResetGameResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);
	}

	@Override
	public GetGameCommandsResponse getGameCommands(String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.GET, requestHeaders, "game/commands", null, Message[].class);
		boolean successful = response.getResponseCode() == 200;
		return new GetGameCommandsResponse(successful, successful ? new Log(Arrays.asList((Message[])response.getResponseObject())) : null);
	}

	@Override
	public PostGameCommandsResponse postGameCommands(Log commands, String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "game/commands", commands, Log.class);
		boolean successful = response.getResponseCode() == 200;
		return new PostGameCommandsResponse(successful, successful ? (ServerModel)response.getResponseObject(): null, successful ?  null : response.getResponseMessage());
	}

	@Override
	public ListAIResponse listAI(String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.GET, requestHeaders, "game/listAI", null, String[].class);
		boolean successful = response.getResponseCode() == 200;
		List<String> list = successful ? Arrays.asList((String[])response.getResponseObject()) : null;
		return new ListAIResponse(response.getResponseCode() == 200, list);
	}

	@Override
	public AddAIResponse addAI(String aiToAdd, String cookie) {
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "game/addAI", aiToAdd, String.class);
		return new AddAIResponse(response.getResponseCode() == 200);
	}

	@Override
	public ChangeLogLevelResponse changeLogLevel(ServerLogLevel logLevel, String cookie) {
		ChangeLogLevelRequest request = new ChangeLogLevelRequest(logLevel.name());
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "util/changeLogLevel", request, String.class);
		return new ChangeLogLevelResponse(response.getResponseCode() == 200);
	}

	@Override
	public MoveResponse sendChat(int playerIndex, String message, String cookie) {
		SendChatRequest request = new SendChatRequest(playerIndex, message);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "games/sendChat", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);
	}

	@Override
	public MoveResponse acceptTrade(int playerIndex, boolean willAccept, String cookie) {
		AcceptTradeRequest request = new AcceptTradeRequest(playerIndex, willAccept);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/acceptTrade", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);
	}

	@Override
	public MoveResponse discardCards(int playerIndex, ResourceHand resourceHand, String cookie) {
		DiscardCardsRequest request = new DiscardCardsRequest(resourceHand, playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/discardCards", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);	}

	@Override
	public MoveResponse rollNumber(int playerIndex, int number, String cookie) {
		RollNumberRequest request = new RollNumberRequest(number, playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/rollNumber", request, ServerModel.class);
		boolean successful = (response.getResponseCode() == 200);
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);	}

	@Override
	public MoveResponse buildRoad(int playerIndex, EdgeLocation roadLocation, boolean free, String cookie) {
		BuildRoadRequest request = new BuildRoadRequest(playerIndex, roadLocation, free);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/buildRoad", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);	}

	@Override
	public MoveResponse buildSettlement(int playerIndex, VertexLocation vertexLocation, boolean free, String cookie) {
		BuildSettlementRequest request = new BuildSettlementRequest(playerIndex, vertexLocation, free);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/buildSettlement", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);	}

	@Override
	public MoveResponse buildCity(int playerIndex, VertexLocation vertexLocation, String cookie) {
		BuildCityRequest request = new BuildCityRequest(playerIndex, vertexLocation);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/buildCity", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);	}

	@Override
	public MoveResponse offerTrade(int playerIndex, ResourceHand offer, int receiver, String cookie) {
		OfferTradeRequest request = new OfferTradeRequest(playerIndex, offer, receiver);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/offerTrade", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);	}

	@Override
	public MoveResponse maritimeTrade(int playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource, String cookie) {
		MaritimeTradeRequest request = new MaritimeTradeRequest(playerIndex, ratio, inputResource.name(), outputResource.name());
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/maritimeTrade", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);	}

	@Override
	public MoveResponse finishTurn(int playerIndex, String cookie) {
		FinishTurnRequest request = new FinishTurnRequest(playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/finishTurn", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);	}

	@Override
	public MoveResponse buyDevCard(int playerIndex, String cookie) {
		BuyDevCardRequest request = new BuyDevCardRequest(playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/buyDevCard", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);	}

	@Override
	public MoveResponse playYearOfPlentyCard(int playerIndex, ResourceType resource1,
			ResourceType resource2, String cookie) {
		YearOfPlentyDevRequest request = new YearOfPlentyDevRequest(playerIndex, resource1.name(), resource2.name());
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/Year_of_Plenty", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);	}

	@Override
	public MoveResponse playRoadBuildingCard(int playerIndex, EdgeLocation spot1, EdgeLocation spot2, String cookie) {
		RoadBuildingDevRequest request = new RoadBuildingDevRequest(playerIndex, spot1, spot2);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/Road_Building", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);	}

	@Override
	public MoveResponse playMonopolyCard(int playerIndex, ResourceType resource, String cookie) {
		MonopolyDevRequest request = new MonopolyDevRequest(playerIndex, resource.name());
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/Monopoly", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);	}

	@Override
	public MoveResponse playSoldierCard(int playerIndex, int victimIndex, HexLocation location, String cookie) {
		SoldierDevRequest request = new SoldierDevRequest(playerIndex, victimIndex, location);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/Soldier", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);	}

	@Override
	public MoveResponse playMonumentCard(int playerIndex, String cookie) {
		MonumentDevRequest request = new MonumentDevRequest(playerIndex);
		ArrayList<Pair<String,String>> requestHeaders = new ArrayList<Pair<String,String>>();
		requestHeaders.add(new Pair<String,String>(COOKIE_STR, cookie));
		requestHeaders.add(new Pair<String,String>(CONTENT_TYPE_STR, APP_JSON_STR));
		ICommandResponse response = this.clientCommunicator.executeCommand(RequestType.POST, requestHeaders, "moves/Monument", request, ServerModel.class);
		boolean successful = response.getResponseCode() == 200;
		return new MoveResponse(successful, successful ? (ServerModel) response.getResponseObject() : null);	}

}
