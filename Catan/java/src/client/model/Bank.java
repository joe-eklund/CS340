package client.model;

import java.util.ArrayList;
import java.util.Stack;

import shared.definitions.*;
import client.resources.*;

/**
 * 
 * @author Epper Marshall
 *
 */
public class Bank {
	private Stack<ResourceType> bricks;
	private Stack<ResourceType> woods;
	private Stack<ResourceType> sheeps;
	private Stack<ResourceType> ores;
	private Stack<ResourceType> wheats;
	private ArrayList<DevCardType> developmentCards;
	
	public Bank(){
		bricks=new Stack();
		wheats=new Stack();
		ores=new Stack();
		woods=new Stack();
		sheeps=new Stack();
	}
}
