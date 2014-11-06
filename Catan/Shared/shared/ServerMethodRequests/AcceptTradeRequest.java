package shared.ServerMethodRequests;

/**
 *  A class for encapsulating AcceptTrade parameters
 *  
 *  @Domain
 *    <ul>
 *      <li>type: "willAccept"</li>
 *      <li>willAccept: true/false indicating whether or not player accepts offer</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index</li>
 *    </ul>
 *
 */
public class AcceptTradeRequest {
	private String type;
	private boolean willAccept;
	private int playerIndex;

	/**
	 * @post 
	 *   <ul>
	 *     <li>this.type = "willAccept"</li>
	 *     <li>this.willAccept = willAccept param</li>
	 *     <li>this.playerIndex = playerIndex param</li>
	 *   </ul>
	 * @param playerIndex
	 * @param willAccept
	 */
	public AcceptTradeRequest(int playerIndex, boolean willAccept) {
		this.type = "acceptTrade";
		this.willAccept = willAccept;
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
	public boolean isWillAccept() {
		return willAccept;
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
	public void setWillAccept(boolean willAccept) {
		this.willAccept = willAccept;
	}

	/**
	 * @obvious
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
}
