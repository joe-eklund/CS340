package server.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import proxy.TranslatorJSON;
import server.commands.games.GamesCommandLog;
import server.commands.games.IGamesCommand;
import server.commands.moves.IMovesCommand;
import server.commands.moves.MovesCommandLog;
import server.commands.users.IUsersCommand;
import server.commands.users.UsersCommandLog;
import server.game.GameFacade;
import server.game.IGameFacade;
import server.games.GamesFacade;
import server.games.GamesFacadeStub;
import server.games.IGamesFacade;
import server.moves.IMovesFacade;
import server.moves.MovesFacade;
import server.serverCommunicator.ServerCommunicator;
import server.users.IUser;
import server.users.IUsersFacade;
import server.users.UsersFacade;
import server.users.UsersFacadeStub;
import server.util.IUtilFacade;
import server.util.UtilFacade;
import shared.definitions.GameDescription;
import shared.definitions.ServerModel;
import database.IDBFactoryPlugin;


public class Catan {
	private final static int MAX_PORT = 65535;
	private final static int MIN_PORT = 0;

	/*
	 * server will run without loading test stubs on port 8081 if no command line options are set
	 * -p <portNumber> ::= set port to port number (i.e -p 39640 will set the server to listen on port 39640)
	 * -t ::= run the server with test stubs (server will be initialized with mock data from stubs)
	 * 
	 * ==> "-p 39640 -t" will run the server on port 39640 with the testing data 
	 */
	public static void main(String[] args) {

		Map<String, String[]> plugins = getRegisteredPluginInfo();
		
		/*
		for(String key : plugins.keySet()) {
			String[] pluginInfo = plugins.get(key);
			File plugin = new File(pluginInfo[1]);
			ITestPlugin obj = loadPlugin(plugin, pluginInfo[2]);
			obj.sayHello();
		}
		*/
		
		
		CommandLineParser parser = new GnuParser();
		
		Options options = new Options();
		options.addOption("t", "test-data", false, "start server using testing stubs");
		options.addOption("p", "port", true, "port on which server will listen");
		options.addOption("delta", "delta db threshold", true, "the number of move commands (cumulative for all games) after which models are stored to specified db");
		options.addOption("clear", "clear all db", false, "clears all persistence data in the databases");
		for(String pluginFlag : plugins.keySet()) {
			options.addOption("db", "database", true, "database plugin for " + pluginFlag);
		}
		
		try {
			CommandLine line = parser.parse(options, args);
			
			// serverModels will be shared by GamesFacade, GameFacade, and MovesFacade
			ArrayList<ServerModel> serverModels = null;// new ArrayList<ServerModel>();
			ArrayList<IUser> users = null;
			ArrayList<GameDescription> gameDescriptions = null;
			
			// Load Plugin
			IDBFactoryPlugin dbPlugin = null;
			
			if(line.hasOption("db")) {
				String plugin = line.getOptionValue("db");
				if(plugins.containsKey(plugin)) {
					String[] pluginInfo = plugins.get(plugin);
					File pluginFile = new File(pluginInfo[1]);
					dbPlugin = loadPlugin(pluginFile, pluginInfo[2]);
				}
				else {
					String defaultPlugin = (String) plugins.keySet().toArray()[0];
					System.err.println("Error: invalid db option. Defaulting to " + defaultPlugin);
					String[] pluginInfo = plugins.get(defaultPlugin);
					File pluginFile = new File(pluginInfo[1]);
					dbPlugin = loadPlugin(pluginFile, pluginInfo[2]);
				}
			}
			
			int threshold = 20;
			if(line.hasOption("delta")) {
				try {
					threshold = Integer.parseInt(line.getOptionValue("delta"));
					if(threshold < 1) {
						threshold = 20;
						throw new NumberFormatException();
					}
				} catch (NumberFormatException e) {
					System.err.println("Invalid input; delta must be a positive integer. Defaulting to 20.");
				}
			};
			
			if(line.hasOption("clear")) {
				dbPlugin.start();
				dbPlugin.clearAllTables();
				dbPlugin.stop(true);
			}
			
			// Load GameModels from DB
			Serializable s = dbPlugin.getModelDAO("Game Model").load();
			serverModels = (s == null) ? new ArrayList<ServerModel>() : (ArrayList<ServerModel>) s;
			
			// Load User from DB
			Serializable u = dbPlugin.getModelDAO("Users").load();
			users = (u == null) ? new ArrayList<IUser>() : (ArrayList<IUser>) u;
			
			// Load Game Descriptions from DB
			Serializable d = dbPlugin.getModelDAO("Game Description").load();
			gameDescriptions = (d == null) ? new ArrayList<GameDescription>() : (ArrayList<GameDescription>) d;
			
			TranslatorJSON translator = new TranslatorJSON();
			IUsersFacade usersFacade = new UsersFacade(users);
			IGamesFacade gamesFacade = new GamesFacade(serverModels, gameDescriptions);
			IGameFacade gameFacade = new GameFacade(serverModels);
			IMovesFacade movesFacade = new MovesFacade(serverModels);
			IUtilFacade utilFacade = new UtilFacade();
			
			// initialize command logs
			UsersCommandLog usersLog = new UsersCommandLog();
			GamesCommandLog gamesLog = new GamesCommandLog();
			MovesCommandLog movesLog = new MovesCommandLog();
			
			// Update Users with Deltas
			List<Serializable> commands = dbPlugin.getNonMoveCommandDAO().getAll("User");
			List<IUsersCommand> usersCommands = (ArrayList<IUsersCommand>) ((commands == null) ? new ArrayList<IUsersCommand>() : commands);
			usersLog.storeAll(usersCommands);
			usersLog.setFacade(usersFacade);
			usersLog.executeAll();
			
			// Update Game Descriptions with Deltas
			commands = dbPlugin.getNonMoveCommandDAO().getAll("Game");
			List<IGamesCommand> gamesCommands = (ArrayList<IGamesCommand>) ((commands == null) ? new ArrayList<IGamesCommand>() : commands);
			gamesLog.storeAll(gamesCommands);
			gamesLog.SetFacade(gamesFacade);
			gamesLog.executeAll();
			
			// Update Game Models with Deltas
			for(int i = 0; i < serverModels.size(); i++) {
				commands = dbPlugin.getMoveCommandDAO().getAll(i);
				List<IMovesCommand> moves = (ArrayList<IMovesCommand>) ((commands == null) ? new ArrayList<IMovesCommand>() : commands);
				movesLog.storeAll(moves);
			}
			movesLog.setFacade(movesFacade);
			movesLog.executeAll();
			
			gamesLog.setDBPlugin(dbPlugin);
			usersLog.setDBPlugin(dbPlugin);
			movesLog.setDBPlugin(dbPlugin);
			movesLog.setGamesLog(gamesLog);
			movesLog.setUsersLog(usersLog);
			
			
			if(line.hasOption("t")) { // testing option ==> load server stubs
				System.out.println("loading testing stubs");
				
				// set stubs here
				usersFacade = new UsersFacadeStub();
				gamesFacade = new GamesFacadeStub(serverModels);
			}
			
			int portNumber = 8081;
			if(line.hasOption("p")) {
				try {
					portNumber = Integer.parseInt(line.getOptionValue("p"));
				} catch (NumberFormatException e) {
					System.err.println("Invalid input; port must be integer where port >= " + MIN_PORT + " && port <= " + MAX_PORT);
				}
			}
			
			if(portNumber >= MIN_PORT && portNumber <= MAX_PORT) {
				ServerCommunicator serverCommunicator = new ServerCommunicator(portNumber, translator, usersFacade, gamesFacade, gameFacade, movesFacade, utilFacade, usersLog, gamesLog, movesLog);
				serverCommunicator.run();
				System.out.println("Catan server listening on port: " + portNumber);
			}
			else {
				System.err.println("Invalid port number; port must be >= " + MIN_PORT + " && port must be <= " + MAX_PORT);
			}

		}catch(ParseException e) {
			System.out.println("Error Starting Server");
			System.err.println(e.getMessage());
		}

	}
	
	
	private static HashMap<String, String[]> getRegisteredPluginInfo() {
		String pluginsFolder = "./lib/dbPlugins/";
		File file = new File(pluginsFolder + "register.txt");
		HashMap<String, String[]> registeredPlugins = new HashMap<String, String[]>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null) {
				String[] info = line.split(" ");
				info[1] = pluginsFolder + info[1];
				registeredPlugins.put(info[0], info);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registeredPlugins;
	}
	
	private static IDBFactoryPlugin loadPlugin(File pluginFile, String className) {
		System.out.println(pluginFile.getName());
		IDBFactoryPlugin plugin = null;
		try {
			URL[] urls = new URL[] {pluginFile.toURI().toURL()};
			ClassLoader loader = new URLClassLoader(urls);
			String path = pluginFile.getName().replace(".jar", "") + "." + className;
			Class c = loader.loadClass(path);
			//System.out.println("server." + pluginFile.getName().replace(".jar", "") + "." + className);
			//Class c = loader.loadClass("server.spanishPlugin.TestPluginSpanish");
			Object pluginObject = c.newInstance();
			plugin = (IDBFactoryPlugin) pluginObject;
		} catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return plugin;
	}
}
