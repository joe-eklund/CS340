package server.junit;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import client.junit.ClientCommunicatorTest;
import client.junit.ClientModelUnitTest;
import client.junit.PlayerTest;
import client.junit.PollerTest;
import client.junit.ServerProxyTest;
import client.junit.TranslatorTest;

@RunWith(Suite.class)
@SuiteClasses({CommandLogTest.class, GamesTest.class,
		GameTest.class, MovesTest.class,
		UserTest.class })
public class AllTests {
	public static void main(String[] args) {		  
		  List<Class> myClasses=new ArrayList();
		  myClasses.add(CommandLogTest.class);
		  myClasses.add(GamesTest.class);
		  myClasses.add(GameTest.class);
		  myClasses.add(MovesTest.class);
		  myClasses.add(UserTest.class);
		  Result result;// = JUnitCore.runClasses(MyClassTest.class);
		  for(Class obj:myClasses)
		  {
			  result=JUnitCore.runClasses(obj);
			  for (Failure failure : result.getFailures()) 
				  System.out.println(failure.toString());
		  }
		  /*for (Failure failure : result.getFailures()) {
			  System.out.println(failure.toString());
		  }	*/
	  }
}
