package shared.ServerMethodRequests;

import shared.definitions.PlayerIndex;
import shared.locations.HexLocation;

public class SoldierDevRequest {
	private String type;
	private int playerIndex;
	private HexLocation location;
	private PlayerIndex victimIndex;
	

	public SoldierDevRequest(int playerIndex, PlayerIndex victimIndex,
			HexLocation location) {
		this.type = "Soldier";
		this.playerIndex = playerIndex;
		this.victimIndex = victimIndex;
		this.location = location;
	}


	public String getType() {
		return type;
	}


	public int getPlayerIndex() {
		return playerIndex;
	}


	public HexLocation getLocation() {
		return location;
	}


	public PlayerIndex getVictimIndex() {
		return victimIndex;
	}


	public void setType(String type) {
		this.type = type;
	}


	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}


	public void setLocation(HexLocation location) {
		this.location = location;
	}


	public void setVictimIndex(PlayerIndex victimIndex) {
		this.victimIndex = victimIndex;
	}
	
}
