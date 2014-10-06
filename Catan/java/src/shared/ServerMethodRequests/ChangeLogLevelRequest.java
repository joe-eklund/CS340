package shared.ServerMethodRequests;

/**
 * A class for encapsulating ChangeLogLevel request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>logLevel: valid server logging level</li>
 *    </ul>
 *
 */
public class ChangeLogLevelRequest {
	private String logLevel;

	/**
	 * @post
	 *   <ul>
	 *     <li> this.logLevel = logLevel parameter (lower case)</li>
	 *   </ul>
	 * @param logLevel
	 */
	public ChangeLogLevelRequest(String logLevel) {
		this.logLevel = logLevel.toLowerCase();
	}

	/**
	 * @obvious
	 */
	public String getlogLevel() {
		return logLevel;
	}

	/**
	 * @obvious
	 */
	public void setlogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
}
