package shared.ServerMethodRequests;

public class BuyDevCardRequest {

	private String type;
	private int playerIndex;

	public BuyDevCardRequest(int playerIndex) {
		this.type = "buyDevCard";
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
