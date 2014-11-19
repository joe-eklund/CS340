package server.users;

public interface IUser {

	/**
	 * 
	 * @return
	 */
	public String getUserame();
	
	/**
	 * 
	 * @param username
	 */
	public void setUserame(String username);
	
	/**
	 * 
	 * @return
	 */
	public String getPassword();
	
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password);
}
