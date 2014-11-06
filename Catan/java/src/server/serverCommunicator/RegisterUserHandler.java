package server.serverCommunicator;

import java.io.IOException;

import proxy.ITranslator;
import server.users.IUsersFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RegisterUserHandler implements HttpHandler {

	public RegisterUserHandler(ITranslator translator, IUsersFacade usersFacade) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
