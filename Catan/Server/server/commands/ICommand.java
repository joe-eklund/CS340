package server.commands;

/**
 * An interface for any Catan command
 *
 */
public interface ICommand {
	/**
	 * The Catan command will execute this method when it is its turn
	 */
	public void execute();
}
