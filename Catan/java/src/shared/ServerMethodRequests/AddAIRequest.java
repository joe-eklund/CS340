/**
 * 
 */
package shared.ServerMethodRequests;

/**
 * @author Chad
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
