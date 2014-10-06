package shared.ServerMethodResponses;

import shared.definitions.ServerModel;

/**
 * A class to encapsulate the servers response returning a GameModel request
 * 
 * @domain 
 * gameModel: (ServerModel) representing the server model being returned
 *
 */
public class GameModelResponse extends ServerResponse {
	private ServerModel gameModel;

	/**
	 * @obvious
	 */
	public GameModelResponse(boolean successful, ServerModel gameModel) {
		super(successful);
		this.gameModel = gameModel;
	}

	/**
	 * @obvious
	 */
	public ServerModel getGameModel() {
		return gameModel;
	}

	/**
	 * @obvious
	 */
	public void setGameModel(ServerModel gameModel) {
		this.gameModel = gameModel;
	}
	
}
