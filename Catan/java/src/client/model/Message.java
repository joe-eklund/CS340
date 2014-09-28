package client.model;

/**
 * Message class will be used for the messages in the chat feature.
 * @author Chad
 */
public class Message {
	private String message;
	private String source;

	public Message(String message, String source) {
		this.message = message;
		this.source = source;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
}
