package server.serverCommunicator;

import java.io.IOException;
import java.net.InetSocketAddress;

import proxy.ITranslator;
import server.game.IGameFacade;
import server.games.IGamesFacade;
import server.moves.IMovesFacade;
import server.users.IUsersFacade;
import server.util.IUtilFacade;

import com.sun.net.httpserver.HttpServer;

/**
 * This is the server control class. Can be used to start up the server, stop the server, etc. 
 * @author Chad
 */
public class ServerCommunicator {
	
	private static final int MAX_WAITING_CONNECTION = 10;
	private static HttpServer server;
	
	private int portNumber;
	
	private ITranslator translator;
	private IUsersFacade usersFacade;
	private IGamesFacade gamesFacade;
	private IGameFacade gameFacade;
	private IMovesFacade movesFacade;
	private IUtilFacade utilFacade;
    
    public ServerCommunicator(int portNumber, ITranslator translator, IUsersFacade usersFacade, IGamesFacade gamesFacade, IGameFacade gameFacade, IMovesFacade movesFacade, IUtilFacade utilFacade) {
    	this.portNumber = portNumber;
    	this.translator = translator;
    	this.usersFacade = usersFacade;
    	this.gamesFacade = gamesFacade;
    	this.gameFacade = gameFacade;
    	this.movesFacade = movesFacade;
    	this.utilFacade = utilFacade;
    }
    
    /**
     * Starts up the server and initializes all the handlers. 
     */
    public void run() {
		try {
			server = HttpServer.create(new InetSocketAddress(portNumber), MAX_WAITING_CONNECTION);
		} catch (IOException e) {
			//could not initialize server -- already running
			System.err.println("Error: Port " + portNumber + " is already in use!");
			return;
		}
        
        server.setExecutor(null);
        
        // 
        // create handlers
        //
        
        // user: operations for users (pre-login)
        server.createContext("/user/login", new LoginUserHandler(translator, usersFacade));
        server.createContext("/user/register", new RegisterUserHandler(translator, usersFacade));
        
        // games: operations for games list (pre-joining)
        server.createContext("/games/list", new ListGamesHandler(translator, gamesFacade));
        server.createContext("/games/create", new CreateGameHandler(translator, gamesFacade));
        server.createContext("/games/join", new JoinGameHandler(translator, gamesFacade));
        server.createContext("/games/save", new SaveGameHandler(translator, gamesFacade));
        server.createContext("/games/load", new LoadGameHandler(translator, gamesFacade));
        
        // game: current game setup operations (requires cookie)
//        server.createContext("/game/model?version=[0-9]*", new GetGameModelHandler(translator, gameFacade));
        server.createContext("/game/model", new GetGameModelHandler(translator, gameFacade));
        server.createContext("/game/reset", new ResetGameHandler(translator, gameFacade));
        server.createContext("/game/commands", new GameCommandsHandler(translator, gameFacade));
        server.createContext("/game/addAI", new AddAIHandler(translator, gameFacade));
        server.createContext("/game/listAI", new ListAIHandler(translator, gameFacade));
        
        // game: mid game actions--moves (requires cookie)
        server.createContext("/moves/sendChat", new SendChatHandler(translator, movesFacade));
        server.createContext("/moves/rollNumber", new RollNumberHandler(translator, movesFacade));
        server.createContext("/moves/robPlayer", new RobPlayerHandler(translator, movesFacade));
        server.createContext("/moves/finishTurn", new FinishTurnHandler(translator, movesFacade));
        server.createContext("/moves/buyDevCard", new BuyDevCardHandler(translator, movesFacade));
        server.createContext("/moves/Year_Of_Plenty", new YearOfPlentyHandler(translator, movesFacade));
        server.createContext("/moves/Road_Building", new RoadBuildingHandler(translator, movesFacade));
        server.createContext("/moves/Soldier", new SoldierHandler(translator, movesFacade));
        server.createContext("/moves/Monopoly", new MonopolyHandler(translator, movesFacade));
        server.createContext("/moves/Monument", new MonumentHandler(translator, movesFacade));
        server.createContext("/moves/buildRoad", new BuildRoadHandler(translator, movesFacade));
        server.createContext("/moves/buildSettlement", new BuildSettlementHandler(translator, movesFacade));
        server.createContext("/moves/buildCity", new BuildCityHandler(translator, movesFacade));
        server.createContext("/moves/offerTrade", new OfferTradeHandler(translator, movesFacade));
        server.createContext("/moves/acceptTrade", new AcceptTradeHandler(translator, movesFacade));
        server.createContext("/moves/maritimeTrade", new MaritimeTradeHandler(translator, movesFacade));
        server.createContext("/moves/discardCards", new DiscardCardsHandler(translator, movesFacade));
        
        // util: change how the server runs
        server.createContext("/util/changeLogLevel", new ChangeLogLevelHandler(translator, utilFacade));
        
        //swagger?
        server.createContext("/docs/api/data", new Handlers.JSONAppender(""));
        server.createContext("/docs/api/view", new Handlers.BasicFile(""));
        server.createContext("/", new HomeHandler());
        
        server.start();
	}
	
    /**
     * Used to stop the server.
     */
    public static void terminate() {
		server.stop(1);
	}

}
