package server.users;

import shared.ServerMethodRequests.UserRequest;

/**
 * This Facade implements the Login and Register commands
 *
 */
public class UsersFacade implements IUsersFacade{

	/*
	 * Takes a username and password and checks if they are valid.
	 * @pre		request		UserRequest object containing a username and password
	 * @post 	boolean		true if the username and password are valid, false otherwise
	 */
	@Override
	public boolean loginUser(UserRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * Takes a username and password and checks if they are valid to register.
	 * @pre		request		UserRequest object containing a username and password
	 * @post 	boolean		true if the username and password are valid, false otherwise
	 */	
	@Override
	public boolean registerUser(UserRequest request) {
		// TODO Auto-generated method stub
		return false;
	}
	
}