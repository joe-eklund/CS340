package database;

public class SQLMoveCommandDAO extends AMoveCommandDAO {

	private SQLPlugin db;
	
	public SQLMoveCommandDAO(SQLPlugin sqlPlugin) {
		db = sqlPlugin;
	}

}
