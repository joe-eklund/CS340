package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.OfferTradeRequest;


/**
 * The command class in charge of offering trades for one player to another
 *
 */
public class OfferTradeCommand extends AMovesCommand {
	private OfferTradeRequest request;

	public OfferTradeCommand(IMovesFacade moves, OfferTradeRequest request, CookieParams cookieParams) {
		super("OfferTradeCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.offerTrade(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

}
