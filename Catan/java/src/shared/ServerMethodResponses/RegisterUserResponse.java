package shared.ServerMethodResponses;

public class RegisterUserResponse extends UserResponse {

	public RegisterUserResponse(boolean successful, String message,
			String name, String cookie, int userID) {
		super(successful, message, name, cookie, userID);
		// TODO Auto-generated constructor stub
	}
}
