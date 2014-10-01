package shared.ServerMethodRequests;

import shared.definitions.ResourceHand;

public class DiscardCardsRequest {
	private String type;
	private ResourceHand discardedCards;
	private int playerIndex;

	public DiscardCardsRequest(ResourceHand discardedCards, int playerIndex) {
		this.type = "discardCards";
		this.discardedCards = discardedCards;
		this.playerIndex = playerIndex;
	}

	public String getType() {
		return type;
	}

	public ResourceHand getDiscardedCards() {
		return discardedCards;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDiscardedCards(ResourceHand discardedCards) {
		this.discardedCards = discardedCards;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	
	
}
