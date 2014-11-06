package shared.ServerMethodRequests;

import shared.locations.HexLocation;

/**
 * A class for encapsulating SoldierDev request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "Robber"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index</li>
 *      <li>location: valid hexLocation to which robber can be moved</li>
 *      <li>victimIndex: 0,1,2,3 denoting the victim player's game index<li>
 *    </ul>
 *
 */
public class RobPlayerRequest {
	private String type;
	private int playerIndex;
	private HexLocation location;
	private int victimIndex;
	

	/**
	 * constructor
	 * 
	 * @post
	 *   <ul>
	 *     <li>this.type = "Robber"</li>
	 *     <li>this.playerIndex = playerIndex param</li>
	 *     <li>this.victimIndex = victimIndex param</li>
	 *     <li>this.location = location param</li>
	 *   </ul>
	 * 
	 * @param playerIndex
	 * @param victimIndex
	 * @param location
	 */
	public RobPlayerRequest(int playerIndex, int victimIndex,
			HexLocation location) {
		this.type = "robPlayer";
		this.playerIndex = playerIndex;
		this.victimIndex = victimIndex;
		this.location = location;
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
	public HexLocation getLocation() {
		return location;
	}

	/**
	 * @obvious
	 */
	public int getVictimIndex() {
		return victimIndex;
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
	public void setLocation(HexLocation location) {
		this.location = location;
	}

	/**
	 * @obvious
	 */
	public void setVictimIndex(int victimIndex) {
		this.victimIndex = victimIndex;
	}
	
}
