package client.model.interfaces;

import shared.definitions.HexType;
import shared.locations.HexLocation;

public abstract class IHex {
	private HexType type;
	
	/**
	 * Gets the hex location.
	 * @return	The current hex location.
	 */
	public abstract HexLocation getLocation();
	
	/**
	 * Sets the hex location.
	 * @param location	The inputed hex location to set.
	 */
	public abstract void setLocation(HexLocation location);
	
	/**
	 * Sets the hex location.
	 * @param x: The x coordinate of the hex location to set.
	 * @param y: The Y coordinate of the hex location to set.
	 */
	public abstract void setLocation(int x, int y);
	
	/**
	 * Gets the resource type.
	 * @return	The current resource type.
	 */
	public abstract String getResourceType(); 
	
	/**
	 * Sets the resource type.
	 * @param ResourceType	The resource type to set.
	 */
	public abstract void setResourceType(String resource);
	
	/**
	 * Gets the chit.
	 * @return	The current chit.
	 */
	public abstract int getChit();
	
	/**
	 * Sets the chit.
	 * @param chit	The inputed chit to set.
	 */
	public abstract void setChit(int chit);

	/**
	 * @return the type
	 */
	public HexType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(HexType type) {
		this.type = type;
	}
}
