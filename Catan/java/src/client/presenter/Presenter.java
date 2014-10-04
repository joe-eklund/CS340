package client.presenter;

import proxy.IServer;
import shared.ServerMethodResponses.GetGameModelResponse;
import client.model.ClientModel;

public class Presenter implements IPresenter {
	private ClientModel clientModel;
	private IServer proxy;
	private int version;
	private String cookie;
	private int pollCycleCount;
	
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
	
	public int getVersion() {
		return version;
	}
	
	public int getPollCycleCount() {
		return pollCycleCount;
	}

}
