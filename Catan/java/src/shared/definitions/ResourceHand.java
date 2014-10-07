package shared.definitions;

/**
 * Represents a collection of resources cards. It has five properties: [Brick, Wood,
 * Sheep, Wheat, Ore]. Each has an integer value. 0 means the card amount doesn’t change. A
 * negative number means you’re gaining a card. Positive means you’re giving a way a card.
 * 
 */
public class ResourceHand {
	private int Brick;
	private int Wood;
	private int Sheep; 
	private int Wheat; 
	private int Ore;
	
	/**
	 * Class constructor
	 * @param brick
	 * @param wood
	 * @param sheep
	 * @param wheat
	 * @param ore
	 */
	public ResourceHand(int brick, int wood, int sheep, int wheat, int ore) {
		Brick = brick;
		Wood = wood;
		Sheep = sheep;
		Wheat = wheat;
		Ore = ore;
	}
	
	/**
	 * @obvious
	 */
	public int getBrick() {
		return Brick;
	}
	/**
	 * @obvious
	 */
	public int getWood() {
		return Wood;
	}
	/**
	 * @obvious
	 */
	public int getSheep() {
		return Sheep;
	}
	/**
	 * @obvious
	 */
	public int getWheat() {
		return Wheat;
	}
	/**
	 * @obvious
	 */
	public int getOre() {
		return Ore;
	}
	/**
	 * @obvious
	 * @param brick
	 */
	public void setBrick(int brick) {
		Brick = brick;
	}
	/**
	 * @obvious
	 * @param wood
	 */
	public void setWood(int wood) {
		Wood = wood;
	}
	/**
	 * @obvious
	 * @param sheep
	 */
	public void setSheep(int sheep) {
		Sheep = sheep;
	}
	/**
	 * @obvious
	 * @param wheat
	 */
	public void setWheat(int wheat) {
		Wheat = wheat;
	}
	/**
	 * @obvious
	 * @param ore
	 */
	public void setOre(int ore) {
		Ore = ore;
	}

	/**
	 * @obvious
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Brick;
		result = prime * result + Ore;
		result = prime * result + Sheep;
		result = prime * result + Wheat;
		result = prime * result + Wood;
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
		if (Brick != other.Brick)
			return false;
		if (Ore != other.Ore)
			return false;
		if (Sheep != other.Sheep)
			return false;
		if (Wheat != other.Wheat)
			return false;
		if (Wood != other.Wood)
			return false;
		return true;
	}
}
