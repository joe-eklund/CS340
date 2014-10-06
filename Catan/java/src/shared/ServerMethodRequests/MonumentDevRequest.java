package shared.ServerMethodRequests;

/**
 * A class for encapsulating MonumentDev request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "Monument"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index</li>
 *    </ul>
 *
 */
public class MonumentDevRequest {
	private String type; 
	private int playerIndex;
	
	/**
	 * constructor
	 * 
	 * @post
	 *   <ul>
	 *     <li> this.type = "Monument"</li>
	 *     <li> this.playerIndex = playerIndex param</li>
	 *   </ul>
	 * 
	 * @param playerIndex
	 */
	public MonumentDevRequest(int playerIndex) {
		this.type = "Monument";
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
