package server.commands.games;

import java.util.List;

import server.commands.ICommandLog;
import server.games.IGamesFacade;

public interface IGamesCommandLog extends ICommandLog {

	public void SetFacade(IGamesFacade games);

	public void storeAll(List<IGamesCommand> commands);
	
	public void Store(IGamesCommand command);

}
