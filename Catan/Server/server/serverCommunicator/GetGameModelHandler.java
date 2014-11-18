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
import server.game.IGameFacade;
import server.games.InvalidJoinGameRequest;
import shared.definitions.ServerModel;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.ssl.HttpsURLConnection;

/**
 * Handler for getgamemodel command
 * 
 * @author Chad
 *
 */
public class GetGameModelHandler implements HttpHandler {

	private ITranslator translator;
	private IGameFacade gameFacade;

	public GetGameModelHandler(ITranslator translator, IGameFacade gameFacade) {
		this.translator = translator;
		this.gameFacade = gameFacade;
	}

	/**
	 * Handles Get Game Model.
	 * 
	 * @param exchange
	 *            : the exchange to be handled.
	 * @pre The handler will be given the proper values to carry out the
	 *      exchange.
	 * @post no post as there is no return value.
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("Handling GetGameModel");

		String responseMessage = "";

		// Check if request method is get
		if (exchange.getRequestMethod().toLowerCase().equals("get")) {
			try {
				// Validate cookie
				
				String unvalidatedCookie = exchange.getRequestHeaders().get("Cookie").get(0);
				CookieParams cookie = Cookie.verifyCookie(unvalidatedCookie,translator);
				// Get URL path
				System.out.println("Getting URL\n");
				String path = exchange.getRequestURI().getPath();
				int equalsInd = path.indexOf("=");
				String subPath = exchange.getRequestURI().getQuery();

				// Check valid game id from cookie
//				System.out.println("PATH");
				//System.out.println(path);
//				System.out.println("Query");
//				System.out.println(exchange.getRequestURI().getQuery());
				
				if (gameFacade.validGameID(cookie.getGameID())) {
					// No version
					if (path.equals("/game/model") && subPath == null) {// Return new model
						
						ServerModel game = gameFacade.getGameModel(cookie.getGameID());
						responseMessage = translator.translateTo(game);
						exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
					}
					// Is a version number. Check the version number and maybe return a model
					else if (subPath != null) {
						
						String[] tokens = subPath.split("=");
						Integer version = null;
						version = (tokens.length > 1) ? Integer.parseInt(tokens[1]) : null;
						if(version != null){
							int versionInt = version.intValue();
							ServerModel game = gameFacade.getGameModel(cookie.getGameID());
							if(versionInt != game.getVersion()){//Different versions, so return new model
								responseMessage = translator.translateTo(game);
							}
							else{//Same version
								responseMessage = "\"true\"";
							}
							exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
						}
						else{//Missing version number
							responseMessage = "Invalid URL format. Missing valid version number.";
							exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
						}
					} else {// bad URI
						System.out.println("Invalid URL");
						responseMessage = "Invalid URL";
						exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
					}
				} else {
					System.out.println("Invalid game ID.");
					responseMessage = "Invalid Game ID";
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				}
			} catch (InvalidCookieException e) { // else send error message
				System.out.println("Unrecognized / invalid get game model request");
				responseMessage = e.getMessage();
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			} catch(NumberFormatException e) {
				System.out.println("Invalid game version.");
				responseMessage = "Invalid game version";
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			}
		}
		// Unsupported request method
		else {
			responseMessage = "Error: \"" + exchange.getRequestMethod() + "\" is not supported.";
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
		}
		// set "Content-Type: text/plain" header
		List<String> contentTypes = new ArrayList<String>();
		String type = " application/json";
		contentTypes.add(type);
		exchange.getResponseHeaders().put("Content-type", contentTypes);

		System.out.println("GET GAME MODEL RESPONSE: " + responseMessage);
		if (!responseMessage.isEmpty()) {
			System.out.println(responseMessage);
			// send failure response message
			OutputStreamWriter writer = new OutputStreamWriter(exchange.getResponseBody());
			writer.write(responseMessage);
			writer.flush();
			writer.close();
		}
		exchange.getResponseBody().close();
	}

}
