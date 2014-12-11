package server.users;

import java.io.Serializable;
import java.util.ArrayList;

public class UsersFacadeStub extends AUsersFacade{
	
	@SuppressWarnings("serial")
	public UsersFacadeStub() {
		users = new ArrayList<IUser>() {{
			add(new User("Bobby", "bobby"));
			add(new User("Billy", "billy"));
			add(new User("Sandy", "sandy"));
			add(new User("Cathy", "cathy"));
		}};
	}

	@Override
	public Serializable getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
