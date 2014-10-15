package shared.definitions;

import java.util.Arrays;


/**
 * A class that describes the game, such as game title and id
 */
public class GameDescription {
	private String title;
	private int id;
	private PlayerDescription[] players;
	//private List<PlayerDescription> playerDescriptions;

	
	/**
	 * Class constructor
	 * @param title
	 * @param id
	 * @param playerDescriptions
	 */
	public GameDescription(String title, int id,
			PlayerDescription[] playerDescriptions) {
		this.title = title;
		this.id = id;
		this.players = playerDescriptions;
	}

	/**
	 * @obvious
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @obvious
	 */
	public int getId() {
		return id;
	}
	
	/** 
	 * Returns descriptions of the current players of the game
	 * @pre none
	 * @post Returns descriptions of the current players of the game
	 */
	public PlayerDescription[] getPlayerDescriptions() {
		return players;
	}
	
	/**
	 * @obvious
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @obvious
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @obvious
	 * @param playerDescriptions
	 */
	public void setPlayerDescriptions(PlayerDescription[] players) {
		this.players = players;
	}

	/**
	 * @obvious
	 */
	@Override
	public String toString() {
		return "GameDescription [title=" + title + ", id=" + id
				+ ", playerDescriptions=" + Arrays.toString(players)
				+ "]";
	}

	/**
	 * @obvious
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + Arrays.hashCode(players);
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * @obvious
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameDescription other = (GameDescription) obj;
		if (id != other.id)
			return false;
		if (!Arrays.equals(players, other.players))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
