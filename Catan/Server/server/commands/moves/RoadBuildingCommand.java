package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.RoadBuildingDevRequest;


/**
 * The command class in charge of playing the road building development card for a player
 *
 */
public class RoadBuildingCommand extends AMovesCommand {

	private RoadBuildingDevRequest request;

	public RoadBuildingCommand(IMovesFacade moves, RoadBuildingDevRequest request, CookieParams cookieParams) {
		super("RoadBuildingCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.roadBuilding(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

}
