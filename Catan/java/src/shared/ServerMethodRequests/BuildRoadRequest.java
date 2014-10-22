package shared.ServerMethodRequests;

import shared.definitions.RoadLocation;

/**
 * A class for encapsulating BuildRoad request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "buildRoad"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index</li>
 *      <li>roadLocation: a valid EdgeLocation on the map where the player is allowed to build a road</li>
 *      <li>free: true/false; whether the road is free (being build during initialization = true) or not</li>
 *    </ul>
 *
 */
public class BuildRoadRequest {
	private String type;
	private int playerIndex;
	private RoadLocation roadLocation;
	private boolean free;
	
	/**
	 * Constructor
	 * @post
	 *  <ul>
	 * 	 <li>this.type = "buildRoad"</li>
	 *   <li>this.playerIndex = playerIndex param</li>
	 *   <li>this.roadLocation = roadLocation param</li>
	 *   <li>this.free = free param</li>
	 *  </ul>
	 * 
	 * @param playerIndex
	 * @param roadLocation
	 * @param free
	 */
	public BuildRoadRequest(int playerIndex, RoadLocation roadLocation,
			boolean free) {
		this.type = "buildRoad";
		this.playerIndex = playerIndex;
		this.roadLocation = roadLocation;
		this.free = free;
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
	public RoadLocation getRoadLocation() {
		return roadLocation;
	}

	/**
	 * @obvious
	 */
	public boolean isFree() {
		return free;
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
	public void setRoadLocation(RoadLocation roadLocation) {
		this.roadLocation = roadLocation;
	}

	/**
	 * @obvious
	 */
	public void setFree(boolean free) {
		this.free = free;
	}

}
