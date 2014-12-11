package shared.ServerMethodRequests;

import java.io.Serializable;


/**
 * A class for encapsulating MonopolyDev request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "Monopoly"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index</li>
 *      <li>resource: valid resource type representing the players desired monopoly</li>
 *    </ul>
 *
 */
public class MonopolyDevRequest implements Serializable {
	private String type;
	private int playerIndex;
	private String resource;

	/**
	 * Constructor
	 * 
	 * @post
	 *   <ul>
	 *     <li>this.type = "Monopoly"</li>
	 *     <li>this.playerIndex = playerIndex param</li>
	 *     <li>this.resource = resource param</li>
	 *   </ul>
	 * @param playerIndex
	 * @param resource
	 */
	public MonopolyDevRequest(int playerIndex, String resource) {
		this.type = "Monopoly";
		this.playerIndex = playerIndex;
		this.resource = resource;
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
	public String getResource() {
		return resource;
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

	/**
	 * @obvious
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}
	
}
