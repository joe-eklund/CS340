package shared.states;

import shared.ServerMethodResponses.MoveResponse;
import shared.locations.HexLocation;
import client.presenter.IPresenter;

public class Robbing extends GamePlay {
	public Robbing() {
		super("Robbing");
	}
	
	@Override
	public void robPlayer(IPresenter presenter, int playerIndex, int victimIndex,HexLocation location) {
		presenter.getProxy().robPlayer(playerIndex, victimIndex, location, presenter.getCookie());		
	}
}
