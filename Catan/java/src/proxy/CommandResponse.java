package proxy;

import java.util.List;
import java.util.Map;

/**
 * a class representing the parameters returned from the ClientCommunicator after executing a call to the server via http
 * 
 * @Domain
 * <ul>
 *	 <li>responseHeaders: http response header value for "Set-cookie"; can be NULL</li> 
 *   <li>responseCode: http response code; cannot be NULL</li>
 *   <li>responseObjectFromJSON: object translated from http response JSON using GSON; can be NULL</li>
 *   <li>responseMessage: response message; can be null<li>
 * </ul>
 */
public class CommandResponse implements ICommandResponse {
	private Map<String, List<String>> responseHeaders; //http response header value for "Set-cookie"; can be NULL
	private int responseCode;	//http response code; cannot be NULL
	private Object responseObjectFromJSON;	//object translated from http response JSON using GSON; can be NULL
	private String responseMessage;  //response message
	
	/**
	 * 
	 * @Obvious
	 */
	public CommandResponse(Map<String, List<String>> responseHeaders, int responseCode,
			Object responseObjectFromJSON, String responseMessage) {
		this.responseHeaders = responseHeaders;
		this.responseCode = responseCode;
		this.responseObjectFromJSON = responseObjectFromJSON;
		this.responseMessage = responseMessage;
	}

	/**
	 * 
	 * @Obvious
	 */
	public Map<String, List<String>> getResponseHeaders() {
		return responseHeaders;
	}
	
	/**
	 * 
	 * @Obvious
	 */
	public int getResponseCode() {
		return responseCode;
	}
	
	/**
	 * 
	 * @Obvious
	 */
	public Object getResponseObject() {
		return responseObjectFromJSON;
	}
	
	/**
	 * 
	 * @Obvious
	 */
	public String getResponseMessage() {
		return responseMessage;
	}
	
	/**
	 * 
	 * @Obvious
	 */
	public void setresponseHeaders(Map<String, List<String>> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}
	
	/**
	 * 
	 * @Obvious
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
	/**
	 * 
	 * @Obvious
	 */
	public void setResponseObjectFromJSON(Object responseObjectFromJSON) {
		this.responseObjectFromJSON = responseObjectFromJSON;
	}
	
	/**
	 * 
	 * @Obvious
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
}
