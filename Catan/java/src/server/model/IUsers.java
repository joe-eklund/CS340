package server.model;

public interface IUsers {

	
	public boolean login(String username, String password);
	
	
	public boolean register(String username, String password);
	
}
