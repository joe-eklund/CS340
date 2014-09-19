package client.model;

/**The road is an item held by the player. It can be placed on the board is specific locations. 
 * Interacts with players, the bank and the board. 
 * @author Chad
 */
public class Road {

	private int location;

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
}
