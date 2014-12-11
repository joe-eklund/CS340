package shared.ServerMethodRequests;

import java.io.Serializable;

import shared.definitions.VertexLocationRequest;

/**
 * A class for encapsulating BuildSettlement request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "buildSettlement"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index</li>
 *      <li>vertexLocation: a valid vertexLocation on the map where the player is allowed to build a settlement</li>
 *      <li>free: true/false; whether the settlement is free (being built during initialization = true) or not</li>
 *    </ul>
 *
 */
public class BuildSettlementRequest implements Serializable{
	private String type;
	private int playerIndex;
	private VertexLocationRequest vertexLocation;
	private boolean free;

	/**
	 * Constructor
	 * @post
	 *  <ul>
	 * 	 <li>this.type = "buildRoad"</li>
	 *   <li>this.playerIndex = playerIndex param</li>
	 *   <li>this.vertexLocation = vertexLocation param</li>
	 *   <li>this.free = free param</li>
	 *  </ul>
	 * 
	 * @param playerIndex
	 * @param roadLocation
	 * @param free
	 */
	public BuildSettlementRequest(int playerIndex,
			VertexLocationRequest vertexLocation, boolean free) {
		this.type = "buildSettlement";
		this.playerIndex = playerIndex;
		this.vertexLocation = vertexLocation;
		this.free = free;
	}

	public String getType() {
		return type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public VertexLocationRequest getVertexLocation() {
		return vertexLocation;
	}

	public boolean isFree() {
		return free;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public void setVertexLocation(VertexLocationRequest vertexLocation) {
		this.vertexLocation = vertexLocation;
	}

	public void setFree(boolean free) {
		this.free = free;
	}
	
}
