package client.model;

import java.util.ArrayList;
import java.util.Stack;

import shared.definitions.*;
import client.resources.*;

/**
 * 
 * @author Epper Marshall, Joe Eklund
 * The Bank class holds resource cards, development cards, and special cards that are
 * available to the players. These are cards that no player currently has.
 */
public class Bank {
	private Resources resources;
	private ArrayList<DevCardType> developmentCards;
	
	/**
	 * Class constructor. Initializes each stack with 19 of its respective resource.
	 */
	public Bank(){
		resources = new Resources();
	}
	
	/**
	 * Class constructor. Initializes each stack with 19 of its respective resource.
	 * @param bricks: the amount of brick cards the bank has.
	 * @param wheats: the amount of wheat cards the bank has.
	 * @param ores: the amount of ores cards the bank has.
	 * @param woods: the amount of woods cards the bank has.
	 * @param sheeps: the amount of sheeps cards the bank has.
	 */
	public Bank(int bricks, int wheats, int ores, int woods, int sheeps) {
		resources = new Resources(bricks, wheats, ores, woods, sheeps);
	}
	
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasSheep(int amt) {
		return (resources.sheeps >= amt) ? true : false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasOre(int amt) {
		return (resources.ores >= amt) ? true : false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasWood(int amt) {
		return (resources.woods >= amt) ? true : false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasWheat(int amt) {
		return (resources.wheats >= amt) ? true : false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasBrick(int amt) {
		return (resources.bricks >= amt) ? true : false;
	}

	public int getBricks() {
		return resources.bricks;
	}

	public void setBricks(int bricks) {
		resources.bricks = bricks;
	}

	public int getOres() {
		return resources.ores;
	}

	public void setOres(int ores) {
		resources.ores = ores;
	}

	public int getSheeps() {
		return resources.sheeps;
	}

	public void setSheeps(int sheeps) {
		resources.sheeps = sheeps;
	}

	public int getWheats() {
		return resources.wheats;
	}

	public void setWheats(int wheats) {
		resources.wheats = wheats;
	}

	public int getWoods() {
		return resources.woods;
	}

	public void setWoods(int woods) {
		resources.woods = woods;
	}
	
	public boolean hasResource(ResourceType resource) {
		boolean hasResource = false;
		
		switch (resource) {
		case BRICK: 
			if (getBricks() > 0)
				hasResource = true;
			break;
		case ORE: 
			if (getOres() > 0)
				hasResource = true;
			break;
		case SHEEP: 
			if (getSheeps() > 0)
				hasResource = true;
			break;
		case WHEAT: 
			if (getWheats() > 0)
				hasResource = true;
			break;
		case WOOD: 
			if (getWoods() > 0)
				hasResource = true;
			break;
		}
		
		return hasResource;
	}
}
