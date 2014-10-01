package shared.definitions;

public class PlayerIndex {
	private int playerGameIndex;

	public PlayerIndex(int playerGameIndex) {
		this.playerGameIndex = playerGameIndex;
	}

	public int getPlayerGameIndex() {
		return playerGameIndex;
	}

	public void setPlayerGameIndex(int playerGameIndex) {
		if(playerGameIndex>3)
			this.playerGameIndex=playerGameIndex%4;
		else
			this.playerGameIndex = playerGameIndex;
	}
}
