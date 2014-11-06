package shared.ServerMethodResponses;

import shared.definitions.ServerModel;

/**
 * A class for encapsulating the server response to the reset game request.  extends GameModelResponse
 *
 */
public class ResetGameResponse extends GameModelResponse {

	/**
	 * @obvious see UserResponse
	 */
	public ResetGameResponse(boolean successful, ServerModel gameModel) {
		super(successful, gameModel);
		// TODO Auto-generated constructor stub
	}

}
