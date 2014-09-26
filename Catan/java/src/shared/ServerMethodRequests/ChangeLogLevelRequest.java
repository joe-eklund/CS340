package shared.ServerMethodRequests;

import shared.definitions.ServerLogLevel;

public class ChangeLogLevelRequest {
	private ServerLogLevel serverLogLevel;

	public ChangeLogLevelRequest(ServerLogLevel serverLogLevel) {
		super();
		this.serverLogLevel = serverLogLevel;
	}

	public ServerLogLevel getServerLogLevel() {
		return serverLogLevel;
	}

	public void setServerLogLevel(ServerLogLevel serverLogLevel) {
		this.serverLogLevel = serverLogLevel;
	}
}
