package database;

import java.io.Serializable;

/**
 * Abstract class for accessing data models stored in databases
 * stored model domain ::= List<ServerModel> | List<User> | List<GameDescription
 * 
 */
public abstract class AModelDAO implements IModelDAO {
	
	/**
	 * 
	 * @param model ::= model to be added to database
	 * @pre none
	 * @post model is saved to appropriate database table 
	 */
	public void save(Serializable model){
		//TODO save the model to database
	}
	
	/**
	 * @pre none
	 * @post returns object representing blob of associated data model to be cast to appropriate model
	 */
	public Object load(){
		//TODO load object
		return null;
	}
	
	/**
	 * @pre none
	 * @post clears all rows in associated data model
	 */
	public void clear(){
		//TODO clear all rows
	}
}
