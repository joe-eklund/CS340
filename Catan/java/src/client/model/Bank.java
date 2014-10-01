package client.model;

import java.util.ArrayList;
import java.util.Stack;

import shared.definitions.*;
import client.resources.*;

/**
 * 
 * @author Epper Marshall, Joe Eklund
 * The Bank class holds resource cards that are
 * available to the players. These are cards that no player currently has.
 */
public class Bank {
	public int brick;
	public int ore;
	public int sheep;
	public int wheat;
	public int wood;
	
	/**
	 * Class constructor. Initializes each stack with 19 of its respective resource.
	 */
	public Bank(){
		brick=19;
		wheat=19;
		ore=19;
		wood=19;
		sheep=19;
	}
	

	/**
	 * Class constructor. Initializes each stack with the params sent in of its respective resource.
	 * @param bricks: the amount of brick cards the bank has.
	 * @param wheats: the amount of wheat cards the bank has.
	 * @param ores: the amount of ores cards the bank has.
	 * @param woods: the amount of woods cards the bank has.
	 * @param sheeps: the amount of sheeps cards the bank has.
	 */
	public Bank(int brick, int wheat, int ore, int wood, int sheep) {
		this.brick = brick;
		this.wheat = wheat;
		this.ore = ore;
		this.wood = wood;
		this.sheep = sheep;
	}
	
	
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasSheep(int amt) {
		return (sheep >= amt) ? true : false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasOre(int amt) {
		return (ore >= amt) ? true : false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasWood(int amt) {
		return (wood >= amt) ? true : false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasWheat(int amt) {
		return (wheat >= amt) ? true : false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasBrick(int amt) {
		return (brick >= amt) ? true : false;
	}

	public int getBrick() {
		return brick;
	}

	public void setBrick(int brick) {
		this.brick = brick;
	}

	public int getOre() {
		return ore;
	}

	public void setOre(int ore) {
		this.ore = ore;
	}

	public int getSheep() {
		return sheep;
	}

	public void setSheep(int sheep) {
		this.sheep = sheep;
	}

	public int getWheat() {
		return wheat;
	}

	public void setWheat(int wheat) {
		this.wheat = wheat;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}
	
	public boolean hasResource(ResourceType resource) {
		boolean hasResource = false;
		
		switch (resource) {
		case BRICK: 
			if (getBrick() > 0)
				hasResource = true;
			break;
		case ORE: 
			if (getOre() > 0)
				hasResource = true;
			break;
		case SHEEP: 
			if (getSheep() > 0)
				hasResource = true;
			break;
		case WHEAT: 
			if (getWheat() > 0)
				hasResource = true;
			break;
		case WOOD: 
			if (getWood() > 0)
				hasResource = true;
			break;
		}
		
		return hasResource;
	}
}
