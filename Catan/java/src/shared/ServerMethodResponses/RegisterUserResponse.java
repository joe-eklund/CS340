package shared.ServerMethodResponses;

/**
 * A class for encapsulating the server response to the register user request
 *
 */
public class RegisterUserResponse extends UserResponse implements IRegisterUserResponse {

	/**
	 * @obvious see UserResponse
	 */
	public RegisterUserResponse(boolean successful, String message,
			String name, String cookie, int userID) {
		super(successful, message, name, cookie, userID);
		// TODO Auto-generated constructor stub
	}
}
