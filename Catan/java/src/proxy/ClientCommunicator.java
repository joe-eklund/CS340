package proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**Class Used for communicating with the server. It runs all the various game commands and communicates results back to the proxy
 * @author Chad
 */
public class ClientCommunicator implements ICommunicator {

	private TranslatorJSON jsonTrans;
	private String Host;
	private int Port;
	
	/**@Obvious
	 * @return the jsonTrans
	 */
	public TranslatorJSON getJsonTrans() {
		return jsonTrans;
	}

	/**@Obvious
	 * @param jsonTrans the jsonTrans to set
	 */
	public void setJsonTrans(TranslatorJSON jsonTrans) {
		this.jsonTrans = jsonTrans;
	}

	/**@Obvious
	 * @return the host
	 */
	public String getHost() {
		return Host;
	}

	/**@Obvious
	 * @param host the host to set
	 */
	public void setHost(String host) {
		Host = host;
	}

	/**@Obvious
	 * @return the port
	 */
	public int getPort() {
		return Port;
	}

	/**@Obvious
	 * @param port the port to set
	 */
	public void setPort(int port) {
		Port = port;
	}

	/**
	 * Basic constructor for the ClientCommunicator
	 * @param host
	 * @param port
	 * @param jsonTrans
	 */
	public ClientCommunicator(String host, int port, TranslatorJSON jsonTrans) {
		super();
		Host = host;
		Port = port;
		this.jsonTrans = jsonTrans;
	}

	/**Starts the request from the server given the information from the proxy. Starts by packaging up the info and having the translator change it to json. Then takes the json object with the request type and sends it to the server. 
	 * @pre A list of headers most be put together, plus all the other necessary information for boxing up a package to be sent to the server
	 * @post If all conditions are right, and execution is normal, A commandResponse object packaged up with all necessary data is sent back to proxy. 
	 * @param commandName
	 * @param commandParameters
	 * @return ICommandResponse from the server
	 * @throws MalformedURLException 
	 */
	@Override
	public CommandResponse executeCommand(RequestType requestType, List<Pair<String,String>> headers, String commandName, Object commandParameters, Class<?> responseCastClass){
		CommandResponse serverResponse = null;
		String translatedJson = jsonTrans.translateTo(commandParameters);
		
		try {
			URL url = new URL("http://" + Host + ':' + Port + "/" + commandName);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			for(Pair header : headers){
				connection.addRequestProperty((String)header.getKey(), (String)header.getValue());
			}
			if(requestType.name().equals("GET")) {
				
				serverResponse = doGet(connection); //will take the translated json object and the parameters and send a request to the server. 
			}
			else {
				serverResponse = doPost(translatedJson, connection);
			}	
		}
		catch (IOException e) { // IO ERROR
			System.out.print("Unable to establish URL connection!");
			e.printStackTrace();
		}
		return serverResponse;
	}
	
	/**Preforms a get operation to the server
	 * @pre The Connection to the server must all ready be set up with the necessary headers
	 * @post Response data is returned. 
	 * @param headers 
	 * @param urlPath
	 * @throws ClientException
	 */
	private CommandResponse doGet(HttpURLConnection connection) //throws ClientException may need to add this in later
	{
		CommandResponse result = null;
		int responseCode;
		String responseMessage = "";
		Map responseHeaders;
		try { 
			connection.setRequestMethod("GET");
			connection.connect(); 
	
			responseCode = connection.getResponseCode();
						
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer responseJson = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				responseJson.append(inputLine);
			}
			in.close();	
			
			responseMessage = connection.getResponseMessage();
			responseHeaders = connection.getHeaderFields();
			
			Object javaObject = jsonTrans.translateTo(responseJson.toString());
			
			result = new CommandResponse(responseHeaders, responseCode, javaObject, responseMessage);
		}
		catch (IOException e) { // IO ERROR
			System.err.print("Unable to doGet\n");
		}
		return result;
	}
	
	/**Make HTTP POST request to the specified URL, 
	 *passing in the specified postData object
	 * @pre The connection will  have already been set up, and the jsonObject has been packaged for being sent to the server
	 * @post The right data is put onto the output stream
	 * @param urlPath
	 * @param postData
	 * @throws ClientException
	 */
	private CommandResponse doPost(String jsonObject, HttpURLConnection connection){
		CommandResponse result = null;
		int responseCode;
		String responseMessage = "";
		Map responseHeaders;
		try { 
			connection.setRequestMethod("POST");
			connection.setDoInput(true); 
			connection.setDoOutput(true); 
			connection.connect(); 
						
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(jsonObject); 
			writer.flush();
			writer.close();

			responseCode = connection.getResponseCode();
			
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer responseJson = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				responseJson.append(inputLine);
			}
			in.close();	
			
			responseHeaders = connection.getHeaderFields();			
			responseMessage = connection.getResponseMessage();
			
			Object javaObject = jsonTrans.translateTo(responseJson.toString()); //send over the buffered reader result ,"result1"
			result = new CommandResponse(responseHeaders, responseCode, javaObject, responseMessage);
		}
		catch (IOException e) { // IO ERROR
			System.err.print("Unable to doPost\n");
		}
		return result;
	}
}
