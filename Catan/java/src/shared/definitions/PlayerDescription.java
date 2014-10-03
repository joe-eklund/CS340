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

	@Override
	public String toString() {
		return "PlayerDescription [color=" + color + ", id=" + id + ", name="
				+ name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		PlayerDescription other = (PlayerDescription) obj;
		if (color != other.color)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
