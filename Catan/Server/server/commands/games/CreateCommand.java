package server.commands.games;

import server.commands.ACommand;
import server.commands.CommandException;
import server.games.IGamesFacade;
import server.games.InvalidGamesRequest;
import shared.ServerMethodRequests.CreateGameRequest;

/**
 * The command class in charge of creating a new game
 *
 */
public class CreateCommand extends ACommand implements IGamesCommand {
	private transient IGamesFacade games;
	private CreateGameRequest request;

	public CreateCommand(IGamesFacade games, CreateGameRequest request) {
		super("CreateCommand");
		this.games = games;
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			games.createGame(request);
		} catch (InvalidGamesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

	@Override
	public void setExecutor(IGamesFacade executor) {
		this.games = executor;
	}
	
}
