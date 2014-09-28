package shared.ServerMethodResponses;

import client.model.Log;

public class GetGameCommandsResponse extends NonMoveResponse {
	private Log commands;

	public GetGameCommandsResponse(boolean successful, Log commands) {
		super(successful);
		this.commands = commands;
	}

	public Log getCommands() {
		return commands;
	}

	public void setCommands(Log commands) {
		this.commands = commands;
	}
}
