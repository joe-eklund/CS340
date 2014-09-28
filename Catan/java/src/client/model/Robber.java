package client.model;

import shared.locations.RobberLocation;

/**Robber is a game piece contained in the game. Affects players on each turn. 
 * @author Chad
 */
public class Robber {
	private RobberLocation previousLocation;
	private RobberLocation currentLocation;
	
	public Robber(int x, int y) {
		currentLocation = new RobberLocation(x, y);
	}
	
	public RobberLocation getPreviousLocation() {
		return previousLocation;
	}
	public void setPreviousLocation(RobberLocation previousLocation) {
		this.previousLocation = previousLocation;
	}
	public RobberLocation getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(RobberLocation currentLocation) {
		this.currentLocation = currentLocation;
	}
}
