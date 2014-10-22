package shared.states;

import shared.ServerMethodResponses.MoveResponse;
import shared.locations.EdgeLocation;
import client.presenter.IPresenter;

public class FirstRound extends GamePlay {
	private boolean placedRoad;

	public FirstRound() {
		super("FirstRound");
	}
	
	public boolean hasPlacedRoad() {
		return this.placedRoad;
	}
	
	public void setPlacedRoad() {
		placedRoad = true;
	}
	
	@Override
	public void buildRoad(IPresenter presenter, EdgeLocation roadLocation) {
//		GetGameModelResponse gameRe = presenter.getProxy().getGameModel(-1, presenter.getCookie());
		MoveResponse response = presenter.getProxy().buildRoad(presenter.getPlayerInfo().getIndex(), roadLocation, true, presenter.getCookie());
	}
}
