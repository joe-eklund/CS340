package server.serverCommunicator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import proxy.ITranslator;
import server.moves.IMovesFacade;
import shared.ServerMethodRequests.YearOfPlentyDevRequest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for YearOfPlenty command
 * @author Chad
 *
 */
public class YearOfPlentyHandler implements HttpHandler {

	private ITranslator translator;
	private IMovesFacade movesFacade;

	public YearOfPlentyHandler(ITranslator translator, IMovesFacade movesFacade) {
		this.translator = translator;
		this.movesFacade = movesFacade;
	}

	/**
	 * Handles Year of Plenty Card. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		YearOfPlentyDevRequest request = (YearOfPlentyDevRequest) translator.translateFrom(exchange.getRequestBody().toString(), YearOfPlentyDevRequest.class);
		exchange.getRequestBody().close();
		int userID = movesFacade.yearOfPlenty(request);
		if(userID > -1) {
			List<String> cookies = new ArrayList<String>();
			exchange.getResponseHeaders().put("Set-cookie", cookies);
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		}
		else {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
		}
		exchange.getResponseBody().close();
		
	}

}
