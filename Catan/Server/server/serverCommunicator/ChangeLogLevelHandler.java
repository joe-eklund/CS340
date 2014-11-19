package server.serverCommunicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import proxy.ITranslator;
import server.util.IUtilFacade;
import shared.ServerMethodRequests.ChangeLogLevelRequest;
import shared.ServerMethodRequests.JoinGameRequest;

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
		System.out.println("In change log handler");
		
		String responseMessage = "";
		
		if(exchange.getRequestMethod().toLowerCase().equals("post")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
			String inputLine;
			StringBuffer requestJson = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				requestJson.append(inputLine);
			}
			in.close();
			
			ChangeLogLevelRequest request = (ChangeLogLevelRequest) translator.translateFrom(requestJson.toString(), ChangeLogLevelRequest.class);
			exchange.getRequestBody().close();
			
			switch(request.getlogLevel()){
			case "SEVERE":		responseMessage = "Success";
								exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
								break;
				
			case "WARNING":		responseMessage = "Success";
								exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
								break;
				
			case "INFO":		responseMessage = "Success";
								exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
								break;
				
			case "CONFIG":		responseMessage = "Success";
								exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
								break;
				
			case "FINE":		responseMessage = "Success";
								exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
								break;
				
			case "FINER":		responseMessage = "Success";
								exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
								break;
				
			case "FINEST":		responseMessage = "Success";
								exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
								break;
			
			default: 			responseMessage = "Not an allowed log level";
								exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
								break;
			}
		}
		else{
			// unsupported request method
			responseMessage = "Error: \"" + exchange.getRequestMethod() + "\" is not supported!";
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
		}

		//set "Content-Type: text/plain" header
		List<String> contentTypes = new ArrayList<String>();
		String type = "text/plain";
		contentTypes.add(type);
		exchange.getResponseHeaders().put("Content-type", contentTypes);
		
		if (!responseMessage.isEmpty()) {
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
