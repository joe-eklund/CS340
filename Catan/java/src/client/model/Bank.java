package client.model;

import java.util.Stack;

import client.resources.*;

/**
 * 
 * @author Epper Marshall
 *
 */
public class Bank {
	private Stack<Resource> bricks;
	private Stack<Resource> woods;
	private Stack<Resource> sheeps;
	private Stack<Resource> ores;
	private Stack<Resource> wheats;
	
	public Bank(){
		bricks=new Stack();
		wheats=new Stack();
		ores=new Stack();
		woods=new Stack();
		sheeps=new Stack();
	}
}
