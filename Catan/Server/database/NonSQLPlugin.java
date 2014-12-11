package database;

import java.net.UnknownHostException;

import com.mongodb.*;

public class NonSQLPlugin implements IDBFactoryPlugin {
	
	Mongo mongo;
	
	public NonSQLPlugin() {
		
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
		return new NonSQLNonMoveCommandDAO(this);
	}

	@Override
	public void start() {
		try {
			mongo = new Mongo();
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
		
	}

	@Override
	public void stop(boolean success) {
		
		mongo.close();
	}

	@Override
	public void clearAllTables() {
		
		mongo.dropDatabase("phase4");	
	}
	
	public DB getDB() {
		
		return mongo.getDB("phase4");
	}
	
	
}
