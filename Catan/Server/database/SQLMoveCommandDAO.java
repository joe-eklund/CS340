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

public class SQLMoveCommandDAO extends AMoveCommandDAO {

	private SQLPlugin db;
	
	public SQLMoveCommandDAO(SQLPlugin sqlPlugin) {
		db = sqlPlugin;
	}
	/**
	 * Adds a row with the gameId and command which was processed
	 */
	@Override
	public void add(Serializable command, int gameID){

	    try {
			PreparedStatement pstmt = db.getConnection().prepareStatement("insert into MoveCommand (game,command) values (?,?)");
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ObjectOutputStream oos = new ObjectOutputStream(baos);
		    oos.writeObject(command);
		    byte[] modelAsBytes = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(modelAsBytes);
			pstmt.setLong(1, gameID);
		    pstmt.setBinaryStream(2, bais, modelAsBytes.length);
		    pstmt.executeUpdate();
		    pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the list of commands from one game
	 */
	@Override
	public List<Serializable> getAll(int gameID){
		List<Serializable> model=new ArrayList<Serializable>();
		Serializable temp=null;
		Statement stmt=null;
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT command FROM MoveCommand where game = " + gameID);
			while (rs.next()) {
				byte[] st = (byte[]) rs.getObject(1);
				ByteArrayInputStream baip = new ByteArrayInputStream(st);
				ObjectInputStream ois = new ObjectInputStream(baip);
				temp = (Serializable) ois.readObject();
				model.add(temp);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
