package client.model;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.CatanColor;
/**
 * The chat class holds the list of messages that have been sent by each player.
 * <br><b>Domain:</b> Lines will have between 0 and the max size of the list lines
 * @author Epper Marshall, Joe Eklund
 */
public class Chat {
	private ArrayList<client.communication.LogEntry> lines;
	
	/**
	 * Class constructor.
	 */
	public Chat(){
		lines = new ArrayList<client.communication.LogEntry>();
	}
	
	/**
	 * Adds a message to the list of messages.
	 * @param Message, the inputed message to add.
	 * @param Souce, the source of the message
	 * @pre none
	 * @post the message is anded to the list of messages along with its source.
	 */
	public void addMessage(CatanColor color, String message){
		lines.add(new client.communication.LogEntry(color, message));
	}
	
	/**
	 * Gets the current list of messages.
	 * @pre none
	 * @post returns a list of messages
	 */
	public List<client.communication.LogEntry> getMessages() {
		return lines;
	}

	/**
	 * Sets the current list of messages.
	 * @param messages	The inputed list of messages to set.
	 * @pre none
	 * @post sets the list of messages to the parameter given
	 */
	public void setMessages(ArrayList<client.communication.LogEntry> messages) {
		this.lines = messages;
	}
	
	
}
