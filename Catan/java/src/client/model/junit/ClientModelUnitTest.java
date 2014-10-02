package client.model.junit;

import client.model.ClientModel;

public class ClientModelUnitTest {
	private ClientModel clientModel;
	
	public static void main(String[] args) 
	{
		String[] testClasses = new String[] 
		{
				"client.ClientModelUnitTest"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}
	
}
