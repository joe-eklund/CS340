package server.commands.users;

import java.util.ArrayList;
import java.util.List;

import database.IDBFactoryPlugin;
import server.commands.CommandException;
import server.users.IUsersFacade;

public class UsersCommandLog implements IUsersCommandLog {
	private IUsersFacade users;
	private ArrayList<IUsersCommand> _usersCommandLog;
	private IDBFactoryPlugin dbPlugin;
	
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
		this.dbPlugin.start();
		this.dbPlugin.getNonMoveCommandDAO().add(command, "User");
		this.dbPlugin.stop(true);
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

	@Override
	public void storeAll(List<IUsersCommand> commands) {
		_usersCommandLog = (ArrayList<IUsersCommand>) commands;
	}

	@Override
	public void setDBPlugin(IDBFactoryPlugin dbPlugin) {
		this.dbPlugin = dbPlugin;
	}

	@Override
	public void storeAllAndClear() {
		this.dbPlugin.start();
		this.dbPlugin.getModelDAO("Users").save(this.users.getModel());
		this.dbPlugin.stop(true);
		
	}

}
