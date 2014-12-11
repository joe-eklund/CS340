package database;

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

public class NonSQLMoveCommandDAO extends AMoveCommandDAO {
private NonSQLPlugin db;
	
	public NonSQLMoveCommandDAO(NonSQLPlugin nonSqlPlugin) {
		db = nonSqlPlugin;
	}
	
	/**
	 * Adds a row with the gameId and command which was processed
	 */
	@Override
	public void add(Serializable command, int gameID){

		db.start();
		DBCollection collection = db.getDB().getCollection("nonmoves");
		XStream xStream = new XStream();
		String xml = xStream.toXML(command);
		
		BasicDBObject dbObject = new BasicDBObject(Integer.toString(gameID), xml);
		
		collection.insert(dbObject);
		db.stop(true);
	}
	
	/**
	 * Loads the list of commands from one game
	 */
	@Override
	public List<Serializable> getAll(int gameID){
		db.start();
		DBCollection collection = db.getDB().getCollection("nonmoves");
		
		XStream xStream = new XStream();
		
		DBCursor cursor = collection.find();

		if (cursor == null)
			return null;
		
		List<Serializable> commands = new ArrayList<Serializable>();
		
		while(cursor.hasNext()) {
			DBObject obj = cursor.next();
			String xml = (String)obj.get(Integer.toString(gameID));
			commands.add((Serializable) xStream.fromXML(xml));
		}
		
		db.stop(true);
		
		return commands;
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
