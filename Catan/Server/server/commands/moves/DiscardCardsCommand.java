package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.DiscardCardsRequest;


/**
 * The command class in charge of discarding cards
 *
 */
public class DiscardCardsCommand extends AMovesCommand {

	private DiscardCardsRequest request;

	public DiscardCardsCommand(IMovesFacade moves, DiscardCardsRequest request, CookieParams cookieParams) {
		super("DiscardCardsCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.discardCards(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

}
