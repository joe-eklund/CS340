package shared.ServerMethodResponses;

import shared.definitions.GameDescription;

/**
 * A class to encapsulate the servers response for CreateGame request
 * 
 * @domain 
 * gameDescription: a description of type GameDescription that provides information about the game such as players and their indices and colors and the game's name
 *
 */
public class CreateGameResponse extends ServerResponse implements ICreateGameResponse{
	private GameDescription gameDescription;

	/**
	 * @obvious
	 */
	public CreateGameResponse(boolean successful,
			GameDescription gameDescription) {
		super(successful);
		this.gameDescription = gameDescription;
	}

	/**
	 * @obvious
	 */
	public GameDescription getGameDescription() {
		return gameDescription;
	}

	/**
	 * @obvious
	 */
	public void setGameDescription(GameDescription gameDescription) {
		this.gameDescription = gameDescription;
	}
	
}
