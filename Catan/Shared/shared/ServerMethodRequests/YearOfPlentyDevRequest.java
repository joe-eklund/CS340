package shared.ServerMethodRequests;

import java.io.Serializable;

/**
 * A class for encapsulating YearOfPlenty request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "Year_of_Plenty"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index (who's offering the trade)</li>
 *      <li>resource1: resourceType of first resource to gain</li>
 *    	<li>resource2: resourceType of second resource to gain</li>
 *    </ul>
 *
 */
public class YearOfPlentyDevRequest implements Serializable{
	private String type;
	private int playerIndex;
	private String resource1;
	private String resource2;

	/**
	 * Constructor
	 * 
	 * @post
	 *   <ul>
	 *     <li>this.type = "Year_of_Plenty"</li>
	 *     <li>this.playerIndex = playerIndex param</li>
	 *     <li>this.resource1 = resource1 param</li>
	 *     <li>this.resource2 = resource2 param</li>
	 *   </ul>
	 * 
	 * @param playerIndex
	 * @param resource1
	 * @param resource2
	 */
	public YearOfPlentyDevRequest(int playerIndex, String resource1, String resource2) {
		this.type = "Year_of_Plenty";
		this.playerIndex = playerIndex;
		this.resource1 = resource1;
		this.resource2 = resource2;
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
	public String getResource1() {
		return resource1;
	}

	/**
	 * @obvious
	 */
	public String getResource2() {
		return resource2;
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
	public void setResource1(String resource1) {
		this.resource1 = resource1;
	}

	/**
	 * @obvious
	 */
	public void setResource2(String resource2) {
		this.resource2 = resource2;
	}

}
