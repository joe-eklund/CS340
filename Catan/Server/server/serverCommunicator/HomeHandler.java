package server.serverCommunicator;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HomeHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String redirectHTML = "<html>\n" + 
				"  <head>\n" + 
				"    <title>IU Webmaster redirect</title>\n" + 
				"    <META http-equiv=\"refresh\" content=\"5;URL=http://localhost:8081/docs/api/view/index.html\">\n" + 
				"  </head>\n" + 
				"  <body bgcolor=\"#ffffff\">\n" + 
				"    <center>You will be redirected to <a href=\"http://localhost:8081/docs/api/view/index.html\">swagger</a> in 5 seconds.\n" + 
				"    </center>\n" + 
				"  </body>\n" + 
				"</html>\n";
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		
		OutputStreamWriter writer = new OutputStreamWriter(
				exchange.getResponseBody());
		writer.write(redirectHTML);
		writer.flush();
		writer.close();
		
		exchange.getResponseBody().close();
	}

}
