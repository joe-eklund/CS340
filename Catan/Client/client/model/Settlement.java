package client.model;

import shared.definitions.Location;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.locations.VertexDirection;
import client.exceptions.ClientModelException;

/**
 * Another piece owned by the player. Interacts with the player and the board. Must be purchased to be placed on the board.
 * <br><b>Domain:</b> 0-1 Vertex Location 
 * @author Chad
 */
public class Settlement {
	private int owner;
	private VertexLocation verLocation;
	private Location location;
	
	/**
	 * Class Constructor
	 * @param ownerIndex
	 * @param x
	 * @param y
	 * @param direction
	 * @throws ClientModelException
	 */
	public Settlement(int ownerIndex, int x, int y, String direction) throws ClientModelException {
		this.owner = ownerIndex;
		this.verLocation = new VertexLocation(new HexLocation(x, y), VertexDirection.determineDirection(direction));
	}
	
	/**
	 * Getter for the owner index
	 * @pre none
	 * @post Returns the integer index associated to the player who is the owner of the settelment
	 */
	public int getOwnerIndex() {
		return owner;
	}

	/**
	 * Setter for the owner index
	 * @param ownerIndex
	 * @pre none
	 * @post Sets the integer index representing the player who owns the settlement to the integer passed as a parameter
	 */
	public void setOwnerIndex(int ownerIndex) {
		this.owner = ownerIndex;
	}

	/** 
	 * Getter for the vertex location of the settlement
	 * @pre none
	 * @post Returns the vertex location of the settlement
	 */
	public VertexLocation getLocation() {
		return verLocation;
	}

	/**
	 * Setter for the vertex location of the settlement
	 * @param location
	 * @pre none
	 * @post The settlements vertex location is updated to be the vertex location passed as a parameter
	 */
	public void setLocation(VertexLocation location) {
		this.verLocation = location;
	}
	
	/**
	 * Setter for the vertex location of the settlement
	 * @param x
	 * @param y
	 * @param direction
	 * @throws ClientModelException
	 * @pre none
	 * @post Sets the coordinates and direction of the settlements vertex location to be the same as the x and y coordinates and the direction given as parameters
	 */
	public void setLocation(int x, int y, String direction) throws ClientModelException{
		this.verLocation = new VertexLocation(new HexLocation(x, y), VertexDirection.determineDirection(direction));
	}
	
	/**
	 * Initializes the vertex location based off of the generic x and y coordinates given when the server data is casted into a settlement
	 * @throws ClientModelException
	 * @pre none
	 * @post The vertex location now reflects the x and y coordinates of the model given by the server data
	 */
	public void initializeLocation() throws ClientModelException{
		setLocation(location.getX(), location.getY(), location.getDirection());
	}
	
}
