package shared.ServerMethodRequests;

public class RollNumberRequest {
	private String type;
	private int number;
	private int playerIndex;

	public RollNumberRequest(int number, int playerIndex) {
		this.type = "rollNumber";
		this.number = number;
		this.playerIndex = playerIndex;
	}

	public String getType() {
		return type;
	}

	public int getNumber() {
		return number;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
}
