package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.MaritimeTradeRequest;


/**
 * The command class in charge of maritime trades
 *
 */
public class MaritimeTradeCommand extends AMovesCommand {

	private MaritimeTradeRequest request;

	public MaritimeTradeCommand(IMovesFacade moves, MaritimeTradeRequest request, CookieParams cookieParams) {
		super("MaritimeTradeCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.maritimeTrade(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

}
