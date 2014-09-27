package shared.definitions;

import java.util.List;

public class GameDescription {
	private String title;
	private int id;
	private List<PlayerDescription> playerDescriptions;
	
	public GameDescription(String title, int id,
			List<PlayerDescription> playerDescriptions) {
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
	
	public List<PlayerDescription> getPlayerDescriptions() {
		return playerDescriptions;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setPlayerDescriptions(List<PlayerDescription> playerDescriptions) {
		this.playerDescriptions = playerDescriptions;
	}
	
	
}
