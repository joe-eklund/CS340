package shared.states;

import client.presenter.IPresenter;
import shared.ServerMethodResponses.CreateGameResponse;
import shared.ServerMethodResponses.ListGamesResponse;
import shared.ServerMethodResponses.LoginUserResponse;
import shared.ServerMethodResponses.RegisterUserResponse;
import shared.definitions.CatanColor;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public abstract class State implements IState {

	@Override
	public LoginUserResponse login(IPresenter presenter, String user, String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListGamesResponse listGames(IPresenter presenter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegisterUserResponse register(IPresenter presenter, String user, String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateGameResponse createGame(IPresenter presenter, boolean randTiles, boolean randNums,
			boolean randPorts, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void joinGame(IPresenter presenter, CatanColor color, int gameID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] listAIChoices(IPresenter presenter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void buildRoad(IPresenter presenter, EdgeLocation roadLocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildSettlement(IPresenter presenter, VertexLocation vertLoc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildCity(IPresenter presenter, VertexLocation vertLoc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollNumber(IPresenter presenter, int diceRoll) {
		// TODO Auto-generated method stub
		
	}

}
