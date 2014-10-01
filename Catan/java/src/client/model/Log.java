package client.model;

import java.util.ArrayList;

/**
 * 
 * @author Joe Eklund
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
	 */
	public void addMessage(Message messageToAdd){
		
	}
	
	/**
	 * Gets the log message list.
	 * @return	the log message list.
	 */
	public ArrayList<Message> getLogMessages() {
		return lines;
	}

	/**
	 * Sets the log message list.
	 * @param logMessages	The inputed log message list to set.
	 */
	public void setLogMessages(ArrayList<Message> logMessages) {
		this.lines = logMessages;
	}

}
