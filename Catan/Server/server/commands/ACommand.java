package server.commands;

/**
 * An abstract class that every Catan command will implement
 *
 */
public abstract class ACommand implements ICommand{
	private String name;

	/**
	 * Constructor
	 * @param name
	 */
	public ACommand(String name) {
		this.name = name;
	}

	/**
	 * @pre none
	 * @post the name of the command
	 */
	public String getName() {
		return name;
	}

	/**
	 * @pre none
	 * @param name the name to set
	 * @post the name of the command is now the name passed as a parameter
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
