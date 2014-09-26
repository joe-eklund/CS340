package shared.ServerMethodRequests;

public class AcceptTradeRequest {
	boolean willAccept;

	public AcceptTradeRequest(boolean willAccept) {
		this.willAccept = willAccept;
	}

	public boolean isWillAccept() {
		return willAccept;
	}

	public void setWillAccept(boolean willAccept) {
		this.willAccept = willAccept;
	}
	
}
