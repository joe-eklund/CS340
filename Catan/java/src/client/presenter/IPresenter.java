package client.presenter;

import java.util.ArrayList;
import java.util.Observer;

import proxy.IServer;
import shared.ServerMethodResponses.CreateGameResponse;
import shared.ServerMethodResponses.ListGamesResponse;
import shared.ServerMethodResponses.LoginUserResponse;
import shared.ServerMethodResponses.RegisterUserResponse;
import shared.definitions.CatanColor;
import shared.definitions.GameDescription;
import shared.definitions.GameState;
import shared.definitions.PlayerDescription;
import shared.definitions.ServerModel;
import shared.definitions.SystemState;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.states.IState;
import client.model.ClientModel;

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
	
	public String[] listAIChoices();
	
	public void addObserverToModel(Observer observer);
	
	public ClientModel getClientModel();
	
	public void setSystemState(SystemState state);
	
	public SystemState getSystemState();
	
	public void buildRoad(EdgeLocation roadLocation);
	
	public Boolean canPlaceRoad(EdgeLocation edgeLoc);

	public boolean canPlaceSettlement(VertexLocation vertLoc);

	public void buildSettlement(VertexLocation vertLoc);

	public boolean canPlaceCity(VertexLocation vertLoc);

	public void buildCity(VertexLocation vertLoc);

	public boolean isPlayersTurn();

	public void setGameStateAccordingToModelState();
	
	public GameState getGameState();
	
	public boolean isGameState(GameState gameState);

	public void rollNumber(int diceRoll);

	public void setCookie(String cookie);

	public IServer getProxy();

	public void setPlayerInfo(PlayerDescription playerDescription);

	public String getCookie();

	public void setGames(ArrayList<GameDescription> games);

	public void updateServerModel(ServerModel serverModel);

	public void setState(IState state);
	
	public IState getState();
}
