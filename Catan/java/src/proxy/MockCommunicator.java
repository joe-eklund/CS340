package proxy;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.List;
import java.util.Map;

/**This class will contain some hard code data for the use of testing.
 * 
 * @author Chad
 *
 */
public class MockCommunicator implements ICommunicator {
		
	String Host;
	int Port;
	TranslatorJSON jsonTrans;
	
	/**
	 * @param host
	 * @param port
	 * @param jsonTrans
	 */
	public MockCommunicator(String host, int port, TranslatorJSON jsonTrans) {
		super();
		Host = host;
		Port = port;
		this.jsonTrans = jsonTrans;
	}

	/**Starts the request from the server given the information from the proxy. Starts by packaging up the info and having the translator change it to json. Then takes the json object with the request type and sends it to the server. 
	 * 
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
			URL url = new URL("http://" + Host + ':' + Port + commandName);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			for(Pair header : headers){
				connection.setRequestProperty((String)header.getKey(), (String)header.getValue());
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
		}
		return serverResponse;
	}
	
	
	/**
	 * 
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
						
			InputStream responseJson = connection.getInputStream();
			responseMessage = connection.getResponseMessage();
			responseCode = connection.getResponseCode();
			responseHeaders = connection.getHeaderFields();
			
			Object javaObject = jsonTrans.translateTo(responseJson.toString());
			
			result = new CommandResponse(responseHeaders, responseCode, javaObject, responseMessage);
			
			responseJson.close();
		}
		catch (IOException e) { // IO ERROR
			System.err.print("Unable to doGet");
			
			//throw new ClientException("FAILURE");
		}
		return result;
	}
	
	
	/**Make HTTP POST request to the specified URL, 
	 *passing in the specified postData object
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
			
			InputStream responseJson = connection.getInputStream();
			responseMessage = connection.getResponseMessage();
			responseCode = connection.getResponseCode();
			responseHeaders = connection.getHeaderFields();
			
			Object javaObject = jsonTrans.translateTo(responseJson.toString());
			
			result = new CommandResponse(responseHeaders, responseCode, javaObject, responseMessage);
			
			writer.close();
			responseJson.close();
		}
		catch (IOException e) { // IO ERROR
			System.err.print("Unable to doPost");
			
			//throw new ClientException("FAILURE");
		}
		return result;
	}
		//Mock Hard coded data: The following data is used for JUnit testing. It contains mock data for testing various commands and the communication between the translator and the server. 
}
