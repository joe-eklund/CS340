package server.serverCommunicator;

import java.io.IOException;

import proxy.ITranslator;
import server.moves.IMovesFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for OfferTrade command
 * @author Chad
 *
 */
public class OfferTradeHandler implements HttpHandler {

	private ITranslator translator;
	private IMovesFacade movesFacade;

	public OfferTradeHandler(ITranslator translator, IMovesFacade movesFacade) {
		this.translator = translator;
		this.movesFacade = movesFacade;
	}

	/**
	 * Handles Offer Trade. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}