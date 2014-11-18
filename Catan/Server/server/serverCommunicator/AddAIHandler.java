package server.serverCommunicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import proxy.ITranslator;
import server.cookie.Cookie;
import server.cookie.CookieParams;
import server.cookie.InvalidCookieException;
import server.game.IGameFacade;
import shared.ServerMethodRequests.AddAIRequest;
import shared.ServerMethodRequests.JoinGameRequest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for addAI command
 * @author Chad
 *
 */
public class AddAIHandler implements HttpHandler {

	private ITranslator translator;
	private IGameFacade gameFacade;

	public AddAIHandler(ITranslator translator, IGameFacade gameFacade) {
		this.translator = translator;
		this.gameFacade = gameFacade;
	}
	
	/**
	 * Handles adding an AI. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("In add AI handler.");
		String responseMessage = "";
		
		if(exchange.getRequestMethod().toLowerCase().equals("post")) {
			try{
				//Validate cookie
				String unvalidatedCookie = exchange.getRequestHeaders().get("Cookie").get(0);
				CookieParams cookie = Cookie.verifyCookie(unvalidatedCookie, translator);
				//Get request JSON
				BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
				String inputLine;
				StringBuffer requestJson = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					requestJson.append(inputLine);
				}
				in.close();
				System.out.println("Request JSON: " + requestJson);
				AddAIRequest request = (AddAIRequest) translator.translateFrom(requestJson.toString(), AddAIRequest.class);
				exchange.getRequestBody().close();
				
				//Check for valid game ID
				if(gameFacade.validGameID(cookie.getGameID())){
					//TODO Create AI and add it. 
				}
				
			}catch (InvalidCookieException e) { // else send error message
				System.out.println("Invalid addAI request");
				responseMessage = e.getMessage();
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			}
		}

	}

}
