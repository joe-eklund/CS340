package shared.ServerMethodResponses;

import shared.definitions.GameModel;

public class PostGameCommandsResponse extends GameModelResponse {
	private String errorMessage;
	
	public PostGameCommandsResponse(boolean successful, GameModel gameModel,
			String errorMessage) {
		super(successful, gameModel);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
