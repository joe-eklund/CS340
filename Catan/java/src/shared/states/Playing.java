package shared.states;

import shared.ServerMethodResponses.GetGameModelResponse;
import shared.locations.EdgeLocation;
import client.presenter.IPresenter;

public class Playing extends GamePlay {
	public Playing() {
		super("Playing");
	}
	
	@Override 
	public GetGameModelResponse getGameModel(IPresenter presenter) {
		return presenter.getProxy().getGameModel(presenter.getVersion(), presenter.getCookie());
	}
	
	@Override
	public void buildRoad(IPresenter presenter, EdgeLocation roadLocation) {
		presenter.getProxy().buildRoad(presenter.getPlayerInfo().getIndex(), roadLocation, false, presenter.getCookie());
	}
	@Override
	public void buyDevCard(IPresenter presenter) {
		presenter.getProxy().buyDevCard(presenter.getPlayerInfo().getIndex(), presenter.getCookie());
	}
}
