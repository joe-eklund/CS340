package server.games;

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
	public GamesFacade(List<ServerModel> serverModels) {
		super(serverModels);
		this.gameDescriptionsList = new ArrayList<GameDescription>();
	}

}
