package proxy;
/**
 * 
 * An Interface for the Communicator to be used for server-client communication
 *
 */
public interface ICommunicator {
	/**
	 * executes a command on the server
	 * 
	 * @pre
	 * 	1) commandName is a valid name server command extension address (as specified in server API)
	 *  2) commandParameters is a valid object type (i.e. list<string>, string, int, etc) for commandName (as specified in server API)
	 *  3) serverReturnClass is a valid Class Definition Type (i.e. String.class could be passed for this parameter); this is used to ensure correct typing (and ultimately recasting) of server response json object
	 *  
	 * @post
	 *  1) commandName is executed on server
	 *  2) CommandResponse object is returned; please note the command response object consists of three (3) fields
	 *  	a) cookieResponseHeader //the result of calling .getHeaderField("Set-cookie") on the connection response and may be null
	 *      b) responseCode	//the resulting http response code from the executed command on the server (i.e. 200, 400, 404, etc.) and may not be null
	 *      c) responseObjectFromJSON //the result of translating any server response json to serverReturnClass type; in the case serverReturnClass = null, responseObjectFromJSON will also be null (i.e. don't worry about looking for / translating a json response
	 * 
	 * @param commandName ::= valid name server command extension address (as specified in server API)
	 * @param commandParameters ::= valid object type (i.e. list<string>, string, int, etc) to be converted to JSON for specified commandName (as specified in server API)
	 * @param responseCastClass ::=	valid Class Definition Type (i.e. String.class could be passed for this parameter); 
	 * 								this is used to ensure correct translation typing (and ultimately recasting) of server response json object
	 */
	public ICommandResponse executeCommand(String commandName, Object commandParameters, Class<?> responseCastClass);
}
