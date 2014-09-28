package client.model;

import shared.locations.HexLocation;
import shared.definitions.ResourceType;

/**
 * 
 * @author Epper Marshall
 *	The hex class represents a hex on the map. It has a location, resource type, 
 *	and a chit.
 */
public class Hex {
	private HexLocation location;
	private String resourceType;
	private int chit;
	
	/**
	 * Class constructor.
	 */
	public Hex(int x, int y, String resource, int chit){
		location = new HexLocation(x, y);
		resourceType = resource;
		
	}
	
	/**
	 * Gets the hex location.
	 * @return	The current hex location.
	 */
	public HexLocation getLocation() {
		return location;
	}
	
	/**
	 * Sets the hex location.
	 * @param location	The inputed hex location to set.
	 */
	public void setLocation(HexLocation location) {
		this.location = location;
	}
	
	/**
	 * Sets the hex location.
	 * @param x: The x coordinate of the hex location to set.
	 * @param y: The Y coordinate of the hex location to set.
	 */
	public void setLocation(int x, int y) {
		location = new HexLocation(x, y);
	}
	
	/**
	 * Gets the resource type.
	 * @return	The current resource type.
	 */
	public String getResourceType() {
		return resourceType;
	}
	
	/**
	 * Sets the resource type.
	 * @param ResourceType	The resource type to set.
	 */
	public void setResourceType(String resource) {
		resourceType = resource;
	}
	
	/**
	 * Gets the chit.
	 * @return	The current chit.
	 */
	public int getChit() {
		return chit;
	}
	
	/**
	 * Sets the chit.
	 * @param chit	The inputed chit to set.
	 */
	public void setChit(int chit) {
		this.chit = chit;
	}
}
