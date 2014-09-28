package shared.ServerMethodRequests;

import shared.definitions.ResourceType;

public class YearOfPlentyDevRequest {
	private ResourceType resource1;
	private ResourceType resource2;
	
	public YearOfPlentyDevRequest(ResourceType resource1, ResourceType resource2) {
		this.resource1 = resource1;
		this.resource2 = resource2;
	}

	public ResourceType getResource1() {
		return resource1;
	}
	
	public ResourceType getResource2() {
		return resource2;
	}
	
	public void setResource1(ResourceType resource1) {
		this.resource1 = resource1;
	}
	
	public void setResource2(ResourceType resource2) {
		this.resource2 = resource2;
	}
}