package server.cookie;

public class CookieParams {
	private String name;
	private String password;
	private int playerID;
	private int game;
	
	/**
	 * @param username
	 * @param password
	 * @param userID
	 */
	public CookieParams(String username, String password, int userID, int gameID) {
		super();
		this.name = username;
		this.password = password;
		this.playerID = userID;
		this.game = gameID;
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
	 * @pre
	 *  none
	 * @return
	 * 	returns the gameID of the game the player is playing currently (null if not yet playing in a game)
	 */
	public int getGameID() {
		return game;
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


	public boolean validateLoggedIn() {
		boolean result = false;
		if(this.name != null && this.password != null) {
			result = true;
		}
		return result;
	}
	
	public boolean validateJoined() {
		boolean result = false;
		if(this.name != null && this.password != null && this.game != -1) {
			result = true;
		}
		return result;
	}


	public void setGameID(int gameID) {
		this.game = gameID;
	}
	
}
