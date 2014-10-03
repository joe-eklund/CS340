package client.model;

import shared.definitions.Location;
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
	private VertexLocation verLocation;
	private Location location;
	
	public Settlement(int ownerIndex, int x, int y, String direction) throws ClientModelException {
		this.ownerIndex = ownerIndex;
		this.verLocation = new VertexLocation(new HexLocation(x, y), VertexDirection.determineDirection(direction));
	}
	
	public int getOwnerIndex() {
		return ownerIndex;
	}

	public void setOwnerIndex(int ownerIndex) {
		this.ownerIndex = ownerIndex;
	}

	public VertexLocation getLocation() {
		return verLocation;
	}

	public void setLocation(VertexLocation location) {
		this.verLocation = location;
	}
	
	public void setLocation(int x, int y, String direction) throws ClientModelException{
		this.verLocation = new VertexLocation(new HexLocation(x, y), VertexDirection.determineDirection(direction));
	}
	
	public void initializeLocation() throws ClientModelException{
		setLocation(location.getX(), location.getY(), location.getDirection());
	}
	
}
