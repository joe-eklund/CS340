package server.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import proxy.TranslatorJSON;
import server.commands.games.GamesCommandLog;
import server.commands.moves.MovesCommandLog;
import server.commands.users.UsersCommandLog;
import server.game.GameFacade;
import server.game.IGameFacade;
import server.games.GamesFacade;
import server.games.GamesFacadeStub;
import server.games.IGamesFacade;
import server.moves.IMovesFacade;
import server.moves.MovesFacade;
import server.serverCommunicator.ServerCommunicator;
import server.testPluginInterface.ITestPlugin;
import server.users.IUsersFacade;
import server.users.UsersFacade;
import server.users.UsersFacadeStub;
import server.util.IUtilFacade;
import server.util.UtilFacade;
import shared.definitions.ServerModel;


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
		for(String pluginFlag : plugins.keySet()) {
			options.addOption("db", "database", true, "database plugin for " + pluginFlag);
		}
		
		try {
			
			// serverModels will be shared by GamesFacade, GameFacade, and MovesFacade
			ArrayList<ServerModel> serverModels = new ArrayList<ServerModel>();
			
			TranslatorJSON translator = new TranslatorJSON();
			IUsersFacade usersFacade = new UsersFacade();
			IGamesFacade gamesFacade = new GamesFacade(serverModels);
			IGameFacade gameFacade = new GameFacade(serverModels);
			IMovesFacade movesFacade = new MovesFacade(serverModels);
			IUtilFacade utilFacade = new UtilFacade();
			
			// initialize command logs
			UsersCommandLog usersLog = new UsersCommandLog();
			GamesCommandLog gamesLog = new GamesCommandLog();
			MovesCommandLog movesLog = new MovesCommandLog();
			
			CommandLine line = parser.parse(options, args);
			
			// Load Plugin
			ITestPlugin p = null;
			for(String pluginFlag : plugins.keySet()) {
				if(line.hasOption(pluginFlag)) {
					String[] pluginInfo = plugins.get(pluginFlag);
					File pluginFile = new File(pluginInfo[1]);
					p = loadPlugin(pluginFile, pluginInfo[2]);
				}
			}
			
			if(line.hasOption("db")) {
				String plugin = line.getOptionValue("db");
				if(plugins.containsKey(plugin)) {
					String[] pluginInfo = plugins.get(plugin);
					File pluginFile = new File(pluginInfo[1]);
					p = loadPlugin(pluginFile, pluginInfo[2]);
				}
				else {
					String defaultPlugin = (String) plugins.keySet().toArray()[0];
					System.err.println("Error: invalid db option. Defaulting to " + defaultPlugin);
					String[] pluginInfo = plugins.get(defaultPlugin);
					File pluginFile = new File(pluginInfo[1]);
					p = loadPlugin(pluginFile, pluginInfo[2]);
				}
				
				p.sayHello();
			}
			
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
	
	private static ITestPlugin loadPlugin(File pluginFile, String className) {
		System.out.println(pluginFile.getName());
		ITestPlugin plugin = null;
		try {
			URL[] urls = new URL[] {pluginFile.toURI().toURL()};
			ClassLoader loader = new URLClassLoader(urls);
			String path = "server." + pluginFile.getName().replace(".jar", "") + "." + className;
			Class c = loader.loadClass(path);
			//System.out.println("server." + pluginFile.getName().replace(".jar", "") + "." + className);
			//Class c = loader.loadClass("server.spanishPlugin.TestPluginSpanish");
			Object pluginObject = c.newInstance();
			plugin = (ITestPlugin) pluginObject;
		} catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return plugin;
	}
}
