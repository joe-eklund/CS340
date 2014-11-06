package server.serverCommunicator;

import java.io.IOException;

import proxy.ITranslator;
import server.game.IGameFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for addAI command
 * @author Chad
 *
 */
public class AddAIHandler implements HttpHandler {

	public AddAIHandler(ITranslator translator, IGameFacade gameFacade) {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Handles adding an AI. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
