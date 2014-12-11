package database;

import java.io.Serializable;

/**
 * Abstract class for accessing data models stored in databases
 * stored model domain ::= List<ServerModel> | List<User> | List<GameDescription
 * 
 */
public abstract class AModelDAO implements IModelDAO {
	
	/**
	 * Saves the object/model into the db after serializing it-only one blob/row
	 * @param model ::= model to be added to database
	 * @pre none
	 * @post model is saved to appropriate database table 
	 */
	public void save(Object model){
		//TODO save the model to database
	}
	
	/**
	 * Loads the blob(should only be one row) from the table into a serializable object
	 * @pre none
	 * @post returns object representing blob of associated data model to be cast to appropriate model
	 */
	public Object load(){
		//TODO load object
		return null;
	}
	
	/**
	 * Drops table, create new empty table
	 * @pre none
	 * @post clears all rows in associated data model
	 */
	public void clear(){
		//TODO clear all rows
	}
}
