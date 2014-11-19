package server.commands.games;

import server.commands.ICommandLog;
import server.games.IGamesFacade;

public interface IGamesCommandLog extends ICommandLog {

	public void SetFacade(IGamesFacade games);

	public void Store(IGamesCommand command);

}
