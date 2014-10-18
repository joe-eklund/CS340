package client.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import client.data.PlayerInfo;
import shared.ServerMethodResponses.CreateGameResponse;
import shared.ServerMethodResponses.JoinGameResponse;
import shared.ServerMethodResponses.ListGamesResponse;
import shared.ServerMethodResponses.LoginUserResponse;
import shared.ServerMethodResponses.RegisterUserResponse;
import shared.definitions.CatanColor;
import shared.definitions.GameDescription;
import shared.definitions.PlayerDescription;

/**
 * The interface that defines a class that holds a proxy and clientModel
 *
 */
public interface IPresenter extends Runnable {
	
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
	
	public LoginUserResponse login(String user,String pass);
	
	public ListGamesResponse listGames();

	public PlayerDescription getPlayerInfo();
	
	public RegisterUserResponse register(String user,String pass);

	public CreateGameResponse createGame(boolean randTiles,boolean randNums,boolean randPorts,String name);
	
	public void joinGame(CatanColor color, int gameID);
	
	public ArrayList<GameDescription> getGames();
	
	public void addObserverToModel(Observer observer);
}
