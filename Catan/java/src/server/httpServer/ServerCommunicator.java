package server.httpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import proxy.ITranslator;
import server.users.IUsersFacade;

import com.sun.net.httpserver.HttpServer;

public class ServerCommunicator {
	
	private static final int MAX_WAITING_CONNECTION = 10;
	private static HttpServer server;
	private ITranslator translator;
	private IUsersFacade usersFacade;
	private int portNumber;
    
    public ServerCommunicator(int portNumber, ITranslator translator, IUsersFacade usersFacade) {
    	this.portNumber = portNumber;
    	this.translator = translator;
    	this.usersFacade = usersFacade;
    }
    
    public void run() {
		try {
			server = HttpServer.create(new InetSocketAddress(portNumber), MAX_WAITING_CONNECTION);
		} catch (IOException e) {
			//could not initialize server -- already running
			return;
		}
        
        server.setExecutor(null);
        
        //handlers
        server.createContext("/user/login", new LoginUserHandler(translator, usersFacade));
        /*
        server.createContext(("/" + VALIDATE_USER), new ValidateUserHandler());
        server.createContext(("/" + DOWNLOAD_BATCH), new DownloadBatchHandler());
        server.createContext(("/" + GET_FIELDS), new GetFieldsHandler());
        server.createContext(("/" + GET_PROJECTS), new GetProjectsHandler());
        server.createContext(("/" + GET_SAMPLE_IMAGE), new GetSampleImageHandler());
        server.createContext(("/" + SEARCH), new SearchHandler());
        server.createContext(("/" + SUBMIT_BATCH), new SubmitBatchHandler());
        */
        
        server.start();
	}
	
    public static void terminate() {
		server.stop(1);
	}

}
