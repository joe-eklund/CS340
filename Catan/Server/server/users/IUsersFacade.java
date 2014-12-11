package server.users;

import java.io.Serializable;

import shared.ServerMethodRequests.UserRequest;

/**
 * This interface defines a Facade containing the Login and Register commands
 *
 */
public interface IUsersFacade {

	/*
	 * Takes a username and password and checks if they are valid.
	 * @pre		request		UserRequest object containing a username and password
	 * @post 	int			-1 if invalid credentials; otherwise userID for validated user (userID >= 0)
	 */
	public int loginUser(UserRequest request);

	/*
	 * Takes a username and password and checks if they are valid to register.
	 * @pre		request		UserRequest object containing a username and password
	 * @post 	int			-1 if invalid credentials; otherwise userID for validated user (userID >= 0)
	 */
	public int registerUser(UserRequest request);

	public Serializable getModel();
}
