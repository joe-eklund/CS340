package server.commands.moves;

import java.io.Serializable;

import server.commands.ICommand;
import server.moves.IMovesFacade;

public interface IMovesCommand extends ICommand, Serializable {
	public void setExecutor(IMovesFacade executor);
}
