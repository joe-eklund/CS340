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
	private Stack<ResourceType> bricks;
	private Stack<ResourceType> woods;
	private Stack<ResourceType> sheeps;
	private Stack<ResourceType> ores;
	private Stack<ResourceType> wheats;
	private ArrayList<DevCardType> developmentCards;
	
	/**
	 * Class constructor. Initializes each stack with 19 of its respective resource.
	 */
	public Bank(){
		bricks=new Stack();
		wheats=new Stack();
		ores=new Stack();
		woods=new Stack();
		sheeps=new Stack();
		for(int i=0;i<19;i++){
			bricks.push(ResourceType.BRICK);
			wheats.push(ResourceType.WHEAT);
			ores.push(ResourceType.ORE);
			woods.push(ResourceType.WOOD);
			sheeps.push(ResourceType.SHEEP);
		}
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasSheep(int amt) {
		if(sheeps.size()>=amt)
			return true;
		return false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasOre(int amt) {
		if(ores.size()>=amt)
			return true;
		return false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasWood(int amt) {
		if(woods.size()>=amt)
			return true;
		return false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasWheat(int amt) {
		if(wheats.size()>=amt)
			return true;
		return false;
	}
	/**
	 * Checks to see if bank has at least the specified amount of card type.
	 * @param amt
	 * @return boolean
	 */
	public boolean hasBrick(int amt) {
		if(bricks.size()>=amt)
			return true;
		return false;
	}
}
