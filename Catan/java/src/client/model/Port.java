package client.model;

import shared.definitions.ResourceType;
import shared.locations.HexLocation;

/**Represents a Port on the port, which contains a specific ratio on a specified location on the board. 
 * @author Epper Marshall
 */
public class Port {
	private String resourceType;
	private HexLocation location;
	private String direction;
	private int ratio;
	
	public Port(String resourceType, int x, int y, String direction, int ratio) {
		this.resourceType = resourceType;
		this.location = new HexLocation(x, y);
		this.direction = direction;
		this.ratio = ratio;
	}
	
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public HexLocation getLocation() {
		return location;
	}
	public void setLocation(int x, int y) {
		this.location = new HexLocation(x, y);
	}
	public void setLocation(HexLocation location) {
		this.location = location;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getRatio() {
		return ratio;
	}
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
}
