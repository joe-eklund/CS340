package client.model;

import java.util.Random;

import shared.definitions.DevCardType;

/**
 * Contains information relevant to the Development Card Deck and player Development Cards.
 * <br><b>Domain:</b>	There are 2 monopoly, 5 monument, 2 roadBuilding, 14 soldier, and 2 yearOfPlenty cards.
 */
public class DevCards {
	private int monopoly;
	private int monument;
	private int roadBuilding;
	private int soldier;
	private int yearOfPlenty;
	
	/**
	 * Class constructor
	 */
	public DevCards() {
		monopoly = 2;
		monument = 5;
		roadBuilding = 2;
		soldier = 14;
		yearOfPlenty = 2;
	}
	
	/**
	 * Class constructor
	 * @param monopoly
	 * @param monument
	 * @param roadBuilding
	 * @param soldier
	 * @param yearOfPlenty
	 */
	public void updateCards(int monopoly, int monument, int roadBuilding, int soldier, int yearOfPlenty) {
		this.monopoly = monopoly;
		this.monument = monument;
		this.roadBuilding = roadBuilding;
		this.soldier = soldier;
		this.yearOfPlenty = yearOfPlenty;
	}

	/**
	 * Getter for the Monopoly Card count
	 * @pre none
	 * @post returns the integer associated to the amount of Monopoly cards in the object
	 */
	public int getMonopoly() {
		return monopoly;
	}

	/**
	 * Setter for the Monopoly Card count
	 * @param monopoly
	 * @pre none
	 * @post Sets the monopoly count to the integer given in the parameters
	 */
	public void setMonopoly(int monopoly) {
		this.monopoly = monopoly;
	}

	/**
	 * Getter for the Monument Card count
	 * @pre none
	 * @post returns the integer associated to the amount of Monumet cards in the object
	 */
	public int getMonument() {
		return monument;
	}

	/**
	 * Setter for the Monument Card count
	 * @param monopoly
	 * @pre none
	 * @post Sets the monument count to the integer given in the parameters
	 */
	public void setMonument(int monument) {
		this.monument = monument;
	}

	/**
	 * Getter for the Road Building Card count
	 * @pre none
	 * @post returns the integer associated to the amount of Road Building cards in the object
	 */
	public int getRoadBuilding() {
		return roadBuilding;
	}

	/**
	 * Setter for the Road Building Card count
	 * @param monopoly
	 * @pre none
	 * @post Sets the Road Building count to the integer given in the parameters
	 */
	public void setRoadBuilding(int roadBuilding) {
		this.roadBuilding = roadBuilding;
	}

	/**
	 * Getter for the Soldier Card count
	 * @pre none
	 * @post returns the integer associated to the amount of Soldier cards in the object
	 */
	public int getSoldier() {
		return soldier;
	}

	/**
	 * Setter for the Soldier Card count
	 * @param monopoly
	 * @pre none
	 * @post Sets the Soldier count to the integer given in the parameters
	 */
	public void setSoldier(int soldier) {
		this.soldier = soldier;
	}

	/**
	 * Getter for the Year of Plenty Card count
	 * @pre none
	 * @post returns the integer associated to the amount of Year of Plenty cards in the object
	 */
	public int getYearOfPlenty() {
		return yearOfPlenty;
	}

	/**
	 * Setter for the Year of Plenty Card count
	 * @param monopoly
	 * @pre none
	 * @post Sets the Year of Plenty count to the integer given in the parameters
	 */
	public void setYearOfPlenty(int yearOfPlenty) {
		this.yearOfPlenty = yearOfPlenty;
	}
	
	/**
	 * Getter for the Total Development Card count
	 * @pre none
	 * @post returns the integer associated to the amount of total Development cards in the object
	 */
	public int getTotalDevCardCount() {
		return monopoly + monument + roadBuilding + soldier + yearOfPlenty;
	}
}
