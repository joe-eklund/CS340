package server.users;

import java.util.ArrayList;

import shared.ServerMethodRequests.UserRequest;

public abstract class AUsersFacade implements IUsersFacade {

	protected ArrayList<IUser> users;
	
	@Override
	public int loginUser(UserRequest request) {
		int result = -1;

		User unvalidatedUser = new User(request.getUsername(), request.getPassword());
		int index = users.indexOf(unvalidatedUser);
		if(index > -1) {
			IUser validUser = users.get(index);
			result = (validUser.getPassword().equals(unvalidatedUser.getPassword()) && validUser.getUserame().equals(unvalidatedUser.getUserame())) ? index : -1; 
		}
		System.out.println("User login return code: " + result);
		
		return result;
	}

	@Override
	public int registerUser(UserRequest request) {
		int result = -1;
		
		User unvalidatedUser = new User(request.getUsername(), request.getPassword());
		if(users.indexOf(unvalidatedUser) == -1) {
			users.add(unvalidatedUser);
			result = users.indexOf(unvalidatedUser);
		}
		
		System.out.println("User registration return code: " + result);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AUsersFacade other = (AUsersFacade) obj;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

}
