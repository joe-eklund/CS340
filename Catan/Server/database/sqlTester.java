package database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class sqlTester {

	public static void main(String[] args) {
		IDBFactoryPlugin plugin=new SQLPlugin();
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
		dao.save((Serializable) names);
		System.out.println("saved-pre load");
		Serializable temp=dao.load();
		List<String> newList=(List<String>) temp;
		System.out.println("post load, list-"+newList.size()+" "+newList.get(0));
		//Test nonMoveCommand
		System.out.println("***testing nonMoveCommand***");
		String type="User";
		String command="Test whatever";
		ANonMoveCommandDAO dao2=plugin.getNonMoveCommandDAO();
		dao2.add(command, type);
		System.out.println("added!");
		List<Serializable> temp2=dao2.getAll(type);
		if(temp2.size()!=0)
			System.out.println("got:"+temp2.size()+" "+(String)temp2.get(0));
	}

}
