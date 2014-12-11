package database;

import java.io.Serializable;
import java.util.List;

/**
 * This is an interface Database Access Object that gives access to the database for any Non-Move Commands of the Settlers of Catan Game  
 *
 */
public interface INonMoveCommandDAO {
	
	/**
	 * This method allows a client to add a single Non-move command to the database
	 * @param type ::= users | gameDescripions
	 * @param command
	 * @param gameID
	 */
	public void add(Object command, String type);
	
	/**
	 * Returns a list of all the Non-move commands of "type" currently in the database.
	 * @param type ::= users | gameDescripions
	 * @return List of move commands
	 */
	public List<Object> getAll(String type);
	
	/**
	 * This clears all Non-move commands(drop-recreate) from the database.  After this method is called there will be no Non-move commands in the database.
	 */
	public void clear();
}
