package client.poller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import client.presenter.IPresenter;
/**
 * A class to poll (execute) a task every n seconds where n is defined by "pollingInterval" and task is defined by "timedTask"
 * 
 * @author joshuabgrigg
 *
 */
public class Poller implements IPoller {
	IPresenter timedTask;
	//Timer timer;
	ScheduledExecutorService timer;
	private int pollingInterval;
	private boolean polling;
	
	/**
	 * @param proxy: server proxy
	 * @param model: catan client side model to be updated every [secondsPollInterval] seconds
	 * @param secondsPollInterval: invterval in seconds at which server will be polled to update client catan model
	 */
	public Poller(IPresenter timedTask, int pollingInterval) {
		this.timedTask = timedTask;
		this.pollingInterval = pollingInterval;
		this.polling = false;
		//timer = new Timer();
		timer = Executors.newSingleThreadScheduledExecutor();
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
	public void start() {
		if(!polling) {
			//timer.schedule(timedTask, 0, pollingInterval * 1000);
			timer.scheduleWithFixedDelay(timedTask, 0, pollingInterval, TimeUnit.MILLISECONDS);
		}
		polling = true;
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
	public void stop() {
		if(polling) {
			//timer.cancel();
			timer.shutdownNow();
		}
		this.polling = false;
	}
}