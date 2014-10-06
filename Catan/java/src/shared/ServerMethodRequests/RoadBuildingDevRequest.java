package shared.ServerMethodRequests;

import shared.locations.EdgeLocation;

/**
 * A class for encapsulating RoadBuilding request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "Road_Building"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index (who's offering the trade)</li>
 *      <li>spot1: edgeLocation where first road is desired to be built; must be valid according to game rulse</li>
 *    	<li>spt2: edgeLocation where second road is desired to be built; must be valid according to game rules</li>
 *    </ul>
 *
 */
public class RoadBuildingDevRequest {
	private String type;
	private int playerIndex;
	private EdgeLocation spot1;
	private EdgeLocation spot2;
	
	/**
	 * Constructor
	 * 
	 * @post
	 *   <ul>
	 *     <li>this.type = "Road_Building"</li>
	 *     <li>this.playerIndex = playerIndex param</li>
	 *     <li>this.spot1 = spot1 param</li>
	 *     <li>this.spot2 = spot2 param</li>
	 * 
	 * @param playerIndex
	 * @param spot1
	 * @param spot2
	 */
	public RoadBuildingDevRequest(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {
		this.type = "Road_Building";
		this.playerIndex = playerIndex;
		this.spot1 = spot1;
		this.spot2 = spot2;
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
	public EdgeLocation getSpot1() {
		return spot1;
	}

	/**
	 * @obvious
	 */
	public EdgeLocation getSpot2() {
		return spot2;
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
	public void setSpot1(EdgeLocation spot1) {
		this.spot1 = spot1;
	}

	/**
	 * @obvious
	 */
	public void setSpot2(EdgeLocation spot2) {
		this.spot2 = spot2;
	}

}
