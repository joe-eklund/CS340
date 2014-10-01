package shared.ServerMethodRequests;

import shared.locations.EdgeLocation;

public class BuildRoadRequest {
	private String type;
	private int playerIndex;
	private EdgeLocation roadLocation;
	private boolean free;
		
	public BuildRoadRequest(int playerIndex, EdgeLocation roadLocation,
			boolean free) {
		this.type = "buildRoad";
		this.playerIndex = playerIndex;
		this.roadLocation = roadLocation;
		this.free = free;
	}

	public String getType() {
		return type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public EdgeLocation getRoadLocation() {
		return roadLocation;
	}

	public boolean isFree() {
		return free;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public void setRoadLocation(EdgeLocation roadLocation) {
		this.roadLocation = roadLocation;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

}
