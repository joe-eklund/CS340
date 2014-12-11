package server.commands.users;

import java.util.List;

import server.commands.ICommandLog;
import server.users.IUsersFacade;

public interface IUsersCommandLog extends ICommandLog {

	public void setFacade(IUsersFacade users);
	
	public void storeAll(List<IUsersCommand> commands);

	public void store(IUsersCommand command);

}
