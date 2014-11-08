package server.main;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import proxy.TranslatorJSON;
import server.game.GameFacade;
import server.game.IGameFacade;
import server.games.GamesFacade;
import server.games.GamesFacadeStub;
import server.games.IGamesFacade;
import server.moves.IMovesFacade;
import server.moves.MovesFacade;
import server.serverCommunicator.ServerCommunicator;
import server.users.IUsersFacade;
import server.users.UsersFacade;
import server.users.UsersFacadeStub;
import server.util.IUtilFacade;
import server.util.UtilFacade;

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
		
		CommandLineParser parser = new GnuParser();
		
		Options options = new Options();
		options.addOption("t", "test-data", false, "start server using testing stubs");
		options.addOption("p", "port", true, "port on which server will listen");
		
		try {
			TranslatorJSON translator = new TranslatorJSON();
			IUsersFacade usersFacade = new UsersFacade();
			IGamesFacade gamesFacade = new GamesFacade();
			IGameFacade gameFacade = new GameFacade();
			IMovesFacade movesFacade = new MovesFacade();
			IUtilFacade utilFacade = new UtilFacade();
			
			CommandLine line = parser.parse(options, args);
			
			if(line.hasOption("t")) { // testing option ==> load server stubs
				System.out.println("loading testing stubs");
				
				// set stubs here
				usersFacade = new UsersFacadeStub();
				gamesFacade = new GamesFacadeStub();
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
				ServerCommunicator serverCommunicator = new ServerCommunicator(portNumber, translator, usersFacade, gamesFacade, gameFacade, movesFacade, utilFacade);
				serverCommunicator.run();
				System.out.println("Catan server listening on port: " + portNumber);
			}
			else {
				System.err.println("Invalid port number; port must be >= " + MIN_PORT + " && port must be <= " + MAX_PORT);
			}

		} catch(ParseException e) {
			System.out.println("Error Starting Server");
			System.err.println(e.getMessage());
		}

	}
	
}
