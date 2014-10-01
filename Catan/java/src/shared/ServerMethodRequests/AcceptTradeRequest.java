package shared.ServerMethodRequests;

public class AcceptTradeRequest {
	private String type;
	private boolean willAccept;
	private int playerIndex;

	public AcceptTradeRequest(int playerIndex, boolean willAccept) {
		this.type = "willAccept";
		this.willAccept = willAccept;
		this.playerIndex = playerIndex;
	}

	public String getType() {
		return type;
	}

	public boolean isWillAccept() {
		return willAccept;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setWillAccept(boolean willAccept) {
		this.willAccept = willAccept;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
}
