package server.commands.users;

import server.commands.ICommand;
import server.users.IUsersFacade;

public interface IUsersCommand extends ICommand {
	public void setExecutor(IUsersFacade executor);
}
