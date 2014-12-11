package shared.ServerMethodRequests;

import java.io.Serializable;

/**
 * A class for encapsulating RollNumber request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "rollNumber"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index (who's offering the trade)</li>
 *      <li>number: integer 2-12 (inclusive) representing the player's dice roll</li>
 *    </ul>
 *
 */
public class RollNumberRequest implements Serializable {
	private String type;
	private int number;
	private int playerIndex;

	/**
	 * Constructor
	 * 
	 * @post
	 *   <ul>
	 *     <li>this.type = "rollNumber"</li>
	 *     <li>this.number = number param</li>
	 *     <li>this.playerIndex = playerIndex param</li>
	 * 
	 * @param number
	 * @param playerIndex
	 */
	public RollNumberRequest(int number, int playerIndex) {
		this.type = "rollNumber";
		this.number = number;
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
	public int getNumber() {
		return number;
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
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @obvious
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
}
