package client.model;

import java.util.ArrayList;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;

/**Player class contains all the information for each individual player in the game.
 * 
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
	private ArrayList<DevCardType> newDevCards;
	private ArrayList<DevCardType> oldDevCards;
	private int playerIndex;
	private boolean playedDevCard;
	private int playerID;
	private ArrayList<ResourceType> resources;
	private int soldiers;
	private int victoryPoints;
	private int sheepRatio;
	private int brickRatio;
	private int wheatRatio;
	private int woodRatio;
	private int oreRatio;
	
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
		resources = new ArrayList<ResourceType>();
		this.playedDevCard = false;
		this.soldiers = 0;
		this.victoryPoints = 2;
		this.sheepRatio = 4;
		this.brickRatio = 4;
		this.wheatRatio = 4;
		this.woodRatio = 4;
		this.oreRatio = 4;
	}
	public int getCities() {
		return cities;
	}
	public void setCities(int cities) {
		this.cities = cities;
	}
	public int getSettlements() {
		return settlements;
	}
	public void setSettlements(int settlements) {
		this.settlements = settlements;
	}
	public int getRoads() {
		return roads;
	}
	public void setRoads(int roads) {
		this.roads = roads;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isDiscarded() {
		return discarded;
	}
	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}
	public int getMonuments() {
		return monuments;
	}
	public void setMonuments(int monuments) {
		this.monuments = monuments;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<DevCardType> getNewDevCards() {
		return newDevCards;
	}
	public void setNewDevCards(ArrayList<DevCardType> newDevCards) {
		this.newDevCards = newDevCards;
	}
	public ArrayList<DevCardType> getOldDevCards() {
		return oldDevCards;
	}
	public void setOldDevCards(ArrayList<DevCardType> oldDevCards) {
		this.oldDevCards = oldDevCards;
	}
	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public boolean isPlayedDevCard() {
		return playedDevCard;
	}
	public void setPlayedDevCard(boolean playedDevCard) {
		this.playedDevCard = playedDevCard;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public ArrayList<ResourceType> getResources() {
		return resources;
	}
	public void setResources(ArrayList<ResourceType> resources) {
		this.resources = resources;
	}
	public void getSheep() {
		resources.add(ResourceType.SHEEP);
	}
	public void getWheat() {
		resources.add(ResourceType.WHEAT);
	}
	public void getOre() {
		resources.add(ResourceType.ORE);
	}
	public void getBrick() {
		resources.add(ResourceType.BRICK);
	}
	public void getWood() {
		resources.add(ResourceType.WOOD);
	}
	public int getSoldiers() {
		return soldiers;
	}
	public void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	}
	public int getVictoryPoints() {
		return victoryPoints;
	}
	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}
	public void buildBuilding() {
		victoryPoints++;
	}
}
