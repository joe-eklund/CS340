package client.model;

import shared.locations.HexLocation;

/**
 * 
 * @author Epper Marshall
 *
 */
public class Hex {
	private HexLocation location;
	private Resource resource;
	private Chit chit;
	public HexLocation getLocation() {
		return location;
	}
	public void setLocation(HexLocation location) {
		this.location = location;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public Chit getChit() {
		return chit;
	}
	public void setChit(Chit chit) {
		this.chit = chit;
	}
}
