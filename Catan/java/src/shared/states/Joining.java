package shared.states;

import java.util.ArrayList;

import shared.ServerMethodResponses.CreateGameResponse;
import shared.ServerMethodResponses.GetGameModelResponse;
import shared.ServerMethodResponses.JoinGameResponse;
import shared.definitions.CatanColor;
import shared.definitions.GameDescription;
import client.presenter.IPresenter;

public class Joining extends State {
	
	@Override
	public CreateGameResponse createGame(IPresenter presenter, boolean randTiles, boolean randNums,
			boolean randPorts, String name) {
		CreateGameResponse response = presenter.getProxy().createGame(randTiles, randNums, randPorts, name, presenter.getCookie());
		ArrayList<GameDescription> games = presenter.getGames();
		if(games == null) {
			games = new ArrayList<GameDescription>();
		}
		games.add(response.getGameDescription());
		presenter.setGames(games);
		return response;
	}
	
	@Override
	public void joinGame(IPresenter presenter, CatanColor color, int gameID) {
		String cookie = presenter.getCookie();
		JoinGameResponse joinResponse = presenter.getProxy().joinGame(color, gameID, cookie);
		presenter.setCookie(joinResponse.getCookie());
		GetGameModelResponse modelResponse = presenter.getProxy().getGameModel(-1, cookie);
		if(modelResponse.isNeedToUpdate()) {
			System.out.println("UPDATING MODEL");
			presenter.updateServerModel(modelResponse.getGameModel());
		}
		else {
			System.err.println("Error: Unable to process update game model request!");
		}
		
		// Can we start playing?
		if(modelResponse.getGameModel().getPlayers().size() == 4) {
			//presenter.setState(new Playing());
		}
	}
}
