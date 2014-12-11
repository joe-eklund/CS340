package database;

import java.io.File;
import java.io.Serializable;
import java.sql.*;

//import org.sqlite.SQLiteConfig;

public class SQLPlugin implements IDBFactoryPlugin{

	private static final String DATABASE_FILE = "database.db";
	private static final String DATABASE_URL = "jdbc:sqlite:" + DATABASE_FILE;
	
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
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(DATABASE_URL);
			connection.setAutoCommit(false);
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
		Statement stmt = null;
		try {		
			stmt = connection.createStatement();
			
			String dropGameDescriptions="DROP TABLE GameDescriptions";
			String dropGameModel="DROP TABLE GameModel";
			String dropMoveCommand="DROP TABLE MoveCommand";
			String dropNonMoveCommand="DROP TABLE NonMoveCommand";
			String dropUsers="DROP TABLE Users";
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

			stmt.addBatch(dropUsers);
			stmt.addBatch(dropNonMoveCommand);
			stmt.addBatch(dropMoveCommand);
			stmt.addBatch(dropGameModel);
			stmt.addBatch(dropGameDescriptions);
			stmt.executeBatch();
			stmt.clearBatch();
			
			stmt.addBatch(makeUsers);
			stmt.addBatch(makeGameModel);
			stmt.addBatch(makeGameDescriptions);
			stmt.addBatch(makeMoveCommand);
			stmt.addBatch(makeNonMoveCommand);
			stmt.executeBatch();
			
		}
		catch (SQLException e) {
			System.out.println("Failed clearing tables:S");
			e.printStackTrace();
		}		
		finally {
			SQLPlugin.safeClose(stmt);
		}
	}
	
	public Connection getConnection(){
		return connection;
	}

}
