package shared.ServerMethodResponses;

/**
 * 
 * An abstract class to serve as base for Server responses to all game commands and implements IServerResponse
 * 
 * @domain
 *   successful: (boolean) true/false success status of the associated game command request
 *
 */
public abstract class ServerResponse implements IServerResponse {
	boolean successful;

	/**
	 * @obvious see UserResponse
	 */
	public ServerResponse(boolean successful) {
		this.successful = successful;
	}

	/**
	 * @obvious see UserResponse
	 */
	public boolean isSuccessful() {
		return successful;
	}

	/**
	 * @obvious see UserResponse
	 */
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

}
