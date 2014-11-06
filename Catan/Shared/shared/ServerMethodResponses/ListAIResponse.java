package shared.ServerMethodResponses;

import java.util.List;

/**
 * A class for encapsulating the server response to ListAI request
 *
 * @Domain
 *  
 */
public class ListAIResponse extends ServerResponse {
	private List<String> aiTypes;

	/**
	 * @obvious
	 */
	public ListAIResponse(boolean successful, List<String> aiTypes) {
		super(successful);
		this.aiTypes = aiTypes;
	}

	/**
	 * @obvious
	 */
	public List<String> getAiTypes() {
		return aiTypes;
	}

	/**
	 * @obvious
	 */
	public void setAiTypes(List<String> aiTypes) {
		this.aiTypes = aiTypes;
	}
}
