package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.BuyDevCardRequest;


/**
 * The command class in charge of buying development cards
 *
 */
public class BuyDevCardCommand extends AMovesCommand {

	private BuyDevCardRequest request;

	public BuyDevCardCommand(IMovesFacade moves, BuyDevCardRequest request, CookieParams cookieParams) {
		super("BuyDevCardCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.buyDevCard(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

}
