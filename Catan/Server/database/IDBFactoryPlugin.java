package database;

/**
 * Interface for getting the DAOs to access and execute commands on a database.
 * Any database plugin that adheres to this API will work with the server.
 */
public interface IDBFactoryPlugin {
	
	/**
	 * Gets the model DAO to access the database.
	 * @param type	The type of database, such as sqlite or mongodb.
	 * @return		AModelDAO object to access the model in the database.
	 */
	public AModelDAO getModelDAO(String type);
	
	/**
	 * Gets the Move Command DAO to access the database.
	 * @return	AMoveCommandDAO object to access the move commands in the database.
	 */
	public AMoveCommandDAO getMoveCommandDAO();
	
	/**
	 * Gets the Non Move Command DAO to access the database.
	 * @return	ANonMoveCommandDAO object to access the non move commands in the database.
	 */
	public ANonMoveCommandDAO getNonMoveCommandDAO();
	
	/**
	 * Starts a command to the database.
	 */
	public void start();
	
	/**
	 * Stops a command to the database.
	 * @param success	True or false to execute the command to the database.
	 */
	public void stop(boolean success);
	
	/**
	 * Clears all tables in the current database plugin being used.
	 */
	public void clearAllTables();
}
