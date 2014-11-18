package server.junit;

import org.junit.*;

import server.users.IUsersFacade;
import server.users.UsersFacadeStub;
import shared.ServerMethodRequests.UserRequest;
import shared.model.Player;
import static org.junit.Assert.*;

public class LoginTest {
	private UsersFacadeStub user;
	@Before 
	public void setUp() { 
		user = new UsersFacadeStub(); 
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
		int register = user.registerUser(new UserRequest("Bobby","bobby"));
		assertEquals("Duplicate user should return invalid registration",-1,register);
		register = user.registerUser(new UserRequest("Ji","jimmy"));
		assertEquals("Username should be 3 or more should return invalid registration",-1,register);
		register = user.registerUser(new UserRequest("Jimmy","ji"));
		assertEquals("Password should be greater than 3 should return invalid registration",-1,register);
		register = user.registerUser(new UserRequest("Jimmy","$mar7"));
		assertEquals("Password shouldn't contain invalid characters should return invalid registration",-1,register);
	}
}
