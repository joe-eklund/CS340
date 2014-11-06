package server.httpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import proxy.ITranslator;
import server.game.GameFacade;
import server.game.IGameFacade;
import server.games.GamesFacade;
import server.games.IGamesFacade;
import server.moves.IMovesFacade;
import server.moves.MovesFacade;
import server.users.IUsersFacade;
import server.util.IUtilFacade;
import server.util.UtilFacade;

import com.sun.net.httpserver.HttpServer;

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
    
    public void run() {
		try {
			server = HttpServer.create(new InetSocketAddress(portNumber), MAX_WAITING_CONNECTION);
		} catch (IOException e) {
			//could not initialize server -- already running
			return;
		}
        
        server.setExecutor(null);
        
        // handlers
        
        // user: operations for users (pre-login)
        server.createContext("/user/login", new LoginUserHandler(translator, usersFacade));
        server.createContext("/user/register", new RegisterUserHandler(translator, usersFacade));
        
        // games: operations for games list (pre-joining)
        server.createContext("/games/list", new ListGameshandler(gamesFacade));
        server.createContext("/games/create", new CreateGameHandler());
        server.createContext("/games/join", new JoinGameHandler());
        server.createContext("/games/save", new SaveGameHandler());
        server.createContext("/games/load", new LoadGameHandler());
        
        // game: current game setup operations (requires cookie)
        server.createContext("/game/model", new GetGameModelHandler());
        server.createContext("/game/reset", new ResetGameHandler());
        server.createContext("/game/commands", new GameCommandsHandler());
        server.createContext("/game/addAI", new AddAIHandler());
        server.createContext("/game/listAI", new ListAIHandler());
        
        // game: mid game actions--moves (requires cookie)
        server.createContext("/moves/sendChat", new SendChatHandler());
        server.createContext("/moves/rollNumber", new RollNumberHandler());
        server.createContext("/moves/robPlayer", new RobPlayerHandler());
        server.createContext("/moves/finishTurn", new FinishTurnHandler());
        server.createContext("/moves/buyDevCard", new BuyDevCardHandler());
        server.createContext("/moves/Year_Of_Plenty", new YearOfPlentHandler());
        server.createContext("/moves/Road_Building", new RoadBuildingHandler());
        server.createContext("/moves/Soldier", new SoldierHandler());
        server.createContext("/moves/Monopoly", new MonopolyHandler());
        server.createContext("/moves/Monument", new Monumenthandler());
        server.createContext("/moves/buildRoad", new BuildRoadHandler());
        server.createContext("/moves/buildSettlement", new BuildSettlementHandler());
        server.createContext("/moves/buildCity", new BuildCityHandler());
        server.createContext("/moves/offerTrade", new OfferTradeHandler());
        server.createContext("/moves/acceptTrade", new AcceptTradeHandler());
        server.createContext("/moves/maritimeTrade", new MaritimeTradeHandler());
        server.createContext("/moves/discardCards", new DiscardCardshandler());
        
        // util: change how the server runs
        server.createContext("/util/changeLogLevel", new ChangeLogLevelHandler());
        
        server.start();
	}
	
    public static void terminate() {
		server.stop(1);
	}

}
