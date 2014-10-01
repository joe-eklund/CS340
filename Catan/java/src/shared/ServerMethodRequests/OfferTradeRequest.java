package shared.ServerMethodRequests;

import shared.definitions.ResourceHand;

public class OfferTradeRequest {
	private String type;
	private int playerIndex;
	private ResourceHand offer;
	private int receiver;
	
	public OfferTradeRequest(int playerIndex, ResourceHand offer, int receiver) {
		this.type = "offerTrade";
		this.playerIndex = playerIndex;
		this.offer = offer;
		this.receiver = receiver;
	}

	public String getType() {
		return type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public ResourceHand getOffer() {
		return offer;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public void setOffer(ResourceHand offer) {
		this.offer = offer;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

}
