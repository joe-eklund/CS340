package shared.definitions;

/**
 * A class that simulates rolling of dice
 *
 */
public class DiceRoll {
	private int rollValue;

	/**
	 * Class constructor
	 * @param rollValue
	 */
	public DiceRoll(int rollValue) {
		if(rollValue > 1 && rollValue < 13) {
			this.rollValue = rollValue;
		}
		else {
			//throw exception
		}
	}

	/**
	 * @obvious
	 */
	public int getRollValue() {
		return rollValue;
	}

	/**
	 * @obvious
	 * @param rollValue
	 */
	public void setRollValue(int rollValue) {
		if(rollValue > 1 && rollValue < 13) {
			this.rollValue = rollValue;
		}
		else {
			//throw exception
		}
	}
	
}
