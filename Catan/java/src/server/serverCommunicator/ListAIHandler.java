package server.serverCommunicator;

import java.io.IOException;

import proxy.ITranslator;
import server.game.IGameFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ListAIHandler implements HttpHandler {

	public ListAIHandler(ITranslator translator, IGameFacade gameFacade) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
