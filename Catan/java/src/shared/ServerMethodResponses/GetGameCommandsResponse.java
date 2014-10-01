package shared.ServerMethodResponses;

import client.model.Log;

public class GetGameCommandsResponse extends ServerResponse {
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
