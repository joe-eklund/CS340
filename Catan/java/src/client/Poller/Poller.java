package client.Poller;

import java.util.Timer;
import client.model.ClientModel;
import proxy.AServerProxy;;

public class Poller {
	private Timer poller;
	
	/**
	 * @param proxy: server proxy
	 * @param model: catan client side model to be updated every [secondsPollInterval] seconds
	 * @param secondsPollInterval: invterval in seconds at which server will be polled to update client catan model
	 */
	public Poller(AServerProxy proxy, ClientModel model, int secondsPollInterval) {
		
	}
	
	
	/**
	 * starts poller
	 * 
	 * @pre 
	 *  none
	 *  
	 * @post
	 * 	if poller is not already polling:
	 * 	   1) starts poller
	 * 
	 */
	public void startUpdates() {
		
	}
	
	
	/**
	 * stops poller
	 * 
	 * @pre
	 *  none
	 * 
	 * @post
	 * 	if poller is already polling:
	 *     1) stops poller
	 */
	public void stopUpdates() {
		
	}
}