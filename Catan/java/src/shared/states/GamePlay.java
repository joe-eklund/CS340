package shared.states;

import shared.ServerMethodResponses.MoveResponse;
import client.presenter.IPresenter;

public abstract class GamePlay extends State {

	public GamePlay(String status) {
		super(status);
	}
	
	@Override
	public void sendChat(IPresenter presenter, String message){
		MoveResponse response = presenter.getProxy().sendChat(presenter.getPlayerInfo().getID(), message, presenter.getCookie());
		if(response.isSuccessful()) {
			presenter.updateServerModel(response.getGameModel());
		}
		else {
			System.err.println("Unable to send chat");
		}
	}
	
}
