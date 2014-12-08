package database;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLGameModelDAO extends AModelDAO {

	private SQLPlugin db;
	
	public SQLGameModelDAO(SQLPlugin sqlPlugin) {
		db = sqlPlugin;
	}

	/**
	 * Saves the list of game models(serialize it first) into the db-only one blob/row
	 */
	@Override
	public void save(Serializable model){
		//TODO save the model to database
	}
	
	/**
	 * Loads the blob representing the list of game models
	 */
	@Override
	public Serializable load(){
		Serializable model=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//TODO change query
			String query = "select id, name, phone, address, email, url from contact";
			stmt = db.getConnection().prepareStatement(query);

			rs = stmt.executeQuery();
			//TODO serialize blob to java object
			
			/*Do not do this, this is 240 example
			 * while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String address = rs.getString(4);
				String email = rs.getString(5);
				String url = rs.getString(6);

				result.add(new Contact(id, name, phone, address, email, url));
			}*/
		}
		catch (SQLException e) {
			System.out.println("Failed DB load game model:"+e.getMessage());
		}		
		finally {
			SQLPlugin.safeClose(rs);
			SQLPlugin.safeClose(stmt);
		}
		return model;
	}
	/**
	 * Drop table, create empty table
	 */
	@Override
	public void clear(){
		//TODO clear all rows
	}

}
