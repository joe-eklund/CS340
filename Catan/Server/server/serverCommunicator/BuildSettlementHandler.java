package server.serverCommunicator;

import java.io.IOException;

import proxy.ITranslator;
import server.moves.IMovesFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * handler for the buildsettlement command
 * @author Chad
 *
 */
public class BuildSettlementHandler implements HttpHandler {

	private ITranslator translator;
	private IMovesFacade movesFacade;

	public BuildSettlementHandler(ITranslator translator,
			IMovesFacade movesFacade) {
		this.translator = translator;
		this.movesFacade = movesFacade;
	}

	/**
	 * Handles Building a Settlement. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
