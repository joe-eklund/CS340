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
		
		List<Player> players = presenter.getClientModel().getServerModel().getPlayers();
		players.removeAll(Collections.singleton(null));
		if(players.size() == 4) {
			presenter.setStateBasedOfString(presenter.getClientModel().getServerModel().getTurnTracker().getStatus());
		}
		
		return response;
	}
}
