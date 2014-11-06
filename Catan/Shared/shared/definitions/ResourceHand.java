package shared.definitions;

/**
 * Represents a collection of resources cards. It has five properties: [Brick, Wood,
 * Sheep, Wheat, Ore]. Each has an integer value. 0 means the card amount doesn’t change. A
 * negative number means you’re gaining a card. Positive means you’re giving a way a card.
 * 
 */
public class ResourceHand {
	private int brick;
	private int wood;
	private int sheep; 
	private int wheat; 
	private int ore;
	
	/**
	 * Class constructor
	 * @param brick
	 * @param wood
	 * @param sheep
	 * @param wheat
	 * @param ore
	 */
	public ResourceHand(int brick, int wood, int sheep, int wheat, int ore) {
		this.brick = brick;
		this.wood = wood;
		this.sheep = sheep;
		this.wheat = wheat;
		this.ore = ore;
	}
	
	/**
	 * @obvious
	 */
	public int getBrick() {
		return brick;
	}
	/**
	 * @obvious
	 */
	public int getWood() {
		return wood;
	}
	/**
	 * @obvious
	 */
	public int getSheep() {
		return sheep;
	}
	/**
	 * @obvious
	 */
	public int getWheat() {
		return wheat;
	}
	/**
	 * @obvious
	 */
	public int getOre() {
		return ore;
	}
	/**
	 * @obvious
	 * @param brick
	 */
	public void setBrick(int brick) {
		this.brick = brick;
	}
	/**
	 * @obvious
	 * @param wood
	 */
	public void setWood(int wood) {
		this.wood = wood;
	}
	/**
	 * @obvious
	 * @param sheep
	 */
	public void setSheep(int sheep) {
		this.sheep = sheep;
	}
	/**
	 * @obvious
	 * @param wheat
	 */
	public void setWheat(int wheat) {
		this.wheat = wheat;
	}
	/**
	 * @obvious
	 * @param ore
	 */
	public void setOre(int ore) {
		this.ore = ore;
	}

	/**
	 * @obvious
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brick;
		result = prime * result + ore;
		result = prime * result + sheep;
		result = prime * result + wheat;
		result = prime * result + wood;
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
		ResourceHand other = (ResourceHand) obj;
		if (brick != other.brick)
			return false;
		if (ore != other.ore)
			return false;
		if (sheep != other.sheep)
			return false;
		if (wheat != other.wheat)
			return false;
		if (wood != other.wood)
			return false;
		return true;
	}
}
