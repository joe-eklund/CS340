package database;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLPlugin implements IDBFactoryPlugin{

	private static final String DATABASE_DIRECTORY = "SQLDatabase";
	private static final String DATABASE_FILE = "database.sqlite";
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
	public AMoveCommandDAO getMoveCommandDAO() {
		AMoveCommandDAO moveCommand=new SQLMoveCommandDAO(this);
		return moveCommand;
	}

	@Override
	public ANonMoveCommandDAO getNonMoveCommandDAO() {
		ANonMoveCommandDAO nonMoveCommand=new SQLNonMoveCommandDAO(this);
		return nonMoveCommand;
	}

	@Override
	public void start() {
		connection = null;
	    try {
			assert (connection == null);
			SQLiteConfig config = new SQLiteConfig();
			// config.setReadOnly(true);   
			config.setSharedCache(true);
			config.recursiveTriggers(true);
			// ... other configuration can be set via SQLiteConfig object
			Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db", config.toProperties());
			//Class.forName("org.sqlite.JDBC");
			//connection = DriverManager.getConnection(DATABASE_URL);
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
		PreparedStatement stmt = null;
		ResultSet rs = null;
		start();
		try {
			
			String drop = "DROP DATABASE " + DATABASE_FILE;
			String makeDB="CREATE DATABASE " + DATABASE_FILE;
			String makeGameDescriptions="CREATE TABLE GameDescriptions " +
					"(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," + 
					"descriptions BLOB NOT NULL)";
			String makeGameModel="CREATE TABLE GameModel " +
					"(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," + 
					"games BLOB NOT NULL)";
			String makeMoveCommand="CREATE TABLE MoveCommand " +
					"(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," + 
					"game INTEGER NOT NULL," + 
					"command BLOB NOT NULL)";
			String makeNonMoveCommand="CREATE TABLE NonMoveCommand " +
					"(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," + 
					"type CHAR(4) NOT NULL," +// -- either 'game' for game descriptions command or 'user' user command\r\n 
					"command BLOB NOT NULL)";
			String makeUsers="CREATE TABLE Users " +
					"(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," + 
					"users BLOB NOT NULL)";
			
			stmt = connection.prepareStatement(drop);
			//stmt.executeUpdate(drop);
			//System.out.println("dropped sql db");
			stmt.addBatch(makeDB);
			stmt.addBatch(makeUsers);
			stmt.addBatch(makeGameModel);
			stmt.addBatch(makeGameDescriptions);
			stmt.addBatch(makeMoveCommand);
			stmt.addBatch(makeNonMoveCommand);
			stmt.executeBatch();
			connection.commit();
			System.out.println("made sql tables");
			
		}
		catch (SQLException e) {
			System.out.println("Failed clearing tables:"+e.getMessage());
		}		
		finally {
			SQLPlugin.safeClose(rs);
			SQLPlugin.safeClose(stmt);
		}
	}
	
	public Connection getConnection(){
		return connection;
	}

}
