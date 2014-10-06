package shared.ServerMethodResponses;

/**
 * A class for encapsulating the server response to a JoinGameRequest
 * @Domain
 *   cookie: (string) representing the player's catan cookie (including catan.user and catan.game ids)
 *
 */
public class JoinGameResponse extends ServerResponse implements IJoinGameResponse{
	private String cookie;

	/**
	 * Constructor
	 * 
	 * @post
	 *   this.cookie = cookie param
	 *   
	 * @param successful
	 * @param cookie
	 */
	public JoinGameResponse(boolean successful, String cookie) {
		super(successful);
		this.cookie = cookie;
	}

	/**
	 * @obvious
	 */
	public String getCookie() {
		return cookie;
	}

	/**
	 * @obvious
	 */
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

}
