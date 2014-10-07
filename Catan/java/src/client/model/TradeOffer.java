package client.model;

/**
 * The TradeOffer class will be used in trades between players and the bank. 
 * <br><b>Domain:</b> 1 sender, 1 receiver, 0-95 resources to be traded
 * @author Chad
 */
public class TradeOffer {
	
	private int sender;
	private int receiver;
	private Resources offer;
	
	/**
	 * Class constructor
	 * @param sender
	 * @param receiver
	 * @param brick
	 * @param ore
	 * @param sheep
	 * @param wheat
	 * @param wood
	 */
	public TradeOffer(int sender, int receiver, int brick, int ore, int sheep, int wheat, int wood) {
		this.sender = sender;
		this.receiver = receiver;
		offer = new Resources();
		offer.brick = brick;
		offer.ore = ore;
		offer.sheep = sheep;
		offer.wheat = wheat;
		offer.wood = wood;
	}
	
	/**
	 * Class constructor
	 * @param sender
	 * @param receiver
	 * @param offer
	 */
	public TradeOffer(int sender, int receiver, Resources offer) {
		this.sender = sender;
		this.receiver = receiver;
		this.offer = offer;
	}
	
	/**
	 * Getter for the sender of the trade
	 * @pre none
	 * @post Returns the integer index of the player who sent the trade
	 */
	public int getSender() {
		return sender;
	}
	
	/**
	 * Setter for the sender of the trade
	 * @param sender
	 * @pre none
	 * @post The integer index representing the sender of the trade is now equal to the integer passed as a parameter
	 */
	public void setSender(int sender) {
		this.sender = sender;
	}
	
	/**
	 * Getter for the receiver of the trade
	 * @pre none
	 * @post Returns the integer index of the player who is to receive the trade
	 */
	public int getReceiver() {
		return receiver;
	}
	
	/**
	 * Setter for the receiver of the trade
	 * @param receiver
	 * @pre none
	 * @post The integer index representing the receiver of the trade is now equal to the integer passed as a parameter
	 */
	public void setReciever(int reciever) {
		this.receiver = reciever;
	}
	
	/**
	 * Getter for the specified resources of the trade
	 * @pre none
	 * @post Returns a Resource class that contains amounts of each of the resources to be traded. Positive numbers for the receiver and negative numbers for the sender
	 */
	public Resources getOffer() {
		return offer;
	}
	
	/**
	 * Setter for the specified resources of the trade
	 * @param brick
	 * @param ore
	 * @param sheep
	 * @param wheat
	 * @param wood
	 * @pre none
	 * @post Sets the amounts specified resources of the trade to the integers of the respective parameters
	 */
	public void setOffer(int brick, int ore, int sheep, int wheat, int wood) {
		offer.brick = brick;
		offer.ore = ore;
		offer.sheep = sheep;
		offer.wheat = wheat;
		offer.wood = wood;
	}
	
	/**
	 * Setter for the specified resources of the trade
	 * @param offer
	 * @pre none
	 * @post Sets the resources object containing the specified amount of resources of the trade to equal the resource object passed as a parameter 
	 */
	public void setOffer(Resources offer) {
		this.offer = offer;
	}
}
