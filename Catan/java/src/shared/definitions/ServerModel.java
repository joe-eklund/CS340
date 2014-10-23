package shared.definitions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import client.model.*;

/**
 * The ServerModel class holds all the information necessary for a game. Such as a chat, bank, log, etc.
 */
public class ServerModel {
	private Chat chat;
	private Bank bank;
	private Log log;
	private Map map;
	private List<Player> players;
	private TradeOffer tradeOffer;
	private TurnTracker turnTracker;
	private int winner;
	private DevCards deck;
	private int version;
	
	/**
	 * 
	 * @param bank
	 * @param chat
	 * @param log
	 * @param map
	 * @param players
	 * @param tradeOffer
	 * @param turnTracker
	 * @param version
	 * @param winner
	 * 
	 * Constructor takes in parameters and sets them.
	 */
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
		this.winner = winner;
		this.deck = new DevCards();
	}
	
	public ServerModel() {
		winner=-1;
		players=new ArrayList<Player>();
	}
	
	/**
	 * Gets bank.
	 * @return	The bank.
	 */
	public Bank getBank() {
		return bank;
	}
	
	/**
	 * Gets the chat.
	 * @return	The chat.
	 */
	public Chat getChat() {
		return chat;
	}
	
	/**
	 * Gets the log.
	 * @return	The log.
	 */
	public Log getLog() {
		return log;
	}
	
	/**
	 * Gets the map.
	 * @return 	The map.
	 */
	public Map getMap() {
		return map;
	}
	
	/**
	 * Gets the list of players.
	 * @return	The list of players.
	 */
	public List<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Gets the current trade offer.
	 * @return	The current trade offer.
	 */
	public TradeOffer getTradeOffer() {
		return tradeOffer;
	}
	
	/**
	 * Gets the turn tracker.
	 * @return	The turn tracker.
	 */
	public TurnTracker getTurnTracker() {
		return turnTracker;
	}
	
	/**
	 * Gets the winner.
	 * @return	The winner.
	 */
	public int getWinner() {
		return winner;
	}
	
	/**
	 * Sets the bank.
	 * @param bank	Inputed bank.
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	/**
	 * Sets the chat.
	 * @param chat	Inputed chat.
	 */
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	
	/**
	 * Sets the log.
	 * @param log	Inputed log.
	 */
	public void setLog(Log log) {
		this.log = log;
	}
	
	/**
	 * Sets the map.
	 * @param map	Inputed map.
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	
	/**
	 * Sets the list of players.
	 * @param players	Inputed list of players.
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	/**
	 * Sets the current trade offer.
	 * @param tradeOffer	Inputed trade offer.
	 */
	public void setTradeOffer(TradeOffer tradeOffer) {
		this.tradeOffer = tradeOffer;
	}
	
	/**
	 * Sets the turn tracker.
	 * @param turnTracker	Inputed turn tracker.
	 */
	public void setTurnTracker(TurnTracker turnTracker) {
		this.turnTracker = turnTracker;
	}
	
	/**
	 * Sets the winner.
	 * @param winner	Inputed winner.
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}

	/**
	 * Gets the deck.
	 * @return	The deck.
	 */
	public DevCards getDeck() {
		return deck;
	}

	/**
	 * Sets the deck.
	 * @param deck	Inputed deck.
	 */
	public void setDeck(DevCards deck) {
		this.deck = deck;
	}
	
	/**
	 * Gets the version.
	 * @return	The version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * 
	 */
	public Set<Port> getRatios(int playerIndex){
		Set<Port> ratios=new HashSet<Port>();

		VertexLocation v1=new VertexLocation();
		VertexLocation v2=new VertexLocation();
		for(int i=0;i<map.getPorts().size();i++){
			if(map.getPorts().get(i).getDirection().equals("N")){
				v1=new VertexLocation(map.getPorts().get(i).getLocation(),VertexDirection.NorthEast);
				v2=new VertexLocation(map.getPorts().get(i).getLocation(),VertexDirection.NorthWest);
			}else if(map.getPorts().get(i).getDirection().equals("S")){
				v1=new VertexLocation(new HexLocation(map.getPorts().get(i).getLocation().getX(),map.getPorts().get(i).getLocation().getY()-1),VertexDirection.NorthEast);
				v2=new VertexLocation(new HexLocation(map.getPorts().get(i).getLocation().getX(),map.getPorts().get(i).getLocation().getY()-1),VertexDirection.NorthWest);
			}else if(map.getPorts().get(i).getDirection().equals("NE")){
				v1=new VertexLocation(map.getPorts().get(i).getLocation(),VertexDirection.NorthEast);
				v2=new VertexLocation(new HexLocation(map.getPorts().get(i).getLocation().getX()+1,map.getPorts().get(i).getLocation().getY()),VertexDirection.NorthWest);
			}else if(map.getPorts().get(i).getDirection().equals("NW")){
				v1=new VertexLocation(new HexLocation(map.getPorts().get(i).getLocation().getX()-1,map.getPorts().get(i).getLocation().getY()-1),VertexDirection.NorthEast);
				v2=new VertexLocation(map.getPorts().get(i).getLocation(),VertexDirection.NorthWest);
			}else if(map.getPorts().get(i).getDirection().equals("SW")){
				v1=new VertexLocation(new HexLocation(map.getPorts().get(i).getLocation().getX()-1,map.getPorts().get(i).getLocation().getY()-1),VertexDirection.NorthEast);
				v2=new VertexLocation(new HexLocation(map.getPorts().get(i).getLocation().getX(),map.getPorts().get(i).getLocation().getY()-1),VertexDirection.NorthWest);
			}else if(map.getPorts().get(i).getDirection().equals("SE")){
				v1=new VertexLocation(new HexLocation(map.getPorts().get(i).getLocation().getX(),map.getPorts().get(i).getLocation().getY()-1),VertexDirection.NorthEast);
				v2=new VertexLocation(new HexLocation(map.getPorts().get(i).getLocation().getX()+1,map.getPorts().get(i).getLocation().getY()),VertexDirection.NorthWest);
			}
			for(int j=0;j<map.getCities().size();j++){
				if(map.getCities().get(j).getOwnerIndex()==playerIndex&&(map.getCities().get(j).getLocation().equals(v1)||map.getCities().get(j).getLocation().equals(v2))){
					ratios.add(map.getPorts().get(i));
				}
			}
			for(int j=0;j<map.getSettlements().size();j++){
				if(map.getSettlements().get(j).getOwnerIndex()==playerIndex&&(map.getSettlements().get(j).getLocation().equals(v1)||map.getSettlements().get(j).getLocation().equals(v2))){
					ratios.add(map.getPorts().get(i));
				}
			}
		}
		return ratios;
	}
	
	public void incrementVersion() {
		version++;
	}
	
	public Player getPlayerByID(int id) {
		for (Player player : players) {
			if (player.getPlayerID() == id)
				return player;
		}
		return null;
	}
	
	public int getPlayerIndexByID(int id) {
		for (Player player : players) {
			if (player.getPlayerID() == id)
				return player.getPlayerIndex();
		}
		return -1;
	}
}
