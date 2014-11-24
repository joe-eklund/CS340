package server.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import proxy.ClientCommunicator;
import proxy.CommandResponse;
import proxy.Pair;
import proxy.RequestType;
import proxy.TranslatorJSON;
import server.commands.users.UsersCommandLog;
import server.main.Catan;
import server.users.IUsersFacade;
import server.users.UsersFacadeStub;
import shared.ServerMethodRequests.UserRequest;
import shared.definitions.User;

public class UserTest {
	private IUsersFacade user;
	private TranslatorJSON jsonTrans;
	private UsersCommandLog userCommands;
	
	@Before 
	public void setUp() { 
		user = new UsersFacadeStub(); 
		jsonTrans=new TranslatorJSON();
		userCommands = new UsersCommandLog();
		userCommands.setFacade(user);
	}
	
	@Test
	public void testValidRegister() {
		int register = user.registerUser(new UserRequest("Candy","candy"));
		assertTrue("Register username & password with only letters is permitted.", register > -1);
		register = user.registerUser(new UserRequest("Player1", "player1"));
		assertTrue("Register username & password with letters and numbers is permitted.", register > -1);
		register = user.registerUser(new UserRequest("User_1", "user_1"));
		assertTrue("Register username & password with letters, numbers, and '_' is permited.", register >- 1);
		register = user.registerUser(new UserRequest("C4-ta-n", "password"));
		assertTrue("Register username & password with letters, numbers, '_', and '-' is permited.", register > -1);
		register = user.registerUser(new UserRequest("Bobbyyy","bobbyyyy"));
		assertTrue("Valid registration username with 7 characters",register > -1);
	}
	
	@Test
	public void testInvalidLogin() {
		int login = user.loginUser(new UserRequest("Jimmy","jimmy"));
		assertEquals("User doesn't exist should return invalid login",-1,login);
		login = user.loginUser(new UserRequest("Bobby", "sandy"));
		assertEquals("Invalid password for registered user should not be authenticated",-1,login);
	}
	
	@Test
	public void testValidLogin() {
		int register = user.loginUser(new UserRequest("Bobby","bobby"));
		assertTrue("Bobby/bobby is a valid username/password combination loaded in testing stub.",register>-1);	
	}
	
	@Test
	public void testInvalidRegister() {
		int register = user.registerUser(new UserRequest("Bobby","bobby"));
		assertEquals("Duplicate user should return invalid registration",-1,register);
		
		register = user.registerUser(new UserRequest("B$bby","bobby"));
		assertEquals("Invalid registration symbol '$'",-1,register);
		
		register = user.registerUser(new UserRequest("B^bby","bobby"));
		assertEquals("Invalid registration symbol '^'",-1,register);
		
		register = user.registerUser(new UserRequest("B&bby","bobby"));
		assertEquals("Invalid registration symbol '&'",-1,register);
		
		register = user.registerUser(new UserRequest("B bby","bobby"));
		assertEquals("Invalid registration symbol ' '",-1,register);
		
		register = user.registerUser(new UserRequest("1","bobby"));
		assertEquals("Invalid registration: username less than 3 characters in length", -1, register);
		
		register = user.registerUser(new UserRequest("bobbbyyy","bobby"));
		assertEquals("Invalid registration: username less than 3 characters in length", -1, register);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testUserHandlers() {
		// start server in testing mode for checking handlers and user registration
		Catan game=new Catan();
		game.main(new String[]{"-p","8080","-t"});
		
		// use client communicator to test handlers
		ClientCommunicator client=new ClientCommunicator("localhost",8080,jsonTrans);
		
		CommandResponse response = client.executeCommand(RequestType.POST, new ArrayList<Pair<String,String>>(), "user/register", new User("Ji","jimmy"), null);
		assertTrue("Username needs to be greater than length of 2",response.getResponseMessage().equals("Don't trust your client -- they have violated the server API contract: invalid username and/or password configuration."));
		response = client.executeCommand(RequestType.POST, new ArrayList<Pair<String,String>>(), "user/register", new User("Tyrannosaurus","trexe"), null);
		assertTrue("Username needs to be less than length of 8",response.getResponseMessage().equals("Don't trust your client -- they have violated the server API contract: invalid username and/or password configuration."));
		response = client.executeCommand(RequestType.POST, new ArrayList<Pair<String,String>>(), "user/register", new User("Jimmy","jimm"), null);
		assertTrue("Password needs to be greater than length of 4",response.getResponseMessage().equals("Don't trust your client -- they have violated the server API contract: invalid username and/or password configuration."));
		response = client.executeCommand(RequestType.POST, new ArrayList<Pair<String,String>>(), "user/register", new User("Jimmy","$ma_r7"), null);
		assertTrue("Password shouldn't contain invalid characters",response.getResponseMessage().equals("Don't trust your client -- they have violated the server API contract: invalid username and/or password configuration."));
		testCommandLogExecuteAll();
	}
	
	private void testCommandLogExecuteAll() {
		UsersFacadeStub restoredUsers = new UsersFacadeStub();
		userCommands.setFacade(restoredUsers);
		userCommands.executeAll();
		assertTrue("User and restoredUsers should be the same after commandLog.executeAll for User command log is executed on restoredUsers.", user.equals(restoredUsers));
	}
}
