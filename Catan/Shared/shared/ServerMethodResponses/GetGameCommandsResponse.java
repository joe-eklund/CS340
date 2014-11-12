package shared.ServerMethodResponses;

import shared.model.Log;

/**
 * A class to encapsulate the servers response for GetGameCommands request
 *
 * @domain
 * commands: (Log) a log of commands exectuted in game so far
 */
public class GetGameCommandsResponse extends ServerResponse {
	private Log commands;

	/**
	 * @obvious
	 */
	public GetGameCommandsResponse(boolean successful, Log commands) {
		super(successful);
		this.commands = commands;
	}

	/**
	 * @obvious
	 */
	public Log getCommands() {
		return commands;
	}

	/**
	 * @obvious
	 */
	public void setCommands(Log commands) {
		this.commands = commands;
	}
}
