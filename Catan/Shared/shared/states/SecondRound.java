package shared.states;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
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
	
	@Override
	public void buildSettlement(IPresenter presenter, VertexLocation vertLoc) {
		presenter.getProxy().buildSettlement(presenter.getPlayerInfo().getIndex(), vertLoc, true, presenter.getCookie());
		presenter.getProxy().finishTurn(presenter.getPlayerInfo().getIndex(), presenter.getCookie());
	}
}
