package client.model;

import java.util.ArrayList;

/**
 * 
 * @author Joe Eklund
 * @domain The list length is between 0 and the max length of a list.
 * The Log class represents a log of commands that have happened.
 */

//TODO: 
public class Log {
	private ArrayList<Message> lines;

	/**
	 * Class constructor.
	 */
	public Log(){
		lines=new ArrayList();
	}
	
	/**
	 * Adds a message to the log
	 * @param messageToAdd	The inputed message to add.
	 * @pre none
	 * @post The Log now contains the message given as a parameter
	 */
	public void addMessage(Message messageToAdd){
		
	}
	
	/**
	 * Gets the log message list.
	 * @pre none
	 * @post Returns the log message list.
	 */
	public ArrayList<Message> getLogMessages() {
		return lines;
	}

	/**
	 * Sets the log message list.
	 * @param logMessages	The inputed log message list to set.
	 * @pre none
	 * @post The list containing the log messages is now the list given as a parameter
	 */
	public void setLogMessages(ArrayList<Message> logMessages) {
		this.lines = logMessages;
	}

}
