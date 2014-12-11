package database;

import java.io.Serializable;
import java.util.List;

/**
 * This is an interface Database Access Object that gives access to the database for any Move Commands of the Settlers of Catan Game  
 *
 */
public interface IMoveCommandDAO {
	/**
	 * This method allows a client to add a move command to the database
	 * @param command
	 * @param gameID
	 */
	public void add(Object command, int gameID);
	
	/**
	 * Returns a list of all the move commands currently in the database associated with gameID
	 * @param gameID
	 * @return List of move commands
	 */
	public List<Object> getAll(int gameID);
	
	/**
	 * This clears all move commands(drop-recreate) from the database.  After this method is called there will be no move commands in the database.
	 */
	public void clear();
}
