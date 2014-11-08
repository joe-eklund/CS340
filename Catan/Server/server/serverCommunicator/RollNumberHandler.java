package server.serverCommunicator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import proxy.ITranslator;
import server.moves.IMovesFacade;
import shared.ServerMethodRequests.RollNumberRequest;
import shared.ServerMethodRequests.SendChatRequest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for RollNumber command
 * @author Chad
 *
 */
public class RollNumberHandler implements HttpHandler {

	private ITranslator translator;
	private IMovesFacade movesFacade;

	public RollNumberHandler(ITranslator translator, IMovesFacade movesFacade) {
		this.translator = translator;
		this.movesFacade = movesFacade;
	}

	/**
	 * Handles Roll Number. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		RollNumberRequest request = (RollNumberRequest) translator.translateFrom(exchange.getRequestBody().toString(), RollNumberRequest.class);
		exchange.getRequestBody().close();
		int userID = movesFacade.rollNumber(request);
		if(request.validatePreConditions() && userID > -1) {
			// create cookie for user
			List<String> cookies = new ArrayList<String>();
			// send success response
			exchange.getResponseHeaders().put("Set-cookie", cookies);
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		}
		else {
			//send failure response
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			
			//set "Content-Type: text/plain" header
		}
		exchange.getResponseBody().close();
	}

}
