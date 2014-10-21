package shared.states;

import shared.ServerMethodResponses.GetGameModelResponse;
import client.presenter.IPresenter;

public class PlayerWaiting extends State {
	public PlayerWaiting() {
		super("PlayerWaiting");
	}
	
	@Override 
	public GetGameModelResponse getGameModel(IPresenter presenter) {
		return presenter.getProxy().getGameModel(-1, presenter.getCookie());
	}
}
