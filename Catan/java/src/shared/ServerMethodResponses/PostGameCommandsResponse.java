package shared.ServerMethodResponses;

import shared.definitions.ServerModel;

public class PostGameCommandsResponse extends GameModelResponse {
	private String errorMessage;
	
	public PostGameCommandsResponse(boolean successful, ServerModel gameModel,
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
