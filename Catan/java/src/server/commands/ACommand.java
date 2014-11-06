package server.commands;

public abstract class ACommand implements ICommand{
	private String name;

	/**
	 * @param name
	 */
	public ACommand(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
