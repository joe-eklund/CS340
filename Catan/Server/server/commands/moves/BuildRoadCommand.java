package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.BuildRoadRequest;


/**
 * The command class in charge of bulding roads
 *
 */
public class BuildRoadCommand extends AMovesCommand {

	private BuildRoadRequest request;

	public BuildRoadCommand(IMovesFacade moves, BuildRoadRequest request, CookieParams cookieParams) {
		super("BuildRoadCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.buildRoad(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

}
