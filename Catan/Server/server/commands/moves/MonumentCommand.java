package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.MonumentDevRequest;


/**
 * The command class in charge of playing the monument development card for a player
 *
 */
public class MonumentCommand extends AMovesCommand {

	private MonumentDevRequest request;

	public MonumentCommand(IMovesFacade moves, MonumentDevRequest request, CookieParams cookieParams) {
		super("MonumentCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.monument(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

}
