package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.RobPlayerRequest;


/**
 * The command class in charge of robbing a player
 *
 */
public class RobPlayerCommand extends AMovesCommand {

	private RobPlayerRequest request;

	public RobPlayerCommand(IMovesFacade moves, RobPlayerRequest request, CookieParams cookieParams) {
		super("RobPlayerCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.robPlayer(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

}
