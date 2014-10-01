package client.model;

public class DevCards {
	public int monopoly;
	public int monument;
	public int roadBuilding;
	public int soldier;
	public int yearOfPlenty;
	
	public DevCards() {
		monopoly = 2;
		monument = 5;
		roadBuilding = 2;
		soldier = 14;
		yearOfPlenty = 2;
	}
	
	public void updateCards(int monopoly, int monument, int roadBuilding, int soldier, int yearOfPlenty) {
		this.monopoly = monopoly;
		this.monument = monument;
		this.roadBuilding = roadBuilding;
		this.soldier = soldier;
		this.yearOfPlenty = yearOfPlenty;
	}

	public int getMonopoly() {
		return monopoly;
	}

	public void setMonopoly(int monopoly) {
		this.monopoly = monopoly;
	}

	public int getMonument() {
		return monument;
	}

	public void setMonument(int monument) {
		this.monument = monument;
	}

	public int getRoadBuilding() {
		return roadBuilding;
	}

	public void setRoadBuilding(int roadBuilding) {
		this.roadBuilding = roadBuilding;
	}

	public int getSoldier() {
		return soldier;
	}

	public void setSoldier(int soldier) {
		this.soldier = soldier;
	}

	public int getYearOfPlenty() {
		return yearOfPlenty;
	}

	public void setYearOfPlenty(int yearOfPlenty) {
		this.yearOfPlenty = yearOfPlenty;
	}
	
	public int getTotalDevCardCount() {
		return monopoly + monument + roadBuilding + soldier + yearOfPlenty;
	}
}
