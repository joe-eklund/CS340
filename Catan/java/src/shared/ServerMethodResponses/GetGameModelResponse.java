package shared.ServerMethodResponses;

import shared.definitions.ServerModel;

public class GetGameModelResponse extends GameModelResponse {
	private boolean needToUpdate;

	public GetGameModelResponse(boolean successful, ServerModel gameModel,
			boolean needToUpdate) {
		super(successful, gameModel);
		this.needToUpdate = needToUpdate;
	}

	public boolean isNeedToUpdate() {
		return needToUpdate;
	}

	public void setNeedToUpdate(boolean needToUpdate) {
		this.needToUpdate = needToUpdate;
	}
	
}
