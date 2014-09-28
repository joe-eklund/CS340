package client.model;

import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import client.exceptions.ClientModelException;

/**
 * 
 * @author Joe Eklund
 * The city class represents a city object.
 */
public class City {
	private int ownerIndex;
	private VertexLocation location;
	
	public City(int ownerIndex, int x, int y, String direction) throws ClientModelException {
		this.ownerIndex = ownerIndex;
		this.location = new VertexLocation(new HexLocation(x, y), VertexDirection.determineDirection(direction));
	}
	
	public int getOwnerIndex() {
		return ownerIndex;
	}

	public void setOwnerIndex(int ownerIndex) {
		this.ownerIndex = ownerIndex;
	}

	public VertexLocation getLocation() {
		return location;
	}

	public void setLocation(VertexLocation location) {
		this.location = location;
	}
	
	public void setLocation(int x, int y, String direction) throws ClientModelException{
		this.location = new VertexLocation(new HexLocation(x, y), VertexDirection.determineDirection(direction));
	}
}
