package server.game;

import java.util.ArrayList;
import java.util.List;

import shared.ServerMethodRequests.AddAIRequest;
import shared.definitions.ServerModel;

/**
 * This Facade implements the model, reset, commands, addAI, listAI commands 
 */
public class GameFacade extends AGameFacade {

	public GameFacade(List<ServerModel> gameModels) {
		super(gameModels);
	}
	@Override
	public List<String> listAI() {
		List<String> ai=new ArrayList<String>();
		ai.add("Easy AI");
		ai.add("Medium AI");
		ai.add("Hard AI");
		return ai;
	}
	@Override
	public int addAI(AddAIRequest request) {
		
		return 0;
	}
}
