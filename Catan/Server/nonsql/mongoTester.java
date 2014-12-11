package nonsql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import database.AModelDAO;
import database.AMoveCommandDAO;
import database.ANonMoveCommandDAO;
import database.IDBFactoryPlugin;
import server.games.GamesFacadeStub;
import shared.definitions.ServerModel;

public class mongoTester {

	public static void main(String[] args) {
		IDBFactoryPlugin plugin=new NonSQLPlugin();
		System.out.println("plugin made-pre start");
		plugin.start();
		System.out.println("connection made-pre clear");
		plugin.clearAllTables();
		System.out.println("made db-pre save");
		//Test Users
		List<String> names=new ArrayList<String>();
		names.add("Bob");
		names.add("Fred");
		AModelDAO dao = plugin.getModelDAO("Users");
		
		Serializable temp=dao.load();
		
		
		
		dao.save((Serializable) names);
		System.out.println("saved-pre load");
		temp=dao.load();
		List<String> newList=(List<String>) temp;
		System.out.println("post load, list-"+newList.size()+" "+newList.get(0)+" "+newList.get(1));
		
		ArrayList<ServerModel> models = new ArrayList<ServerModel>();
		
		GamesFacadeStub stub = new GamesFacadeStub(models);
		
		dao = plugin.getModelDAO("Game Model");
		
		dao.save((Serializable) models);
		Serializable tempmodels = dao.load();
		ArrayList<ServerModel> loadedModels = (ArrayList<ServerModel>) tempmodels;
		
		System.out.println(loadedModels.size());
		
		//Non moves
		
		System.out.println("***testing nonMoveCommand***");
		String type="User";
		String command="Test whatever";
		ANonMoveCommandDAO dao2=plugin.getNonMoveCommandDAO();
		dao2.add(command, type);
		System.out.println("added!");
		List<Serializable> temp2=dao2.getAll(type);
		if(temp2.size()!=0)
			System.out.println("got:"+temp2.size()+" "+(String)temp2.get(0));
		
		command = "2nd Command";
		dao2.add(command, type);
		System.out.println("added!");
		temp2=dao2.getAll(type);
		if(temp2.size()!=0)
			System.out.println("got:"+temp2.size()+" "+(String)temp2.get(1));
		
		AMoveCommandDAO dao3=plugin.getMoveCommandDAO();
		
//		temp2=dao3.getAll(0);
		
		command = "Move command";
		dao3.add(command, 0);
		System.out.println("added!");
		temp2=dao3.getAll(0);
		if(temp2.size()!=0)
			System.out.println("got:"+temp2.size()+" "+(String)temp2.get(0));
	}

}
