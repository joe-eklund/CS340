package client.presenter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import proxy.IServer;
import shared.ServerMethodResponses.AddAIResponse;
import shared.ServerMethodResponses.ChangeLogLevelResponse;
import shared.ServerMethodResponses.CreateGameResponse;
import shared.ServerMethodResponses.GetGameCommandsResponse;
import shared.ServerMethodResponses.GetGameModelResponse;
import shared.ServerMethodResponses.ListAIResponse;
import shared.ServerMethodResponses.ListGamesResponse;
import shared.ServerMethodResponses.LoginUserResponse;
import shared.ServerMethodResponses.PostGameCommandsResponse;
import shared.ServerMethodResponses.RegisterUserResponse;
import shared.ServerMethodResponses.ResetGameResponse;
import shared.definitions.CatanColor;
import shared.definitions.GameDescription;
import shared.definitions.GameState;
import shared.definitions.PlayerDescription;
import shared.definitions.ResourceHand;
import shared.definitions.ResourceType;
import shared.definitions.ServerLogLevel;
import shared.definitions.ServerModel;
import shared.definitions.SystemState;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.states.IState;
import client.model.ClientModel;
import client.model.Log;

/**
 * A class that holds a proxy and clientModel and acts upon those objects
 *
 */
public class Presenter extends Observable implements IPresenter {
	private ClientModel clientModel;
	private IServer proxy;
	private int version;
	private String cookie;
	private int pollCycleCount;
	private PlayerDescription playerInfo;
	private ArrayList<GameDescription> games;
	private SystemState systemState;
	private GameState gameState;
	private IState state;
	
	
	/**
	 * @pre
	 *  none
	 * 
	 * @post
	 *  @obvious
	 * 
	 * @param clientModel
	 * @param proxy
	 * @param version
	 * @param cookie
	 */
	public Presenter(ClientModel clientModel, IServer proxy, String cookie) {
		super();
		this.clientModel = clientModel;
		this.proxy = proxy;
		this.version = -1;
		this.cookie = cookie;	// no cookie = empty string
		pollCycleCount = 0;
		gameState = null;
		//playerInfo=new PlayerInfo();
	}

	@Override
	public void run() {
		System.out.println("run() method in presenter");
		updateModel();
		pollCycleCount++;
	}
	
	/**
	 * @obvious
	 */
	public int getVersion() {
		return version;
	}
	
	/**
	 * @obvious
	 */
	public int getPollCycleCount() {
		return pollCycleCount;
	}
	
	@Override
	public LoginUserResponse login(String user,String pass) {
		/*
		LoginUserResponse response = this.proxy.loginUser(user, pass);
		this.cookie = response.getCookie();
		playerInfo=new PlayerDescription(null, response.getUserID(), response.getName());
		return response;
		*/
		return state.login(this, user, pass);
	}
	
	@Override
	public ListGamesResponse listGames(){
		/*
		ListGamesResponse response =  this.proxy.listGames(cookie);
		games = (ArrayList<GameDescription>) response.getGameDescriptions();
		setChanged();
		notifyObservers();
		return response;
		*/
		return state.listGames(this);
	}

	@Override
	public PlayerDescription getPlayerInfo() {
		return this.playerInfo;
	}

	@Override
	public RegisterUserResponse register(String user, String pass) {
		/*
		RegisterUserResponse response = this.proxy.registerUser(user, pass);
		this.cookie = response.getCookie();
		playerInfo = new PlayerDescription(null,response.getUserID(),response.getName());
		return response;
		*/
		return state.register(this, user, pass);
	}

	@Override
	public CreateGameResponse createGame(boolean randTiles,boolean randNums,boolean randPorts,String name) {
		/*
		CreateGameResponse response = this.proxy.createGame(randTiles, randNums, randPorts, name, this.cookie);
		games.add(response.getGameDescription());
		setChanged();
		notifyObservers();
		return response;
		*/
		return state.createGame(this, randTiles, randNums, randPorts, name);
	}
	
