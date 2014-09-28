package client.model;

import java.util.ArrayList;
/**
 * 
 * @author Epper Marshall, Joe Eklund
 * The chat class holds the list of messages that have been sent by each player.
 */
public class Chat {
	private ArrayList<Message> messages;
	
	/**
	 * Class constructor.
	 */
	public Chat(){
		messages = new ArrayList<Message>();
	}
	
	/**
	 * Adds a message to the list of messages.
	 * @param messageToAdd	The inputed message to add.
	 */
	public void addMessage(String message, String source){
		messages.add(new Message(message, source));
	}
	
	/**
	 * Gets the current list of messages.
	 * @return	The list of messages.
	 */
	public ArrayList<Message> getMessages() {
		return messages;
	}

	/**
	 * Sets the current list of messages.
	 * @param messages	The inputed list of messages to set.
	 */
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
	
}
