package server.cookie;

public class LoggedInCookieParams {
	private String username;
	private String password;
	private int userID;
	
	/**
	 * @param username
	 * @param password
	 * @param userID
	 */
	public LoggedInCookieParams(String username, String password, int userID) {
		super();
		this.username = username;
		this.password = password;
		this.userID = userID;
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
	
}
