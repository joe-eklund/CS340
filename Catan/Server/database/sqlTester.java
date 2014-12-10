package database;

public class sqlTester {

	public static void main(String[] args) {
		IDBFactoryPlugin plugin=new SQLPlugin();
		System.out.println("plugin");
		plugin.clearAllTables();
		System.out.println("made db");
	}

}
