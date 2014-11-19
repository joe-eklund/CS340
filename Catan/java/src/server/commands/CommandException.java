package server.commands;

@SuppressWarnings("serial")
public class CommandException extends Exception {
	/**
	 * @param message
	 */
	public CommandException(String message) {
		super(message);
	}
}
