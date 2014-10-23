package shared.states;

import shared.locations.HexLocation;
import client.presenter.IPresenter;

public class Robbing extends GamePlay {
	public Robbing() {
		super("Robbing");
	}
	
	@Override
	public void playSoldierCard(IPresenter presenter, int victimIndex,HexLocation location) {
		presenter.setState(new Playing());
	}
}
