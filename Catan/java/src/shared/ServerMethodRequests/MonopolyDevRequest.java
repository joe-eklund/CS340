package shared.ServerMethodRequests;


public class MonopolyDevRequest {
	private String type;
	private int playerIndex;
	private String resource;

	public MonopolyDevRequest(int playerIndex, String resource) {
		this.type = "Monopoly";
		this.playerIndex = playerIndex;
		this.resource = resource;
	}

	public String getType() {
		return type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public String getResource() {
		return resource;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	
}
