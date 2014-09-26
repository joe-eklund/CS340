package shared.ServerMethodRequests;

import shared.locations.VertexLocation;

public class BuildCityRequest {
	private VertexLocation cityLocation;

	public BuildCityRequest(VertexLocation cityLocation) {
		this.cityLocation = cityLocation;
	}

	public VertexLocation getCityLocation() {
		return cityLocation;
	}

	public void setCityLocation(VertexLocation cityLocation) {
		this.cityLocation = cityLocation;
	}
	
}
