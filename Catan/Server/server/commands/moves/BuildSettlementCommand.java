package server.commands.moves;

import client.exceptions.ClientModelException;
import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.BuildSettlementRequest;



/**
 * The command class in charge of building settlements
 *
 */
public class BuildSettlementCommand extends AMovesCommand {

	private BuildSettlementRequest request;

	public BuildSettlementCommand(IMovesFacade moves, BuildSettlementRequest request, CookieParams cookieParams) {
		super("BuildSettlementCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.buildSettlement(request, cookieParms);
		} catch (InvalidMovesRequest | ClientModelException e) {
			throw new CommandException(e.getMessage());
		}
	}

}
