package server.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import server.commands.users.UsersCommandLog;
import server.users.IUsersFacade;
import server.users.UsersFacadeStub;
import shared.ServerMethodRequests.UserRequest;

public class UserTest {
	private IUsersFacade user;
	private UsersCommandLog userCommands;
	
	@Before 
	public void setUp() { 
		user = new UsersFacadeStub(); 
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

}
