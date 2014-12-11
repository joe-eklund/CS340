package server.users;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This Facade implements the Login and Register commands
 *
 */
public class UsersFacade extends AUsersFacade {
	
	public UsersFacade(ArrayList<IUser> users) {
		this.users = users;
	}

	@Override
	public Serializable getModel() {
		return this.users;
	}	
}