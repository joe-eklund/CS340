package client.model;

import shared.locations.HexLocation;
import shared.definitions.ResourceType;

/**
 * 
 * @author Epper Marshall
 *
 */
public class Hex {
	private HexLocation location;
	private ResourceType ResourceType;
	private Chit chit;
	public HexLocation getLocation() {
		return location;
	}
	public void setLocation(HexLocation location) {
		this.location = location;
	}
	public ResourceType getResourceType() {
		return ResourceType;
	}
	public void setResourceType(ResourceType ResourceType) {
		this.ResourceType = ResourceType;
	}
	public Chit getChit() {
		return chit;
	}
	public void setChit(Chit chit) {
		this.chit = chit;
	}
}
