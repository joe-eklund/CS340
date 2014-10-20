package client.model;

import shared.definitions.ResourceType;

/**
 * Player class contains all the information for each individual player in the game.
 * <br><b>Domain:</b> 0-5 settlements, 0-4 cities, 0-15 roads
 * @author Epper Marshall
 *
 */
public class Player {
	private int cities;
	private int settlements;
	private int roads;
	private String color;
	private boolean discarded;
	private int monuments;
	private String name;
	private DevCards newDevCards;
	private DevCards oldDevCards;
	private int playerIndex;
	private boolean playedDevCard;
	private int playerID;
	private Resources resources;
	private int soldiers;
	private int victoryPoints;
	
	/** 
	 * Class constructor
	 * @param color
	 * @param name
	 * @param playerIndex
	 */
	public Player(String color, String name, int playerIndex) {
		super();
		this.cities = 4;
		this.settlements = 5;
		this.roads = 15;
		this.color = color;
		this.discarded = false;
		this.monuments = 0;
		this.name = name;
		this.playerIndex = playerIndex;
		resources = new Resources();
		this.playedDevCard = false;
		this.soldiers = 0;
		this.victoryPoints = 0;
	}
	/**
	 * Getter for the list of cities
	 * @pre none
	 * @post Returns the list of cities that are associated to this player
	 */
	public int getCities() {
		return cities;
	}
	
