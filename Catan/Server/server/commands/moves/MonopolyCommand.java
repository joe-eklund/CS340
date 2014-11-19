package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.MonopolyDevRequest;


/**
 * The command class in charge of playing a monolpoly development card for a player
 *
 */
public class MonopolyCommand extends AMovesCommand {

	private MonopolyDevRequest request;

	public MonopolyCommand(IMovesFacade moves, MonopolyDevRequest request, CookieParams cookieParams) {
		super("MonopolyCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.monopoly(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

}
