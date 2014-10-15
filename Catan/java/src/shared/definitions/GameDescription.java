package shared.definitions;

import java.util.ArrayList;
import java.util.List;


/**
 * A class that describes the game, such as game title and id
 */
public class GameDescription {
	private String title;
	private int id;
	private List<PlayerDescription> players;
	//private List<PlayerDescription> playerDescriptions;

	
	/**
	 * Class constructor
	 * @param title
	 * @param id
	 * @param playerDescriptions
	 */
	public GameDescription(String title, int id,
			List<PlayerDescription> players) {
		this.title = title;
		this.id = id;
		this.players = players;
	}
	
	public GameDescription(GameDescription game) {
		this.title = game.title;
		this.id = game.id;
		this.players = new ArrayList<PlayerDescription>();
		for(PlayerDescription player : game.getPlayerDescriptions()) {
			if(player.getName() != null) {
				this.players.add(player);
			}
		}
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
	public List<PlayerDescription> getPlayerDescriptions() {
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
	public void setPlayerDescriptions(List<PlayerDescription> players) {
		this.players = players;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameDescription [title=" + title + ", id=" + id + ", players="
				+ players + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
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
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
	
}
