package shared.ServerMethodRequests;

import shared.locations.VertexLocation;

public class BuildCityRequest {
	private String type;
	private int playerIndex;
	private VertexLocation cityLocation;

	public BuildCityRequest(int playerIndex, VertexLocation cityLocation) {
		this.type = "buildCity";
		this.playerIndex = playerIndex;
		this.cityLocation = cityLocation;
	}

	public String getType() {
		return type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public VertexLocation getCityLocation() {
		return cityLocation;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public void setCityLocation(VertexLocation cityLocation) {
		this.cityLocation = cityLocation;
	}
}
