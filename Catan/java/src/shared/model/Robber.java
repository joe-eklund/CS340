package shared.model;

import shared.locations.HexLocation;
import shared.locations.RobberLocation;

/**Robber is a game piece contained in the game. Affects players on each turn. 
 * @author Chad
 */
public class Robber {
	private int x;
	private int y;
	
	/**
	 * Class constructor
	 * @param x
	 * @param y
	 */
	public Robber(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter for the x coordinate of the hex location
	 * @pre none
	 * @post Returns the x coordinate of the hex location
	 * 
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Getter for the y coordinate of the robber's hex location
	 * @pre none
	 * @post Return the y coordinate of the robber's hex location
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Getter for the hex location of the robber
	 * @pre none
	 * @post Returns the hex location of the robber
	 */
	public HexLocation getLocation() {
		return new HexLocation(x,y);
	}
	
	/**
	 * Setter for the hex location of the robber
	 * @param hex
	 * @pre none
	 * @post The hex location of the robber is now equal to the hex location passed as a parameter
	 */
	public void setLocation(HexLocation hex) {
		x=hex.getX();
		y=hex.getY();
	}
	
	/**
	 * Setter for the hex location of the robber
	 * @param x
	 * @param y
	 * @pre none
	 * @post The x and y coordinates of the robber are now equal to the x and y coordinates passed as a parameter
	 */
	public void setLocation(int x,int y) {
		this.x = x;
		this.y = y;
	}
	/*private HexLocation previousLocation;
	private HexLocation currentLocation;
	
	public Robber(int x, int y) {
		currentLocation = new HexLocation(x, y);
	}
	
	public HexLocation getPreviousLocation() {
		return previousLocation;
	}
	public void setPreviousLocation(HexLocation previousLocation) {
		this.previousLocation = previousLocation;
	}
	public HexLocation getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(HexLocation currentLocation) {
		this.currentLocation = currentLocation;
	}*/
}
