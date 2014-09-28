package proxy;

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
