package shared.states;

import client.presenter.IPresenter;

public abstract class GamePlay extends State {

	public GamePlay(String status) {
		super(status);
	}
	
	@Override
	public void sendChat(IPresenter presenter, String message){
		presenter.getProxy().sendChat(presenter.getPlayerInfo().getID(), message, presenter.getCookie());
	}
	
}
