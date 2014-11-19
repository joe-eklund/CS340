package shared.model;

import client.exceptions.ClientModelException;
import shared.definitions.Location;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

/**The road is an item held by the player. It can be placed on the board is specific locations. 
 * Interacts with players, the bank and the board. 
 * <br><b>Domain:</b> 0-1 Edge Location
 * @author Chad
 */
public class Road {
	
	private int owner;
	private transient EdgeLocation edgeLocation;
	public Location location;
	
	/**
	 * Class constructor
	 * @param ownerIndex
	 * @param x
	 * @param y
	 * @param direction
	 * @throws ClientModelException
	 */
	public Road(int ownerIndex, int x, int y, String direction) {
		this.owner = ownerIndex;
		this.edgeLocation = new EdgeLocation(new HexLocation(x, y), EdgeDirection.determineDirection(direction));
		this.location = new Location();
		location.setX(x);
		location.setY(y);
		location.setDirection(direction);
	}
	
	/**
	 * Class constructor
	 * @param ownerIndex
	 * @param edgeLocation
	 */
	public Road(int ownerIndex, EdgeLocation edgeLocation) {
		this.owner = ownerIndex;
		this.edgeLocation = edgeLocation;
	}
	
	/**
	 * Getter for the owner index
	 * @pre none
	 * @post Returns the integer index that represents the player that owns the road
	 */
	public int getOwnerIndex() {
		return owner;
	}

	/**
	 * Setter for the owner index
	 * @param ownerIndex
	 * @pre none
	 * @post Sets the integer index representing the player that owns the road to the integer passed as a parameter
	 */
	public void setOwnerIndex(int ownerIndex) {
		this.owner = ownerIndex;
	}

	/**
	 * Getter for the edge location of the road
	 * @pre none
	 * @post Returns the edge location of the road
	 */
	public EdgeLocation getLocation() {
		return edgeLocation;
	}

	/**
	 * Setter for the edge location of the road
	 * @param edgeLocation
	 * @pre none
	 * @post Sets the edge location of the road to be the same as the edge location that is passed as a parameter
	 */
	public void setLocation(EdgeLocation edgeLocation) {
		this.edgeLocation = edgeLocation;
	}
	
	/**
	 * Setter for the edge location of the road
	 * @param x
	 * @param y
	 * @param direction
	 * @throws ClientModelException
	 * @post Sets the edge location of the road to have the same x and y coordinates and direction as the parameters passed to the method
	 */
	public void setLocation(int x, int y, String direction) throws ClientModelException{
		this.edgeLocation = new EdgeLocation(new HexLocation(x, y), EdgeDirection.determineDirection(direction));
	}

	/**
	 * Setter for the generic location of this road.  This is used after server data has been casted to java objects.
	 * @param location the location to set
	 * @pre none
	 * @post The generic location of this road is now the same as the location passed as a parameter
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	/**
	 * Initializes the edge location of the road to correspond to the x and y coordinates of the roads generic location 
	 * @throws ClientModelException
	 * @pre none
	 * @post The roads edge location's coordinates are now the same as the roads generic location
	 */
	public void initializeLocation() throws ClientModelException{
		setLocation(location.getX(), location.getY(), location.getDirection());
	}
}
