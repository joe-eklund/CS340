package server.users;

import shared.ServerMethodRequests.UserRequest;

public interface IUsersFacade {

	boolean loginUser(UserRequest request);

}
