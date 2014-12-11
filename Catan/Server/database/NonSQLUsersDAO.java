package database;

import java.io.Serializable;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.thoughtworks.xstream.XStream;

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
		DBCollection collection = db.getDB().getCollection("users");
		XStream xStream = new XStream();
		String xml = xStream.toXML(model);
		
		BasicDBObject dbObject = new BasicDBObject("users", xml);
		
		collection.insert(dbObject);
		
		db.closeDB();
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
