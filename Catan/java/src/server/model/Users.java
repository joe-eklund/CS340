package server.model;

/*
 * The Users class implements methods that a user will have to complete before selecting a game, 
 * such as login and register.
 */
public class Users implements IUsers{
	
	/*
	 * Takes a username and password and checks if they are valid.
	 * @pre		username	username of the user
	 * @pre		password	password of the user
	 * @post 	boolean		true if the username and password are valid, false otherwise
	 */
	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * Takes a username and password and checks if they are valid to register.
	 * @pre		username	username of the user
	 * @pre		password	password of the user
	 * @post 	boolean		true if the username and password are valid, false otherwise
	 */
	@Override
	public boolean register(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
