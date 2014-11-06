package shared.ServerMethodResponses;

import shared.definitions.ServerModel;

/**
 * A class for encapsulating the server response to a PostGameCommands request
 * 
 * @domain
 *   errorMessage: (String) any error that occurred while trying to deserialize game commands
 *
 */
public class PostGameCommandsResponse extends GameModelResponse {
	private String errorMessage;
	
	/**
	 * @obvious
	 */
	public PostGameCommandsResponse(boolean successful, ServerModel gameModel,
			String errorMessage) {
		super(successful, gameModel);
		this.errorMessage = errorMessage;
	}

	/**
	 * @obvious
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @obvious
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
