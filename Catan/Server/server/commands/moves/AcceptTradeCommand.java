package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.AcceptTradeRequest;

/**
 * The command class in charge of accepting trades
 *
 */
public class AcceptTradeCommand extends AMovesCommand {
	
	private AcceptTradeRequest request;

	public AcceptTradeCommand(IMovesFacade moves, AcceptTradeRequest request, CookieParams cookieParams) {
		super("AcceptTradeCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.acceptTrade(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

	@Override
	public void setExecutor(IMovesFacade executor) {
		this.setExecutor(executor);
	}

}
