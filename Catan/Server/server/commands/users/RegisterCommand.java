package server.commands.users;

import server.commands.ACommand;
import server.users.IUsersFacade;
import shared.ServerMethodRequests.UserRequest;

/**
 * The command class in charge registering a player to the system
 *
 */
public class RegisterCommand extends ACommand implements IUsersCommand{
	private IUsersFacade executor;
	private UserRequest request;
	
	public RegisterCommand(IUsersFacade executor, UserRequest request) {
		super("RegisterCommand");
		this.executor = executor;
		this.request = request;
	}

	@Override
	public void execute() {
		executor.registerUser(request);
	}

	@Override
	public void setExecutor(IUsersFacade executor) {
		this.executor = executor;
	}

}
