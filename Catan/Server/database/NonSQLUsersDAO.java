package database;

import java.io.Serializable;

public class NonSQLUsersDAO extends AModelDAO{
private NonSQLPlugin db;
	
	public NonSQLUsersDAO(NonSQLPlugin nonSqlPlugin) {
		db = nonSqlPlugin;
	}
	/**
	 * Saves the list of users(serialize it first) into the db-only one blob/row
	 */
	@Override
	public void save(Serializable model){
		//TODO save the model to database
	}
	
	/**
	 * Loads the blob representing the list of users
	 */
	@Override
	public Serializable load(){
		return null;
	}
	/**
	 * Drop table, create empty table
	 */
	@Override
	public void clear(){
		//TODO clear all rows
	}
}
