package client.model;

import shared.definitions.ResourceType;
import shared.locations.HexLocation;

/**
 * 
 * @author Epper Marshall
 *
 */
public class Port {
	private ResourceType type;
	private HexLocation location;
	private String direction;
	private int ratio;
	public ResourceType getType() {
		return type;
	}
	public void setType(ResourceType type) {
		this.type = type;
	}
	public HexLocation getLocation() {
		return location;
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
