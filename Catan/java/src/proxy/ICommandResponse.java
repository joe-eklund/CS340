package proxy;

import java.util.List;
import java.util.Map;

/**
 * An interface for defining the object returned by ICommunicator.executeCommand
 *
 * @Domain
 * <ul>
 *	 <li>responseHeaders: http response header value for "Set-cookie"; can be NULL</li> 
 *   <li>responseCode: http response code; cannot be NULL</li>
 *   <li>responseObjectFromJSON: object translated from http response JSON using GSON; can be NULL</li>
 *   <li>responseMessage: response message; can be null<li>
 * </ul>
 *
 */
public interface ICommandResponse {
	/**
	 * Gets 'Set-cookie' response header (may be null in case there is no 'Set-cookie' response header)
	 * @pre
	 *   None
	 * 
	 * @post
	 *   returns all response headers (return connection.getHeaderFields() after submitting request)
	 */
	public Map<String, List<String>> getResponseHeaders();
	
	/**
	 * Gets http response code for ICommunicator.executeCommand command (may not be null nor 0)
	 * @pre
	 *   None
	 *   
	 * @post
	 *   returns http response code for ICommunicator.executeCommand command parameter
	 */
	public int getResponseCode();
	
	/**
	 * Gets Java Object equivalent of server encoded (json, xml, etc) object response (may be null in case no JSON object is returned)
	 * @pre
	 *   None
	 *   
	 * @post
	 * 	 returns Java Object representation of encoded (json, xml, etc) object returned in server response that can be cast to ICommunicator.executeCommand responseCastClass parameter
	 */
	public Object getResponseObject();
	
	/**
	 * Gets the server response message for non success http responses (may be null in case no response message is given)
	 * @pre
	 *   None
	 *  
	 * @post
	 *   server response message for non success http responses (may be null in case no response message is given)
	 */
	public String getResponseMessage();
}
