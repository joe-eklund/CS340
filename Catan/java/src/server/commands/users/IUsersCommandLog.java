package server.commands.users;

import server.commands.ICommandLog;
import server.users.IUsersFacade;

public interface IUsersCommandLog extends ICommandLog {

	public void setFacade(IUsersFacade users);

	public void store(IUsersCommand command);

}
