package shared.model;

/**
 * Message class will be used for the messages in the chat feature.
 * @author Chad
 */
public class LogEntry {
	private String message;
	private String source;

	/**
	 * Class Constructor
	 * @param message The command that is played
	 * @param source 
	 */
	public LogEntry(String source, String message) {
		this.message = message;
		this.source = source;
	}
	/**
	 * Gets the message
	 * @pre none
	 * @post Returns a string that contains the substance of the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Setter for the message
	 * @param message
	 * @pre none
	 * @post The string within the object that contains the substance of the message is now the same as the string passed as a parameter
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Getter for the source of the message
	 * @pre none
	 * @post Returns a string that represents the source of the message
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Setter for the Source
	 * @param source
	 * @The string within the object that represents the source of the message is now the same as the string passed as a parameter
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * Creates and gives a hash code that can uniquely identify this message
	 * @pre none
	 * @post Returns an interger that is the hashCode of the object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}
	
	/**
	 * Determines if another object/message is equal to this message
	 * @pre none
	 * @post Returns a boolean: true if the message given as a parameter is equal to this message, otherwise returns false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogEntry other = (LogEntry) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}
	
	
}
