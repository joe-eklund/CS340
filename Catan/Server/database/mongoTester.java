package database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
		
	}

}
