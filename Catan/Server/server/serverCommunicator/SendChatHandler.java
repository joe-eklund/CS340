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
import server.moves.IMovesFacade;
import shared.ServerMethodRequests.SendChatRequest;
import shared.ServerMethodRequests.UserRequest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for SendChat command
 * @author Chad
 *
 */
public class SendChatHandler implements HttpHandler {

	private ITranslator translator;
	private IMovesFacade movesFacade;

	public SendChatHandler(ITranslator translator, IMovesFacade movesFacade) {
		this.translator = translator;
		this.movesFacade = movesFacade;
	}

	/**
	 * Handles Chat. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("In send chat handler");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
		String inputLine;
		StringBuffer requestJson = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			requestJson.append(inputLine);
		}
		in.close();
		
		SendChatRequest request = (SendChatRequest) translator.translateFrom(requestJson.toString(), SendChatRequest.class);
		exchange.getRequestBody().close();

		int userID = movesFacade.sendChat(request);
		
		if(userID > -1) {
			// create cookie for user
			List<String> cookies = new ArrayList<String>();
			//String cookie = Cookie.createLoginCookie(request.getUsername(), request.getPassword(), userID);
			//cookies.add(cookie);
			
			// send success response
			exchange.getResponseHeaders().put("Set-cookie", cookies);
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		}
		else {
			//set "Content-Type: text/plain" header
			List<String> contentTypes = new ArrayList<String>();
			String textPlain = "text/plain";
			contentTypes.add(textPlain);
			exchange.getResponseHeaders().put("Content-type", contentTypes);
			
			//send failure response
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
	
			OutputStreamWriter writer = new OutputStreamWriter(exchange.getResponseBody());
			writer.write("Send chat failed"); 
			writer.flush();
			writer.close();
		}
		exchange.getResponseBody().close();
	}

}
