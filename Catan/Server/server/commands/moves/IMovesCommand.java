package server.commands.moves;

import server.commands.ICommand;
import server.moves.IMovesFacade;

public interface IMovesCommand extends ICommand {
	public void setExecutor(IMovesFacade executor);
}
