package server.games;

import java.util.ArrayList;
import java.util.List;

import shared.ServerMethodRequests.CreateGameRequest;
import shared.ServerMethodRequests.JoinGameRequest;
import shared.definitions.GameDescription;
import shared.definitions.ServerModel;

public class GamesFacadeStub extends AGamesFacade {

	/**
	 * 
	 */
	//@SuppressWarnings("serial")
	public GamesFacadeStub(List<ServerModel> serverModels) {
		super(serverModels);
		this.gameDescriptionsList = new ArrayList<GameDescription>();
		/*
		this.gameDescriptionsList = new ArrayList<GameDescription>(){{
			add(new GameDescription("Empty Game", 0, new ArrayList<PlayerDescription>(4)));
			add(new GameDescription("Second Game", 1, new ArrayList<PlayerDescription>(4) {{
				add(new PlayerDescription("red", 0, "Bobby"));
				add(new PlayerDescription("blue", 1, "Billy"));
				add(new PlayerDescription("green", 2, "Sandy"));
				add(new PlayerDescription("yellow", 3, "Cathy"));
			}}));
		}};
		*/
		
		try {
			this.createGame(new CreateGameRequest(false, false, false, "Empty Game"));
			this.createGame(new CreateGameRequest(false, false, false, "Second Game"));
			this.joinGame(new JoinGameRequest(1, "red"), "Bobby", 0);
			this.joinGame(new JoinGameRequest(1, "blue"), "Billy", 1);
			this.joinGame(new JoinGameRequest(1, "green"), "Sandy", 2);
			this.joinGame(new JoinGameRequest(1, "yellow"), "Cathy", 3);
			/*
			this.addPlayerToGameModel(1, "Bobby", "red", 0);
			this.addPlayerToGameModel(1, "Billy", "blue", 1);
			this.addPlayerToGameModel(1, "Sandy", "green", 2);
			this.addPlayerToGameModel(1, "Cathy", "yellow", 3);
			*/
		} catch (InvalidCreateGameRequest | InvalidJoinGameRequest e) {
			e.printStackTrace();
		}
	}
}
