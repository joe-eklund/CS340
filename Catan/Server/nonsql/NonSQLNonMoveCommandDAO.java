package nonsql;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.thoughtworks.xstream.XStream;

import database.ANonMoveCommandDAO;

public class NonSQLNonMoveCommandDAO extends ANonMoveCommandDAO {
private NonSQLPlugin db;
	
	public NonSQLNonMoveCommandDAO(NonSQLPlugin nonSqlPlugin) {
		db = nonSqlPlugin;
	}
	
	/**
	 * Adds a row with the type and command which was processed
	 */
	@Override
	public void add(Object command, String type){
		db.start();
		DBCollection collection = db.getDB().getCollection("nonmoves");
		XStream xStream = new XStream();
		String xml = xStream.toXML(command);
		
		BasicDBObject dbObject = new BasicDBObject(type, xml);
		
		collection.insert(dbObject);
		db.stop(true);
	}
	
	/**
	 * Loads the list of commands of type
	 */
	@Override
	public List<Object> getAll(String type){
		try {
			db.start();
			DBCollection collection = db.getDB().getCollection("nonmoves");
			
			XStream xStream = new XStream();
			
			DBCursor cursor = collection.find();
	
			if (cursor == null)
				return null;
			
			List<Object> commands = new ArrayList<Object>();
			
			while(cursor.hasNext()) {
				DBObject obj = cursor.next();
				String xml = (String)obj.get(type);
				if (xml != null)
					commands.add((Object) xStream.fromXML(xml));
			}
			
			
			db.stop(true);
			
			return commands;
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
		db.getDB().getCollection("nonmoves").drop();
		db.stop(true);
	}
}
