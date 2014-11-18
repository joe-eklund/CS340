package shared.model;

/**
 * Tracks whose turn it currently is, and changes the possession of the Longest road and largest army cards each turn if neccessary.
 * @author Chad
 */
public class TurnTracker {
	private int currentTurn;
	private String status;
	private int longestRoad;
	private int largestArmy;
	
	/**
	 * Class constructor
	 */
	public TurnTracker() {
		status = "Playing";
		currentTurn = 0;
		longestRoad=-1;
		largestArmy=-1;
	}
	
	public TurnTracker(String status, int currentTurn, int longestRoad, int largestArmy) {
		this.status = status;
		this.currentTurn = currentTurn;
		this.longestRoad = longestRoad;
		this.largestArmy = largestArmy;
	}
	
	/**
	 * Getter for the player whose current turn it is
	 * @pre none
	 * @post Returns the integer index representing the player whose turn it is
	 */
	public int getCurrentTurn() {
		return currentTurn;
	}
	
	/**
	 * Setter for the player whose current turn it is
	 * @param currentTurn
	 * @pre none
	 * @post The current turn is set to reflect the integer passed as a parameter 
	 */
	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}
	
	/**
	 * Getter for what state game play is currently in
	 * @pre none
	 * @post Returns the string that represents the current state of game play
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Setter for the state game play is currently in
	 * @param status
	 * @pre none
	 * @post The string representing the state of game play is updated to equal the string passed as a parameter
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Getter for the player with the longest road achievement
	 * @pre none
	 * @post Returns the integer index of the current player who has the longest road
	 */
	public int getLongestRoad() {
		return longestRoad;
	}
	
	/**
	 * Setter for the player with the longest road achievement 
	 * @param longestRoad
	 * @pre none
	 * @post The integer representing the player with the longest road achievement is updated to the integer passed as a parameter
	 */
	public void setLongestRoad(int longestRoad) {
		this.longestRoad = longestRoad;
	}
	
	/**
	 * Getter for the player with the largest army achievement
	 * @pre none
	 * @post Returns the intger index of the current player who has the longest road
	 */
	public int getLargestArmy() {
		return largestArmy;
	}
	/**
	 * Setter for the player with the longest road achievement
	 * @param largestArmy
	 * @pre none
	 * @post The integer representing the player with the longest road achievement is updated to the integer passed as a parameter
	 */
	public void setLargestArmy(int largestArmy) {
		this.largestArmy = largestArmy;
	}
	
	public void nextTurn() {
		if (currentTurn < 3) {
			currentTurn++;
		}
		else {
			currentTurn = 0;
		}
	}
}
