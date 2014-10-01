package client.model;

import shared.locations.HexLocation;
import shared.locations.RobberLocation;

/**Robber is a game piece contained in the game. Affects players on each turn. 
 * @author Chad
 */
public class Robber {
	private int x;
	private int y;
	
	public Robber(int x,int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public HexLocation getLocation() {
		return new HexLocation(x,y);
	}
	public void setLocation(HexLocation hex) {
		x=hex.getX();
		y=hex.getY();
	}
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
