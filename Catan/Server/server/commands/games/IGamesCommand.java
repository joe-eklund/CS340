package server.commands.games;

import java.io.Serializable;

import server.commands.ICommand;
import server.games.IGamesFacade;

public interface IGamesCommand extends ICommand, Serializable {
	public void setExecutor(IGamesFacade executor);
}
