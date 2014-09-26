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
	
	public ResourceHand(int brick, int wood, int sheep, int wheat, int ore) {
		Brick = brick;
		Wood = wood;
		Sheep = sheep;
		Wheat = wheat;
		Ore = ore;
	}
	
	public int getBrick() {
		return Brick;
	}
	public int getWood() {
		return Wood;
	}
	public int getSheep() {
		return Sheep;
	}
	public int getWheat() {
		return Wheat;
	}
	public int getOre() {
		return Ore;
	}
	public void setBrick(int brick) {
		Brick = brick;
	}
	public void setWood(int wood) {
		Wood = wood;
	}
	public void setSheep(int sheep) {
		Sheep = sheep;
	}
	public void setWheat(int wheat) {
		Wheat = wheat;
	}
	public void setOre(int ore) {
		Ore = ore;
	}
}