	@Override
	public void joinGame(CatanColor color, int gameID) {
		/*
		JoinGameResponse response = this.proxy.joinGame(color, gameID, this.cookie);
		this.cookie = response.getCookie();
		updateModel();
		*/
		state.joinGame(this, color, gameID);
	}
	
	@Override
	public ArrayList<GameDescription> getGames() {
		return games;
	}
	
	private void updateModel() {
		/*
		GetGameModelResponse response; 
		if (systemState.equals(SystemState.PLAYERWAITING)) {
			response = proxy.getGameModel(-1, cookie);
		}
		else {
			response = proxy.getGameModel(version, cookie);
		}
		*/
		GetGameModelResponse response = state.getGameModel(this);
		if(response != null && response.isSuccessful()) {
			if(response.isNeedToUpdate()) {
				System.out.println("UPDATING MODEL");
				version = response.getGameModel().getVersion();
				clientModel.updateServerModel(response.getGameModel());
			}
		}
		else {
			System.err.println("Error: Unable to process update game model request!");
		}
	}
	
	@Override
	public String[] listAIChoices() {
		ListAIResponse response = proxy.listAI(cookie);
		return (String[])response.getAiTypes().toArray();
	}
	
	public void addObserverToModel(Observer observer) {
		clientModel.addObserver(observer);
	}
	
	public ClientModel getClientModel() {
		return clientModel;
	}
	
	public void setSystemState(SystemState state) {
		this.systemState = state;
	}
	
	public SystemState getSystemState() {
		return systemState;
	}
	
	public void setGameStateAccordingToModelState() {
		gameState = GameState.valueOf(clientModel.getServerModel().getTurnTracker().getStatus().toUpperCase());
		clientModel.notifyModelObservers();
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	public boolean isGameState(GameState gameState) {
		return (this.gameState != null && this.gameState.equals(gameState));
	}
	
	public Boolean canPlaceRoad(EdgeLocation edgeLoc) {
		if (systemState.equals(GameState.SETUP)) {
			return clientModel.canBuildRoad(playerInfo.getID(), edgeLoc, true);
		}
		else {
			//TESTING BUILDING ROAD, SETTLEMENT, CITY
//			clientModel.getServerModel().getPlayers().get(0).setBrick(5);
//			clientModel.getServerModel().getPlayers().get(0).setWood(5);
//			clientModel.getServerModel().getPlayers().get(0).setWheat(5);
//			clientModel.getServerModel().getPlayers().get(0).setSheep(5);
//			clientModel.getServerModel().getPlayers().get(0).setOre(5);
//			clientModel.getServerModel().getTurnTracker().setStatus("Playing");
			//end test setup
			
			return clientModel.canBuildRoad(playerInfo.getID(), edgeLoc, false);
		}
	}
	
	public void buildRoad(EdgeLocation roadLocation) {
		if (gameState.equals(GameState.SETUP)) {
			proxy.buildRoad(playerInfo.getID(), roadLocation, true, cookie);
		}
		else {
			proxy.buildRoad(playerInfo.getID(), roadLocation, false, cookie);
		}
		updateModel();
	}
	
	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		//TESTING BUILDING ROAD, SETTLEMENT, CITY
//		clientModel.getServerModel().getPlayers().get(0).setBrick(5);
//		clientModel.getServerModel().getPlayers().get(0).setWood(5);
//		clientModel.getServerModel().getPlayers().get(0).setWheat(5);
//		clientModel.getServerModel().getPlayers().get(0).setSheep(5);
//		clientModel.getServerModel().getPlayers().get(0).setOre(5);
//		clientModel.getServerModel().getTurnTracker().setStatus("Playing");
		//end test setup
		
		return clientModel.canBuildSettlement(playerInfo.getID(), vertLoc);
	}
	
	public void buildSettlement(VertexLocation vertLoc) {
		if (gameState.equals(GameState.SETUP)) {
			proxy.buildSettlement(playerInfo.getID(), vertLoc, true, cookie);
		}
		else {
			proxy.buildSettlement(playerInfo.getID(), vertLoc, false, cookie);
		}
	}
	
