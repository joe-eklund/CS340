package shared.ServerMethodRequests;


public class MaritimeTradeRequest {
	private String type;
	private int playerIndex;
	private int ratio;
	private String inputResource;
	private String outputResource;
	
	public MaritimeTradeRequest(int playerIndex, int ratio,
			String inputResource, String outputResource) {
		this.type = "maritimeTrade";
		this.playerIndex = playerIndex;
		this.ratio = ratio;
		this.inputResource = inputResource;
		this.outputResource = outputResource;
	}

	public String getType() {
		return type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public int getRatio() {
		return ratio;
	}

	public String getInputResource() {
		return inputResource;
	}

	public String getOutputResource() {
		return outputResource;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public void setInputResource(String inputResource) {
		this.inputResource = inputResource;
	}

	public void setOutputResource(String outputResource) {
		this.outputResource = outputResource;
	}
	
}
