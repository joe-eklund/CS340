package client.model;
/**
 * 
 * Keeps track of the number of each resource a player has.
 *
 */
public class Resources {
	public int brick;
	public int ore;
	public int sheep;
	public int wheat;
	public int wood;
	
	public Resources() {
		brick=0;
		wheat=0;
		ore=0;
		wood=0;
		sheep=0;
	}
	public Resources(int brick, int wheat, int ore, int wood, int sheep) {
		this.brick = brick;
		this.wheat = wheat;
		this.ore = ore;
		this.wood = wood;
		this.sheep = sheep;
	}
	public int getBrick() {
		return brick;
	}
	public void setBrick(int brick) {
		this.brick = brick;
	}
	public int getOre() {
		return ore;
	}
	public void setOre(int ore) {
		this.ore = ore;
	}
	public int getSheep() {
		return sheep;
	}
	public void setSheep(int sheep) {
		this.sheep = sheep;
	}
	public int getWheat() {
		return wheat;
	}
	public void setWheat(int wheat) {
		this.wheat = wheat;
	}
	public int getWood() {
		return wood;
	}
	public void setWood(int wood) {
		this.wood = wood;
	}
	
	public int totalResourcesCount() {
		return brick + ore + sheep + wheat + wood;
	}
}

