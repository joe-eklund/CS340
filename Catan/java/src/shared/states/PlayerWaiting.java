package shared.states;

import java.util.Collections;
import java.util.List;

import shared.ServerMethodResponses.GetGameModelResponse;
import client.model.Player;
import client.presenter.IPresenter;

public class PlayerWaiting extends State {
	public PlayerWaiting() {
		super("PlayerWaiting");
	}
	
	@Override 
	public GetGameModelResponse getGameModel(IPresenter presenter) {
		GetGameModelResponse response = presenter.getProxy().getGameModel(-1, presenter.getCookie());
		
		return response;
	}
}
