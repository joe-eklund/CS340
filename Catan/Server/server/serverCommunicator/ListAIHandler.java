package server.serverCommunicator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import proxy.ITranslator;
import server.cookie.Cookie;
import server.cookie.InvalidCookieException;
import server.cookie.CookieParams;
import server.game.IGameFacade;
import shared.ServerMethodRequests.UserRequest;
import shared.definitions.GameDescription;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for ListAI command
 * @author Epper
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
	 * Handles List Games. 
	 * @param exchange: the HttpExchange to be handled. 
	 * @pre 
	 *  exchange will contain a request header of "cookie : <valid logged in cookie>"
	 *  <valid logged in cookie> ::= as defined in CS 340 webpage cookie specification
	 * @post 
	 *  the exchange response code will be set to HTTP 200 Success
	 *  the exchange response headers include Content�Type: application/json
	 * 	the exchange response body will contain a json formatted list of Strings(AIs)
	 *  
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("In list games handler");
		
		String responseMessage = "";
		
		if(exchange.getRequestMethod().toLowerCase().equals("get")) {
			String unvalidatedCookie = exchange.getRequestHeaders().get("Cookie").get(0);
			System.out.println(unvalidatedCookie);
			try {  // check user login cookie and if valid get params
				
				Cookie.verifyCookie(unvalidatedCookie, translator);
				System.out.println("good cookie");
				List<String> gameAI = this.gameFacade.listAI();
				responseMessage = this.translator.translateTo(gameAI.toArray());			
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
				
			} catch (InvalidCookieException e) { // else send error message
				System.out.println("bad cookie");
				responseMessage = "Error: You either did not provide a cookie or the provided cookie is invalid";
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			}
		}
		else {
			// unsupported request method
			responseMessage = "Error: \"" + exchange.getRequestMethod() + "\" is no supported!";
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
		}
		
		//set "Content-Type: application/json" header
		List<String> contentTypes = new ArrayList<String>();
		String appJson = "application/json";
		contentTypes.add(appJson);
		exchange.getResponseHeaders().put("Content-type", contentTypes);
		
		//send failure response message
		OutputStreamWriter writer = new OutputStreamWriter(
				exchange.getResponseBody());
		writer.write(responseMessage);
		writer.flush();
		writer.close();
		
		exchange.getResponseBody().close();
	}

}
