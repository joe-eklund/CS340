package shared.ServerMethodRequests;


public class ChangeLogLevelRequest {
	private String logLevel;

	public ChangeLogLevelRequest(String logLevel) {
		logLevel = logLevel.toLowerCase();
	}

	public String getlogLevel() {
		return logLevel;
	}

	public void setlogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
}
