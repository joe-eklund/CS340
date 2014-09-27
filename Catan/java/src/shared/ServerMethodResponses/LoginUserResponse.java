package shared.ServerMethodResponses;

public class LoginUserResponse extends UserResponse implements ILoginUserResponse{

	public LoginUserResponse(boolean successful, String message, String name,
			String cookie, int userID) {
		super(successful, message, name, cookie, userID);
		// TODO Auto-generated constructor stub
	}

}
