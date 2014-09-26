package shared.ServerMethodRequests;

import shared.definitions.ResourceType;

public class MonopolyDevRequest {
	private ResourceType monopolizedResource;

	public MonopolyDevRequest(ResourceType monopolizedResource) {
		this.monopolizedResource = monopolizedResource;
	}

	public ResourceType getMonopolizedResource() {
		return monopolizedResource;
	}

	public void setMonopolizedResource(ResourceType monopolizedResource) {
		this.monopolizedResource = monopolizedResource;
	}
	
}
