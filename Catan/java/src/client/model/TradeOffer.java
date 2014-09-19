package client.model;

/**The TradeOffer class will be used in trades between players and the bank. 
 * @author Chad
 */
public class TradeOffer {
	
	private int sender;
	private int reciever;
	private Object offer;
	
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getReciever() {
		return reciever;
	}
	public void setReciever(int reciever) {
		this.reciever = reciever;
	}
	public Object getOffer() {
		return offer;
	}
	public void setOffer(Object offer) {
		this.offer = offer;
	}
}
