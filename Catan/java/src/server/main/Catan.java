package server.main;

import proxy.TranslatorJSON;
import server.httpServer.ServerCommunicator;
import server.users.UsersFacade;

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
				ServerCommunicator serverHandler = new ServerCommunicator(portNumber, translator, usersFacade);
				serverHandler.run();
			}
			else {
				System.err.println("Invalid port number; port must be >= " + MIN_PORT + " && port must be <= " + MAX_PORT);
			}
    	}	
    	else {
			System.err.println("USAGE: java Server <port>");
		}
	}
	
}
