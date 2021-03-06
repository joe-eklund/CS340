package sql;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import database.AMoveCommandDAO;

public class SQLMoveCommandDAO extends AMoveCommandDAO {

	private SQLPlugin db;
	
	public SQLMoveCommandDAO(SQLPlugin sqlPlugin) {
		db = sqlPlugin;
	}
	
	/**
	 * Adds a row with the gameId and command which was processed
	 */
	@Override
	public void add(Object command, int gameID){

		XStream xStream = new XStream(new DomDriver());
	    try {
	    	String xml = xStream.toXML(command);
			PreparedStatement pstmt = db.getConnection().prepareStatement("insert into MoveCommand (game,command) values (?,?)");
		    /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ObjectOutputStream oos = new ObjectOutputStream(baos);
		    oos.writeObject(command);
		    byte[] modelAsBytes = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(modelAsBytes);*/
			pstmt.setLong(1, gameID);
			pstmt.setString(2, xml);
		    //pstmt.setBinaryStream(2, bais, modelAsBytes.length);
		    pstmt.executeUpdate();
		    db.getConnection().commit();
		    pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the list of commands from one game
	 */
	@Override
	public List<Object> getAll(int gameID){
		XStream xStream = new XStream(new DomDriver());
		List<Object> model=new ArrayList<Object>();
		Object temp=null;
		Statement stmt=null;
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT command FROM MoveCommand where game = " + gameID);
		    db.getConnection().commit();
			while (rs.next()) {
				/*byte[] st = (byte[]) rs.getObject(1);
				ByteArrayInputStream baip = new ByteArrayInputStream(st);
				ObjectInputStream ois = new ObjectInputStream(baip);
				temp = (Object) ois.readObject();
				model.add(temp);*/
				String xml = (String)rs.getString(1);
				model.add((Object) xStream.fromXML(xml));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return model;
	}
	
	/**
	 * Drop table, create empty table
	 */
	@Override
	public void clear(){

		String dropMoveCommand="DROP TABLE MoveCommand";
		String makeMoveCommand="CREATE TABLE MoveCommand " +
				"(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," + 
				"game INTEGER NOT NULL," + 
				"command BLOB NOT NULL)";
		Statement stmt = null;
		try {		
			stmt = db.getConnection().createStatement();
			stmt.addBatch(dropMoveCommand);
			stmt.addBatch(makeMoveCommand);
			stmt.executeBatch();
		    db.getConnection().commit();
		}
		catch (SQLException e) {
			System.out.println("Failed clearing MoveCommand table:");
			e.printStackTrace();
		}		
		finally {
			SQLPlugin.safeClose(stmt);
		}
	}
}
