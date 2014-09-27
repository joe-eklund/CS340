package shared.ServerMethodResponses;

public interface IUserResponse extends INonMoveResponse{
	public String getMessage();
	
	public String getName();
	
	public String getCookie();
	
	public int getUserID();
}
