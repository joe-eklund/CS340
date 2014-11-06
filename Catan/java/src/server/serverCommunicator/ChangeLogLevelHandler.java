package server.serverCommunicator;

import java.io.IOException;

import proxy.ITranslator;
import server.util.IUtilFacade;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ChangeLogLevelHandler implements HttpHandler {
	
	private ITranslator translator;
	private IUtilFacade utilFacade;

	public ChangeLogLevelHandler(ITranslator translator, IUtilFacade utilFacade) {
		this.translator = translator;
		this.utilFacade = utilFacade;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub

	}

}
