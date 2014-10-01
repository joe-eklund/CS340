package client.model;

import java.util.ArrayList;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;

/**
 * Player class contains all the information for each individual player in the game.
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
	public DevCards getNewDevCards() {
		return newDevCards;
	}
	public void setNewDevCards(DevCards newDevCards) {
		this.newDevCards = newDevCards;
	}
	public DevCards getOldDevCards() {
		return oldDevCards;
	}
	public void setOldDevCards(DevCards oldDevCards) {
		this.oldDevCards = oldDevCards;
	}
	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public boolean hasPlayedDevCard() {
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
	public Resources getResources() {
		return resources;
	}
	public void setResources(Resources resources) {
		this.resources = resources;
	}
	public int getSheep() {
		return resources.sheep;
	}
	public void setSheep(int sheep) {
		resources.sheep = sheep;
	}
	public int getWheat() {
		return resources.wheat;
	}
	public void setWheat(int wheat) {
		resources.wheat = wheat;
	}
	public int getOre() {
		return resources.ore;
	}
	public void setOre(int ore) {
		resources.ore = ore;
	}
	public int getBrick() {
		return resources.brick;
	}
	public void setBrick(int brick) {
		resources.brick = brick;
	}
	public int getWood() {
		return resources.wood;
	}
	public void setWood(int wood) {
		resources.wood = wood;
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
	/**
	 * Increases victory points when a player builds a building.
	 */
	public void buildBuilding() {
		victoryPoints++;
	}
	/**
	 * Checks that player has enough remaining cities and resources to build a city.
	 * @return boolean
	 */
	public boolean canBuildCity() {
		if(cities>0&&resources.getOre()>2&&resources.getWheat()>1)
			return true;
		return false;
	}
	/**
	 * Checks that player has enough remaining settlements and resources to build a settlement.
	 * @return boolean
	 */
	public boolean canBuildSettlement() {
		if(settlements>0&&resources.getBrick()>0&&resources.getWood()>0&&
				resources.getSheep()>0&&resources.getWheat()>0)
			return true;
		return false;
	}
	/**
	 * Checks that player has enough remaining roads and resources to build a road.
	 * @return boolean
	 */
	public boolean canBuildRoad() {
		if(roads>0&&resources.getBrick()>0&&resources.getWood()>0)
			return true;
		return false;
	}
	/**
	 * Checks to see if a player has a specific number of a specific type of resource.
	 * @param resource
	 * @param amount
	 * @return hasResource
	 */
	public boolean hasResource(ResourceType resource, int amount) {
		boolean hasResource = false;
		
		switch (resource) {
		case BRICK: 
			if (getBrick() > amount)
				hasResource = true;
			break;
		case ORE: 
			if (getOre() > amount)
				hasResource = true;
			break;
		case SHEEP: 
			if (getSheep() > amount)
				hasResource = true;
			break;
		case WHEAT: 
			if (getWheat() > amount)
				hasResource = true;
			break;
		case WOOD: 
			if (getWood() > amount)
				hasResource = true;
			break;
		}
		
		return hasResource;
	}
}
