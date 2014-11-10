package server.game;

import java.util.List;

import shared.ServerMethodRequests.AddAIRequest;
import shared.ServerMethodRequests.ResetGameRequest;
import shared.definitions.ServerModel;

/**
 * This Facade implements the model, reset, commands, addAI, listAI commands 
 */
public class GameFacade implements IGameFacade {

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
	public ServerModel getGameModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int gameCommands() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> listAI() {
		// TODO Auto-generated method stub
		return null;
	}

}
