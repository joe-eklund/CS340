package server.cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import proxy.ITranslator;

/**
 * This class will be used to help with handling the cookie used with the server
 * @author Chad
 *
 */
public class Cookie {

	public Cookie(){}
	
	/**
	 * Makes sure the login cookie is correctly formated.
	 * @param cookie - the decoded cookie JSON to be checked
	 * @return
	 * @throws InvalidCookieException 
	 */
	public static CookieParams verifyLoginCookie(String cookie, ITranslator translator) throws InvalidCookieException {
		System.out.println("verifying login cookie: " + cookie);
		
		int userCookieIndex = cookie.indexOf("catan.user=");
		
		CookieParams result;
		
		if (userCookieIndex != -1) {
			cookie = cookie.replaceFirst("catan.user=", "");
			cookie = cookie.trim();
			cookie = cookie.replaceFirst(";", "");
			int gameID = -1;
			if(cookie.contains("catan.game=")) {
				String gameCookie = cookie.split("catan.game=")[0];
				if(gameCookie.contains("%7d")) {
					try {
						gameID = Integer.parseInt(gameCookie.split("%7d")[0]);
					} catch (NumberFormatException e) {
						throw new InvalidCookieException(
								"Error: the cookie catan.game value is invalid");
					}
				}
				cookie = cookie.split("catan.game=")[0];
			}
			try {
				String cookieJSON = URLDecoder.decode(cookie, "UTF-8");
				System.out.println(cookieJSON);
				result = (CookieParams) translator.translateFrom(
						cookieJSON, CookieParams.class);
				result.setGameID(gameID);
				if (result == null || !result.validateLoggedIn()) {
					System.out.println("could not translate json: " + cookieJSON);
					throw new InvalidCookieException(
							"Error: You either did not provide a cookie or the cookie provided is invalid");
				}
			} catch (UnsupportedEncodingException e) {
				throw new InvalidCookieException(
						"Error: the provided cookie is encoded in an unrecognized format.  Please use UTF-8 encoding.");
			}
		}
		else {
			throw new InvalidCookieException(
					"Error: You either did not provide a cookie or the cookie provided is invalid");
		}
		return result;
	}
	
	/**
	 * Makes sure the Join cookie is correctly formated.
	 * @param cookie - the cookie to be checked
	 * @return
	 */
	public static CookieParams verifyJoinCookie(String cookie) {
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
		return "catan.user=%7B%22name%22%3A%22" + username + "%22%2C%22password%22%3A%22" + password + "%22%2C%22playerID%22%3A" + userID + "%7D;Path=/";
	}
	
	/**
	 * Creates the JoinCookie for joining a game
	 * @param gameID - The desired games ID 
	 * @return The completed cookie in string form
	 */
	public static String createJoinCookie(int gameID) {
		// to do
		return "catan.game=" + gameID + "%7D;Path=/;";		
	}
	
}
