package shared.states;

import shared.ServerMethodResponses.MoveResponse;
import shared.locations.EdgeLocation;
import client.presenter.IPresenter;

public class RoadBuilding extends GamePlay{
	private boolean firstRoad,secondRoad;
	public RoadBuilding() {
		super("RoadBuilding");
	}
	public boolean hasPlacedFirstRoad() {
		return this.firstRoad;
	}
	
	public void setPlacedFirstRoad() {
		firstRoad = true;
	}
	public boolean hasPlacedSecondRoad() {
		return this.secondRoad;
	}
	
	public void setPlacedSecondRoad() {
		secondRoad = true;
	}
	@Override
	public void buildRoad(IPresenter presenter, EdgeLocation roadLocation) {
		MoveResponse response = presenter.getProxy().buildRoad(presenter.getPlayerInfo().getIndex(), roadLocation, true, presenter.getCookie());
	}

}
