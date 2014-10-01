package shared.ServerMethodResponses;

public abstract class ServerResponse implements IServerResponse {
	boolean successful;

	public ServerResponse(boolean successful) {
		this.successful = successful;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

}
