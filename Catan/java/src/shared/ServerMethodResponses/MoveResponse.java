package shared.ServerMethodResponses;

import shared.definitions.ServerModel;

/**
 * A class for encapsulating the server response to any of the move requests (commands)
 * @domain
 *   gameModel: (ServerModel) model representing the most current server game model
 *
 */
public class MoveResponse extends ServerResponse{
	private ServerModel gameModel;
	
	/**
	 * @obvious see UserResponse
	 */
	public MoveResponse(boolean successful, ServerModel gameModel) {
		super(successful);
		this.gameModel = gameModel;
	}

	/**
	 * @obvious see UserResponse
	 */
	public ServerModel getGameModel() {
		return gameModel;
	}

	/**
	 * @obvious see UserResponse
	 */
	public void setGameModel(ServerModel gameModel) {
		this.gameModel = gameModel;
	}

}
