package shared.ServerMethodRequests;

public class RollNumberRequest {
	private int number;
	private int playerIndex;

	public RollNumberRequest(int number, int playerIndex) {
		this.number = number;
		this.playerIndex = playerIndex;
	}

	public int getNumber() {
		return number;
	}
	
	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
	
}
