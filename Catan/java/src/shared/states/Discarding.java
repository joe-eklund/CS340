package shared.states;

import shared.definitions.ResourceHand;
import client.presenter.IPresenter;

public class Discarding extends GamePlay {
	public Discarding() {
		super("Discarding");
	}
	
	@Override
	public void discardCards(IPresenter presenter, ResourceHand resourceHand) {
		presenter.getProxy().discardCards(presenter.getPlayerInfo().getIndex(), resourceHand, presenter.getCookie());
		presenter.setState(new Robbing());
	}
}
