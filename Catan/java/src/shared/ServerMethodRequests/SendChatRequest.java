package shared.ServerMethodRequests;

public class SendChatRequest {
	private String message;

	public SendChatRequest(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
