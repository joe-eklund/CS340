package shared.definitions;

import java.util.Arrays;
import java.util.List;

public class GameDescription {
	private String title;
	private int id;
	private PlayerDescription[] playerDescriptions;
	
	public GameDescription(String title, int id,
			PlayerDescription[] playerDescriptions) {
		this.title = title;
		this.id = id;
		this.playerDescriptions = playerDescriptions;
	}

	public String getTitle() {
		return title;
	}
	
	public int getId() {
		return id;
	}
	
	public PlayerDescription[] getPlayerDescriptions() {
		return playerDescriptions;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setPlayerDescriptions(PlayerDescription[] playerDescriptions) {
		this.playerDescriptions = playerDescriptions;
	}

	@Override
	public String toString() {
		return "GameDescription [title=" + title + ", id=" + id
				+ ", playerDescriptions=" + Arrays.toString(playerDescriptions)
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + Arrays.hashCode(playerDescriptions);
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

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
		if (!Arrays.equals(playerDescriptions, other.playerDescriptions))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
