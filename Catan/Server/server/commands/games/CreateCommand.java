package server.commands.games;

import proxy.ITranslator;
import server.commands.ACommand;
import server.commands.CommandException;
import server.games.IGamesFacade;
import server.games.InvalidGamesRequest;
import shared.ServerMethodRequests.CreateGameRequest;

/**
 * The command class in charge of creating a new game
 *
 */
public class CreateCommand extends ACommand {
	private IGamesFacade games;
	private CreateGameRequest request;

	public CreateCommand(ITranslator translator, IGamesFacade games) {
		super("CreateCommand");
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
	public void setParam(Object param) {
		try {
			this.request = (CreateGameRequest) param;
		} catch (Exception e) {
			System.err.println("Error: the provided param cannot be cast to CreateGameRequest.");
		}
	}
	
}
