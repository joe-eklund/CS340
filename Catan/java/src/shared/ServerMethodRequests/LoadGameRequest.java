package shared.ServerMethodRequests;

public class LoadGameRequest {
	private String name;
	
	public LoadGameRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
