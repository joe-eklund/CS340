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
	
	/**
	 * Sets the request to be executed on the command model(s) for this command
	 * @param param
	 */
	public void setParam(Object request);
}
