package client.turntracker;

import java.util.Observer;

import client.base.*;

/**
 * Interface for the turn tracker controller
 */
public interface ITurnTrackerController extends IController, Observer
{
	
	/**
	 * This is called when the local player ends their turn
	 */
	void endTurn();
}

