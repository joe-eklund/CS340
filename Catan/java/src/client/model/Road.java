package client.model;

import client.exceptions.ClientModelException;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

/**The road is an item held by the player. It can be placed on the board is specific locations. 
 * Interacts with players, the bank and the board. 
 * @author Chad
 */
public class Road {
	
	private int ownerIndex;
	private EdgeLocation location;
	
	public Road(int ownerIndex, int x, int y, String direction) throws ClientModelException {
		this.ownerIndex = ownerIndex;
		this.location = new EdgeLocation(new HexLocation(x, y), EdgeDirection.determineDirection(direction));
	}
	
	public int getOwnerIndex() {
		return ownerIndex;
	}

	public void setOwnerIndex(int ownerIndex) {
		this.ownerIndex = ownerIndex;
	}

	public EdgeLocation getLocation() {
		return location;
	}

	public void setLocation(EdgeLocation location) {
		this.location = location;
	}
	
	public void setLocation(int x, int y, String direction) throws ClientModelException{
		this.location = new EdgeLocation(new HexLocation(x, y), EdgeDirection.determineDirection(direction));
	}
}
