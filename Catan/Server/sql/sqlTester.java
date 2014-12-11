package sql;

import java.util.ArrayList;
import java.util.List;

import database.AModelDAO;
import database.ANonMoveCommandDAO;
import database.IDBFactoryPlugin;

public class sqlTester {

	private static IDBFactoryPlugin plugin=new SQLPlugin();
	
	
	//*********We could easily change this over to JUnits****//
	public static void main(String[] args) {
		
		System.out.println("plugin made-pre start");
		plugin.start();
		System.out.println("connection made-pre clear");
		plugin.clearAllTables();
		System.out.println("made db-pre save");
		//testUsers();
		//testNonMoveCommand();
		testGameDescription();
		//testGameModel();
		plugin.stop(false);
	}

	//Test Users
	private static boolean testUsers(){
		List<String> names=new ArrayList<String>();
		names.add("Bob");
		names.add("Fred");
		AModelDAO dao = plugin.getModelDAO("Users");
		dao.save(names);
		System.out.println("saved-pre load");
		Object temp=dao.load();
		List<String> newList=(List<String>) temp;
		System.out.println("post load, list-"+newList.size()+" "+newList.get(0));
		return true;
	}
	
	//Test nonMoveCommand
	private static boolean testNonMoveCommand(){
		System.out.println("***testing nonMoveCommand***");
		String type="User";
		String command="Test whatever";
		ANonMoveCommandDAO dao2=plugin.getNonMoveCommandDAO();
		dao2.add(command, type);
		System.out.println("added!");
		List<Object> temp2=dao2.getAll(type);
		if(temp2.size()!=0)
			System.out.println("got:"+temp2.size()+" "+(String)temp2.get(0));
		return true;
	}
	
	//Test GameDescription
	private static boolean testGameDescription(){
		System.out.println("***testing Game Description***");
		String description = "This game is cool!";
		SQLGameDescriptionDAO dao = (SQLGameDescriptionDAO) plugin.getModelDAO("Game Description");
		dao.save((Object) description);
		System.out.println("added!");
		String result = (String) dao.load();
		if(result!=null)
			System.out.println("got:"+result);
		return true;
	}
	
	//Test GameModel
	private static boolean testGameModel(){
		System.out.println("***testing Game Model***");
		String description = "Model model model model!!";
		SQLGameModelDAO dao = (SQLGameModelDAO) plugin.getModelDAO("Game Model");
		dao.save((Object) description);
		System.out.println("added!");
		String result = (String) dao.load();
		if(result!=null)
			System.out.println("got:"+result);
		return true;
	}
}
