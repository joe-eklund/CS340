package proxy;

/**
 *  A request type enum for specifying type of http request ClientCommunicator will be making
 *  <br>
 *  Domain
 *  <ul>
 *    <li>GET = "GET"</li>
 *    <li>POST = "POST"</li>
 *  </ul>
 *
 */
public enum RequestType {
	GET, POST/*, PUT, DELETE*/;
	
	private String value;
	
	static {
		GET.value = new String("GET");
		POST.value = new String("POST");
		/*
		PUT.value = new String("PUT");
		DELETE.value = new String("DELETE");
		*/
	}
	
	public String getValue() {
		return value;
	}
}
