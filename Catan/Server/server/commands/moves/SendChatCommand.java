package server.commands.moves;

import server.commands.CommandException;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;
import server.moves.InvalidMovesRequest;
import shared.ServerMethodRequests.SendChatRequest;


/**
 * The command class in charge of sending a chat for a player
 *
 */
public class SendChatCommand extends AMovesCommand {

	private SendChatRequest request;

	public SendChatCommand(IMovesFacade moves, SendChatRequest request, CookieParams cookieParams) {
		super("SendChatCommand", moves, cookieParams);
		this.request = request;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.sendChat(request, cookieParms);
		} catch (InvalidMovesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

}
