package shared.states;

import client.presenter.IPresenter;
import shared.ServerMethodResponses.CreateGameResponse;
import shared.ServerMethodResponses.ListGamesResponse;
import shared.ServerMethodResponses.LoginUserResponse;
import shared.ServerMethodResponses.RegisterUserResponse;
import shared.definitions.CatanColor;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public interface IState {
public LoginUserResponse login(IPresenter presenter, String user,String pass);
	
	public ListGamesResponse listGames(IPresenter presenter);
	
	public RegisterUserResponse register(IPresenter presenter, String user,String pass);

	public CreateGameResponse createGame(IPresenter presenter, boolean randTiles,boolean randNums,boolean randPorts,String name);
	
	public void joinGame(IPresenter presenter, CatanColor color, int gameID);
	
	public String[] listAIChoices(IPresenter presenter);
	
	public void buildRoad(IPresenter presenter, EdgeLocation roadLocation);

	public void buildSettlement(IPresenter presenter, VertexLocation vertLoc);

	public void buildCity(IPresenter presenter, VertexLocation vertLoc);

	public void rollNumber(IPresenter presenter, int diceRoll);
}
