package shared.ServerMethodRequests;


/**
 * A class for encapsulating MaritimeTrade request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "maritimeTrade"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index (Who's doing the trading)</li>
 *      <li>ratio: (integer, optional) The ratio of the trade your doing as an integer (ie. put 3 for a 3:1 trade)</li>
 *      <li>inputResource: (String, optional) What type of resource you're giving.</li>
 *      <li>outputResource: (String, optional) What type of resource you're getting.</li>
 *    </ul>
 *
 */
public class MaritimeTradeRequest {
	private String type;
	private int playerIndex;
	private int ratio;
	private String inputResource;
	private String outputResource;
	
	/**
	 * Constructor
	 * 
	 * @post
	 *   <ul>
	 *     <li>this.type = "maritimeTrade"</li>
	 *     <li>this.playerIndex = playerIndex param</li>
	 *     <li>this.ratio = ratio param</li>
	 *     <li>this.inputResource = inputResource param</li>
	 *     <li>this.outputResource = outputResource param</li>
	 *   </ul>
	 * 
	 * @param playerIndex
	 * @param ratio
	 * @param inputResource
	 * @param outputResource
	 */
	public MaritimeTradeRequest(int playerIndex, int ratio,
			String inputResource, String outputResource) {
		this.type = "maritimeTrade";
		this.playerIndex = playerIndex;
		this.ratio = ratio;
		this.inputResource = inputResource;
		this.outputResource = outputResource;
	}

	/**
	 * @obvious
	 */
	public String getType() {
		return type;
	}

	/**
	 * @obvious
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	/**
	 * @obvious
	 */
	public int getRatio() {
		return ratio;
	}

	/**
	 * @obvious
	 */
	public String getInputResource() {
		return inputResource;
	}

	/**
	 * @obvious
	 */
	public String getOutputResource() {
		return outputResource;
	}

	/**
	 * @obvious
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @obvious
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	/**
	 * @obvious
	 */
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	/**
	 * @obvious
	 */
	public void setInputResource(String inputResource) {
		this.inputResource = inputResource;
	}

	/**
	 * @obvious
	 */
	public void setOutputResource(String outputResource) {
		this.outputResource = outputResource;
	}
	
}
