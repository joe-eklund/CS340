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
	 *  
	 * @post
	 *  1) commandName is executed on server
	 *  2) commandReturn object is returned
	 * 
	 * @param commandName
	 * @param commandParameters
	 */
	public Object executeCommand(String commandName, Object commandParameters);
}
