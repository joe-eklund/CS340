package database;

import java.io.Serializable;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
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
		
		BasicDBObject dbObject = new BasicDBObject("blob", xml);
		
		collection.drop();
		
		collection.insert(dbObject);
		
		db.closeDB();
	}
	
	/**
	 * Loads the blob representing the list of users
	 */
	@Override
	public Serializable load(){
		DBCollection collection = db.getDB().getCollection("users");
		
		XStream xStream = new XStream();
		
		DBObject obj = collection.findOne();
//		try {
//			   while(cursor.hasNext()) {
//			       System.out.println(cursor.next());
//			   }
//			} finally {
//			   cursor.close();
//			}
		String xml = (String)obj.get("blob");
		Serializable xmlObj = (Serializable) xStream.fromXML(xml);
		
		return xmlObj;
		
	}
	/**
	 * Drop table, create empty table
	 */
	@Override
	public void clear(){
		//TODO clear all rows
	}
}
