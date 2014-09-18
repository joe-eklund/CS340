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
		
	}
	
	/**
	 * Gets the list of hexes.
	 * @return	The list of hexes.
	 */
	public ArrayList<Hex> getHexes() {
		return hexes;
	}
	
	/**
	 * Sets the list of hexes.
	 * @param hexes	The inputed list of hexes.
	 */
	public void setHexes(ArrayList<Hex> hexes) {
		this.hexes = hexes;
	}
	
	/**
	 * Gets the list of ports.
	 * @return	The list of ports.
	 */
	public ArrayList<Port> getPorts() {
		return ports;
	}
	
	/**
	 * Sets the list of ports.
	 * @param ports	The inputed list of ports.
	 */
	public void setPorts(ArrayList<Port> ports) {
		this.ports = ports;
	}
	
	/**
	 * Gets the list of roads.
	 * @return	The inputed list of roads.
	 */
	public ArrayList<Road> getRoads() {
		return roads;
	}
	
	/**
	 * Sets the list of roads.
	 * @param roads	The inputed list of roads.
	 */
	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}
	
	/**
	 * Gets the list of settlements.
	 * @return	The list of settlements.
	 */
	public ArrayList<Settlement> getSettlements() {
		return settlements;
	}
	
	/**
	 * Sets the list of settlements.
	 * @param settlements	The inputed list of settlements.
	 */
	public void setSettlements(ArrayList<Settlement> settlements) {
		this.settlements = settlements;
	}
	
	/**
	 * Gets the list of cities.
	 * @return	The list of cities.
	 */
	public ArrayList<City> getCities() {
		return cities;
	}

	/**
	 * Sets the list of cities.
	 * @param cities	The inputed list of cities.
	 */
	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}

	/**
	 * Gets the radius of the map. Number of hexes from the middle.
	 * @return	The radius.
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Sets the radius.
	 * @param radius	The inputed radius to set.
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	/**
	 * Gets the robber.
	 * @return	The robber.
	 */
	public Robber getRobber() {
		return robber;
	}

	/**
	 * Sets the robber.
	 * @param robber	The inputed robber to set.
	 */
	public void setRobber(Robber robber) {
		this.robber = robber;
	}
}
