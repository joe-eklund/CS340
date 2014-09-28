package client.model;

import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.locations.VertexDirection;
import client.exceptions.ClientModelException;

/**
 * Another piece owned by the player. Interacts with the player and the board. Must be purchased to be placed on the board. 
 * @author Chad
 */
public class Settlement {
	private int ownerIndex;
	private VertexLocation location;
	
	public Settlement(int ownerIndex, int x, int y, String direction) throws ClientModelException {
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
