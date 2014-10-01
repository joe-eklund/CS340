package client.Poller;


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
