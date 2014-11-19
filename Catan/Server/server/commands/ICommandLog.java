package server.commands;

public interface ICommandLog {
	public void storeAndExecute(ICommand command);
}
