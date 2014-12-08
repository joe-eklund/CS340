package database;

import java.io.Serializable;
import java.util.List;

/**
 * This is an abstract Database Access Object that gives access to the database for any Non-Move Commands of the Settlers of Catan Game  
 *
 */
public abstract class ANonMoveCommandDAO implements INonMoveCommandDAO {

	@Override
	public void add(Serializable command, String type) {
		// TODO Auto-generated method stub
			// insert into ...
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
			// drop table
			// create table
		
	}

	@Override
	public List<Serializable> getAll(String type) {
		// TODO Auto-generated method stub
			// SELECT * FROM [non move command table] WHERE type = '[type]'
		return null;
	}
}
