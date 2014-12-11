package nonsql;

import java.io.Serializable;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.thoughtworks.xstream.XStream;

import database.AModelDAO;

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
		db.start();
		DBCollection collection = db.getDB().getCollection("users");
		XStream xStream = new XStream();
		String xml = xStream.toXML(model);
		
		BasicDBObject dbObject = new BasicDBObject("blob", xml);
		
		collection.drop();
		
		collection.insert(dbObject);
		db.stop(true);
		
	}
	
	/**
	 * Loads the blob representing the list of users
	 */
	@Override
	public Serializable load(){
		try {
			db.start();
			DBCollection collection = db.getDB().getCollection("users");
			
			XStream xStream = new XStream();
			
			DBObject obj = collection.findOne();
	
			if (obj == null)
				return null;
			
			String xml = (String)obj.get("blob");
			Serializable xmlObj = (Serializable) xStream.fromXML(xml);
			db.stop(true);
			
			return xmlObj;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * Drop table, create empty table
	 */
	@Override
	public void clear(){
		db.start();
		db.getDB().getCollection("users").drop();
		db.stop(true);
	}
}
