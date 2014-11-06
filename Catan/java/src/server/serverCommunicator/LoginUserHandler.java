package server.serverCommunicator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import proxy.ITranslator;
import server.users.IUsersFacade;
import shared.ServerMethodRequests.UserRequest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class LoginUserHandler implements HttpHandler {
	
	private ITranslator translator;
	private IUsersFacade usersFacade;
	
	public LoginUserHandler(ITranslator translator, IUsersFacade usersFacade) {
		this.translator = translator;
		this.usersFacade = usersFacade;
	}
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		UserRequest request = (UserRequest) translator.translateFrom(exchange.getRequestBody().toString(), UserRequest.class);
		exchange.getRequestBody().close();
		if(request.validatePreConditions() && usersFacade.loginUser(request)) {
			// create cookie for user
			List<String> cookies = new ArrayList<String>();
			// send success response
			exchange.getResponseHeaders().put("Set-cookie", cookies);
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		}
		else {
			//send failure response
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			
			//set "Content-Type: text/plain" header
		}
		exchange.getResponseBody().close();
	}

}
