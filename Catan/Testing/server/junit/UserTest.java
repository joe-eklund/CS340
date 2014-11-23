package server.junit;

import java.util.ArrayList;

import org.junit.*;

import proxy.ClientCommunicator;
import proxy.CommandResponse;
import proxy.Pair;
import proxy.RequestType;
import proxy.TranslatorJSON;
import server.commands.users.UsersCommandLog;
import server.main.Catan;
import server.serverCommunicator.RegisterUserHandler;
import server.serverCommunicator.LoginUserHandler;
import server.users.IUsersFacade;
import server.users.UsersFacadeStub;
import shared.ServerMethodRequests.UserRequest;
import shared.ServerMethodResponses.UserResponse;
import shared.definitions.User;
import shared.model.Player;
import static org.junit.Assert.*;

public class UserTest {
	private IUsersFacade user;
	private RegisterUserHandler regHandler;
	private TranslatorJSON jsonTrans;
	private UsersCommandLog userCommands;
	
	@Before 
	public void setUp() { 
		user = new UsersFacadeStub(); 
		jsonTrans=new TranslatorJSON();
		userCommands = new UsersCommandLog();
		userCommands.setFacade(user);
		regHandler=new RegisterUserHandler(jsonTrans,user, userCommands);
	}
	
	@Test
	public void testValidLogin() {
		int login = user.loginUser(new UserRequest("Bobby","bobby"));
		assertTrue("Should be valid login",login>-1);
	}
	
	@Test
	public void testInvalidLogin() {
		int login = user.loginUser(new UserRequest("Bobby","boby"));
		assertEquals("Password should return invalid login",-1,login);
		login = user.loginUser(new UserRequest("Jimmy","jimmy"));
		assertEquals("User doesn't exist should return invalid login",-1,login);
	}
	
	@Test
	public void testValidRegister() {
		int register = user.registerUser(new UserRequest("Jimmy","jimmy"));
		assertTrue("Should be valid registration",register>-1);
	}
	
	@Test
	public void testInvalidRegister() {
		//check for duplicates
		int register = user.registerUser(new UserRequest("Bobby","bobby"));
		assertEquals("Duplicate user should return invalid registration",-1,register);
		//check invalid server registrations
		Catan game=new Catan();
		game.main(new String[]{"-p", "8080", "-t"});
		ClientCommunicator client=new ClientCommunicator("localhost",8080,jsonTrans);
		CommandResponse response = client.executeCommand(RequestType.POST, new ArrayList<Pair<String,String>>(), "user/register", new User("Ji","jimmy"), null);
		assertTrue("Username needs to be greater than length of 2",response.getResponseMessage().equals("Don't trust your client -- they have violated the server API contract: invalid username and/or password configuration."));
		response = client.executeCommand(RequestType.POST, new ArrayList<Pair<String,String>>(), "user/register", new User("Tyrannosaurus","trexe"), null);
		assertTrue("Username needs to be less than length of 8",response.getResponseMessage().equals("Don't trust your client -- they have violated the server API contract: invalid username and/or password configuration."));
		response = client.executeCommand(RequestType.POST, new ArrayList<Pair<String,String>>(), "user/register", new User("Jimmy","jimm"), null);
		assertTrue("Password needs to be greater than length of 4",response.getResponseMessage().equals("Don't trust your client -- they have violated the server API contract: invalid username and/or password configuration."));
		response = client.executeCommand(RequestType.POST, new ArrayList<Pair<String,String>>(), "user/register", new User("Jimmy","$ma_r7"), null);
		assertTrue("Password shouldn't contain invalid characters",response.getResponseMessage().equals("Don't trust your client -- they have violated the server API contract: invalid username and/or password configuration."));
	}
	
	@Test
	public void testCommandLogExecuteAll() {
		UsersFacadeStub restoredUsers = new UsersFacadeStub();
		userCommands.setFacade(restoredUsers);
		userCommands.executeAll();
		assertTrue("User and restoredUsers should be the same after commandLog.executeAll for User command log is executed on restoredUsers.", user.equals(restoredUsers));
	}
}
