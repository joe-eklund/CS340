package server.serverCommunicator;

import java.io.IOException;

import proxy.ITranslator;
import server.game.IGameFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for ListAI command
 * @author Chad
 *
 */
public class ListAIHandler implements HttpHandler {

	private IGameFacade gameFacade;
	private ITranslator translator;

	public ListAIHandler(ITranslator translator, IGameFacade gameFacade) {
		this.translator = translator;
		this.gameFacade = gameFacade;
	}

	/**
	 * Handles List AI. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
