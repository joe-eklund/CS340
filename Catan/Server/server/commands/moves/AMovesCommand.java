package server.commands.moves;

import server.commands.ACommand;
import server.moves.IMovesFacade;

public abstract class AMovesCommand extends ACommand implements IMovesCommand {
	protected IMovesFacade executor;
	
	public AMovesCommand(String name) {
		super(name);
	}

	@Override
	public void setExecutor(IMovesFacade executor) {
		this.executor = executor;
	}

}
