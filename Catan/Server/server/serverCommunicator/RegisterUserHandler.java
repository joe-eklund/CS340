package server.serverCommunicator;

import java.io.IOException;

import proxy.ITranslator;
import server.users.IUsersFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for RegisterUser command
 * @author Chad
 *
 */
public class RegisterUserHandler implements HttpHandler {

	public RegisterUserHandler(ITranslator translator, IUsersFacade usersFacade) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles Register User. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
