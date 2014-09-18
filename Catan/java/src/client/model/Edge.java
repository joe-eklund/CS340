package client.model;

import shared.locations.EdgeLocation;
/**
 * 
 * @author Epper Marshall, Joe Eklund
 * The Edge class holds where the edge is located at. It represents an edge of a hex.
 */
public class Edge {
	private EdgeLocation location;
	private int owner;
	
	/**
	 * Class constructor.
	 */
	public Edge(){
		
	}
	
	/**
	 * Gets the edge location.
	 * @return	The edge location.
	 */
	public EdgeLocation getLocation() {
		return location;
	}
	
	/**
	 * Sets the edge location.
	 * @param location	The inputed edge location to set.
	 */
	public void setLocation(EdgeLocation location) {
		this.location = location;
	}
	
	/**
	 * Gets the owner.
	 * @return	The owner.
	 */
	public int getOwner() {
		return owner;
	}
	
	/**
	 * Sets the owner.
	 * @param owner	The inputed owner to set.
	 */
	public void setOwner(int owner) {
		this.owner = owner;
	}
}
