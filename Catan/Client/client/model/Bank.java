package client.model;

import java.util.ArrayList;
import java.util.Stack;

import shared.definitions.*;
import client.resources.*;

/**
 * The Bank class holds resource cards that are
 * available to the players. These are cards that no player currently has.
 *  <br><b>Domain:</b> Each resource must be between 0 and 19
 * @author Epper Marshall, Joe Eklund
 *
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
	 * @pre none
	 * @post returns boolean
	 */
	public boolean hasSheep(int amt) {
		return (sheep >= amt) ? true : false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @pre none
	 * @post returns boolean
	 */
	public boolean hasOre(int amt) {
		return (ore >= amt) ? true : false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @pre none
	 * @post returns boolean
	 */
	public boolean hasWood(int amt) {
		return (wood >= amt) ? true : false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @pre none
	 * @post returns boolean
	 */
	public boolean hasWheat(int amt) {
		return (wheat >= amt) ? true : false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @pre none
	 * @post returns boolean
	 */
	public boolean hasBrick(int amt) {
		return (brick >= amt) ? true : false;
	}

	/**
	 * Getter for brick
	 * @pre none
	 * @post returns the amount of brick the bank has
	 */
	public int getBrick() {
		return brick;
	}

	/**
	 * Setter for brick
	 * @param brick: an integer representing how much brick the bank needs to have after the method is called
	 * @pre none
	 * @post sets the banks amount of bricks to the specified value given in the parameters
	 */
	public void setBrick(int brick) {
		this.brick = brick;
	}

	/**
	 * Getter for ore
	 * @pre none
	 * @post returns the amount of ore the bank has
	 */
	public int getOre() {
		return ore;
	}

	/**
	 * Setter for Ore
	 * @param brick: an integer representing how much ore the bank needs to have after the method is called
	 * @pre none
	 * @post sets the banks amount of ore to the specified value given in the parameters
	 */
	public void setOre(int ore) {
		this.ore = ore;
	}

	/**
	 * Getter for sheep
	 * @pre none
	 * @post returns the amount of sheep the bank has
	 */
	public int getSheep() {
		return sheep;
	}

	/**
	 * Setter for sheep
	 * @param brick: an integer representing how much sheep the bank needs to have after the method is called
	 * @pre none
	 * @post sets the banks amount of sheep to the specified value given in the parameters
	 */
	public void setSheep(int sheep) {
		this.sheep = sheep;
	}

	/**
	 * Getter for wheat
	 * @pre none
	 * @post returns the amount of wheat the bank has
	 */
	public int getWheat() {
		return wheat;
	}
	
	/**
	 * Setter for wheat
	 * @param brick: an integer representing how much wheat the bank needs to have after the method is called
	 * @pre none
	 * @post sets the banks amount of wheat to the specified value given in the parameters
	 */
	public void setWheat(int wheat) {
		this.wheat = wheat;
	}

	/**
	 * Getter for wood
	 * @pre none
	 * @post returns the amount of wood the bank has
	 */
	public int getWood() {
		return wood;
	}
	
	/**
	 * Setter for wood
	 * @param brick: an integer representing how much wood the bank needs to have after the method is called
	 * @pre none
	 * @post sets the banks amount of wood to the specified value given in the parameters
	 */
	public void setWood(int wood) {
		this.wood = wood;
	}
	
	/**
	 * This method checks whether the bank has enough resources as specified in the parameter
	 * @param resource: resource is a class that has values for all five possible resources
	 * @pre none
	 * @post This method returns a boolean: true if the bank has the amount of resources specified, false if the bank does not have the amount of resources specified.
	 */
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
