package client.model;

import shared.locations.EdgeLocation;
/**
 * 
 * @author Epper Marshall
 *
 */
public class Edge {
	private EdgeLocation location;
	private int owner;
	public EdgeLocation getLocation() {
		return location;
	}
	public void setLocation(EdgeLocation location) {
		this.location = location;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
}
