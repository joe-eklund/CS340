package shared.states;

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
	
}
