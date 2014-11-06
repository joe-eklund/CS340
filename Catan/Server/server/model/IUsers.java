package server.model;

/*
 * The Users interface defines methods that a user will have to complete before selecting a game, 
 * such as login and register.
 */
public interface IUsers {

	/*
	 * Takes a username and password and checks if they are valid.
	 * @pre		username	username of the user
	 * @pre		password	password of the user
	 * @post 	boolean		true if the username and password are valid, false otherwise
	 */
	public boolean login(String username, String password);
	
	/*
	 * Takes a username and password and checks if they are valid to register.
	 * @pre		username	username of the user
	 * @pre		password	password of the user
	 * @post 	boolean		true if the username and password are valid, false otherwise
	 */
	public boolean register(String username, String password);
	
}
