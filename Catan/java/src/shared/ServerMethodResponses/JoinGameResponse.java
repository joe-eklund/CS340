package shared.ServerMethodResponses;

public class JoinGameResponse extends ServerResponse implements IJoinGameResponse{

	private String cookie;

	public JoinGameResponse(boolean successful, String cookie) {
		super(successful);
		this.cookie = cookie;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

}
