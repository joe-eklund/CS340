package server.cookie;

@SuppressWarnings("serial")
public class InvalidCookieException extends Exception{

	/**
	 * @param message
	 */
	public InvalidCookieException(String message) {
		super(message);
	}
	
}
