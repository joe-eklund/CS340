package shared.ServerMethodRequests;

import java.io.Serializable;

import shared.definitions.RoadLocation;
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
public class RoadBuildingDevRequest implements Serializable {
	private String type;
	private int playerIndex;
	private RoadLocation spot1;
	private RoadLocation spot2;
	
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
	public RoadBuildingDevRequest(int playerIndex, RoadLocation spot1, RoadLocation spot2) {
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
	public RoadLocation getSpot1() {
		return spot1;
	}

	/**
	 * @obvious
	 */
	public RoadLocation getSpot2() {
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
	public void setSpot1(RoadLocation spot1) {
		this.spot1 = spot1;
	}

	/**
	 * @obvious
	 */
	public void setSpot2(RoadLocation spot2) {
		this.spot2 = spot2;
	}

}
