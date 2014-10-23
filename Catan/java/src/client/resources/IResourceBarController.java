package client.resources;

import java.util.Observer;

import client.base.*;

/**
 * Interface for the resource bar controller
 */
public interface IResourceBarController extends IController, Observer
{
	
	/**
	 * Called by the view then the user requests to build a road
	 */
	void buildRoad();
	
	/**
	 * Called by the view then the user requests to build a settlement
	 */
	void buildSettlement();
	
	/**
	 * Called by the view then the user requests to build a city
	 */
	void buildCity();
	
	/**
	 * Called by the view then the user requests to buy a card
	 */
	void buyCard();
	
	/**
	 * Called by the view then the user requests to play a card
	 */
	void playCard();
}

