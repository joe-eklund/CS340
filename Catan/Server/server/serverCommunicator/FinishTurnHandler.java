package server.serverCommunicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import proxy.ITranslator;
import server.commands.moves.FinishTurnCommand;
import server.commands.moves.IMovesCommandLog;
import server.commands.moves.SendChatCommand;
import server.cookie.Cookie;
import server.cookie.CookieParams;
import server.cookie.InvalidCookieException;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.BuildCityRequest;
import shared.ServerMethodRequests.BuyDevCardRequest;
import shared.ServerMethodRequests.FinishTurnRequest;
import shared.definitions.ServerModel;
import client.exceptions.ClientModelException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for finish turn command
 * @author Epper
 *
 */
public class FinishTurnHandler implements HttpHandler {

	private ITranslator translator;
	private IMovesFacade movesFacade;
	private IMovesCommandLog movesLog;

	public FinishTurnHandler(ITranslator translator, IMovesFacade movesFacade, IMovesCommandLog movesLog) {
		this.translator = translator;
		this.movesFacade = movesFacade;
		this.movesLog = movesLog;
	}

	/**
	 * Handles Finish Turn. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
//		FinishTurnRequest request = (FinishTurnRequest) translator.translateFrom(exchange.getRequestBody().toString(), FinishTurnRequest.class);
//		exchange.getRequestBody().close();
//		int userID = movesFacade.finishTurn(request);
//		if(/*request.validatePreConditions() &&*/ userID > -1) {
//			// create cookie for user
//			List<String> cookies = new ArrayList<String>();
//			// send success response
//			exchange.getResponseHeaders().put("Set-cookie", cookies);
//			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
//		}
//		else {
//			//send failure response
//			exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
//			
//			//set "Content-Type: text/plain" header
//		}
//		exchange.getResponseBody().close();
		

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
				
				FinishTurnRequest request = (FinishTurnRequest) translator.translateFrom(requestJson.toString(), FinishTurnRequest.class);
				exchange.getRequestBody().close();
				
				ServerModel serverModel = this.movesFacade.finishTurn(request, cookie);
				System.out.println("Request Accepted!");
				// create cookie for user
				List<String> cookies = new ArrayList<String>();

				// send success response headers
				exchange.getResponseHeaders().put("Set-cookie", cookies);
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
				movesLog.store(new FinishTurnCommand(movesFacade, request, cookie));
				
				
				responseMessage = translator.translateTo(serverModel);
				
				// TODO join game in gameModels list

			} catch (InvalidCookieException | InvalidMovesRequest e) { // else send error message
				System.out.println("unrecognized / invalid finish turn request");
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
