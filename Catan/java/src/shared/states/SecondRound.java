package shared.states;

public class SecondRound extends GamePlay {
	private boolean placedRoad;

	public SecondRound(boolean placedRoad) {
		super("SecondRound");
		this.placedRoad = placedRoad;
	}
	
	public boolean hasPlacedRoad() {
		return this.placedRoad;
	}
	
	public void setPlacedRoad() {
		placedRoad = true;
	}
	
}
