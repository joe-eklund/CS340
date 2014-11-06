package server.commands;

public abstract class Command implements ICommand{
	private String name;

	/**
	 * @param name
	 */
	public Command(String name) {
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
