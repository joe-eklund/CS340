package shared.states;

import shared.ServerMethodResponses.GetGameModelResponse;
import client.presenter.IPresenter;

public class PlayerWaiting extends State {
	@Override 
	public GetGameModelResponse getGameModel(IPresenter presenter) {
		return presenter.getProxy().getGameModel(-1, presenter.getCookie());
	}
}
