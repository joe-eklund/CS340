package shared.ServerMethodRequests;

public class AddAIRequest {
	private String artificialIntelligenceName;

	public AddAIRequest(String artificialIntelligenceName) {
		this.artificialIntelligenceName = artificialIntelligenceName;
	}

	public String getArtificialIntelligenceName() {
		return artificialIntelligenceName;
	}

	public void setArtificialIntelligenceName(String artificialIntelligenceName) {
		this.artificialIntelligenceName = artificialIntelligenceName;
	}
	
	
}
