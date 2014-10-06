package shared.ServerMethodRequests;

/**
 * A class for encapsulating FinishTurn request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "finishTurn"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index</li>
 *    </ul>
 *
 */
public class FinishTurnRequest {
	private String type;
	private int playerIndex;
	
	/**
	 * Constructor
	 * 
	 * @post
	 *   <ul>
	 *     <li>this.type = "finishTurn"</li>
	 *     <li>this.playerIndex = playerIndex</li>
	 *   </ul
	 * 
	 * @param playerIndex
	 */
	public FinishTurnRequest(int playerIndex) {
		this.type = "finishTurn";
		this.playerIndex = playerIndex;
	}

	/**
	 * @obvious
	 */
	public String getType() {
		return type;
	}

	/**
	 * @obvious
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	/**
	 * @obvious
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @obvious
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
}
