package database;

import java.io.Serializable;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.thoughtworks.xstream.XStream;

public class NonSQLGameDescriptionDAO extends AModelDAO{
private NonSQLPlugin db;
	
	public NonSQLGameDescriptionDAO(NonSQLPlugin nonSqlPlugin) {
		db =  nonSqlPlugin;
	}
	/**
	 * Saves the list of game descriptions(serialize it first) into the db-only one blob/row
	 */
	@Override
	public void save(Serializable model){
		db.start();
		DBCollection collection = db.getDB().getCollection("gamedescriptions");
		XStream xStream = new XStream();
		String xml = xStream.toXML(model);
		
		BasicDBObject dbObject = new BasicDBObject("blob", xml);
		
		collection.drop();
		
		collection.insert(dbObject);
		db.stop(true);
	}
	
	/**
	 * Loads the blob representing the list of game descriptions
	 */
	@Override
	public Serializable load(){
		try {
			db.start();
			DBCollection collection = db.getDB().getCollection("gamedescriptions");
			
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
		db.getDB().getCollection("gamedescriptions").drop();
		db.stop(true);
	}
}
