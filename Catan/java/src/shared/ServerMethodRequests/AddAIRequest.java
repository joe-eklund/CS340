/**
 * 
 */
package shared.ServerMethodRequests;

/**
 * A class for encapsulating AddAI request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>AIType: aiType to be added and must be from list of available ais as provided by server</li>
 *    </ul>
 *
 */
public class AddAIRequest {
	private String AIType;

	/**
	 * @param aIName
	 */
	public AddAIRequest(String aIType) {
		super();
		AIType = aIType;
	}

	/**
	 * @return the aIName
	 */
	public String getAIType() {
		return AIType;
	}

	/**
	 * @param aIName the aIName to set
	 */
	public void setAIType(String aIType) {
		AIType = aIType;
	}
}
