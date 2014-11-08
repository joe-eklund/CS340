package server.games;

import java.util.ArrayList;

import shared.definitions.GameDescription;
import shared.definitions.PlayerDescription;

public class GamesFacadeStub extends AGamesFacade {

	/**
	 * 
	 */
	@SuppressWarnings("serial")
	public GamesFacadeStub() {
		super();
		this.gameDescriptionsList = new ArrayList<GameDescription>(){{
			add(new GameDescription("Empty Game", 0, new ArrayList<PlayerDescription>(4)));
			add(new GameDescription("Second Game", 1, new ArrayList<PlayerDescription>(4) {{
				add(new PlayerDescription("red", 0, "Bobby"));
				add(new PlayerDescription("blue", 1, "Billy"));
				add(new PlayerDescription("green", 2, "Sandy"));
				add(new PlayerDescription("yellow", 3, "Cathy"));
			}}));
		}};
	}
}
