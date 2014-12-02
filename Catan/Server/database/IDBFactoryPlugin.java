package database;

public interface IDBFactoryPlugin {
	public AModelDAO getModelDAO(String type);
	public AMoveCommandDAO getMoveCommandDAO();
	public ANonMoveCommandDAO getNonMoveCommandDAO();
	public void start();
	public void stop(boolean success);
	public void clearAllTables();
}
