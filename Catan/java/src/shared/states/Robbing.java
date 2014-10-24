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
		MoveResponse response=presenter.getProxy().robPlayer(playerIndex, victimIndex, location, presenter.getCookie());
		if(response != null && response.isSuccessful()) {
			presenter.updateServerModel(response.getGameModel());
			try {
			    Thread.sleep(1000); //Don't even ask
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		else {
			System.err.println("Error robbing player in Robbing state.");
		}
				
		
	}
}