	public boolean canPlaceCity(VertexLocation vertLoc) {
		//TESTING BUILDING ROAD, SETTLEMENT, CITY
//		clientModel.getServerModel().getPlayers().get(0).setBrick(5);
//		clientModel.getServerModel().getPlayers().get(0).setWood(5);
//		clientModel.getServerModel().getPlayers().get(0).setWheat(5);
//		clientModel.getServerModel().getPlayers().get(0).setSheep(5);
//		clientModel.getServerModel().getPlayers().get(0).setOre(5);
//		clientModel.getServerModel().getTurnTracker().setStatus("Playing");
		//end test setup
		
		return clientModel.canBuildCity(playerInfo.getID(), vertLoc);
	}
	
	public void buildCity(VertexLocation vertLoc) {
		proxy.buildCity(playerInfo.getID(), vertLoc, cookie);
	}
	
	public void rollNumber(int diceRoll) {
		proxy.rollNumber(playerInfo.getID(), diceRoll, cookie);
		updateModel();
	}
	
	public boolean isPlayersTurn() {
		return (playerInfo.getID() == clientModel.getServerModel().getTurnTracker().getCurrentTurn());
	}

	@Override
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	@Override
	public IServer getProxy() {
		return this.proxy;
	}

	@Override
	public void setPlayerInfo(PlayerDescription playerInfo) {
		this.playerInfo = playerInfo;
	}

	@Override
	public String getCookie() {
		return cookie;
	}

	@Override
	public void setGames(ArrayList<GameDescription> games) {
		this.games = games;
		setChanged();
		notifyObservers();
	}

	@Override
	public void updateServerModel(ServerModel serverModel) {
		this.clientModel.updateServerModel(serverModel);
		this.version = serverModel.getVersion();
	}

	@Override
	public void setState(IState state) {
		this.state = state;
	}
	
	public IState getState() {
		return state;
	}

	@Override
	public void sendChat(String message) {
		this.state.sendChat(this, message);
		
	}

	@Override
	public GetGameModelResponse getGameModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResetGameResponse resetGame() {
		return this.state.resetGame(this);
	}

	@Override
	public GetGameCommandsResponse getGameCommands() {
		return this.state.getGameCommands(this);
	}

	@Override
	public PostGameCommandsResponse postGameCommands(Log commands) {
		return this.state.postGameCommands(this, commands);
	}

	@Override
	public AddAIResponse addAI(String aiToAdd) {
		return this.state.addAI(this, aiToAdd);
	}

	@Override
	public ChangeLogLevelResponse changeLogLevel(ServerLogLevel logLevel) {
		return this.state.changeLogLevel(this, logLevel);
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		this.state.acceptTrade(this, willAccept);
	}

	@Override
	public void discardCards(ResourceHand resourceHand) {
		this.state.discardCards(this, resourceHand);
	}

	@Override
	public void offerTrade(ResourceHand offer, int receiver) {
		this.state.offerTrade(this, offer, receiver);
	}

	@Override
	public void maritimeTrade(int ratio, ResourceType inputResource,
			ResourceType outputResource) {
		this.state.maritimeTrade(this, ratio, inputResource, outputResource);
	}

	@Override
	public void finishTurn() {
		this.state.finishTurn(this);
	}

	@Override
	public void buyDevCard() {
		this.state.buyDevCard(this);
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1,
			ResourceType resource2) {
		this.state.playYearOfPlentyCard(this, resource1, resource2);
	}

	@Override
	public void playRoadBuildingCard(EdgeLocation spot1, EdgeLocation spot2) {
		this.state.playRoadBuildingCard(this, spot1, spot2);
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
		this.state.playMonopolyCard(this, resource);
	}

	@Override
	public void playSoldierCard(int victimIndex, HexLocation location) {
		this.state.playSoldierCard(this, victimIndex, location);
	}

	@Override
	public void playMonumentCard() {
		this.state.playMonumentCard(this);
	}
	
}

