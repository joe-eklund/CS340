package server.serverCommunicator;

import java.io.IOException;

import proxy.ITranslator;
import server.games.IGamesFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for ListGame command
 * @author Chad
 *
 */
public class ListGameshandler implements HttpHandler {

	public ListGameshandler(IGamesFacade gamesFacade) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles List Games. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
