package server.serverCommunicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import proxy.ITranslator;
import server.cookie.Cookie;
import server.cookie.CookieParams;
import server.cookie.InvalidCookieException;
import server.games.IGamesFacade;
import server.games.InvalidGamesRequest;
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
		System.out.println("In join game handler");
		
		String responseMessage = "";
		
		if(exchange.getRequestMethod().toLowerCase().equals("post")) {
			try {  // check user login cookie and if valid get params
				String unvalidatedCookie = exchange.getRequestHeaders().get("Cookie").get(0);
				CookieParams cookie = Cookie.verifyCookie(unvalidatedCookie, translator);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
				String inputLine;
				StringBuffer requestJson = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					requestJson.append(inputLine);
				}
				in.close();
				
				System.out.println(requestJson);
				
				JoinGameRequest request = (JoinGameRequest) translator.translateFrom(requestJson.toString(), JoinGameRequest.class);
				exchange.getRequestBody().close();
				
				if(this.gamesFacade.joinGame(request, cookie.getName(), cookie.getPlayerID())) {
					System.out.println("Request Accepted!");
					// create cookie for user
					List<String> cookies = new ArrayList<String>();
					String joinCookie = Cookie.createJoinCookie(request.getID());
					System.out.println("join cookie: " + joinCookie);
					cookies.add(joinCookie);

					// send success response headers
					exchange.getResponseHeaders().put("Set-cookie", cookies);
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
				}
				else {
					System.out.println("join game request had invalid color (duplicate or unrecognized)");
					responseMessage = "Unable to join game b/c invalid color and/or color is already taken and/or game is full";
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				}
				
				// TODO join game in gameModels list

			} catch (InvalidCookieException | InvalidGamesRequest e) { // else send error message
				System.out.println("unrecognized / invalid join game request");
				responseMessage = e.getMessage();
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			}
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
