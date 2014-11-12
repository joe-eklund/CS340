package shared.model;

import shared.locations.HexLocation;

/**Represents a Port on the port, which contains a specific ratio on a specified location on the board. 
 * @author Epper Marshall
 */
public class Port {
	private String resource;
	private HexLocation location;
	private String direction;
	private int ratio;
	
	/**
	 * Class constructor
	 * @param resourceType
	 * @param x
	 * @param y
	 * @param direction
	 * @param ratio
	 */
	public Port(String resourceType, int x, int y, String direction, int ratio) {
		this.resource = resourceType;
		this.location = new HexLocation(x, y);
		this.direction = direction;
		this.ratio = ratio;
	}
	
	/**
	 * Getter for the type of resource the port can trade
	 * @pre none
	 * @post Returns a string representing the type of resource that the port can trade
	 */
	public String getResourceType() {
		return resource;
	}
	
	/**
	 * Setter for the type of resource the port can trade
	 * @param resourceType
	 * @pre none
	 * @post The string that represents the type of resource that a port can trade now equals the string passed as a parameter
	 */
	public void setResourceType(String resourceType) {
		this.resource = resourceType;
	}
	
	/**
	 * Getter for the hex location of the port
	 * @pre none
	 * @post Returns the hex location of the port 
	 */
	public HexLocation getLocation() {
		return location;
	}
	
	/**
	 * Setetr for the hex location of the port
	 * @param x
	 * @param y
	 * @pore none
	 * @post The hex location of this port is now has the same coordinates as the x and y integers passed in
	 */
	public void setLocation(int x, int y) {
		this.location = new HexLocation(x, y);
	}
	
	/**
	 * Setter
	 * @param location
	 * @pre none
	 * @post The hex location of this port is now has the same location as the hex location passed as a parameter
	 */
	public void setLocation(HexLocation location) {
		this.location = location;
	}
	
	/**
	 * Getter for the direction of the port
	 * @pre none
	 * @post Returns a string that represents the direction of the port
	 */
	public String getDirection() {
		return direction;
	}
	
	/**
	 * Setter for the direction of the port
	 * @param direction
	 * @pre none
	 * @post The string that represents the direction of the port now equals the string that was passed in as a parameter
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	/** 
	 * Getter for the trade ratio of the port
	 * @pre none
	 * @post Returns an integer representing the trade ratio for this port
	 */
	public int getRatio() {
		return ratio;
	}
	
	/**
	 * Setter for the trade ratio fo the port
	 * @param ratio
	 * @pre none
	 * @post Sets the trade ratio for the port to equal the ratio passed as a parameter
	 */
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
}
