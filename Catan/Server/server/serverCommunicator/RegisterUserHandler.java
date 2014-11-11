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
 * Handler for RegisterUser command
 * @author Chad
 *
 */
public class RegisterUserHandler implements HttpHandler {

	private ITranslator translator;
	private IUsersFacade usersFacade;

	public RegisterUserHandler(ITranslator translator, IUsersFacade usersFacade) {
		this.translator = translator;
		this.usersFacade = usersFacade;
	}

	/**
	 * Handles Register User. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("In register user handler");
		
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
			if (request.validatePreConditions()) {
				int userID = usersFacade.registerUser(request);
				if (userID > -1) {
					// create cookie for user
					List<String> cookies = new ArrayList<String>();
					String cookie = Cookie.createLoginCookie(
							request.getUsername(), request.getPassword(),
							userID);
					cookies.add(cookie);

					// send success response headers
					exchange.getResponseHeaders().put("Set-cookie", cookies);
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
					successfulRequest = true;
				} else {
					responseMessage = "Registration failed - duplicate username";
				}
			} else {
				responseMessage = "Don't trust your client -- they have violated the server API contract: invalid username and/or password configuration.";
			}
		}
		else {
			responseMessage = "Error: \"" + exchange.getRequestMethod() + "\" is no supported!";
		}
		
		if(!successfulRequest) {
			//set "Content-Type: text/plain" header
			List<String> contentTypes = new ArrayList<String>();
			String textPlain = "text/plain";
			contentTypes.add(textPlain);
			exchange.getResponseHeaders().put("Content-type", contentTypes);

			//send failure headers
			exchange.sendResponseHeaders(
					HttpURLConnection.HTTP_BAD_REQUEST, 0);
			
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
