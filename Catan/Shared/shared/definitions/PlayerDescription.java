package shared.definitions;

/**
 * A class that describes each player, such as the players color and id
 *
 */
public class PlayerDescription {
	private String color;
	private int id;
	private String name;
	private int index;
	
	/**
	 * Class Constructor
	 * @param color
	 * @param id
	 * @param name
	 */
	public PlayerDescription(String color, int id, String name) {
		this.color = color;
		this.id = id;
		this.name = name;
	}

	/**
	 * @obvious
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * @obvious
	 * @return
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * @obvious
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	public int getIndex() {
		return index;
	}
	
	/**
	 * @obvious
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * @obvious
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * @obvious
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @obvious
	 */
	@Override
	public String toString() {
		return "PlayerDescription [color=" + color + ", id=" + id + ", name="
				+ name + "]";
	}

	/**
	 * @obvious
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		PlayerDescription other = (PlayerDescription) obj;
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
