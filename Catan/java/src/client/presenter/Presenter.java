package client.presenter;

import java.util.List;

import proxy.IServer;
import shared.ServerMethodResponses.GetGameModelResponse;
import shared.ServerMethodResponses.ListGamesResponse;
import shared.ServerMethodResponses.LoginUserResponse;
import shared.definitions.PlayerDescription;
import client.data.PlayerInfo;
import client.model.ClientModel;

/**
 * A class that holds a proxy and clientModel and acts upon those objects
 *
 */
public class Presenter implements IPresenter {
	private ClientModel clientModel;
	private IServer proxy;
	private int version;
	private String cookie;
	private int pollCycleCount;
	private PlayerDescription playerInfo;
	
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
		this.version = 0;
		this.cookie = cookie;	// no cookie = empty string
		pollCycleCount = 0;
		//playerInfo=new PlayerInfo();
	}

	@Override
	public void run() {
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
	
	public ListGamesResponse getGames(){
		return this.proxy.listGames(cookie);
	}

	@Override
	public PlayerDescription getPlayerInfo() {
		// TODO Auto-generated method stub
		return this.playerInfo;
	}
}
