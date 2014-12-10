package database;

import java.io.Serializable;

public class NonSQLGameModelDAO extends AModelDAO{
	private NonSQLPlugin db;
	
	public NonSQLGameModelDAO(NonSQLPlugin nonSqlPlugin) {
		db = nonSqlPlugin;
	}
	
	/**
	 * Saves the list of game models(serialize it first) into the db-only one blob/row
	 */
	@Override
	public void save(Serializable model){
		//TODO save the model to database
	}
	
	/**
	 * Loads the blob representing the list of game models
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
