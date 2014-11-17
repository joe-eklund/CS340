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
import server.cookie.CookieParams;
import server.cookie.InvalidCookieException;
import server.moves.IMovesFacade;
import server.moves.InvalidBuildCityRequest;
import server.moves.InvalidOfferTradeRequest;
import shared.ServerMethodRequests.BuildCityRequest;
import shared.ServerMethodRequests.OfferTradeRequest;
import client.exceptions.ClientModelException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for OfferTrade command
 * @author Chad
 *
 */
public class OfferTradeHandler implements HttpHandler {

	private ITranslator translator;
	private IMovesFacade movesFacade;

	public OfferTradeHandler(ITranslator translator, IMovesFacade movesFacade) {
		this.translator = translator;
		this.movesFacade = movesFacade;
	}

	/**
	 * Handles Offer Trade. 
	 * @param exchange: the exchange to be handled. 
	 * @pre The handler will be given the proper values to carry out the exchange.
	 * @post no post as there is no return value. 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("In Offer Trade handler");
		
		String responseMessage = "";
		
		if(exchange.getRequestMethod().toLowerCase().equals("post")) {
			try { 
				String unvalidatedCookie = exchange.getRequestHeaders().get("Cookie").get(0);
				CookieParams cookie = Cookie.verifyLoginCookie(unvalidatedCookie, translator);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
				String inputLine;
				StringBuffer requestJson = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					requestJson.append(inputLine);
				}
				in.close();
				
				System.out.println(requestJson);
				
				//
				OfferTradeRequest request = (OfferTradeRequest) translator.translateFrom(requestJson.toString(), OfferTradeRequest.class);
				exchange.getRequestBody().close();
				
				if(this.movesFacade.offerTrade(request, cookie)) {
					System.out.println("Request Accepted!");
					// create cookie for user
					List<String> cookies = new ArrayList<String>();

					// send success response headers
					exchange.getResponseHeaders().put("Set-cookie", cookies);
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
				}
				else {
					System.out.println("Offer trade request insvalid");
					responseMessage = "Unable to offer trade";
					exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				}

			} catch (InvalidCookieException | InvalidOfferTradeRequest e) { // else send error message
				System.out.println("unrecognized / invalid offer trade request");
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
