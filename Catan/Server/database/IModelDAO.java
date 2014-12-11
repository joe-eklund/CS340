package database;

import java.io.Serializable;

/**
 * Interface for accessing data models stored in databases
 * stored model domain ::= List<ServerModel> | List<User> | List<GameDescription
 * 
 */
public interface IModelDAO {
	
	/**
	 * 
	 * @param model ::= model to be added to database
	 * @pre none
	 * @post model is saved to appropriate database table 
	 */
	public void save(Object model);
	
	/**
	 * @pre none
	 * @post returns object representing blob of list of game models
	 */
	public Object load();
	
	/**
	 * @pre none
	 * @post clears the row (drops table-recreates)in associated data model
	 */
	public void clear();
}
