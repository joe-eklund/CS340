package junit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClientCommunicatorTest.class, ClientModelUnitTest.class,
		PlayerTest.class, ServerProxyTest.class,
		TaylorJUnit.class, TranslatorTest.class, PollerTest.class })
public class AllTests {
	  public static void main(String[] args) {		  
		  List<Class> myClasses=new ArrayList();
		  myClasses.add(ClientModelUnitTest.class);
		  myClasses.add(PollerTest.class);
		  myClasses.add(ClientCommunicatorTest.class);
		  myClasses.add(PlayerTest.class);
		  myClasses.add(ServerProxyTest.class);
		  myClasses.add(TaylorJUnit.class);
		  myClasses.add(TranslatorTest.class);
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
