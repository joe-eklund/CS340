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
import server.users.IUsersFacade;
import shared.ServerMethodRequests.UserRequest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for the LoginUser command
 *
 */
public class LoginUserHandler implements HttpHandler {
	
	private ITranslator translator;
	private IUsersFacade usersFacade;
	
	public LoginUserHandler(ITranslator translator, IUsersFacade usersFacade) {
		this.translator = translator;
		this.usersFacade = usersFacade;
	}
	
	/**
	 * Handles Login User. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("In login user handler");
		
		String responseMessage = "";
		boolean successfulRequest = false;
		
		if (exchange.getRequestMethod().toLowerCase().equals("post")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					exchange.getRequestBody()));
			String inputLine;
			StringBuffer requestJson = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				requestJson.append(inputLine);
			}
			in.close();
			UserRequest request = (UserRequest) translator.translateFrom(
					requestJson.toString(), UserRequest.class);
			exchange.getRequestBody().close();
			int userID = usersFacade.loginUser(request);
			if (userID > -1) {
				// create cookie for user
				List<String> cookies = new ArrayList<String>();
				String cookie = Cookie.createLoginCookie(request.getUsername(),
						request.getPassword(), userID);
				cookies.add(cookie);

				// send success response
				exchange.getResponseHeaders().put("Set-cookie", cookies);
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
				
				successfulRequest = true;
			} else {
				responseMessage = "Login failed - bad password or username";
			}
		}
		else {
			responseMessage = "Error: \"" + exchange.getRequestMethod() + "\" is no supported!";
		}
		
		
		if (!successfulRequest) {
			
			//set "Content-Type: text/plain" header
			List<String> contentTypes = new ArrayList<String>();
			String textPlain = "text/plain";
			contentTypes.add(textPlain);
			exchange.getResponseHeaders().put("Content-type", contentTypes);
			
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			
			OutputStreamWriter writer = new OutputStreamWriter(
					exchange.getResponseBody());
			writer.write(responseMessage);
			writer.flush();
			writer.close();
		}
		
		exchange.getResponseBody().close();
		System.out.println("response body closed");
	}

}
