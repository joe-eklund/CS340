package proxy;

/**
 * A class that represents the information stored in the player cookie as set by the server
 * @author joshuabgrigg
 *
 */
public class PlayerCookie {
	private String name;
	private String password;
	private int playerID;
	
	public PlayerCookie(String name, String password, int playerID) {
		this.name = name;
		this.password = password;
		this.playerID = playerID;
	}
	
	/**
	 * Gets player name associated with logged in player
	 * @pre
	 *   None
	 *   
	 * @post 
	 *   returns player's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets player password associated with logged in player
	 * @pre
	 *   None
	 *   
	 * @post
	 *   returns the player's password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Gets player ID (catan user id) associated with logged in player
	 * @pre
	 *   None
	 *   
	 * @return
	 *   returns the player's catan user id
	 */
	public int getPlayerID() {
		return playerID;
	}
	
	/**
	 * sets player name
	 * @pre
	 *   None
	 * 
	 * @post
	 *   name is set to provided name parameter
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * sets player password
	 * @pre
	 *   None
	 *   
	 * @post
	 *   password is set to provided password parameter
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * sets player id
	 * 
	 * @pre
	 *  None
	 *  
	 * @post
	 *   playerID is set to provided playerID parameter
	 * 
	 * @param playerID
	 * 
	 */
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
}
