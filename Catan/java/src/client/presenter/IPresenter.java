package client.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import proxy.IServer;
import shared.ServerMethodResponses.AddAIResponse;
import shared.ServerMethodResponses.ChangeLogLevelResponse;
import shared.ServerMethodResponses.CreateGameResponse;
import shared.ServerMethodResponses.GetGameCommandsResponse;
import shared.ServerMethodResponses.GetGameModelResponse;
import shared.ServerMethodResponses.ListGamesResponse;
import shared.ServerMethodResponses.LoginUserResponse;
import shared.ServerMethodResponses.PostGameCommandsResponse;
import shared.ServerMethodResponses.RegisterUserResponse;
import shared.ServerMethodResponses.ResetGameResponse;
import shared.definitions.CatanColor;
import shared.definitions.GameDescription;
import shared.definitions.PlayerDescription;
import shared.definitions.ResourceHand;
import shared.definitions.ResourceType;
import shared.definitions.ServerLogLevel;
import shared.definitions.ServerModel;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.states.IState;
import client.model.ClientModel;
import client.model.Log;

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
	
	public void buildRoad(EdgeLocation roadLocation);
	
	public Boolean canPlaceRoad(EdgeLocation edgeLoc);

	public boolean canPlaceSettlement(VertexLocation vertLoc);

	public void buildSettlement(VertexLocation vertLoc);

	public boolean canPlaceCity(VertexLocation vertLoc);

	public boolean isPlayersTurn();

	public void setGameStateAccordingToModelState();

	public void setCookie(String cookie);

	public IServer getProxy();

	public void setPlayerInfo(PlayerDescription playerDescription);

	public String getCookie();

	public void setGames(ArrayList<GameDescription> games);

	public void updateServerModel(ServerModel serverModel);

	public void setState(IState state);
	
	public IState getState();

	public GetGameModelResponse getGameModel();

	public ResetGameResponse resetGame();

	public GetGameCommandsResponse getGameCommands();

	public PostGameCommandsResponse postGameCommands( Log commands);

	public void addAI( String aiToAdd);

	public ChangeLogLevelResponse changeLogLevel( ServerLogLevel logLevel);

	public void sendChat( String message);

	public void acceptTrade( boolean willAccept);

	public void discardCards( ResourceHand resourceHand);

	public void rollNumber(int number);

	public void buildCity( VertexLocation vertexLocation);

	public void offerTrade( ResourceHand offer, int receiver);

	public void maritimeTrade( int ratio, ResourceType inputResource, ResourceType outputResource);

	public void finishTurn();

	public void buyDevCard();

	public void playYearOfPlentyCard( ResourceType resource1,
			ResourceType resource2);

	public void playRoadBuildingCard( EdgeLocation spot1, EdgeLocation spot2);

	public void playMonopolyCard( ResourceType resource);

	public void playSoldierCard(int playerIndex, int victimIndex, HexLocation location);
	
	public void robPlayer(int playerIndex, int victimIndex, HexLocation location);

	public void playMonumentCard();

	public void setStateBasedOffString(String status);

	public void setVersion(int version);

	public boolean canPlaceRobber(HexLocation hexLoc);
	
	public PlayerDescription[] getPlayerInfoArray();
	
	public void setPlayerDescriptionsForGame(List<PlayerDescription> list);
}
