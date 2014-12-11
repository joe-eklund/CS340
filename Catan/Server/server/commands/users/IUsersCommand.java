package server.commands.users;

import java.io.Serializable;

import server.commands.ICommand;
import server.users.IUsersFacade;

public interface IUsersCommand extends ICommand, Serializable {
	public void setExecutor(IUsersFacade executor);
}
