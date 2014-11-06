/**
 * 
 */
package shared.definitions;

/**
 * A class that represents any given user
 * @author Chad
 *
 */
public class User {
	private String username;
	private String password;
	
	/**
	 * Class constructor
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	/**
	 * @obvious
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @obvious
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @obvious
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @obvious
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
