package server.users;

import shared.ServerMethodRequests.UserRequest;

/**
 * This interface defines a Facade containing the Login and Register commands
 *
 */
public interface IUsersFacade {

	boolean loginUser(UserRequest request);

}
