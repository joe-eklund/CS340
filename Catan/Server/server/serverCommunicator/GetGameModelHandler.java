package server.serverCommunicator;

import java.io.IOException;
import java.net.HttpURLConnection;

import proxy.ITranslator;
import server.cookie.Cookie;
import server.cookie.CookieParams;
import server.cookie.InvalidCookieException;
import server.game.IGameFacade;
import server.games.InvalidJoinGameRequest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.ssl.HttpsURLConnection;

/**
 * Handler for getgamemodel command
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
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("Handling GetGameModel");
		
		String responseMessage = "";
		
		//Check if request method is get
		if(exchange.getRequestMethod().toLowerCase().equals("get")){
			try{
				String unvalidatedCookie = exchange.getRequestHeaders().get("Cookie").get(0);
				CookieParams cookie = Cookie.verifyJoinCookie(unvalidatedCookie);
				String path = exchange.getRequestURI().getPath();
				int id = 0;
			}catch (Exception e) { // else send error message
				System.out.println("unrecognized / invalid get game model request");
				responseMessage = e.getMessage();
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			}
		}
		//Unsupported request method
		else{
			responseMessage = "Error: \"" + exchange.getRequestMethod() + "\" is not supported.";
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
		}
	}

}
