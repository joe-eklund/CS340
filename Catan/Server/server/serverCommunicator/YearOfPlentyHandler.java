package server.serverCommunicator;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import proxy.ITranslator;
import server.cookie.Cookie;
import server.cookie.CookieParams;
import server.cookie.InvalidCookieException;
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
		String responseMessage = "";
		String unvalidatedCookie = exchange.getRequestHeaders().get("Cookie").get(0);
		if(exchange.getRequestMethod().toLowerCase().equals("post")) {
			try {
				CookieParams cookie = Cookie.verifyLoginCookie(unvalidatedCookie, translator);
				YearOfPlentyDevRequest request = (YearOfPlentyDevRequest) translator.translateFrom(exchange.getRequestBody().toString(), YearOfPlentyDevRequest.class);
				exchange.getRequestBody().close();
				int userID = movesFacade.yearOfPlenty(request,cookie);
				if(userID > -1) {
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
				}
				else {
					System.out.println("join game request had invalid color (duplicate or unrecognized)");
					responseMessage = "Unable to join game b/c invalid color and/or color is already taken and/or game is full";
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				}
				exchange.getResponseBody().close();
			} catch (InvalidCookieException e) {
				System.out.println("unrecognized / invalid join game request");
				responseMessage = e.getMessage();
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			}
		}
		else {
			responseMessage = "Error: \"" + exchange.getRequestMethod() + "\" is no supported!";
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
		}
		
		List<String> contentTypes = new ArrayList<String>();
		String type = "text/plain";
		contentTypes.add(type);
		exchange.getResponseHeaders().put("Content-type", contentTypes);
		
		if (!responseMessage.isEmpty()) {
			//send failure response message
			OutputStreamWriter writer = new OutputStreamWriter(
					exchange.getResponseBody());
			writer.write(responseMessage);
			writer.flush();
			writer.close();
		}
		exchange.getResponseBody().close();
	}

}
