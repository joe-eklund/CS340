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
	 * Class constructor.
	 */
	public Bank(){
		bricks=new Stack();
		wheats=new Stack();
		ores=new Stack();
		woods=new Stack();
		sheeps=new Stack();
	}
}
