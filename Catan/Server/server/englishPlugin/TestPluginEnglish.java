package server.englishPlugin;

import server.testPluginInterface.ITestPlugin;

public class TestPluginEnglish implements ITestPlugin {

	@Override
	public void sayHello() {
		System.out.println("Hello");
	}

}
