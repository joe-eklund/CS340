package shared.ServerMethodRequests;

import shared.definitions.PlayerIndex;
import shared.definitions.ResourceHand;

public class OfferTradeRequest {
	ResourceHand offer;
	PlayerIndex receiver;
	
	public OfferTradeRequest(ResourceHand offer, PlayerIndex receiver) {
		this.offer = offer;
		this.receiver = receiver;
	}
	
	public ResourceHand getOffer() {
		return offer;
	}
	
	public PlayerIndex getReceiver() {
		return receiver;
	}
	
	public void setOffer(ResourceHand offer) {
		this.offer = offer;
	}
	
	public void setReceiver(PlayerIndex receiver) {
		this.receiver = receiver;
	}
}
