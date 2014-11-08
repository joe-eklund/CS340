package server.game;

import server.game.IGameFacade;
import shared.ServerMethodRequests.AddAIRequest;
import shared.ServerMethodRequests.ResetGameRequest;

public class GameFacadeStub implements IGameFacade {

	@Override
	public int resetGame(ResetGameRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addAI(AddAIRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGameModel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int gameCommands() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int listAI() {
		// TODO Auto-generated method stub
		return 0;
	}

}
