package shared.ServerMethodRequests;

public class AcceptTradeRequest {
	boolean willAccept;
	int playerIndex;

	public AcceptTradeRequest(boolean willAccept, int playerIndex) {
		this.willAccept = willAccept;
		this.playerIndex = playerIndex;
	}

	public boolean isWillAccept() {
		return willAccept;
	}
	
	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setWillAccept(boolean willAccept) {
		this.willAccept = willAccept;
	}
	
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
}
