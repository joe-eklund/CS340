package server.commands.moves;

import java.util.ArrayList;

import server.commands.CommandException;
import server.moves.IMovesFacade;

public class MovesCommandLog implements IMovesCommandLog {
	private IMovesFacade movesFacade;
	private ArrayList<IMovesCommand> _movesCommandLog;

	public MovesCommandLog() {
		_movesCommandLog = new ArrayList<IMovesCommand>();
	}
	
	@Override
	public void setFacade(IMovesFacade movesFacade) {
		this.movesFacade = movesFacade;
	}

	@Override
	public void store(IMovesCommand command) {
		_movesCommandLog.add(command);
	}
	@Override
	public void clear() {
		_movesCommandLog = new ArrayList<IMovesCommand>();
	}

	@Override
	public void executeAll() {
		try {
			for(IMovesCommand c : _movesCommandLog) {
				c.setExecutor(movesFacade);
				c.execute();
			}
		} catch (CommandException e) {
			System.err.println("Error: Illegal/Invalid command was stored in MovesCommandLog");
			e.printStackTrace();
		}
	}

}
