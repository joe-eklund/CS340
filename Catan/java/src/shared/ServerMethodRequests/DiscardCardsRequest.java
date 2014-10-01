package shared.ServerMethodRequests;

import shared.definitions.ResourceHand;

public class DiscardCardsRequest {
	ResourceHand discardedCards;
	int playerIndex;

	public DiscardCardsRequest(ResourceHand discardedCards, int playerIndex) {
		this.discardedCards = discardedCards;
		this.playerIndex = playerIndex;
	}

	public ResourceHand getDiscardedCards() {
		return discardedCards;
	}
	
	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setDiscardedCards(ResourceHand discardedCards) {
		this.discardedCards = discardedCards;
	}
	
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
}
