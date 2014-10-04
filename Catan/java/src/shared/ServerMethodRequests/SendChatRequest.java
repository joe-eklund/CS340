package shared.ServerMethodRequests;

public class SendChatRequest {
	private String type;
	private int playerIndex;
	private String content;

	public SendChatRequest(int playerIndex, String content) {
		this.type = "sendChat";
		this.playerIndex = playerIndex;
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public String getContent() {
		return content;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
