package server.junit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import proxy.TranslatorJSON;
import server.serverCommunicator.LoginUserHandler;
import server.serverCommunicator.RegisterUserHandler;
import server.users.UsersFacadeStub;
import shared.ServerMethodRequests.UserRequest;

public class MovesTest {
	@Before 
	public void setUp() { 
		/*user = new UsersFacadeStub(); 
		jsonTrans=new TranslatorJSON();
		regHandler=new RegisterUserHandler(jsonTrans,user);
		logHandler=new LoginUserHandler(jsonTrans,user);*/
	}
	
	@Test
	public void testValidLogin() {
		/*int login = user.loginUser(new UserRequest("Bobby","bobby"));
		assertTrue("Should be valid login",login>-1);*/
	}
}
