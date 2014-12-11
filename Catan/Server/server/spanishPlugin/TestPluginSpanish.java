package server.spanishPlugin;

import server.testPluginInterface.ITestPlugin;


public class TestPluginSpanish implements ITestPlugin {

	@Override
	public void sayHello() {
		System.out.println("Hola");
	}

}
