package server.serverCommunicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import proxy.ITranslator;
import server.commands.moves.BuildRoadCommand;
import server.commands.moves.BuildSettlementCommand;
import server.commands.moves.IMovesCommandLog;
import server.cookie.Cookie;
import server.cookie.CookieParams;
import server.cookie.InvalidCookieException;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.BuildCityRequest;
import shared.ServerMethodRequests.BuildSettlementRequest;
import shared.definitions.ServerModel;
import shared.model.LogEntry;
import client.exceptions.ClientModelException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * handler for the buildsettlement command
 * @author Chad
 *
 */
public class BuildSettlementHandler implements HttpHandler {

	private ITranslator translator;
	private IMovesFacade movesFacade;
	private IMovesCommandLog movesLog;

	public BuildSettlementHandler(ITranslator translator,
			IMovesFacade movesFacade, IMovesCommandLog movesLog) {
		this.translator = translator;
		this.movesFacade = movesFacade;
		this.movesLog = movesLog;
	}

	/**
	 * Handles Building a Settlement. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
System.out.println("In build settlement handler");
		
		String responseMessage = "";
		
		if(exchange.getRequestMethod().toLowerCase().equals("post")) {
			try { 
				//TODO verify cookie method
				String unvalidatedCookie = exchange.getRequestHeaders().get("Cookie").get(0);
				CookieParams cookie = Cookie.verifyCookie(unvalidatedCookie, translator);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
				String inputLine;
				StringBuffer requestJson = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					requestJson.append(inputLine);
				}
				in.close();
				
				System.out.println(requestJson);
				
				BuildSettlementRequest request = (BuildSettlementRequest) translator.translateFrom(requestJson.toString(), BuildSettlementRequest.class);
				exchange.getRequestBody().close();
				
				ServerModel serverModel = this.movesFacade.buildSettlement(request, cookie);
				System.out.println("Request Accepted!");
				// create cookie for user
				List<String> cookies = new ArrayList<String>();

				// send success response headers
				exchange.getResponseHeaders().put("Set-cookie", cookies);
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
				movesLog.store(new BuildSettlementCommand(movesFacade, request, cookie));
				
				String name = serverModel.getPlayerByID(cookie.getPlayerID()).getName();
				serverModel.getLog().addMessage(new LogEntry(name+ " built a settlement", serverModel.getPlayerByID(cookie.getPlayerID()).getName()));

				responseMessage = translator.translateTo(serverModel);
				
				// TODO join game in gameModels list

			} catch (InvalidCookieException | InvalidMovesRequest | ClientModelException e) { // else send error message
				System.out.println("unrecognized / invalid build settlement request");
				responseMessage = e.getMessage();
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
			}
		}
		else {
			// unsupported request method
			responseMessage = "Error: \"" + exchange.getRequestMethod() + "\" is no supported!";
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
