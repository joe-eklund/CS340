package server.commands.users;

import java.util.ArrayList;

import server.commands.CommandException;
import server.users.IUsersFacade;

public class UsersCommandLog implements IUsersCommandLog {
	private IUsersFacade users;
	private ArrayList<IUsersCommand> _usersCommandLog;
	
	public UsersCommandLog() {
		_usersCommandLog = new ArrayList<IUsersCommand>();
	}
	
	@Override
	public void setFacade(IUsersFacade users) {
		this.users = users;
	}
	
	@Override
	public void store(IUsersCommand command) {
		_usersCommandLog.add(command);
	}

	@Override
	public void clear() {
		_usersCommandLog = new ArrayList<IUsersCommand>();
	}

	@Override
	public void executeAll() {
		try {
			for(IUsersCommand c : _usersCommandLog) {
				c.setExecutor(users);
				c.execute();
			}
		} catch (CommandException e) {
			System.err.println("Error: Illegal/Invalid command was stored in UsersCommandLog");
			e.printStackTrace();
		}
	}

}
