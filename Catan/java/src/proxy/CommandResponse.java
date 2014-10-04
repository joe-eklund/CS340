package proxy;

import java.util.List;
import java.util.Map;

/**
 * a class representing the parameters returned from the ClientCommunicator after executing a call to the server via http
 * @author joshuabgrigg
 *
 */
public class CommandResponse implements ICommandResponse {
	private Map<String, List<String>> responseHeaders; //http response header value for "Set-cookie"; can be NULL
	private int responseCode;	//http response code; cannot be NULL
	private Object responseObjectFromJSON;	//object translated from http response JSON using GSON; can be NULL
	private String responseMessage;  //response message
	
	public CommandResponse(Map<String, List<String>> responseHeaders, int responseCode,
			Object responseObjectFromJSON, String responseMessage) {
		this.responseHeaders = responseHeaders;
		this.responseCode = responseCode;
		this.responseObjectFromJSON = responseObjectFromJSON;
		this.responseMessage = responseMessage;
	}

	public Map<String, List<String>> getResponseHeaders() {
		return responseHeaders;
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	
	public Object getResponseObject() {
		return responseObjectFromJSON;
	}
	
	public String getResponseMessage() {
		return responseMessage;
	}
	
	public void setresponseHeaders(Map<String, List<String>> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}
	
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
	public void setResponseObjectFromJSON(Object responseObjectFromJSON) {
		this.responseObjectFromJSON = responseObjectFromJSON;
	}
	
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
}
