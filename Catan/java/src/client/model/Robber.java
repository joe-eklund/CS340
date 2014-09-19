package client.model;

/**Robber is a game piece contained in the game. Affects players on each turn. 
 * @author Chad
 */
public class Robber {
	private int previousLocation;
	private int currentLocation;
	
	public int getPreviousLocation() {
		return previousLocation;
	}
	public void setPreviousLocation(int previousLocation) {
		this.previousLocation = previousLocation;
	}
	public int getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(int currentLocation) {
		this.currentLocation = currentLocation;
	}
}
