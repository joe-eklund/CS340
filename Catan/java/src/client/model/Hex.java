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
	private ResourceType ResourceType;
	private Chit chit;
	
	/**
	 * Class constructor.
	 */
	public Hex(){
		
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
	 * Gets the resource type.
	 * @return	The current resource type.
	 */
	public ResourceType getResourceType() {
		return ResourceType;
	}
	
	/**
	 * Sets the resource type.
	 * @param ResourceType	The resource type to set.
	 */
	public void setResourceType(ResourceType ResourceType) {
		this.ResourceType = ResourceType;
	}
	
	/**
	 * Gets the chit.
	 * @return	The current chit.
	 */
	public Chit getChit() {
		return chit;
	}
	
	/**
	 * Sets the chit.
	 * @param chit	The inputed chit to set.
	 */
	public void setChit(Chit chit) {
		this.chit = chit;
	}
}
