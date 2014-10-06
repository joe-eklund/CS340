package client.model;

import java.util.ArrayList;
/**
 * 
 * @author Epper Marshall, Joe Eklund
 * The chat class holds the list of messages that have been sent by each player.
 */
public class Chat {
	private ArrayList<Message> lines;
	
	/**
	 * Class constructor.
	 */
	public Chat(){
		lines = new ArrayList<Message>();
	}
	
	/**
	 * Adds a message to the list of messages.
	 * @param Message, the inputed message to add.
	 * @param Souce, the source of the message
	 * @pre none
	 * @post the message is anded to the list of messages along with its source.
	 */
	public void addMessage(String message, String source){
		lines.add(new Message(message, source));
	}
	
	/**
	 * Gets the current list of messages.
	 * @pre none
	 * @post returns a list of messages
	 */
	public ArrayList<Message> getMessages() {
		return lines;
	}

	/**
	 * Sets the current list of messages.
	 * @param messages	The inputed list of messages to set.
	 * @pre none
	 * @post sets the list of messages to the parameter given
	 */
	public void setMessages(ArrayList<Message> messages) {
		this.lines = messages;
	}
	
	
}
