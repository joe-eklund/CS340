package shared.model;
/**
 * 
 * Keeps track of the number of each resource a player has.
 * <br><b>Domain:</b> 0-95 Resources
 */
public class Resources {
	public int brick;
	public int ore;
	public int sheep;
	public int wheat;
	public int wood;
	
	/**
	 * Class constructor
	 */
	public Resources() {
		brick=0;
		wheat=0;
		ore=0;
		wood=0;
		sheep=0;
	}
	
	/**
	 * Class constructor
	 * @param brick
	 * @param wheat
	 * @param ore
	 * @param wood
	 * @param sheep
	 */
	public Resources(int brick, int wheat, int ore, int wood, int sheep) {
		this.brick = brick;
		this.wheat = wheat;
		this.ore = ore;
		this.wood = wood;
		this.sheep = sheep;
	}
	
	/**
	 * Getter for the amount of brick in this resource object
	 * @pre none
	 * @post Returns an integer representing the amount of brick in this resource object
	 */
	public int getBrick() {
		return brick;
	}
	
	/**
	 * Setter for the amount of brick in this resource object
	 * @param brick
	 * @pre none
	 * @post The integer representing the amount of brick this resource holds now equals the integer passed in as a parameter
	 */
	public void setBrick(int brick) {
		this.brick = brick;
	}
	
	/**
	 * Getter for the amount of ore in this resource object
	 * @pre none
	 * @post Returns an integer representing the amount of ore in this resource object
	 */
	public int getOre() {
		return ore;
	}
	
	/**
	 * Setter for the amount of brick in this resource object
	 * @param ore
	 * @pre none
	 * @post The integer representing the amount of ore this resource holds now equals the integer passed in as a parameter
	 */
	public void setOre(int ore) {
		this.ore = ore;
	}
	
	/**
	 * Getter for the amount of sheep in this resource object
	 * @pre none
	 * @post Returns an integer representing the amount of sheep in this resource object
	 */
	public int getSheep() {
		return sheep;
	}
	
	/**
	 * Setter for the amount of sheep in this resource object
	 * @param sheep
	 * @pre none
	 * @post The integer representing the amount of sheep this resource holds now equals the integer passed in as a parameter
	 */
	public void setSheep(int sheep) {
		this.sheep = sheep;
	}
	
	/**
	 * Getter for the amount of wheat in this resource object
	 * @pre none
	 * @post Returns an integer representing the amount of wheat in this resource object
	 */
	public int getWheat() {
		return wheat;
	}
	
	/**
	 * Setter for the amount of wheat in this resource object
	 * @param wheat
	 * @pre none
	 * @post The integer representing the amount of wheat this resource holds now equals the integer passed in as a parameter
	 */
	public void setWheat(int wheat) {
		this.wheat = wheat;
	}
	
	/**
	 * Getter for the amount of wood in this resource object
	 * @pre none
	 * @post Returns an integer representing the amount of wood in this resource object
	 */
	public int getWood() {
		return wood;
	}
	
	/**
	 * Setter for the amount of wood in this resource object
	 * @param wood
	 * @pre none
	 * @post The integer representing the amount of wood this resource holds now equals the integer passed in as a parameter
	 */
	public void setWood(int wood) {
		this.wood = wood;
	}
	
	/**
	 * Calculates the total amount of resources this Resource object holds
	 * @pre none
	 * @post returns an integer representing the total amount of resources this Resource object holds
	 */
	public int totalResourcesCount() {
		return brick + ore + sheep + wheat + wood;
	}
}

