package shared.ServerMethodRequests;

import shared.locations.EdgeLocation;

public class RoadBuildingDevRequest {
	private String type;
	private int playerIndex;
	private EdgeLocation spot1;
	private EdgeLocation spot2;
	
	public RoadBuildingDevRequest(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {
		this.type = "Road_Building";
		this.playerIndex = playerIndex;
		this.spot1 = spot1;
		this.spot2 = spot2;
	}

	public String getType() {
		return type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public EdgeLocation getSpot1() {
		return spot1;
	}

	public EdgeLocation getSpot2() {
		return spot2;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public void setSpot1(EdgeLocation spot1) {
		this.spot1 = spot1;
	}

	public void setSpot2(EdgeLocation spot2) {
		this.spot2 = spot2;
	}

}
