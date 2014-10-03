package junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClientCommunicatorTest.class, ClientModelUnitTest.class,
		MockCommunicatorTest.class, PlayerTest.class, ServerProxyTest.class,
		TaylorJUnit.class, TranslatorTest.class })
public class AllTests {
	  /*public static void main(String[] args) {
		    Result result = JUnitCore.runClasses(TranslatorTest.class);
		    for (Failure failure : result.getFailures()) {
		      System.out.println(failure.toString());
		    }
		  }*/
}
