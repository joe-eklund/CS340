package shared.ServerMethodRequests;

import java.io.Serializable;

/**
 * A class for encapsulating ChangeLogLevel request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>logLevel: valid server logging level</li>
 *    </ul>
 *
 */
public class ChangeLogLevelRequest implements Serializable{
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
