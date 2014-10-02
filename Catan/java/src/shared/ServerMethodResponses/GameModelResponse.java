package shared.ServerMethodResponses;

import shared.definitions.ServerModel;

public class GameModelResponse extends ServerResponse {
	private ServerModel gameModel;

	public GameModelResponse(boolean successful, ServerModel gameModel) {
		super(successful);
		this.gameModel = gameModel;
	}

	public ServerModel getGameModel() {
		return gameModel;
	}

	public void setGameModel(ServerModel gameModel) {
		this.gameModel = gameModel;
	}
	
}
