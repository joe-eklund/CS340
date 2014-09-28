package shared.ServerMethodResponses;

import java.util.List;

import shared.definitions.GameDescription;

public interface IListGamesResponse extends INonMoveResponse{
	public List<GameDescription> getGameDescriptions();
}
