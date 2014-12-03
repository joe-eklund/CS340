package database;

/**
 * This is an abstract Database Access Object that gives access to the database for any Move Commands of the Settlers of Catan Game  
 *
 */
public abstract class AMoveCommandDAO implements IMoveCommandDAO {
	
	/**
	 * Gets the commands name
	 * @return Information about the the command
	 */
	public String getName(){
		return null;
	}
}
