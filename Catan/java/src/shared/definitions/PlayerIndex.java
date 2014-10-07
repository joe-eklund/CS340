package shared.definitions;

/**
 * A class that holds the index of a player
 *
 */
public class PlayerIndex {
	private int playerGameIndex;

	/**
	 * Class constructor
	 * @param playerGameIndex
	 */
	public PlayerIndex(int playerGameIndex) {
		this.playerGameIndex = playerGameIndex;
	}

	/**
	 * @obvious
	 */
	public int getPlayerGameIndex() {
		return playerGameIndex;
	}

	/**
	 * @obvious
	 * @param playerGameIndex
	 */
	public void setPlayerGameIndex(int playerGameIndex) {
		if(playerGameIndex>3)
			this.playerGameIndex=playerGameIndex%4;
		else
			this.playerGameIndex = playerGameIndex;
	}
}
