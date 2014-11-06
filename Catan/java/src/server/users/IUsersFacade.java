package server.users;

import shared.ServerMethodRequests.UserRequest;

/**
 * This interface defines a Facade containing the Login and Register commands
 *
 */
public interface IUsersFacade {

	/*
	 * Takes a username and password and checks if they are valid.
	 * @pre		request		UserRequest object containing a username and password
	 * @post 	boolean		true if the username and password are valid, false otherwise
	 */
	boolean loginUser(UserRequest request);

	/*
	 * Takes a username and password and checks if they are valid to register.
	 * @pre		request		UserRequest object containing a username and password
	 * @post 	boolean		true if the username and password are valid, false otherwise
	 */	
	boolean registerUser(UserRequest request);
}
