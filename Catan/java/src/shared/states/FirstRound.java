package shared.states;

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
}
