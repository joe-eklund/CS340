package database;
import java.sql.*;

import java.io.Serializable;

public class SQLGameModelDAO extends AModelDAO {

	public SQLGameModelDAO(){
		
	}
	public void connectToDB(){
		Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:gameModel.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      //System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	}
}
