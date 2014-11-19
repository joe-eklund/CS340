package shared.ServerMethodRequests;


/**
 * A class for encapsulating UserRequest (login and registration) request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>username: string representing players username (must be valid according to username rules)</li>
 *      <li>password: string representing players password (must be valid according to password rules)</li>
 *    </ul>
 *
 */
public class UserRequest {
	private String username;
	private String password;
	private final static String validCharsRegex = "[0-9]*[A-z]*[_]*[-]*";
	
	/**
	 * Constructor
	 * 
	 * @post
	 *   <ul>
	 *     <li>this.username = username param</li>
	 *     <li>this.password = password param</li>
	 *   </ul>
	 * 
	 * @param username
	 * @param password
	 */
	public UserRequest(String username, String password) {
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
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @obvious
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @obvious
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean validatePreConditions() {
		boolean result = true;
		if(username.length() < 3 || username.length() > 7){
			result = false;
		}  
		else if(!username.matches(validCharsRegex)) {
			result = false;
		}    
		else if(!password.matches(validCharsRegex)) {
			result = false;
		}
		else if(password.length() < 5){
			result = false;
		}
		return result;
	}
	
}
