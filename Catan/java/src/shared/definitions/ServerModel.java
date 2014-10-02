package shared.definitions;

import java.util.List;

import client.model.*;

public class ServerModel {
	private Chat chat;
	private Bank bank;
	private Log log;
	private Map map;
	private List<Player> players;
	private TradeOffer tradeOffer;
	private TurnTracker turnTracker;
	private int version;
	private int winner;
	private DevCards deck;
	
	public ServerModel(Bank bank, Chat chat, Log log, Map map,
			List<Player> players, TradeOffer tradeOffer,
			TurnTracker turnTracker, int version, int winner) {
		this.bank = bank;
		this.chat = chat;
		this.log = log;
		this.map = map;
		this.players = players;
		this.tradeOffer = tradeOffer;
		this.turnTracker = turnTracker;
		this.version = version;
		this.winner = winner;
		this.deck = new DevCards();
	}
	
	public Bank getBank() {
		return bank;
	}
	public Chat getChat() {
		return chat;
	}
	public Log getLog() {
		return log;
	}
	public Map getMap() {
		return map;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public TradeOffer getTradeOffer() {
		return tradeOffer;
	}
	public TurnTracker getTurnTracker() {
		return turnTracker;
	}
	public int getVersion() {
		return version;
	}
	public int getWinner() {
		return winner;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	public void setLog(Log log) {
		this.log = log;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public void setTradeOffer(TradeOffer tradeOffer) {
		this.tradeOffer = tradeOffer;
	}
	public void setTurnTracker(TurnTracker turnTracker) {
		this.turnTracker = turnTracker;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}

	public DevCards getDeck() {
		return deck;
	}

	public void setDeck(DevCards deck) {
		this.deck = deck;
	}
	
	
}
