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
import server.cookie.InvalidCookieException;
import server.games.IGamesFacade;
import server.games.InvalidGamesRequest;
import shared.ServerMethodRequests.CreateGameRequest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for create game command
 * @author Chad
 *
 */
public class CreateGameHandler implements HttpHandler {

	private ITranslator translator;
	private IGamesFacade gamesFacade;

	public CreateGameHandler(ITranslator translator, IGamesFacade gamesFacade) {
		this.translator = translator;
		this.gamesFacade = gamesFacade;
	}

	/**
	 * Handles the Create Game. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("In create game handler");
		
		String responseMessage = "";
		
		if(exchange.getRequestMethod().toLowerCase().equals("post")) {
			try {  // check user login cookie and if valid get params
				String unvalidatedCookie = exchange.getRequestHeaders().get("Cookie").get(0);
				Cookie.verifyCookie(unvalidatedCookie, translator);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
				String inputLine;
				StringBuffer requestJson = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					requestJson.append(inputLine);
				}
				in.close();
				
				System.out.println(requestJson.toString());
				CreateGameRequest request = (CreateGameRequest) translator.translateFrom(requestJson.toString(), CreateGameRequest.class);
				exchange.getRequestBody().close();
				
				responseMessage = this.translator.translateTo(this.gamesFacade.createGame(request));
				
				// TODO Create empty game model and add to gameModels list
				
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			} catch (InvalidCookieException | InvalidGamesRequest e) { // else send error message
				responseMessage = e.getMessage();
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			}
		}
		else {
			// unsupported request method
			responseMessage = "Error: \"" + exchange.getRequestMethod() + "\" is not supported!";
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
		}
		
		System.out.println(responseMessage + "\n\n");
		
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
