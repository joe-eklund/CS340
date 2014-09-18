package client.model;
/**
 * 
 * @author Epper Marshall, Joe Eklund
 * The Chit class holds the value for each hex on the map. When a player rolls dice
 * that equal a chit's value, players with a settlement/city touching hexes with
 * that chit value receive the corresponding amount of resources that is associated 
 * with that hex.
 */
public class Chit {
	private int value;

	/**
	 * Class constructor.
	 */
	public Chit(){
		
	}
	
	/**
	 * Returns value of the Chit.
	 * @return	The current Chit value.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the value of the Chit.
	 * @param value	The inputed value to set.
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
