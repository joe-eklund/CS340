package proxy;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ClientCommunicator implements ICommunicator {

	
	String Host = new String();
	int Port = 0;
	TranslatorJSON jsonTrans = new TranslatorJSON();
	
	/**Starts the request from the server given the information from the proxy. Starts by packaging up the info and having the translator change it to json. Then takes the json object with the request type and sends it to the server. 
	 * 
	 * @param commandName
	 * @param commandParameters
	 * @return ICommandResponse from the server
	 */
	@Override
	public ICommandResponse executeCommand(RequestType requestType, List<Pair<String,String>> headers, String commandName, Object commandParameters, Class<?> responseCastClass) {
		
		String translatedJson = jsonTrans.translateTo(commandParameters);
		doGet(); //will take the translated json object and the parameters and send a request to the server. 
		ICommandResponse response= actionMethod(); //
		return response;
	}
	
	/**
	 * 
	 * @param urlPath
	 * @throws ClientException
	 */
	private void doGet(String urlPath) //throws ClientException may need to add this in later
	{
		try { 
			URL url = new URL("http://" + Host + ':' + Port + urlPath);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET"); 
			connection.connect(); 
			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream responseBody = connection.getInputStream(); 
				responseBody.read();
			}
			else {
				//throw new ClientException("FAILURE"); 
			}
		}
		catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	/**Make HTTP POST request to the specified URL, 
	 *passing in the specified postData object
	 * @param urlPath
	 * @param postData
	 * @throws ClientException
	 */
	private Object doPost(String urlPath, Object postData){// throws ClientException{
		Object response = null;
		try { 
			URL url = new URL("http://" + Host + ":" + Port + urlPath);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection(); 
			connection.setRequestMethod("POST");
			connection.setDoInput(true); 
			connection.setDoOutput(true); 
			connection.addRequestProperty("Accept", "text/html"); 
			connection.connect(); 
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) { 
				//get response here. 
			}
			else { 
				System.out.print("Failure");
				//throw new ClientException("FAILURE");
			}
		}
		catch (IOException e) { // IO ERROR
			System.out.print("Failure");
			//throw new ClientException("FAILURE");
		}
		return response;
	}
}
