package shared.ServerMethodRequests;

import java.io.Serializable;

import shared.definitions.ResourceHand;

/**
 * A class for encapsulating OfferTrade request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "offerTrade"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index (who's offering the trade)</li>
 *      <li>offer: valid resourceHand representing cards to be offered with value of 1+ in fields for desired types to trade away and -1 desired types to gain</li>
 *    	<li>receiver: 0,1,2,3 denoting the receiver's game index</li>
 *    </ul>
 *
 */
public class OfferTradeRequest implements Serializable {
	private String type;
	private int playerIndex;
	private ResourceHand offer;
	private int receiver;
	
	/**
	 * Constructor
	 * 
	 * @post
	 *   <ul>
	 *     <li>this.type = "offerTrade"</li>
	 *     <li>this.playerIndex = playerIndex param</li>
	 *     <li>this.offer = offer param</li>
	 *     <li>this.receiver = receiver param</li>
	 *   </ul>
	 *   
	 * @param playerIndex
	 * @param offer
	 * @param receiver
	 */
	public OfferTradeRequest(int playerIndex, ResourceHand offer, int receiver) {
		this.type = "offerTrade";
		this.playerIndex = playerIndex;
		this.offer = offer;
		this.receiver = receiver;
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
	public int getPlayerIndex() {
		return playerIndex;
	}

	/**
	 * @obvious
	 */
	public ResourceHand getOffer() {
		return offer;
	}

	/**
	 * @obvious
	 */
	public int getReceiver() {
		return receiver;
	}

	/**
	 * @obvious
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @obvious
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	/**
	 * @obvious
	 */
	public void setOffer(ResourceHand offer) {
		this.offer = offer;
	}

	/**
	 * @obvious
	 */
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

}
