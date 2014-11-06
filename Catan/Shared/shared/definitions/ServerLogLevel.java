package shared.definitions;

/**
 * An enum representing all the different typs of server log levels
 * @author taylorcgbell
 *
 */
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
	
	/**
	 * Returns a string that represents the type of log level
	 * @pre none
	 * @post returns string
	 */
	public String toString()
	{
		return value;
	}

}
