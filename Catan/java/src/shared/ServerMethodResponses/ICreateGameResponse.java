package shared.ServerMethodResponses;

import shared.definitions.GameDescription;

public interface ICreateGameResponse extends IServerResponse {
	public GameDescription getGameDescription();
}
