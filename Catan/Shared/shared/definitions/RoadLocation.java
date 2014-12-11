package shared.definitions;

import java.io.Serializable;

import shared.locations.EdgeDirection;

public class RoadLocation implements Serializable {
	private int x;
	private int y;
	private EdgeDirection direction;
	
	/**
	 * @param x
	 * @param y
	 * @param direction
	 */
	public RoadLocation(int x, int y, EdgeDirection direction) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the direction
	 */
	public EdgeDirection getDirection() {
		
		return direction;
	}
	
	public String getDirectionStr() {
		String dir = direction.name().toLowerCase();
		
		switch(dir) {
		case "northwest":
			dir = "NW";
			break;
		case "north":
			dir = "N";
			break;
		case "northeast":
			dir = "NE";
			break;
		case "south":
			dir = "S";
			break;
		case "southwest":
			dir = "SW";
			break;
		case "southeast":
			dir = "SE";
			break;
		}
		
		return dir;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(EdgeDirection direction) {
		this.direction = direction;
	}
	
}
