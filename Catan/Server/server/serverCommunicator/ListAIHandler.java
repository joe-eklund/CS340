package server.serverCommunicator;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import proxy.ITranslator;
import server.game.IGameFacade;
import shared.ServerMethodRequests.UserRequest;

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
		ArrayList<String> response = new ArrayList<String>();
        exchange.sendResponseHeaders(200, response.size());
        OutputStream os = exchange.getResponseBody();
        //os.write(response.toArray());
        os.close();
		/*UserRequest request = (UserRequest) translator.translateFrom(exchange.getRequestBody().toString(), UserRequest.class);
		exchange.getRequestBody().close();
		int userID = gameFacade.listAI();
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
		exchange.getResponseBody().close();*/
	}

}
