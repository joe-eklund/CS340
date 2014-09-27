package proxy;

/**
 * a class representing the parameters returned from the ClientCommunicator after executing a call to the server via http
 * @author joshuabgrigg
 *
 */
public class CommandResponse implements ICommandResponse {
	private String cookieResponseHeader; //http response header value for "Set-cookie"; can be NULL
	private int responseCode;	//http response code; cannot be NULL
	private Object responseObjectFromJSON;	//object translated from http response JSON using GSON; can be NULL
	private String responseMessage;  //response message if “Content­Type:text/plain” response header
	
	public CommandResponse(String cookieResponseHeader, int responseCode,
			Object responseObjectFromJSON, String responseMessage) {
		this.cookieResponseHeader = cookieResponseHeader;
		this.responseCode = responseCode;
		this.responseObjectFromJSON = responseObjectFromJSON;
		this.responseMessage = responseMessage;
	}

	public String getCookieResponseHeader() {
		return cookieResponseHeader;
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
	
	public void setCookieResponseHeader(String cookieResponseHeader) {
		this.cookieResponseHeader = cookieResponseHeader;
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
