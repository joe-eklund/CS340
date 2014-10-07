package client.model;

import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import client.exceptions.ClientModelException;

/**
 * The city class represents a city object.
 * <br><b>Domain:</b> The owner index must be between 0 and 4
 * @author Joe Eklund
 */
public class City {
	private int ownerIndex;
	private VertexLocation location;
	
	/**
	 * Class constructor
	 * @param ownerIndex
	 * @param x
	 * @param y
	 * @param direction
	 * @throws ClientModelException
	 */
	public City(int ownerIndex, int x, int y, String direction) throws ClientModelException {
		this.ownerIndex = ownerIndex;
		this.location = new VertexLocation(new HexLocation(x, y), VertexDirection.determineDirection(direction));
	}
	
	/**
	 * Getter for the OwnerIndex
	 * @pre none
	 * @post returns the index of the player that owns the city.
	 */
	public int getOwnerIndex() {
		return ownerIndex;
	}

	/**
	 * Sets the owner index
	 * @param ownerIndex
	 * @pre none
	 * @post The Owner Index is set to the value given as a parameter
	 */
	public void setOwnerIndex(int ownerIndex) {
		this.ownerIndex = ownerIndex;
	}

	/**
	 * Gets the Vertex Locaiton of the city
	 * @pre none
	 * @post Returns the vertex location of the city
	 */
	public VertexLocation getLocation() {
		return location;
	}

	/**
	 * Sets the vertex location of the city
	 * @param VertexLocation
	 * @pre none
	 * @post The Vertex Locaion of the city is set to the value given as a parameter
	 */
	public void setLocation(VertexLocation location) {
		this.location = location;
	}
	
	/**
	 * Sets the vertex location of the city
	 * @param x
	 * @param y
	 * @param direction
	 * @pre none
	 * @post The Vertex Locaion of the city is set to the vertex location that corresponds to the parameters given
	 */
	public void setLocation(int x, int y, String direction) throws ClientModelException{
		this.location = new VertexLocation(new HexLocation(x, y), VertexDirection.determineDirection(direction));
	}
}
