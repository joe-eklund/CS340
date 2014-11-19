package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.YearOfPlentyDevRequest;


/**
 * The command class in charge of playing the year of plenty development card for a player
 *
 */
public class YearOfPlentyCommand extends AMovesCommand {

	private YearOfPlentyDevRequest request;

	public YearOfPlentyCommand(IMovesFacade moves, YearOfPlentyDevRequest request, CookieParams cookieParams) {
		super("YearOfPlentyCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.yearOfPlenty(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

}
