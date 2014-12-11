package server.commands.moves;

import java.util.ArrayList;
import java.util.List;

import database.IDBFactoryPlugin;
import server.commands.CommandException;
import server.commands.games.GamesCommandLog;
import server.commands.users.UsersCommandLog;
import server.moves.IMovesFacade;

public class MovesCommandLog implements IMovesCommandLog {
	private IMovesFacade movesFacade;
	private ArrayList<IMovesCommand> _movesCommandLog;
	private IDBFactoryPlugin dbPlugin;
	private GamesCommandLog gamesLog;
	private UsersCommandLog usersLog;
	private int DeltaThreshold;

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
		
		this.dbPlugin.start();
		this.dbPlugin.getMoveCommandDAO().add(command, command.getGameID());;
		this.dbPlugin.stop(true);
		
		if(_movesCommandLog.size() > DeltaThreshold) {
			this.storeAllAndClear();
		}
		
		
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

	@Override
	public void storeAll(List<IMovesCommand> commands) {
		_movesCommandLog = (ArrayList<IMovesCommand>) commands;
	}

	@Override
	public void setDBPlugin(IDBFactoryPlugin dbPlugin) {
		this.dbPlugin = dbPlugin;
	}

	@Override
	public void setGamesLog(GamesCommandLog gamesLog) {
		this.gamesLog = gamesLog;
	}

	@Override
	public void setUsersLog(UsersCommandLog usersLog) {
		this.usersLog = usersLog;
	}

	@Override
	public void storeAllAndClear() {
		this.dbPlugin.start();
		this.dbPlugin.clearAllTables();
		this.dbPlugin.stop(true);
		this.gamesLog.storeAllAndClear();
		this.usersLog.storeAllAndClear();
		this.dbPlugin.start();
		this.dbPlugin.getModelDAO("Game Model").save(this.movesFacade.getModelsList());
		this.dbPlugin.stop(true);
		
	}

	@Override
	public void setDeltaThreshold(int deltaThreshold) {
		DeltaThreshold = deltaThreshold;
	}
}
