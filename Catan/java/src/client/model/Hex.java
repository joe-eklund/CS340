package client.model;

import client.model.interfaces.IHex;
import shared.locations.HexLocation;
import shared.definitions.HexType;
import shared.definitions.ResourceType;

/**
 * The hex class represents a hex on the map. It has a location, resource type, 
 *	and an int.
 * @author Epper Marshall
 *	
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
	
	/**
	 * Class constructor
	 * @param location
	 */
	public Hex(HexLocation location) {
		this.location = location;
	}
	
	/**
	 * Gets the hex location.
	 * @pre none
	 * @post Returns the current hex location.
	 */
	@Override
	public HexLocation getLocation() {
		return location;
	}
	
	/**
	 * Sets the hex location.
	 * @param location	The inputed hex location to set.
	 * @pre none
	 * @post The Hex Location of the object is now the Hex Location given as a parameter
	 */
	@Override
	public void setLocation(HexLocation location) {
		this.location = location;
	}
	
	/**
	 * Sets the hex location.
	 * @param x: The x coordinate of the hex location to set.
	 * @param y: The Y coordinate of the hex location to set.
	 * @pre none
	 * @post The Hex Location of the object now has the x and y coordinates that were specified in the parameters
	 */
	@Override
	public void setLocation(int x, int y) {
		location = new HexLocation(x, y);
	}
	
	/**
	 * Gets the resource type.
	 * @return	The current resource type.
	 * @pre none
	 * @post returns the type of resource the Hex produces as a String
	 */
	@Override
	public String getResourceType() {
		return resource;
	}
	
	/**
	 * Sets the resource type.
	 * @param ResourceType	The resource type to set.
	 * @pre none
	 * @post Returns the type of resource that the Hex produces as a String
	 */
	@Override
	public void setResourceType(String resource) {
		this.resource = resource;
	}
	
	/**
	 * Gets the chit.
	 * @return	The current chit.
	 * @pre none
	 * @post Returns the chit associated to the Hex
	 */
	@Override
	public int getChit() {
		return chit;
	}
	
	/**
	 * Sets the chit.
	 * @param chit	The inputed chit to set.
	 * @pre none
	 * @post Sets the chit of the hex to the interger passed in as a parameter
	 */
	@Override
	public void setChit(int chit) {
		this.chit = chit;
	}	
}
