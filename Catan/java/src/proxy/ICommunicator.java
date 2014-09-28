package proxy;

import java.util.List;

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
	 *  1) requestType is a valid http request type (so far only "GET" or "POST")
	 *  2) headers is a list of Pair<Key, Value> where Key represents the header title and Value the header value
	 * 	3) commandName is a valid name server command extension address (as specified in server API)
	 *  4) commandParameters is a valid object type (i.e. list<string>, string, int, etc) for commandName (as specified in server API)
	 *  5) serverReturnClass is a valid Class Definition Type (i.e. String.class could be passed for this parameter); this is used to ensure correct typing (and ultimately recasting) of server response json object
	 *  
	 * @post
	 *  1) commandName is executed on server in form of http request with that following based on passed parameters:
	 *  	a) http request type is define by requestType (i.e "GET", "POST")
	 *  	b) http request headers are set according to headers
	 *  2) CommandResponse object is returned according to http response; please note the command response object consists of three (3) fields
	 *  	a) cookieResponseHeader //the result of calling .getHeaderField("Set-cookie") on the connection response and may be null
	 *      b) responseCode	//the resulting http response code from the executed command on the server (i.e. 200, 400, 404, etc.) and may not be null
	 *      c) responseObjectFromJSON //the result of translating any server response json to serverReturnClass type; in the case serverReturnClass = null, responseObjectFromJSON will also be null (i.e. don't worry about looking for / translating a json response
	 * 
	 * @param requsetType ::= valid http request type (so far only "GET" or "POST")
	 * @param headers ::= List of header to be set when sending request; key = header title; value = header value;  (i.e Key may be "Set-cookie" and Value may be "catan.user=%7B%22name%22%3A%22Sam%22%2C%22password%22%3A%22sam%22%2C%22playerID%22%3A0%7D"
	 * @param commandName ::= valid name server command extension address (as specified in server API)
	 * @param commandParameters ::= valid object type (i.e. list<string>, string, int, etc) to be converted to JSON for specified commandName (as specified in server API)
	 * @param responseCastClass ::=	valid Class Definition Type (i.e. String.class could be passed for this parameter); 
	 * 								this is used to ensure correct translation typing (and ultimately recasting) of server response json object
	 */
	public ICommandResponse executeCommand(RequestType requestType, List<Pair<String,String>> headers, String commandName, Object commandParameters, Class<?> responseCastClass);
}
