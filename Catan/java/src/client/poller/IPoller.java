package client.poller;

/**
 * An interface that defines a class to poll (execute) a task every n seconds where n is defined by "pollingInterval" and task is defined by "timedTask" 
 *
 */
public interface IPoller {
	
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
	public void start();
	
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
	public void stop();
}
