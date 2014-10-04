package client.presenter;

public interface IPresenter {
	
	/**
	 * Updates the client game model to same version as server game model (named "run" b/c Presenter class extends TimerTask and run is able to be polled using Timer
	 * 
	 * @pre
	 * 	<ol>
	 * 	  <li>Player has valid catan.user and catan.game cookies</li>
	 *  </ol>
	 *  
	 * @post
	 *  If server game model version has changed
	 *  <ol>
	 *  	<li>client game model is updated to same version as server game model</li>
	 *  	<li>version is updated to reflect current game version</li>
	 *  </ol>
	 *  
	 * @post
	 */
	public void run();
	
	public int getVersion();
	
	public int getPollCycleCount();
}
