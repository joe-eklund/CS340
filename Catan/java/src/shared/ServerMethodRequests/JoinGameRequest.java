package shared.ServerMethodRequests;


public class JoinGameRequest {
	private int id;
	private String color;
	
	public JoinGameRequest(int gameID, String color) {
		this.id = gameID;
		this.color = color.toLowerCase();
	}
	
	public int getID() {
		return id;
	}
	public String getColor() {
		return color;
	}
	public void setGameID(int gameID) {
		this.id = gameID;
	}
	public void setColor(String color) {
		this.color = color.toLowerCase();
	}
	
}
