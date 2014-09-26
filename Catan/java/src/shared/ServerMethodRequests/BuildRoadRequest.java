package shared.ServerMethodRequests;

import shared.locations.EdgeLocation;

public class BuildRoadRequest {
	private boolean free;
	private EdgeLocation roadLocation;
	
	public BuildRoadRequest(boolean free, EdgeLocation roadLocation) {
		this.free = free;
		this.roadLocation = roadLocation;
	}
	
	public boolean isFree() {
		return free;
	}
	public EdgeLocation getRoadLocation() {
		return roadLocation;
	}
	public void setFree(boolean free) {
		this.free = free;
	}
	public void setRoadLocation(EdgeLocation roadLocation) {
		this.roadLocation = roadLocation;
	}
	
	
}
