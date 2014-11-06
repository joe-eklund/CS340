package server.cookie;

public class JoinedGameCookieParams {
	private String username;
	private String password;
	private int userID;
	private int gameID;
	
	/**
	 * @param username
	 * @param password
	 * @param userID
	 * @param gameID
	 */
	public JoinedGameCookieParams(String username, String password, int userID,
			int gameID) {
		super();
		this.username = username;
		this.password = password;
		this.userID = userID;
		this.gameID = gameID;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @return the gameID
	 */
	public int getGameID() {
		return gameID;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @param gameID the gameID to set
	 */
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
		
}
