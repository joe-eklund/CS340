package shared.ServerMethodRequests;

import shared.definitions.PlayerIndex;
import shared.locations.HexLocation;

public class SoldierDevRequest {
	private HexLocation location;
	private PlayerIndex victimIndex;
	
	public SoldierDevRequest(HexLocation location, PlayerIndex victimIndex) {
		this.location = location;
		this.victimIndex = victimIndex;
	}

	public HexLocation getLocation() {
		return location;
	}

	public PlayerIndex getVictimIndex() {
		return victimIndex;
	}

	public void setLocation(HexLocation location) {
		this.location = location;
	}

	public void setVictimIndex(PlayerIndex victimIndex) {
		this.victimIndex = victimIndex;
	}	
	
}
