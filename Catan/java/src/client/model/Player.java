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
//	private int sheepRatio;
//	private int brickRatio;
//	private int wheatRatio;
//	private int woodRatio;
//	private int oreRatio;
	
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
		this.victoryPoints = 2;
//		this.sheepRatio = 4;
//		this.brickRatio = 4;
//		this.wheatRatio = 4;
//		this.woodRatio = 4;
//		this.oreRatio = 4;
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
		return resources.sheeps;
	}
	public void setSheep(int sheeps) {
		resources.sheeps = sheeps;
	}
	public int getWheat() {
		return resources.wheats;
	}
	public void setWheat(int wheats) {
		resources.wheats = wheats;
	}
	public int getOre() {
		return resources.ores;
	}
	public void setOre(int ores) {
		resources.ores = ores;
	}
	public int getBrick() {
		return resources.bricks;
	}
	public void setBrick(int bricks) {
		resources.bricks = bricks;
	}
	public int getWood() {
		return resources.woods;
	}
	public void setWood(int woods) {
		resources.woods = woods;
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
//	public int getSheepRatio() {
//		return sheepRatio;
//	}
//	public void setSheepRatio(int sheepRatio) {
//		this.sheepRatio = sheepRatio;
//	}
//	public int getBrickRatio() {
//		return brickRatio;
//	}
//	public void setBrickRatio(int brickRatio) {
//		this.brickRatio = brickRatio;
//	}
//	public int getWheatRatio() {
//		return wheatRatio;
//	}
//	public void setWheatRatio(int wheatRatio) {
//		this.wheatRatio = wheatRatio;
//	}
//	public int getWoodRatio() {
//		return woodRatio;
//	}
//	public void setWoodRatio(int woodRatio) {
//		this.woodRatio = woodRatio;
//	}
//	public int getOreRatio() {
//		return oreRatio;
//	}
//	public void setOreRatio(int oreRatio) {
//		this.oreRatio = oreRatio;
//	}
//	/**
//	 * Updates all trade ratios from 4 to 3 when build on generic harbor, any ratios at 2 remain at 2.
//	 */
//	public void updateAllRatios()
//	{
//		if(oreRatio==4)
//			oreRatio=3;
//		if(woodRatio==4)
//			woodRatio=3;
//		if(wheatRatio==4)
//			wheatRatio=3;
//		if(brickRatio==4)
//			brickRatio=3;
//		if(sheepRatio==4)
//			sheepRatio=3;
//	}
	/**
	 * Checks that player has enough remaining cities
	 * @return boolean
	 */
	public boolean canBuildCity() {
		if(cities>0)
			return true;
		return false;
	}
	/**
	 * Checks that player has enough remaining settlements
	 * @return boolean
	 */
	public boolean canBuildSettlement() {
		if(settlements>0)
			return true;
		return false;
	}
	/**
	 * Checks that player has enough remaining roads
	 * @return boolean
	 */
	public boolean canBuildRoad() {
		if(roads>0)
			return true;
		return false;
	}
}
