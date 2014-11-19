package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.FinishTurnRequest;


/**
 * The command class in charge of finishing a players turn
 *
 */
public class FinishTurnCommand extends AMovesCommand {

	private FinishTurnRequest request;

	public FinishTurnCommand(IMovesFacade moves, FinishTurnRequest request, CookieParams cookieParams) {
		super("FinishTurnCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.finishTurn(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

}
