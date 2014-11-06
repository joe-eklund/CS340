package shared.ServerMethodRequests;

import shared.definitions.VertexLocationRequest;
import shared.locations.VertexLocation;

/**
 * A class for encapsulating BuildCity request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "buildCity"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index</li>
 *      <li>cityLocation: a valid vertexLocation on the map where a settlement already exists for the providing player</li>
 *    </ul>
 *
 */
public class BuildCityRequest {
	private String type;
	private int playerIndex;
	private VertexLocationRequest vertexLocation;

	/**
	 * @post
	 *   <ol>
	 *     <li>this.type = "buildCity"</li>
	 *     <li>this.playerIndex = playerIndex param</li>
	 *     <li>this.cityLocation = cityLocation param</li>
	 *   </ol>
	 * @param playerIndex
	 * @param cityLocation
	 */
	public BuildCityRequest(int playerIndex, VertexLocationRequest cityLocation) {
		this.type = "buildCity";
		this.playerIndex = playerIndex;
		this.vertexLocation = cityLocation;
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
	public VertexLocationRequest getCityLocation() {
		return vertexLocation;
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
	public void setCityLocation(VertexLocationRequest cityLocation) {
		this.vertexLocation = cityLocation;
	}
}
