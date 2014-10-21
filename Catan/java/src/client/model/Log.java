package client.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Log class represents a log of commands that have happened.
 * <br><b>Domain:</b> The list length is between 0 and the max length of a list.
 * @author Joe Eklund
 * 
 */

//TODO: 
public class Log {
	private List<client.communication.LogEntry> lines;

	/**
	 * Class constructor.
	 */
	public Log(){
		lines=new ArrayList<client.communication.LogEntry>();
	}
	
	public Log(List<client.communication.LogEntry> list) {
		this.lines = list;
	}
	
	/**
	 * Adds a message to the log
	 * @param messageToAdd	The inputed message to add.
	 * @pre none
	 * @post The Log now contains the message given as a parameter
	 */
	public void addMessage(client.communication.LogEntry messageToAdd){
		lines.add(messageToAdd);
	}
	
	/**
	 * Gets the log message list.
	 * @pre none
	 * @post Returns the log message list.
	 */
	public List<client.communication.LogEntry> getLogMessages() {
		return lines;
	}

	/**
	 * Sets the log message list.
	 * @param logMessages	The inputed log message list to set.
	 * @pre none
	 * @post The list containing the log messages is now the list given as a parameter
	 */
	public void setLogMessages(ArrayList<client.communication.LogEntry> logMessages) {
		this.lines = logMessages;
	}

}
