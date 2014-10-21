package shared.states;

public class FirstRound extends GamePlay {
	private boolean placedRoad;

	public FirstRound(boolean placedRoad) {
		super("FirstRound");
		this.placedRoad = placedRoad;
	}
	
	public boolean hasPlacedRoad() {
		return this.placedRoad;
	}
	
	public void setPlacedRoad() {
		placedRoad = true;
	}
}
