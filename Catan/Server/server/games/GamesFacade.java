package server.games;

import java.util.ArrayList;

import shared.definitions.GameDescription;


/**
 * This Facade implements the list, create, join, save, and load commands
 *
 */
public class GamesFacade extends AGamesFacade {

	/**
	 * 
	 */
	public GamesFacade() {
		super();
		this.gameDescriptionsList = new ArrayList<GameDescription>();
	}

}
