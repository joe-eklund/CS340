package shared.ServerMethodResponses;

import shared.definitions.GameModel;

public class MoveResponse extends ServerResponse{
	private GameModel gameModel;
	
	public MoveResponse(boolean successful, GameModel gameModel) {
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
