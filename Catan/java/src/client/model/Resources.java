package client.model;

public class Resources {
	public int bricks;
	public int ores;
	public int sheeps;
	public int wheats;
	public int woods;
	
	public Resources() {
		bricks=0;
		wheats=0;
		ores=0;
		woods=0;
		sheeps=0;
	}
	public Resources(int bricks, int wheats, int ores, int woods, int sheeps) {
		this.bricks = bricks;
		this.wheats = wheats;
		this.ores = ores;
		this.woods = woods;
		this.sheeps = sheeps;
	}
	public int getBricks() {
		return bricks;
	}
	public void setBricks(int bricks) {
		this.bricks = bricks;
	}
	public int getOres() {
		return ores;
	}
	public void setOres(int ores) {
		this.ores = ores;
	}
	public int getSheeps() {
		return sheeps;
	}
	public void setSheeps(int sheeps) {
		this.sheeps = sheeps;
	}
	public int getWheats() {
		return wheats;
	}
	public void setWheats(int wheats) {
		this.wheats = wheats;
	}
	public int getWoods() {
		return woods;
	}
	public void setWoods(int woods) {
		this.woods = woods;
	}
	
	public int totalResourcesCount() {
		return bricks + ores + sheeps + wheats + woods;
	}
}
