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
		MoveResponse response=presenter.getProxy().playSoldierCard(playerIndex, victimIndex, location, presenter.getCookie());
		if(response != null && response.isSuccessful()) {
			presenter.updateServerModel(response.getGameModel());
			presenter.setState(new Playing());
		}
		else {
			System.err.println("Error robbing player in Robbing state.");
		}
				
		
	}
}
