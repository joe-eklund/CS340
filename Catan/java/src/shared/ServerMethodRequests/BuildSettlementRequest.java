package shared.ServerMethodRequests;

import shared.locations.VertexLocation;

public class BuildSettlementRequest {
	private String type;
	private int playerIndex;
	private VertexLocation vertexLocation;
	private boolean free;

	public BuildSettlementRequest(int playerIndex,
			VertexLocation vertexLocation, boolean free) {
		this.type = "buildSettlement";
		this.playerIndex = playerIndex;
		this.vertexLocation = vertexLocation;
		this.free = free;
	}

	public String getType() {
		return type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public VertexLocation getVertexLocation() {
		return vertexLocation;
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

	public void setVertexLocation(VertexLocation vertexLocation) {
		this.vertexLocation = vertexLocation;
	}

	public void setFree(boolean free) {
		this.free = free;
	}
	
}
