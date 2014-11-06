package server.main;

import proxy.TranslatorJSON;
import server.game.GameFacade;
import server.games.GamesFacade;
import server.serverCommunicator.ServerCommunicator;
import server.moves.MovesFacade;
import server.users.UsersFacade;
import server.util.UtilFacade;

public class Catan {
	private final static int MAX_PORT = 65535;
	private final static int MIN_PORT = 0;

	public static void main(String[] args) {
    	int portNumber = -1;
    	if (args.length == 1) {
			try {
				portNumber = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("Invalid input; port must be integer where port >= " + MIN_PORT + " && port <= " + MAX_PORT);
			}
			
			if(portNumber >= MIN_PORT && portNumber <= MAX_PORT) {
				TranslatorJSON translator = new TranslatorJSON();
				UsersFacade usersFacade = new UsersFacade();
				GamesFacade gamesFacade = new GamesFacade();
				GameFacade gameFacade = new GameFacade();
				MovesFacade movesFacade = new MovesFacade();
				UtilFacade utilFacade = new UtilFacade();
				ServerCommunicator serverCommunicator = new ServerCommunicator(portNumber, translator, usersFacade, gamesFacade, gameFacade, movesFacade, utilFacade);
				serverCommunicator.run();
				System.out.println("Catan server listening on port: " + portNumber);
			}
			else {
				System.err.println("Invalid port number; port must be >= " + MIN_PORT + " && port must be <= " + MAX_PORT);
			}
    	}	
    	else {
			System.err.println("USAGE: java Catan <port>");
		}
	}
	
}
