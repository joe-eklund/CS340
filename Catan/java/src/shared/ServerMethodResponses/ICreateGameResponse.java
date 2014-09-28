package shared.ServerMethodResponses;

import shared.definitions.GameDescription;

public interface ICreateGameResponse extends INonMoveResponse {
	public GameDescription getGameDescription();
}
