package shared.ServerMethodRequests;

import shared.definitions.ResourceHand;

public class DiscardCardsRequest {
	ResourceHand discardedCards;

	public DiscardCardsRequest(ResourceHand discardedCards) {
		this.discardedCards = discardedCards;
	}

	public ResourceHand getDiscardedCards() {
		return discardedCards;
	}

	public void setDiscardedCards(ResourceHand discardedCards) {
		this.discardedCards = discardedCards;
	}
	
}
