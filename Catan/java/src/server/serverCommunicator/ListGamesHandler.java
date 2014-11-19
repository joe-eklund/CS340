package server.serverCommunicator;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import proxy.ITranslator;
import server.cookie.Cookie;
import server.cookie.InvalidCookieException;
import server.games.IGamesFacade;
import shared.definitions.GameDescription;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for ListGame command
 * @author Chad
 *
 */
public class ListGamesHandler implements HttpHandler {

	private IGamesFacade gamesFacade;
	private ITranslator translator;

	public ListGamesHandler(ITranslator translator, IGamesFacade gamesFacade) {
		this.gamesFacade = gamesFacade;
		this.translator = translator;
	}

	/**
	 * Handles List Games. 
	 * @param exchange: the HttpExchange to be handled. 
	 * @pre 
	 *  exchange will contain a request header of "cookie : <valid logged in cookie>"
	 *  <valid logged in cookie> ::= as defined in CS 340 webpage cookie specification
	 * @post 
	 *  the exchange response code will be set to HTTP 200 Success
	 *  the exchange response headers include Content­Type: application/json
	 * 	the exchange response body will contain a json formatted list of gameDescriptions
	 *  in gameDescription,
	 *  	the game id is a non-negative unique integer 
	 *  	the player id's are non-negative unique integers,
	 *  	and the player colors are one of the 9 following values: red, green, blue, yellow, puce, brown, white, purple, orange
	 *  
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("In list games handler");
		
		/*
		BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
		String inputLine;
		StringBuffer requestJson = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			requestJson.append(inputLine);
		}
		in.close();
		
		UserRequest request = (UserRequest) translator.translateFrom(requestJson.toString(), UserRequest.class);
		exchange.getRequestBody().close();
		*/
		
		String responseMessage = "";
		
		if(exchange.getRequestMethod().toLowerCase().equals("get")) {
			exchange.getResponseHeaders().set("Content-Type", "appliction/json");
			String unvalidatedCookie = exchange.getRequestHeaders().get("Cookie").get(0);
			System.out.println(unvalidatedCookie);
			//try {  // check user login cookie and if valid get params
				
				//Cookie.verifyCookie(unvalidatedCookie, translator);
				System.out.println("good cookie");
				List<GameDescription> gameDescriptions = this.gamesFacade.listGames();
				responseMessage = this.translator.translateTo(gameDescriptions.toArray());			
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
				
			/*} catch (InvalidCookieException e) { // else send error message
				System.out.println("bad cookie");
				responseMessage = "Error: You either did not provide a cookie or the provided cookie is invalid";
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			}*/
		}
		else {
			// unsupported request method
			responseMessage = "Error: \"" + exchange.getRequestMethod() + "\" is no supported!";
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
		}
		
		//send failure response message
		OutputStreamWriter writer = new OutputStreamWriter(
				exchange.getResponseBody());
		writer.write(responseMessage);
		writer.flush();
		writer.close();
		
		exchange.getResponseBody().close();
	}

}
