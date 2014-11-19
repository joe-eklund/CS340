package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.SoldierDevRequest;



/**
 * The command class in charge of playing the soldier development card for a player
 *
 */
public class SoldierCommand extends AMovesCommand {

	private SoldierDevRequest request;

	public SoldierCommand(IMovesFacade moves, SoldierDevRequest request, CookieParams cookieParams) {
		super("SoldierCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.soldier(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}
}
