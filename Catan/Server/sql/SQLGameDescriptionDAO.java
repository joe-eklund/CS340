package sql;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import database.AModelDAO;

public class SQLGameDescriptionDAO extends AModelDAO {

	private SQLPlugin db;
	
	public SQLGameDescriptionDAO(SQLPlugin sqlPlugin) {
		db =  sqlPlugin;
	}
	
	/**
	 * Saves the list of game descriptions(serialize it first) into the db-only one blob/row
	 */
	@Override
	public void save(Object model){
		XStream xStream = new XStream(new DomDriver());
		try {
			String xml = xStream.toXML(model);
			PreparedStatement pstmt = db.getConnection().prepareStatement("insert into GameDescriptions (descriptions) values (?)");
			pstmt.setString(1, xml);
		    /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ObjectOutputStream oos = new ObjectOutputStream(baos);
		    oos.writeObject(model);
		    byte[] modelAsBytes = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(modelAsBytes);
		    pstmt.setBinaryStream(1, bais, modelAsBytes.length);*/
		    pstmt.executeUpdate();
		    db.getConnection().commit();
		    pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the blob representing the list of game descriptions
	 */
	@Override
	public Object load(){
		Object model=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		XStream xStream = new XStream(new DomDriver());
		try {
			//TODO change query
			String query = "select descriptions from GameDescriptions";
			stmt = db.getConnection().prepareStatement(query);
			rs = stmt.executeQuery();
			db.getConnection().commit();
			while (rs.next()) {
				/*byte[] st = (byte[]) rs.getObject(1);
				ByteArrayInputStream baip = new ByteArrayInputStream(st);
				ObjectInputStream ois = new ObjectInputStream(baip);*/
				String xml = (String)rs.getString(1);
				model = (Object) xStream.fromXML(xml);
				//model = (Object)ois.readObject();
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println("Failed DB load game description:"+e.getMessage());
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
