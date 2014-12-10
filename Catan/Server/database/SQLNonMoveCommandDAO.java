package database;

public class SQLNonMoveCommandDAO extends ANonMoveCommandDAO {

	private SQLPlugin db;
	
	public SQLNonMoveCommandDAO(SQLPlugin sqlPlugin) {
		db = sqlPlugin;
	}

}
