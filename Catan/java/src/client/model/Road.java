package client.model;

import client.exceptions.ClientModelException;
import shared.definitions.Location;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

/**The road is an item held by the player. It can be placed on the board is specific locations. 
 * Interacts with players, the bank and the board. 
 * @author Chad
 */
public class Road {
	
	private int owner;
	private EdgeLocation edgeLocation;
	public Location location;
	
	public Road(int ownerIndex, int x, int y, String direction) throws ClientModelException {
		this.owner = ownerIndex;
		this.edgeLocation = new EdgeLocation(new HexLocation(x, y), EdgeDirection.determineDirection(direction));
	}
	
	public Road(int ownerIndex, EdgeLocation edgeLocation) {
		this.owner = ownerIndex;
		this.edgeLocation = edgeLocation;
	}
	
	public int getOwnerIndex() {
		return owner;
	}

	public void setOwnerIndex(int ownerIndex) {
		this.owner = ownerIndex;
	}

	public EdgeLocation getLocation() {
		return edgeLocation;
	}

	public void setLocation(EdgeLocation edgeLocation) {
		this.edgeLocation = edgeLocation;
	}
	
	public void setLocation(int x, int y, String direction) throws ClientModelException{
		this.edgeLocation = new EdgeLocation(new HexLocation(x, y), EdgeDirection.determineDirection(direction));
	}

	/**
	 * @return the owner
	 */
	public int getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(int owner) {
		this.owner = owner;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void initializeLocation() throws ClientModelException{
		setLocation(location.getX(), location.getY(), location.getDirection());
	}
}
