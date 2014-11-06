package shared.ServerMethodResponses;

/**
 * A class for encapsulating the server response to a LoginUser request (extends UserResponse)
 *
 */
public class LoginUserResponse extends UserResponse implements ILoginUserResponse{

	/**
	 * @obvious see UserResponse
	 */
	public LoginUserResponse(boolean successful, String message, String name,
			String cookie, int userID) {
		super(successful, message, name, cookie, userID);
		// TODO Auto-generated constructor stub
	}

}
