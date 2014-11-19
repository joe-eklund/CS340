package server.commands.games;

import java.io.IOException;

import server.commands.ACommand;
import server.commands.CommandException;
import server.games.IGamesFacade;

/**
 * The command class in charge of loading a game
 *
 */
public class LoadCommand extends ACommand{

	private IGamesFacade games;
	private String gameName;

	public LoadCommand(IGamesFacade games, String gameName) {
		super("LoadCommand");
		this.games = games;
		this.gameName = gameName;
	}

	@Override
	public void execute() throws CommandException {
		try {
			games.loadGame(gameName);
		} catch (IOException e) {
			throw new CommandException(e.getMessage());
		}
	}

}
