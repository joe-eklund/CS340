package client.presenter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
import shared.definitions.GameDescription;
import shared.definitions.GameState;
import proxy.IServer;
import shared.ServerMethodResponses.CreateGameResponse;
import shared.ServerMethodResponses.GetGameModelResponse;
import shared.ServerMethodResponses.JoinGameResponse;
import shared.ServerMethodResponses.ListAIResponse;
import shared.ServerMethodResponses.ListGamesResponse;
import shared.ServerMethodResponses.LoginUserResponse;
import shared.ServerMethodResponses.RegisterUserResponse;
import shared.definitions.PlayerDescription;
import client.model.ClientModel;

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
	private GameState state;
	
	
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
		state = GameState.LOGIN;
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
	
	public LoginUserResponse login(String user,String pass) {
		LoginUserResponse response = this.proxy.loginUser(user, pass);
		this.cookie = response.getCookie();
		playerInfo=new PlayerDescription(null, response.getUserID(), response.getName());
		return response;
	}
	
	public ListGamesResponse listGames(){
		ListGamesResponse response =  this.proxy.listGames(cookie);
		games = (ArrayList<GameDescription>) response.getGameDescriptions();
		setChanged();
		notifyObservers();
		return response;
	}

	@Override
	public PlayerDescription getPlayerInfo() {
		// TODO Auto-generated method stub
		return this.playerInfo;
	}

	@Override
	public RegisterUserResponse register(String user, String pass) {
		RegisterUserResponse response = this.proxy.registerUser(user, pass);
		this.cookie = response.getCookie();
		playerInfo = new PlayerDescription(null,response.getUserID(),response.getName());
		return response;
	}

	@Override
	public CreateGameResponse createGame(boolean randTiles,boolean randNums,boolean randPorts,String name) {
		CreateGameResponse response = this.proxy.createGame(randTiles, randNums, randPorts, name, this.cookie);
		games.add(response.getGameDescription());
		setChanged();
		notifyObservers();
		return response;
	}
	
	@Override
	public void joinGame(CatanColor color, int gameID) {
		JoinGameResponse response = this.proxy.joinGame(color, gameID, this.cookie);
		this.cookie = response.getCookie();
		updateModel();
	}
	
	@Override
	public ArrayList<GameDescription> getGames() {
		return games;
	}
	
	private void updateModel() {
		GetGameModelResponse response = proxy.getGameModel(version, cookie);
		if(response.isSuccessful()) {
			if(response.isNeedToUpdate()) {
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
	
	public void setGameState(GameState state) {
		this.state = state;
	}
	
	public GameState getGameState() {
		return state;
	}
}

