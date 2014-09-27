package shared.ServerMethodResponses;

import java.util.List;

public class ListAIResponse extends NonMoveResponse {
	private List<String> aiTypes;

	public ListAIResponse(boolean successful, List<String> aiTypes) {
		super(successful);
		this.aiTypes = aiTypes;
	}

	public List<String> getAiTypes() {
		return aiTypes;
	}

	public void setAiTypes(List<String> aiTypes) {
		this.aiTypes = aiTypes;
	}
}
