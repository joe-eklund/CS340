package shared.states;

import shared.ServerMethodResponses.MoveResponse;
import client.presenter.IPresenter;

public class Rolling extends GamePlay {
	public Rolling() {
		super("Rolling");
	}
	
	@Override
	public void rollNumber(IPresenter presenter, int diceRoll) {
		presenter.getProxy().rollNumber(presenter.getPlayerInfo().getIndex(), diceRoll, presenter.getCookie());
//		if(diceRoll==7){
//			presenter.setState(new Discarding());
//		}
//		else{
//			//update resouce count for player
//			//SaveGameResponse response = presenter.getProxy().saveGame(gameId, name, presenter.getCookie());
//			presenter.setState(new Playing());
//		}
	}
}