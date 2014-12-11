package shared.ServerMethodRequests;

import java.io.Serializable;

/**
 * A class for encapsulating SaveGame request parameters
 *
 * @Domain
 *    <ul>
 *      <li>name: valid game name</li>
 *    </ul>
 *
 */
public class SaveGameRequest implements Serializable {

	private int id;
	private String name;

	/**
	 * Constructor
	 * 
	 * @post
	 *   this.name = name param
	 *   
	 * @param name
	 */
	public SaveGameRequest(int id, String name) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
	}

	/**
	 * @obvious
	 */
	public int getId() {
		return id;
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
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @obvious
	 */
	public void setName(String name) {
		this.name = name;
	}

}
