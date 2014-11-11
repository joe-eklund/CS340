package shared.ServerMethodRequests;

/**
 * A class for encapsulating JoinGame request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>id: valid catan session player id</li>
 *      <li>color: valid catan color string</li>
 *    </ul>
 *
 */
public class JoinGameRequest {
	private int id;
	private String color;
	
	/**
	 * Constructor
	 * 
	 * @post
	 *   <ul>
	 *     <li>this.id = gameID param</li>
	 *     <li>this.color = color param (lower case)</li>
	 *   </ul>
	 * 
	 * @param gameID
	 * @param color
	 */
	public JoinGameRequest(int gameID, String color) {
		this.id = gameID;
		this.color = color.toLowerCase();
	}
	
	/**
	 * @obvious
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * @obvious
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * @obvious
	 */
	public void setGameID(int gameID) {
		this.id = gameID;
	}
	
	/**
	 * @obvious
	 */
	public void setColor(String color) {
		this.color = color.toLowerCase();
	}

	public boolean validate() {
		return (this.color != null);
	}
	
}
