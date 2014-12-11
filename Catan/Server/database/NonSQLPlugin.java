package database;

import com.mongodb.*;

public class NonSQLPlugin implements IDBFactoryPlugin {

	Mongo mongo;
	
	public NonSQLPlugin() {
		try {
			mongo = new Mongo();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public AModelDAO getModelDAO(String type) {
		AModelDAO modelDAO=null;
		switch(type){
		case "Game Model":
			modelDAO=new NonSQLGameModelDAO(this);
			break;
		case "Game Description":
			modelDAO=new NonSQLGameDescriptionDAO(this);
			break;
		case "Users":
			modelDAO=new NonSQLUsersDAO(this);
			break;
		}
		return modelDAO;
	}

	@Override
	public AMoveCommandDAO getMoveCommandDAO() {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop(boolean success) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearAllTables() {
		mongo.dropDatabase("phase4");
		
		
	}
	
	public DB getDB() {
		return mongo.getDB("phase4");
	}
	
	public void closeDB() {
		mongo.close();
	}

}
