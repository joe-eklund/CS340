package shared.ServerMethodResponses;

public abstract class NonMoveResponse implements INonMoveResponse {
	boolean successful;

	public NonMoveResponse(boolean successful) {
		this.successful = successful;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

}