package shared.states;

import shared.locations.EdgeLocation;
import client.presenter.IPresenter;

public class SecondRound extends GamePlay {
	private boolean placedRoad;

	public SecondRound() {
		super("SecondRound");
	}
	
	public boolean hasPlacedRoad() {
		return this.placedRoad;
	}
	
	public void setPlacedRoad() {
		placedRoad = true;
	}
	
	@Override
	public void buildRoad(IPresenter presenter, EdgeLocation roadLocation) {
		presenter.getProxy().buildRoad(presenter.getPlayerInfo().getIndex(), roadLocation, true, presenter.getCookie());
	}
}
