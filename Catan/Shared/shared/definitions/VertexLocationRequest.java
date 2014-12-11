package shared.definitions;

import java.io.Serializable;

import shared.locations.VertexDirection;

public class VertexLocationRequest implements Serializable{
	private int x;
	private int y;
	private VertexDirection direction;
	
	
	/**
	 * @param x
	 * @param y
	 * @param direction
	 */
	public VertexLocationRequest(int x, int y, VertexDirection direction) {
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
	public VertexDirection getDirection() {
		return direction;
	}
	
	public String getDirectionStr() {
		String dir = direction.name().toLowerCase();
		
		switch(dir) {
		case "northwest":
			dir = "NW";
			break;
		case "northeast":
			dir = "NE";
			break;
		case "east":
			dir = "E";
			break;
		case "west":
			dir = "W";
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
	public void setDirection(VertexDirection direction) {
		this.direction = direction;
	}
	
	
}
