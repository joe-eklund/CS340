package database;

import java.io.Serializable;
import java.util.List;

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

	@Override
	public void add(Serializable command, int gameID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Serializable> getAll(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
