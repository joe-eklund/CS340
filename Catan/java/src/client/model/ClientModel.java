package client.model;
/**
 * 
 * @author Epper Marshall
 *
 * The client side representation of the model.
 * 
 */
public class ClientModel {
	private Bank bank;
	private Chat chat;
	private Log log;
	private Map map;
	private Players players;
	private TradeOffer tradeOffer;
	private TurnTracker turnTracker;
	private int winner=-1;
	private int version=0;
	
	/**
	 * Class constructor.
	 */
	public ClientModel(){
		
	}
	
	/**
	 * Gets the bank.
	 * @return	The current bank.
	 */
	public Bank getBank() {
		return bank;
	}
	
	/**
	 * Sets the bank.
	 * @param bank	The inputed bank to set.
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	/**
	 * Gets the chat. 
	 * @return	The current chat.
	 */
	public Chat getChat() {
		return chat;
	}
	
	/**
	 * Sets the chat.
	 * @param chat	The inputed chat to set.
	 */
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	
	/**
	 * Gets the log.
	 * @return	The current log.
	 */
	public Log getLog() {
		return log;
	}
	
	/**
	 * Sets the log.
	 * @param log	The inputed log to set.
	 */
	public void setLog(Log log) {
		this.log = log;
	}
	
	/**
	 * Gets the map.
	 * @return	The current map.
	 */
	public Map getMap() {
		return map;
	}
	
	/**
	 * Sets the map.
	 * @param map	The inputed map to set.
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	
	/**
	 * Gets the players.
	 * @return	The current players.
	 */
	public Players getPlayers() {
		return players;
	}
	
	/**
	 * Sets the players.
	 * @param players	The inputed players to set.
	 */
	public void setPlayers(Players players) {
		this.players = players;
	}
	
	/**
	 * Gets the trade offer.
	 * @return	Gets the current trade offer.
	 */
	public TradeOffer getTradeOffer() {
		return tradeOffer;
	}
	
	/**
	 * Sets the trade offer.
	 * @param tradeOffer	The inputed trade offer to set.
	 */
	public void setTradeOffer(TradeOffer tradeOffer) {
		this.tradeOffer = tradeOffer;
	}
	
	/**
	 * Gets the turn tracker.
	 * @return	The current turn tracker.
	 */
	public TurnTracker getTurnTracker() {
		return turnTracker;
	}
	
	/**
	 * Sets the turn tracker.
	 * @param turnTracker	The inputed turn tracker to set.
	 */
	public void setTurnTracker(TurnTracker turnTracker) {
		this.turnTracker = turnTracker;
	}
	
	/**
	 * Gets the winner.
	 * @return	The current winner.
	 */
	public int getWinner() {
		return winner;
	}
	
	/**
	 * Sets the winner.
	 * @param winner	The inputed winner to set.
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	/**
	 * Gets the version.
	 * @return	The current version.
	 */
	public int getVersion() {
		return version;
	}
	
	/**
	 * Sets the version.
	 * @param version	The inputed version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
}

