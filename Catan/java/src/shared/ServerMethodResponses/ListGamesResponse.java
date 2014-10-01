package shared.ServerMethodResponses;

import java.util.List;

import shared.definitions.GameDescription;

public class ListGamesResponse extends ServerResponse implements IListGamesResponse {
	private List<GameDescription> gameDescriptions;

	public ListGamesResponse(boolean successful,
			List<GameDescription> gameDescriptions) {
		super(successful);
		this.gameDescriptions = gameDescriptions;
	}

	public List<GameDescription> getGameDescriptions() {
		return gameDescriptions;
	}

	public void setGameDescriptions(List<GameDescription> gameDescriptions) {
		this.gameDescriptions = gameDescriptions;
	}

}
