package shared.ServerMethodResponses;

import shared.definitions.GameModel;

public class GameModelResponse extends NonMoveResponse {
	private GameModel gameModel;

	public GameModelResponse(boolean successful, GameModel gameModel) {
		super(successful);
		this.gameModel = gameModel;
	}

	public GameModel getGameModel() {
		return gameModel;
	}

	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	
}
