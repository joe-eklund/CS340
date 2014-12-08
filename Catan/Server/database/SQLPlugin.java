package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLPlugin implements IDBFactoryPlugin{

	private static final String DATABASE_DIRECTORY = "database";
	private static final String DATABASE_FILE = "contactmanager.sqlite";
	private static final String DATABASE_URL = "jdbc:sqlite:" + DATABASE_DIRECTORY + File.separator + DATABASE_FILE;
	
	private Connection connection;
	
	@Override
	public AModelDAO getModelDAO(String type) {
		AModelDAO modelDAO=null;
		switch(type){
		case "Game Model":
			modelDAO = new SQLGameModelDAO(this);
			break;
		case "Game Description":
			modelDAO = new SQLGameDescriptionDAO(this);
			break;
		case "Users":
			modelDAO = new SQLUsersDAO(this);
			break;
		}
		return modelDAO;
	}

	@Override
	public MoveCommandDAO getMoveCommandDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ANonMoveCommandDAO getNonMoveCommandDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start() {
		connection = null;
	    try {
			assert (connection == null);	
			connection = DriverManager.getConnection(DATABASE_URL);
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	}

	@Override
	public void stop(boolean success) {
		if (connection != null) {		
			try {
				if (success) {
					connection.commit();
				}
				else {
					connection.rollback();
				}
			}
			catch (SQLException e) {
				System.out.println("Could not end transaction");
				e.printStackTrace();
			}
			finally {
				safeClose(connection);
				connection = null;
			}
		}
	}
	
	public static void safeClose(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				System.out.println("Failed to safeClose: "+e.getMessage());
			}
		}
	}
	
	public static void safeClose(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			}
			catch (SQLException e) {
				// ...
			}
		}
	}
	
	public static void safeClose(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			}
			catch (SQLException e) {
				// ...
			}
		}
	}
	
	public static void safeClose(PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			}
			catch (SQLException e) {
				// ...
			}
		}
	}

	@Override
	public void clearAllTables() {
		// TODO Auto-generated method stub
		
	}
	
	public Connection getConnection(){
		return connection;
	}

}
