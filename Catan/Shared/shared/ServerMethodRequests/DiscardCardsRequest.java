package shared.ServerMethodRequests;

import java.io.Serializable;

import shared.definitions.ResourceHand;

/**
 * A class for encapsulating DiscardCard request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "discardCards"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index</li>
 *      <li>discardCards: valid discard resourceHand representing cards to be discarded with value of 1+ in fields for desired types to discard</li>
 *    </ul>
 *
 */
public class DiscardCardsRequest implements Serializable{
	private String type;
	private ResourceHand discardedCards;
	private int playerIndex;

	/**
	 * Constructor
	 * 
	 * @post
	 *   <ul>
	 *     <li>this.type = "discardCards"</li>
	 *     <li>this.discardedCards = discardedCards param</li>
	 *     this.playerIndex = playerIndex param</li>
	 *   </ul>
	 * 
	 * @param discardedCards
	 * @param playerIndex
	 */
	public DiscardCardsRequest(ResourceHand discardedCards, int playerIndex) {
		this.type = "discardCards";
		this.discardedCards = discardedCards;
		this.playerIndex = playerIndex;
	}

	/**
	 * @obvious
	 */
	public String getType() {
		return type;
	}

	/**
	 * @obvious
	 */
	public ResourceHand getDiscardedCards() {
		return discardedCards;
	}

	/**
	 * @obvious
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @obvious
	 */
	public void setDiscardedCards(ResourceHand discardedCards) {
		this.discardedCards = discardedCards;
	}

	/**
	 * @obvious
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

}
