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
}