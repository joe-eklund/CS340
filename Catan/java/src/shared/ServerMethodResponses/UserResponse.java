package shared.ServerMethodResponses;

/**
 * An abstract class to act as a base class for server response to User Requests (login/register).  Extends server response.
 *
 */
public abstract class UserResponse extends ServerResponse implements IUserResponse {
	private String message;
	private String name;
	private String cookie;
	private int userID;
	
	/**
	 * @obvious see UserResponse
	 */
	public UserResponse(boolean successful, String message, String name,
			String cookie, int userID) {
		super(successful);
		this.message = message;
		this.name = name;
		this.cookie = cookie;
		this.userID = userID;
	}

	/**
	 * @obvious see UserResponse
	 */
	public String getMessage() {
		return message;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * @obvious see UserResponse
	 */
	public String getCookie() {
		return cookie;
	}
	
	/**
	 * @obvious see UserResponse
	 */
	public int getUserID() {
		return userID;
	}
	
	/**
	 * @obvious see UserResponse
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @obvious see UserResponse
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @obvious see UserResponse
	 */
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	/**
	 * @obvious see UserResponse
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

}
