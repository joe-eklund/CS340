package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.BuildCityRequest;
import client.exceptions.ClientModelException;

/**
 * The command class in charge of building cities
 *
 */
public class BuildCityCommand extends AMovesCommand {
	private BuildCityRequest request;

	public BuildCityCommand(IMovesFacade moves, BuildCityRequest request, CookieParams cookieParams) {
		super("BuildCityCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			this.executor.buildCity(request, cookieParms);
		} catch (InvalidMovesRequest | ClientModelException e) {
			throw new CommandException(e.getMessage());
		}
	}

}
