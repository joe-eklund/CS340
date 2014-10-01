package shared.ServerMethodRequests;


public class YearOfPlentyDevRequest {
	private String type;
	private int playerIndex;
	private String resource1;
	private String resource2;

	public YearOfPlentyDevRequest(int playerIndex, String resource1, String resource2) {
		this.type = "Year_Of_Plenty";
		this.playerIndex = playerIndex;
		this.resource1 = resource1;
		this.resource2 = resource2;
	}

	public String getType() {
		return type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public String getResource1() {
		return resource1;
	}

	public String getResource2() {
		return resource2;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public void setResource1(String resource1) {
		this.resource1 = resource1;
	}

	public void setResource2(String resource2) {
		this.resource2 = resource2;
	}

}
