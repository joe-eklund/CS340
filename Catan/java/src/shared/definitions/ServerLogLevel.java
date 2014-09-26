package shared.definitions;


public enum ServerLogLevel {
	SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST;

	
	private String value;
	
	static
	{
		SEVERE.value = new String("SEVERE");
		WARNING.value = new String("WARNING");
		INFO.value = new String("INFO");
		CONFIG.value = new String("CONFIG");
		FINE.value = new String("FINE");
		FINER.value = new String("FINER");
		FINEST.value = new String("FINEST");
	}
	
	public String toString()
	{
		return value;
	}

}
