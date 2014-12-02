package database;

import java.io.Serializable;
import java.util.List;

/**
 * This is an interface Database Access Object that gives access to the database for any Non-Move Commands of the Settlers of Catan Game  
 *
 */
public interface INonMoveCommandDAO {
	
	/**
	 * This method allows a client to add a Non-move command to the database
	 * @param command
	 * @param gameID
	 */
	public void add(Serializable command);
	
	/**
	 * Returns a list of all the Non-move commands currently in the database.
	 * @param gameID
	 * @return List of move commands
	 */
	public List<Object> getAll();
	
	/**
	 * This clears all Non-move commands from the database.  After this method is called there will be no Non-move commands in the database.
	 */
	public void clear();
}
