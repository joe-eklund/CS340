package shared.ServerMethodResponses;

import java.util.List;

import shared.definitions.GameDescription;

/**
 * A class for encapsulating the server response to ListGames request 
 * 
 * @Domain
 *  gameDescriptions: list of GameDescription representing all active games on server
 *
 */
public class ListGamesResponse extends ServerResponse implements IListGamesResponse {
	private List<GameDescription> gameDescriptions;

	/**
	 * @obvious
	 */
	public ListGamesResponse(boolean successful,
			List<GameDescription> gameDescriptions) {
		super(successful);
		this.gameDescriptions = gameDescriptions;
	}

	/**
	 * @obvious
	 */
	public List<GameDescription> getGameDescriptions() {
		return gameDescriptions;
	}

	/**
	 * @obvious
	 */
	public void setGameDescriptions(List<GameDescription> gameDescriptions) {
		this.gameDescriptions = gameDescriptions;
	}

}
