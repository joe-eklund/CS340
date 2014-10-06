package shared.ServerMethodResponses;

import java.util.List;

import shared.definitions.GameDescription;

/**
 * An interface that defines the ability to return a list of GameDescription that represent active games.  Extends IServerResponse
 */
public interface IListGamesResponse extends IServerResponse{
	/**
	 * gets the list of game descriptions returned after a ListGamesRequest
	 * @post
	 *   list of game descriptions representing current games on server
	 */
	public List<GameDescription> getGameDescriptions();
}
