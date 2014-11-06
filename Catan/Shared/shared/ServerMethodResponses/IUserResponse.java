package shared.ServerMethodResponses;

/**
 * An interface for handling user requests(Login or Register User)
 *
 */
public interface IUserResponse extends IServerResponse{
	/**
	 * @obvious
	 */
	public String getMessage();
	
	/**
	 * @obvious
	 */
	public String getName();
	
	/**
	 * @obvious
	 */
	public String getCookie();
	
	/**
	 * @obvious
	 */
	public int getUserID();
}
