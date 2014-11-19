package server.commands.games;

import server.commands.ICommand;
import server.games.IGamesFacade;

public interface IGamesCommand extends ICommand {
	public void setExecutor(IGamesFacade executor);
}
