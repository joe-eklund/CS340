package server.games;

@SuppressWarnings({ "serial" })
public class InvalidCreateGameRequest extends Exception {

	/**
	 * @param message
	 */
	public InvalidCreateGameRequest(String message) {
		super(message);
	}

}
