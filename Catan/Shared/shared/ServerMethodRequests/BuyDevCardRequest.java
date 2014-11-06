package shared.ServerMethodRequests;

/**
 * A class for encapsulating BuyDevCard request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "buyDevCard"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index</li>
 *    </ul>
 *
 */
public class BuyDevCardRequest {

	private String type;
	private int playerIndex;

	/**
	 * constructor
	 * 
	 * @post
	 *   <ul>
	 *     <li> this.type = "buyDevCard"</li>
	 *     <li> this.playerIndex = playerIndex param</li>
	 *   </ul>
	 * 
	 * @param playerIndex
	 */
	public BuyDevCardRequest(int playerIndex) {
		this.type = "buyDevCard";
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
