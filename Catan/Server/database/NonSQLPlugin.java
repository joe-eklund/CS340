package database;

public class NonSQLPlugin implements IDBFactoryPlugin {

	@Override
	public AModelDAO getModelDAO(String type) {
		AModelDAO modelDAO=null;
		switch(type){
		case "Game Model":
			modelDAO=new NonSQLGameModelDAO();
			break;
		case "Game Description":
			modelDAO=new NonSQLGameDescriptionDAO();
			break;
		case "Users":
			modelDAO=new NonSQLUsersDAO();
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
		// TODO Auto-generated method stub
		
	}

}
