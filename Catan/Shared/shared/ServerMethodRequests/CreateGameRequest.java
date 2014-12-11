package shared.ServerMethodRequests;

import java.io.Serializable;

/**
 * A class for encapsulating CreateGame request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>randomTiles: true/false whether game tiles will be randomized or default</li>
 *      <li>randomNumbers: true/false whether game number/chits will be randomized or default</li>
 *      <li>randomPorts: true/false whether game ports will be randomized or default</li>
 *      <li>name: the name by which the game will be known</li>
 *    </ul>
 *
 */
public class CreateGameRequest implements Serializable{
	private boolean randomTiles;
	private boolean randomNumbers;
	private boolean randomPorts;
	private String name;
	
	/**
	 * @post
	 *  <ul>
	 *   <li>this.randomTiles = randomTiles param</li>
	 *   <li>this.randomNumbers = randomNumbers param</li>
	 *   <li>this.randomPorts = randomPorts param</li>
	 *   <li>this.name = name param </li>
	 *  </ul>
	 * 
	 * @param randomTiles
	 * @param randomNumbers
	 * @param randomPorts
	 * @param name
	 */
	public CreateGameRequest(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name) {
		this.randomTiles = randomTiles;
		this.randomNumbers = randomNumbers;
		this.randomPorts = randomPorts;
		this.name = name;
	}

	/**
	 * @obvious
	 */
	public boolean isRandomTiles() {
		return randomTiles;
	}

	/**
	 * @obvious
	 */
	public boolean isRandomNumbers() {
		return randomNumbers;
	}

	/**
	 * @obvious
	 */
	public boolean isRandomPorts() {
		return randomPorts;
	}

	/**
	 * @obvious
	 */
	public String getName() {
		return name;
	}

	/**
	 * @obvious
	 */
	public void setRandomTiles(boolean randomTiles) {
		this.randomTiles = randomTiles;
	}

	/**
	 * @obvious
	 */
	public void setRandomNumbers(boolean randomNumbers) {
		this.randomNumbers = randomNumbers;
	}

	/**
	 * @obvious
	 */
	public void setRandomPorts(boolean randomPorts) {
		this.randomPorts = randomPorts;
	}

	/**
	 * @obvious
	 */
	public void setName(String name) {
		this.name = name;
	}

	public boolean validate() {
		return(this.name != null);
	}
	
}
