package shared.ServerMethodRequests;

import java.io.Serializable;

/**
 * A class for encapsulating LoadGame request parameters
 *
 * @Domain
 *    <ul>
 *      <li>name: valid saved game name</li>
 *    </ul>
 *
 */
public class LoadGameRequest implements Serializable{
	private String name;
	
	/**
	 * Constructor
	 * @post
	 *   this.name = name param
	 * @param name
	 */
	public LoadGameRequest(String name) {
		this.name = name;
	}

	/**
	 * @obvious
	 */
	public String getName() {
		return name;
	}

	/**
	 * @obvious
	 */
	public void setName(String name) {
		this.name = name;
	}

}
