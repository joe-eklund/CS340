package shared.ServerMethodResponses;

import shared.definitions.ServerModel;

/**
 * A class to encapsulate the servers response returning a GetGameModel request (update request)
 * 
 * @domain 
 * gameModel: (ServerModel) representing the server model being returned
 * needToUpdate: (Boolean) true/false is the server model version different thatn the clients version
 *
 */
public class GetGameModelResponse extends GameModelResponse {
	private boolean needToUpdate;

	/**
	 * @obvious
	 * @param successful
	 * @param gameModel
	 * @param needToUpdate
	 */
	public GetGameModelResponse(boolean successful, ServerModel gameModel,
			boolean needToUpdate) {
		super(successful, gameModel);
		this.needToUpdate = needToUpdate;
	}

	/**
	 * @obvious
	 */
	public boolean isNeedToUpdate() {
		return needToUpdate;
	}

	/**
	 * @obvious
	 */
	public void setNeedToUpdate(boolean needToUpdate) {
		this.needToUpdate = needToUpdate;
	}
	
}
