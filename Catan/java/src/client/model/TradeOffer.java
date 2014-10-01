package client.model;

/**The TradeOffer class will be used in trades between players and the bank. 
 * @author Chad
 */
public class TradeOffer {
	
	private int sender;
	private int receiver;
	private Resources offer;
	
	public TradeOffer(int sender, int receiver, int brick, int ore, int sheep, int wheat, int wood) {
		this.sender = sender;
		this.receiver = receiver;
		offer.brick = brick;
		offer.ore = ore;
		offer.sheep = sheep;
		offer.wheat = wheat;
		offer.wood = wood;
	}
	
	public TradeOffer(int sender, int receiver, Resources offer) {
		this.sender = sender;
		this.receiver = receiver;
		this.offer = offer;
	}
	
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReciever(int reciever) {
		this.receiver = reciever;
	}
	public Resources getOffer() {
		return offer;
	}
	
	public void setOffer(int brick, int ore, int sheep, int wheat, int wood) {
		offer.brick = brick;
		offer.ore = ore;
		offer.sheep = sheep;
		offer.wheat = wheat;
		offer.wood = wood;
	}
	
	public void setOffer(Resources offer) {
		this.offer = offer;
	}
}
