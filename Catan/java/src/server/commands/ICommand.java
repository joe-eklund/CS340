package server.commands;

/**
 * An interface for any Catan command
 *
 */
public interface ICommand {
	/**
	 * Executes this command on the model(s) defined within the command (passed in to the constructor)
	 * @throws CommandException 
	 */
	public void execute() throws CommandException;
	
}
