package server.games;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import shared.definitions.GameDescription;
import shared.definitions.ServerModel;


/**
 * This Facade implements the list, create, join, save, and load commands
 *
 */
public class GamesFacade extends AGamesFacade {

	/**
	 * 
	 */
	public GamesFacade(List<ServerModel> serverModels, ArrayList<GameDescription> gameDescriptions) {
		super(serverModels);
		this.gameDescriptionsList = gameDescriptions;
	}

	@Override
	public Serializable getModel() {
		return this.gameDescriptionsList;
	}

}
