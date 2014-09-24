package client.model;

import java.util.ArrayList;

/**Contains the list of players for the a game.
 * @author Chad
 * @domain set of all players in the game
 */
public class Players {
	private ArrayList<Player> players;
	/**
	 * Returns set of all players
	 * @return ArrayList<Player>
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	/**
	 * Returns player at specific index
	 * @param i
	 * @return Player
	 */
	public Player getPlayer(int i) {
		return players.get(i);
	}
	/**
	 * Sets specific player in array to new player
	 * @param i
	 * @param player
	 */
	public void setPlayer(int i, Player player) {
		players.set(i, player);
	}
	/**
	 * Adds a player to the set of players
	 * @param player
	 */
	public void addPlayer(Player player) {
		players.add(player);
	}
	/**
	 * Checks to see if another player can be added
	 * @return boolean
	 */
	public boolean canAddPlayer() {
		if(players.size()<4)
			return true;
		return false;
	}
}
