package server.cookie;

/**
 * This class will be used to help with handling the cookie used with the server
 * @author Chad
 *
 */
public class Cookie {

	public Cookie(){}
	
	/**
	 * Makes sure the login cookie is correctly formated.
	 * @param cookie - the cookie to be checked
	 * @return
	 */
	public static LoggedInCookieParams verifyLoginCookie(String cookie) {
		// to do
		return null;
	}
	
	/**
	 * Makes sure the Join cookie is correctly formated.
	 * @param cookie - the cookie to be checked
	 * @return
	 */
	public static JoinedGameCookieParams verifyJoinCookie(String cookie) {
		// to do
		return null;
	}
	
	/**
	 * Creates the Login cookie given the params.
	 * @param username - Username of player
	 * @param password - Password of player
	 * @param userID - Users ID in the game
	 * @return The completed cookie in string form
	 */
	public static String createLoginCookie(String username, String password, int userID) {
		// to do
		return null;
	}
	
	/**
	 * Creates the JoinCookie for joining a game
	 * @param gameID - The desired games ID 
	 * @return The completed cookie in string form
	 */
	public static String createJoinCookie(int gameID) {
		// to do
		return null;		
	}
	
}
