package shared.definitions;

public class DiceRoll {
	private int rollValue;

	public DiceRoll(int rollValue) {
		if(rollValue > 1 && rollValue < 13) {
			this.rollValue = rollValue;
		}
		else {
			//throw exception
		}
	}

	public int getRollValue() {
		return rollValue;
	}

	public void setRollValue(int rollValue) {
		if(rollValue > 1 && rollValue < 13) {
			this.rollValue = rollValue;
		}
		else {
			//throw exception
		}
	}
	
}
