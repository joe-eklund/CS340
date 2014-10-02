package client.model;

import client.model.interfaces.IHex;
import shared.locations.HexLocation;
import shared.definitions.HexType;
import shared.definitions.ResourceType;

/**
 * 
 * @author Epper Marshall
 *	The hex class represents a hex on the map. It has a location, resource type, 
 *	and an int.
 */
public class Hex extends IHex{
	private HexLocation location;
	private String resource;
	private int chit;
	
	/**
	 * Class constructor.
	 */
	public Hex(int x, int y, String resource, int chit){
		location = new HexLocation(x, y);
		this.resource = resource;
		
	}
	
	public Hex(HexLocation location) {
		this.location = location;
	}
	
	/**
	 * Gets the hex location.
	 * @return	The current hex location.
	 */
	@Override
	public HexLocation getLocation() {
		return location;
	}
	
	/**
	 * Sets the hex location.
	 * @param location	The inputed hex location to set.
	 */
	@Override
	public void setLocation(HexLocation location) {
		this.location = location;
	}
	
	/**
	 * Sets the hex location.
	 * @param x: The x coordinate of the hex location to set.
	 * @param y: The Y coordinate of the hex location to set.
	 */
	@Override
	public void setLocation(int x, int y) {
		location = new HexLocation(x, y);
	}
	
	/**
	 * Gets the resource type.
	 * @return	The current resource type.
	 */
	@Override
	public String getResourceType() {
		return resource;
	}
	
	/**
	 * Sets the resource type.
	 * @param ResourceType	The resource type to set.
	 */
	@Override
	public void setResourceType(String resource) {
		this.resource = resource;
	}
	
	/**
	 * Gets the chit.
	 * @return	The current chit.
	 */
	@Override
	public int getChit() {
		return chit;
	}
	
	/**
	 * Sets the chit.
	 * @param chit	The inputed chit to set.
	 */
	@Override
	public void setChit(int chit) {
		this.chit = chit;
	}

	/**
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	
}
