package shared.ServerMethodResponses;

/**
 * An interface for all server responses
 */
public interface IServerResponse {
	/**
	 * gets success status of corresponding server request
	 * @post
	 *   returns true/false based on whether request was successful
	 */
	public boolean isSuccessful();
}
