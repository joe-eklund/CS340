package shared.ServerMethodRequests;

import shared.locations.VertexLocation;

public class BuildSettlementRequest {
	private boolean free;
	private VertexLocation settlementLocation;
	
	public BuildSettlementRequest(boolean free,
			VertexLocation settlementLocation) {
		this.free = free;
		this.settlementLocation = settlementLocation;
	}

	public boolean isFree() {
		return free;
	}

	public VertexLocation getSettlementLocation() {
		return settlementLocation;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public void setSettlementLocation(VertexLocation settlementLocation) {
		this.settlementLocation = settlementLocation;
	}
	
}
