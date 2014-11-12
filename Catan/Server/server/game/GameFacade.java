package server.game;

import java.util.List;

import shared.definitions.ServerModel;

/**
 * This Facade implements the model, reset, commands, addAI, listAI commands 
 */
public class GameFacade extends AGameFacade {

	public GameFacade(List<ServerModel> gameModels) {
		super(gameModels);
	}

}
