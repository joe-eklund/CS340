package database;

import java.io.Serializable;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.thoughtworks.xstream.XStream;


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
		db.start();
		DBCollection collection = db.getDB().getCollection("games");
		XStream xStream = new XStream();
		String xml = xStream.toXML(model);
		
		BasicDBObject dbObject = new BasicDBObject("blob", xml);
		
		collection.drop();
		
		collection.insert(dbObject);
		db.stop(true);
	}
	
	/**
	 * Loads the blob representing the list of game models
	 */
	@Override
	public Serializable load(){
		db.start();
		DBCollection collection = db.getDB().getCollection("games");
		
		XStream xStream = new XStream();
		
		DBObject obj = collection.findOne();

		if (obj == null)
			return null;
		
		String xml = (String)obj.get("blob");
		Serializable xmlObj = (Serializable) xStream.fromXML(xml);
		db.stop(true);
		
		return xmlObj;
	}
	/**
	 * Drop table, create empty table
	 */
	@Override
	public void clear(){
		db.start();
		db.getDB().getCollection("games").drop();
		db.stop(true);
	}
}
