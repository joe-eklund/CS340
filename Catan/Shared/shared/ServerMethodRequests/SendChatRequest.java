package shared.ServerMethodRequests;


/**
 * A class for encapsulating SendChat request parameters
 * 
 * @Domain
 *    <ul>
 *      <li>type: "sendChat"</li>
 *      <li>playerIndex: 0,1,2,3 denoting the player's game index</li>
 *      <li>content: (string) chat message being sent</li>
 *    </ul>
 *
 */
public class SendChatRequest {
	private String type;
	private int playerIndex;
	private String content;

	/**
	 * Constructor
	 * 
	 * @post
	 *   <ul>
	 *     <li>this.type = "sendChat"</li>
	 *     <li>this.playerIndex = playerIndex param</li>
	 *     <li>this.content = content param</li>
	 *   <ul>
	 * 
	 * @param playerIndex
	 * @param content
	 */
	public SendChatRequest(int playerIndex, String content) {
		this.type = "sendChat";
		this.playerIndex = playerIndex;
		this.content = content;
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
	public String getContent() {
		return content;
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
	public void setContent(String content) {
		this.content = content;
	}
	
}
