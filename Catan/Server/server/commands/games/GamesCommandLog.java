package server.commands.games;

import java.util.ArrayList;

import server.commands.CommandException;
import server.games.IGamesFacade;

public class GamesCommandLog implements IGamesCommandLog {
	private IGamesFacade games;
	private ArrayList<IGamesCommand> _gamesCommandLog;
	
	public GamesCommandLog() {
		_gamesCommandLog = new ArrayList<IGamesCommand>();
	}
	
	@Override
	public void SetFacade(IGamesFacade games) {
		this.games = games;
	}
	
	@Override
	public void Store(IGamesCommand command) {
		_gamesCommandLog.add(command);
	}
	
	@Override
	public void clear() {
		_gamesCommandLog = new ArrayList<IGamesCommand>();
	}

	@Override
	public void executeAll() {
		try {
			for(IGamesCommand c : _gamesCommandLog) {
				c.setExecutor(games);
				c.execute();
			}
		} catch (CommandException e) {
			System.err.println("Error: Illegal/Invalid command was stored in GamesCommandLog");
			e.printStackTrace();
		}
	}

}
