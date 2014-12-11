package database;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLGameDescriptionDAO extends AModelDAO {

	private SQLPlugin db;
	
	public SQLGameDescriptionDAO(SQLPlugin sqlPlugin) {
		db =  sqlPlugin;
	}
	
	/**
	 * Saves the list of game descriptions(serialize it first) into the db-only one blob/row
	 */
	@Override
	public void save(Serializable model){
		//TODO save the model to database
	}
	
	/**
	 * Loads the blob representing the list of game descriptions
	 */
	@Override
	public Serializable load(){
		Serializable model=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//TODO change query
			String query = "select * from GameDescription";
			stmt = db.getConnection().prepareStatement(query);
			rs = stmt.executeQuery();
			db.getConnection().commit();
			while (rs.next()) {
				byte[] st = (byte[]) rs.getObject(1);
				ByteArrayInputStream baip = new ByteArrayInputStream(st);
				ObjectInputStream ois = new ObjectInputStream(baip);
				model = (Serializable)ois.readObject();
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println("Failed DB load game description:"+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
//		finally {
//			SQLPlugin.safeClose(rs);
//			SQLPlugin.safeClose(stmt);
//		}
		return model;
	}
	
	/**
	 * Drop table, create empty table
	 */
	@Override
	public void clear(){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String query = "Delete from GameDescription";
			stmt = db.getConnection().prepareStatement(query);

			rs = stmt.executeQuery();
			//May want to do a check for proper result here. Test if all rows have been deleted.
		}
		catch (SQLException e) {
			System.out.println("Failed DB clear game description table: "+e.getMessage());
		}		
		finally {
			SQLPlugin.safeClose(rs);
			SQLPlugin.safeClose(stmt);
		}
	}
}
