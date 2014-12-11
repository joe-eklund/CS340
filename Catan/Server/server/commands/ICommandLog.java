package server.commands;

import database.IDBFactoryPlugin;

public interface ICommandLog {
	public void clear();
	public void executeAll();
	public void setDBPlugin(IDBFactoryPlugin dbPlugin);
	public void storeAllAndClear();
}
