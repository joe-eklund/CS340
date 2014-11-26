package server.game;

import java.util.List;

import shared.ServerMethodRequests.AddAIRequest;
import shared.ServerMethodRequests.ResetGameRequest;
import shared.definitions.ServerModel;

public abstract class AGameFacade implements IGameFacade {
	private List<ServerModel> gameModels;
	
	public AGameFacade(List<ServerModel> gameModels) {
		this.gameModels = gameModels;
	}
	
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
	public ServerModel getGameModel(int id) {
		return this.getGameByID(id);
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

	@Override
	public boolean validGameID(int id){
		if(id >= 0 && id < gameModels.size()){
			return true;
		}
		else return false;
	}

	private ServerModel getGameByID(int gameID) {
		ServerModel result = null;
		try {
			result = this.gameModels.get(gameID);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}	
		return result;
	}
}