	/**
	 * Setter for the list of cities
	 * @param cities
	 * @pre none
	 * @post The list of cities within this player object is now the same of the list of cities given as a parameter
	 */
	public void setCities(int cities) {
		this.cities = cities;
	}
	/**
	 * Getter for the list of settlements
	 * @pre none
	 * @post Returns the list of settlements that are associated to this player
	 */
	public int getSettlements() {
		return settlements;
	}
	/**
	 * Setter for the list of settlements
	 * @param settlements
	 * @pre none
	 * @post The list of settlements within this player object is now the same as the list of settlements given as a parameter
	 */
	public void setSettlements(int settlements) {
		this.settlements = settlements;
	}
	/**
	 * Getter for the list roads
	 * @pre none
	 * @post Returns the list of roads associated to this player
	 */
	public int getRoads() {
		return roads;
	}
	/**
	 * Setter for the list of Roads
	 * @param roads
	 * @pre none
	 * @post The list of roads within this player object is now the same as the list of roads given as a parameter
	 */
	public void setRoads(int roads) {
		this.roads = roads;
	}
	/**
	 * Getter for the player's color
	 * @pre none
	 * @post Returns the color of the player
	 */
	public String getColor() {
		return color;
	}
	/**
	 * Setter for the player's color
	 * @param color
	 * @pre none
	 * @post The string that represents the player's color is now the same as the string passed in as a parameter
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * Checks to see if a player has already discard this turn
	 * @pre none
	 * @post Returns a boolean that represents whether the player has already discarded this turn or not
	 */
	public boolean isDiscarded() {
		return discarded;
	}
	/**
	 * Setter for the boolean that represents whether a player has discard this turn or not
	 * @param discarded
	 * @pre none
	 * @post The discarded boolean within this player object now equals the boolean passed in as a parameter
	 */
	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}
	/**
	 * Getter for the amount of Monument cards the player currently has
	 * @pre none
	 * @post Returns the amount of Monument cards that the player currently has
	 */
	public int getMonuments() {
		return monuments;
	}
	/**
	 * Setter for the amount of Monument cards the player currently has
	 * @param monuments
	 * @pre none
	 * @post The integer representing the amount of monument cards the player currently has is now equal to the integer passed as a parameter
	 */
	public void setMonuments(int monuments) {
		this.monuments = monuments;
	}
	/**
	 * Getter for the name of the player
	 * @pre none
	 * @post Returns the name of the player as a string
	 */
	public String getName() {
		return name;
	}
	/**
	 * Setter for the name of the player
	 * @param name
	 * @pre none
	 * @post The string representing the name of the player within this object now equals the string that is passed in as a parameter
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Getter for all the development cards that the player holds, including development cards that the player has received in the current turn
	 * @pre none
	 * @post Returns the new development cards
	 */
	public DevCards getNewDevCards() {
		return newDevCards;
	}
	/**
	 * Setter for all the development cards that the player holds, including development cards that the player has received in the current turn
	 * @param newDevCards
	 * @pre none
	 * @post The new development card hand associated to this player object now equals the development card hand passed in as a parameter
	 */
	public void setNewDevCards(DevCards newDevCards) {
		this.newDevCards = newDevCards;
	}
	/**
	 * Getter for all the development cards that the player holds, excluding development cards that the player has received in the current turn
	 * @pre none
	 * @post Returns the old development cards
	 */
	public DevCards getOldDevCards() {
		return oldDevCards;
	}
	/**
	 * Setter for all the development cards that the player holds, excluding development cards that the player has received in the current turn
	 * @param oldDevCards
	 * @pre none
	 * @post The old development card hand associated to this player object now equals the development card hand passed in as a parameter
	 */
	public void setOldDevCards(DevCards oldDevCards) {
		this.oldDevCards = oldDevCards;
	}
	/**
	 * Getter for the integer index that represents this player in the game
	 * @pre none
	 * @post Returns an index associated to this player
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}
	/**
	 * Setter for the integer index that represents this player in the game
	 * @param playerIndex
	 * @pre none
	 * @post The integer index that represents this player in the current game is now set to be the same as the integer passed as a parameter
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	/**
	 * Getter for a boolean that represents whether the current player has played a development card this turn or not
	 * @pre none
	 * @post Returns a boolean that represents whether the current player has played a development card this turn or not
	 */
	public boolean hasPlayedDevCard() {
		return playedDevCard;
	}
	/**
	 * Setter for a boolean that represents whether the current player has played a development card this turn or not
	 * @param playedDevCard
	 * @pre none
	 * @post The "playedDevCard" boolean is now set to be the booean passed in as a parameter
	 */
	public void setPlayedDevCard(boolean playedDevCard) {
		this.playedDevCard = playedDevCard;
	}
	/**
	 * Getter for the ID of the player
	 * @pre none
	 * @post Returns an integer that representst the ID of this player
	 */
	public int getPlayerID() {
		return playerID;
	}
	/**
	 * Setter for the ID of the player
	 * @param playerID
	 * @pre none
	 * @post The integer within this player that represents this player's ID is now set to be the same as the integer passed as a parameter
	 */
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	/**
	 * Getter for all the resources that the player currently holds
	 * @pre none
	 * @post Returns the resources of this player
	 */
	public Resources getResources() {
		return resources;
	}
	/**
	 * Setter for all the resources that the player currently holds
	 * @param resources
	 * @pre none
	 * @post Sets all the amounts of the resources that this player holds to be equal to the amount of resources passed in as a parameter
	 */
	public void setResources(Resources resources) {
		this.resources = resources;
	}
	/**
	 * Getter for the amount of sheep resources this player holds
	 * @pre none
	 * @post Returns the integer that represents the amount of sheep resources this player holds
	 */
	public int getSheep() {
		return resources.sheep;
	}
	/**
	 * Setter for the amount of sheep resources this player holds
	 * @param sheep
	 * @pre none
	 * @post The integer that represents the amount of sheep resources this player holds now equals the integer passed in as a parameter
	 */
	public void setSheep(int sheep) {
		resources.sheep = sheep;
	}
	/**
	 * Getter for the amount of wheat resources this player holds
	 * @pre none
	 * @post Returns the integer that represents the amount of wheat resources this player holds
	 */
	public int getWheat() {
		return resources.wheat;
	}
	/**
	 * Setter for the amount of wheat resources this player holds
	 * @param wheat
	 * @pre none
	 * @post The integer that represents the amount of wheat resources this player holds now equals the integer passed in as a parameter
	 */
	public void setWheat(int wheat) {
		resources.wheat = wheat;
	}
	/**
	 * Getter for the amount of ore resources this player holds
	 * @pre none
	 * @post Returns the integer that represents the amount of ore resources this player holds
	 */
	public int getOre() {
		return resources.ore;
	}
	/**
	 * Setter for the amount of ore resources this player holds
	 * @param ore
	 * @pre none
	 * @post The integer that represents the amount of ore resources this player holds now equals the integer passed in as a parameter
	 */
	public void setOre(int ore) {
		resources.ore = ore;
	}
	/**
	 * Getter for the amount of brick resources this player holds
	 * @pre none
	 * @post Returns the integer that represents the amount of brick resources this player holds
	 */
	public int getBrick() {
		return resources.brick;
	}
	/**
	 * Setter for the amount of brick resources this player holds
	 * @param brick
	 * @pre none
	 * @post The integer that represents the amount of brick resources this player holds now equals the integer passed in as a parameter
	 */
	public void setBrick(int brick) {
		resources.brick = brick;
	}
	/**
	 * Getter for the amount of wood resources this player holds
	 * @pre none
	 * @post Returns the integer that represents the amount of wood resources this player holds
	 */
	public int getWood() {
		return resources.wood;
	}
	/**
	 * Setter for the amount of wood resources this player holds
	 * @param wood
	 * @pre none
	 * @post The integer that represents the amount of wood resources this player holds now equals the integer passed in as a parameter
	 */
	public void setWood(int wood) {
		resources.wood = wood;
	}
	/**
	 * Getter for the amount of soldier development cards this player holds
	 * @pre none
	 * @post Returns the integer that represents the amount of soldier development cards this player holds
	 */
	public int getSoldiers() {
		return soldiers;
	}
	/**
	 * Setter for the amount of wood resources this player holds
	 * @param soldiers
	 * @pre none
	 * @post The integer that represents the amount of soldier development cards this player holds now equals the integer passed in as a parameter
	 */
	public void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	}
	/**
	 * Getter for the amount of Victory Points this player has earned
	 * @pre none
	 * @post Returns the integer that represents the amount of Victory Points this player has earned so far in the game
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}
	/**
	 * Setter for the amount of Victory Points this player has earned
	 * @param victoryPoints
	 * @pre none
	 * @post The integer that represetnst the amount of Victory Points this player has earned so far in the game is now equal to the integer passed as a parameter
	 */
	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}
	
	/**
	 * Gets the current total number of resources a player has.
	 * @pre none
	 * @post Returns the total number of resources the player has.
	 */
	public int getResourceCount(){
		return resources.getBrick() + resources.getOre() + resources.getSheep() + 
				resources.getWheat() + resources.getWood();
	}
	
	/**
	 * Increases victory points when a player builds a building
	 * @pre none
	 * @post Increases victory points when a player builds a building
	 */
	public void buildBuilding() {
		victoryPoints++;
	}
	/**
	 * Checks that player has enough remaining cities and resources to build a city
	 * @pre none
	 * @post Returns a boolean: true if the player has enough cities and resources to build another city, otherwise returns false
	 */
	public boolean canBuildCity() {
		if(cities>0&&resources.getOre()>2&&resources.getWheat()>1)
			return true;
		return false;
	}
	/**
	 * Checks that player has enough remaining settlements and resources to build a settlement.
	 * @pre none
	 * @post Returns a boolean: true if the player has enough settlements and resources to build another settlement, otherwise returns false
	 */
	public boolean canBuildSettlement() {
		if(settlements>0&&resources.getBrick()>0&&resources.getWood()>0&&
				resources.getSheep()>0&&resources.getWheat()>0)
			return true;
		return false;
	}
	/**
	 * Checks that player has enough remaining roads and resources to build a road.
	 * @pre none
	 * @post Returns a boolean: true if the player has enough roads and resources to build another road, otherwise returns false
	 */
	public boolean canBuildRoad() {
		if(roads>0&&resources.getBrick()>0&&resources.getWood()>0)
			return true;
		return false;
	}
	/**
	 * Checks to see if a player has at least specific number of a specific type of resource.
	 * @param resource
	 * @param amount
	 * @pre none
	 * @post Returns a boolean: true if the player has enough of all the specified resources compared to the amount indicated as a parameter
	 */
	public boolean hasResource(ResourceType resource, int amount) {
		boolean hasResource = false;
		
		switch (resource) {
		case BRICK: 
			if (getBrick() >= amount)
				hasResource = true;
			break;
		case ORE: 
			if (getOre() >= amount)
				hasResource = true;
			break;
		case SHEEP: 
			if (getSheep() >= amount)
				hasResource = true;
			break;
		case WHEAT: 
			if (getWheat() >= amount)
				hasResource = true;
			break;
		case WOOD: 
			if (getWood() >= amount)
				hasResource = true;
			break;
		}
		
		return hasResource;
	}
}
