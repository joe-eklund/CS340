package database;

public class sqlTester {

	public static void main(String[] args) {
		IDBFactoryPlugin plugin=new SQLPlugin();
		System.out.println("plugin made-pre start");
		plugin.start();
		System.out.println("connection made-pre clear");
		plugin.clearAllTables();
		System.out.println("made db");
	}

}
