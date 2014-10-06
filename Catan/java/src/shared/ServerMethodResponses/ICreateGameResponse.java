package shared.ServerMethodResponses;

import shared.definitions.GameDescription;

/**
 * An interface that extends IServerResponse and provides method for getGameDescription
 *
 */
public interface ICreateGameResponse extends IServerResponse {
	/**
	 * @post
	 *   returns the GameDescription of the game created in the corresponding CreateGameRequest
	 */
	public GameDescription getGameDescription();
}
