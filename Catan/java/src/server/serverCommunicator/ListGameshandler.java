package server.serverCommunicator;

import java.io.IOException;

import proxy.ITranslator;
import server.games.IGamesFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ListGameshandler implements HttpHandler {

	public ListGameshandler(IGamesFacade gamesFacade) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
