package shared.ServerMethodRequests;

public class MonumentDevRequest {
	private String type; 
	private int playerIndex;
	
	public MonumentDevRequest(int playerIndex) {
		this.type = "Monument";
		this.playerIndex = playerIndex;
	}

	public String getType() {
		return type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

}
