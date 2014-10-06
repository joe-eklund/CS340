package client.model;

import java.util.ArrayList;

/**
 * 
 * @author Joe Eklund
 * The map class represents the game board and everything on it. This includes hexes,
 * ports, roads, settlements, cities, the radius of the board, and the robber.
 */
public class Map {
	private ArrayList<Hex> hexes;
	private ArrayList<Port> ports;
	private ArrayList<Road> roads;
	private ArrayList<Settlement> settlements;
	private ArrayList<City> cities;
	private int radius;
	private Robber robber;
	
	/**
	 * Class constructor.
	 */
	public Map(){
		radius=3;
	}
	
	/**
	 * Gets the list of hexes.
	 * @pre none
	 * @post Returns the list of hexes.
	 */
	public ArrayList<Hex> getHexes() {
		return hexes;
	}
	
	/**
	 * Sets the list of hexes.
	 * @param hexes	The inputed list of hexes.
	 * @pre none
	 * @post Sets the list of Hexes in the object to be the same as the list of hexes passed as a parameter
	 */
	public void setHexes(ArrayList<Hex> hexes) {
		this.hexes = hexes;
	}
	
	/**
	 * Gets the list of ports.
	 * @pre none
	 * @post Returns the list of ports.
	 */
	public ArrayList<Port> getPorts() {
		return ports;
	}
	
	/**
	 * Sets the list of ports.
	 * @param ports	The inputed list of ports.
	 * @pre none
	 * @post Sets the list of ports in the object to be the same as the List of ports passed as a parameter
	 */
	public void setPorts(ArrayList<Port> ports) {
		this.ports = ports;
	}
	
	/**
	 * Gets the list of roads.
	 * @pre none
	 * @post Returns the inputed list of roads.
	 */
	public ArrayList<Road> getRoads() {
		return roads;
	}
	
	/**
	 * Sets the list of roads.
	 * @param roads	The inputed list of roads.
	 * @pre none
	 * @post Sets the list of roads within the object to be the same as the list of roads passed in as a parameter
	 */
	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}
	
	/**
	 * Gets the list of settlements.
	 * @pre none
	 * @post Returns the list of settlements.
	 */
	public ArrayList<Settlement> getSettlements() {
		return settlements;
	}
	
	/**
	 * Sets the list of settlements.
	 * @param settlements	The inputed list of settlements.
	 * @pre none
	 * @post Sets the list of settlements within the object to be the same as the list of settlements passed as a parameter
	 */
	public void setSettlements(ArrayList<Settlement> settlements) {
		this.settlements = settlements;
	}
	
	/**
	 * Gets the list of cities.
	 * @pre none
	 * @post Returns the list of cities.
	 */
	public ArrayList<City> getCities() {
		return cities;
	}

	/**
	 * Sets the list of cities.
	 * @param cities: The inputed list of cities.
	 * @pre none
	 * @post Sets the list of cities within the object to be the same as the list of cities passed as a parameter
	 */
	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}

	/**
	 * Gets the radius of the map. Number of hexes from the middle.
	 * @pre none
	 * @post Returns the radius.
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Sets the radius.
	 * @param radius:	The inputed radius to set.
	 * @pre none
	 * @post Sets the radius within the object to be the same as the radius passed as a parameter
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	/**
	 * Gets the robber.
	 * @pre none
	 * @post Returns the robber.
	 */
	public Robber getRobber() {
		return robber;
	}

	/**
	 * Sets the robber.
	 * @param robber:	The inputed robber to set.
	 * @pre none
	 * @post Sets the robber within the object to be the same as the robber passed as a parameter
	 */
	public void setRobber(Robber robber) {
		this.robber = robber;
	}
}
