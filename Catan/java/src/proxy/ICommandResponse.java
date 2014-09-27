package proxy;
/**
 * An interface for defining the object returned by ICommunicator.executeCommand
 * @author joshuabgrigg
 *
 */
public interface ICommandResponse {
	/**
	 * Gets 'Set-cookie' response header (may be null in case there is no 'Set-cookie' response header)
	 * @pre
	 *   None
	 * 
	 * @post
	 *   returns catan.user cookie in string of form "catan.user=%7B%22name%22%3A%22Sam%22%2C%22password%22%3A%22sam%22%2C%22playerID%22%3A0%7D" (see http://students.cs.byu.edu/~cs340ta/fall2014/group_project/Cookies.pdf)
	 */
	public String getCookieResponseHeader();
	
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
