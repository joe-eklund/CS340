package proxy;

public interface ICommandResponse {
	public String getCookieResponseHeader();
	public int getResponseCode();
	public Object getResponseObjectFromJSON();
	public String getResponseMessage();
}
