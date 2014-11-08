package server.serverCommunicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import proxy.ITranslator;
import server.games.IGamesFacade;
import shared.ServerMethodRequests.JoinGameRequest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for JoinGame command
 *
 */
public class JoinGameHandler implements HttpHandler {

	private ITranslator translator;
	private IGamesFacade gamesFacade;

	public JoinGameHandler(ITranslator translator, IGamesFacade gamesFacade) {
		this.translator = translator;
		this.gamesFacade = gamesFacade;
	}

	/**
	 * Handles Join Game. 
	 * @param exchange: the exchange to be handled. 
	 * @pre 
	 *	the method is set to "post"
	 *	the exhange headers contain "cookie : <valid login cookie>" where valid login cookie is the cookie issued when a user logs in successfully
	 *	the exchange body contains a valid JoinGameRequest encoded in json
	 *	  the game id matches the index of a game in the gameDescriptions list of GamesFacade
	 *	  the player is already part of the game or there is room to add a player in the requested game
	 *	  this color is a valid catan color and is not in use by another player in the requested game
	 * @post
	 * 	the exchange headers have a gameid cookie set identifying the game the player just joined
	 *  response code is set to 200 Successful
	 *  the exchange response body is empty 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("In list games user handler");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
		String inputLine;
		StringBuffer requestJson = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			requestJson.append(inputLine);
		}
		in.close();
		
		JoinGameRequest request = (JoinGameRequest) translator.translateFrom(requestJson.toString(), JoinGameRequest.class);
		exchange.getRequestBody().close();

		String responseMessage = "";
		
		if(exchange.getRequestMethod().toLowerCase().equals("post")) {
			// check cookie
			
			// handle request if cookie is good
		}
		else {
			// unsupported request method
			responseMessage = "Error: \"" + exchange.getRequestMethod() + "\" is no supported!";
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
		}
		
		//set "Content-Type: text/plain" header
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
