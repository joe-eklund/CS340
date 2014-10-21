package shared.states;

import shared.ServerMethodResponses.GetGameModelResponse;
import client.presenter.IPresenter;

public class Rolling extends GamePlay {
	public Rolling() {
		super("Rolling");
	}
	
	@Override
	public void rollNumber(IPresenter presenter, int diceRoll) {
		presenter.getProxy().rollNumber(presenter.getPlayerInfo().getIndex(), diceRoll, presenter.getCookie());
		
	}
	
	@Override 
	public GetGameModelResponse getGameModel(IPresenter presenter) {
		GetGameModelResponse response = presenter.getProxy().getGameModel(presenter.getClientModel().getServerModel().getVersion(), presenter.getCookie());
		
		return response;
	}
}