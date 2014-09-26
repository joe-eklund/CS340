package shared.ServerMethodRequests;

import shared.definitions.CatanColor;

public class JoinGameRequest {
	private int gameID;
	private CatanColor color;
	
	public JoinGameRequest(int gameID, CatanColor color) {
		this.gameID = gameID;
		this.color = color;
	}
	
	public int getGameID() {
		return gameID;
	}
	public CatanColor getColor() {
		return color;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public void setColor(CatanColor color) {
		this.color = color;
	}
	
}
