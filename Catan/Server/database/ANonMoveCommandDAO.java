package database;

/**
 * This is an abstract Database Access Object that gives access to the database for any Non-Move Commands of the Settlers of Catan Game  
 *
 */
public abstract class ANonMoveCommandDAO implements INonMoveCommandDAO {

	/**
	 * Gets the commands name
	 * @return Information about the the command
	 */
	public String getName(){
		return null;
	}
}
