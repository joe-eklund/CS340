package shared.ServerMethodResponses;

import shared.definitions.GameDescription;

public class CreateGameResponse extends NonMoveResponse{
	private GameDescription gameDescription;

	public CreateGameResponse(boolean successful,
			GameDescription gameDescription) {
		super(successful);
		this.gameDescription = gameDescription;
	}

	public GameDescription getGameDescription() {
		return gameDescription;
	}

	public void setGameDescription(GameDescription gameDescription) {
		this.gameDescription = gameDescription;
	}
	
}
