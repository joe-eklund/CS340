package client.model;

/**
 * Tracks whos turn it currently is, and changes the posession of the Longest road and largest army cards each turn if neccessary.
 * @author Chad
 */
public class TurnTracker {
	private int currentTurn;
	private String status;
	private int longestRoad;
	private int largestArmy;
	
	public TurnTracker() {
		status = "Playing";
		currentTurn = 0;
	}
	
	public int getCurrentTurn() {
		return currentTurn;
	}
	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getLongestRoad() {
		return longestRoad;
	}
	public void setLongestRoad(int longestRoad) {
		this.longestRoad = longestRoad;
	}
	public int getLargestArmy() {
		return largestArmy;
	}
	public void setLargestArmy(int largestArmy) {
		this.largestArmy = largestArmy;
	}
}
