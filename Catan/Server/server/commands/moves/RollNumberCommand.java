package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.RollNumberRequest;


/**
 * The command class in charge of rolling a number for a player
 *
 */
public class RollNumberCommand extends AMovesCommand {

	private RollNumberRequest request;

	public RollNumberCommand(IMovesFacade moves, RollNumberRequest request, CookieParams cookieParams) {
		super("RollNumberCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.rollNumber(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

}
