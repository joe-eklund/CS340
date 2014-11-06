package server.serverCommunicator;

import java.io.IOException;

import proxy.ITranslator;
import server.util.IUtilFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for changelog command
 * @author Chad
 *
 */
public class ChangeLogLevelHandler implements HttpHandler {
	
	private ITranslator translator;
	private IUtilFacade utilFacade;

	public ChangeLogLevelHandler(ITranslator translator, IUtilFacade utilFacade) {
		this.translator = translator;
		this.utilFacade = utilFacade;
	}

	/**
	 * Handles Changing the log level. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
