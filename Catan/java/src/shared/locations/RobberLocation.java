package shared.locations;

/**
 * A class that represents the location of the Robber
 *
 */
public class RobberLocation {
	int x;
	int y;
	
	/**
	 * Class Constructor
	 * @param x
	 * @param y
	 */
	public RobberLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @obvious
	 */
	public int getX() {
		return x;
	}

	/**
	 * @obvious
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @obvious
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * @obvious
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	
}
