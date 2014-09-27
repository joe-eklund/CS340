package proxy;

public class PlayerCookie {
	private String name;
	private String password;
	private int playerID;
	
	public PlayerCookie(String name, String password, int playerID) {
		this.name = name;
		this.password = password;
		this.playerID = playerID;
	}

	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getPlayerID() {
		return playerID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
}
