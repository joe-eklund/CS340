package server.main;

import proxy.TranslatorJSON;
import server.httpServer.ServerCommunicator;
import server.users.UsersFacade;

public class Catan {

	public static void main(String[] args) {
    	int portNumber = -1;
    	if (args.length == 1) {
			try {
				portNumber = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				//invalid input
			}
		} 
    	if(portNumber >= 0 && portNumber <= 65535) {
    		TranslatorJSON translator = new TranslatorJSON();
    		UsersFacade usersFacade = new UsersFacade();
    		ServerCommunicator serverHandler = new ServerCommunicator(portNumber, translator, usersFacade);
    		serverHandler.run();
    	}
    	else {
			System.out.println("USAGE: java Server <port>");
		}
	}
	
}
