package shared.ServerMethodResponses;

public abstract class UserResponse extends ServerResponse {
	private String message;
	private String name;
	private String cookie;
	private int userID;
	
	
	public UserResponse(boolean successful, String message, String name,
			String cookie, int userID) {
		super(successful);
		this.message = message;
		this.name = name;
		this.cookie = cookie;
		this.userID = userID;
	}

	public String getMessage() {
		return message;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCookie() {
		return cookie;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}

}
