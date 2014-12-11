package server.commands.games;

import server.commands.ACommand;
import server.commands.CommandException;
import server.games.IGamesFacade;
import server.games.InvalidGamesRequest;
import shared.ServerMethodRequests.JoinGameRequest;

/**
 * The command class in charge of joining a player to a game
 *
 */
public class JoinCommand extends ACommand implements IGamesCommand{
	private transient IGamesFacade executor;
	private JoinGameRequest request;
	private String username;
	private int userID;

	public JoinCommand(IGamesFacade executor, JoinGameRequest request, String username, int userID) {
		super("JoinCommand");
		this.executor = executor;
		this.request = request;
		this.username = username;
		this.userID = userID;
	}
	
	public void setUserInfo(String username, int userID) {
		this.username = username;
		this.userID = userID;
	}

	@Override
	public void execute() throws CommandException {
		try {
			executor.joinGame(request, username, userID);
		} catch (InvalidGamesRequest e) {
			throw new CommandException(e.getMessage());
		}
	}

	@Override
	public void setExecutor(IGamesFacade executor) {
		this.executor = executor;
	}

}
