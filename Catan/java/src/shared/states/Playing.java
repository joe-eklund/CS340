package shared.states;

import shared.ServerMethodResponses.GetGameModelResponse;
import client.presenter.IPresenter;

public class Playing extends GamePlay {
	public Playing() {
		super("Playing");
	}
	
	@Override 
	public GetGameModelResponse getGameModel(IPresenter presenter) {
		return presenter.getProxy().getGameModel(presenter.getVersion(), presenter.getCookie());
	}
}
