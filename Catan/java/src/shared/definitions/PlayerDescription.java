package shared.definitions;

public class PlayerDescription {
	private CatanColor color;
	private int id;
	private String name;
	
	public PlayerDescription(CatanColor color, int id, String name) {
		this.color = color;
		this.id = id;
		this.name = name;
	}

	public CatanColor getColor() {
		return color;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setColor(CatanColor color) {
		this.color = color;
	}
	
	public void setIndex(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
