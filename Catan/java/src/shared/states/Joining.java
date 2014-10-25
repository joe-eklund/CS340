package shared.states;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import shared.ServerMethodResponses.CreateGameResponse;
import shared.ServerMethodResponses.GetGameModelResponse;
import shared.ServerMethodResponses.JoinGameResponse;
import shared.ServerMethodResponses.ListGamesResponse;
import shared.definitions.CatanColor;
import shared.definitions.GameDescription;
import client.model.Player;
import client.presenter.IPresenter;

public class Joining extends State {
	public Joining() {
		super("Joining");
	}
	
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
	public ListGamesResponse listGames(IPresenter presenter) {
		ListGamesResponse response =  presenter.getProxy().listGames(presenter.getCookie());
		presenter.setGames((ArrayList<GameDescription>) response.getGameDescriptions());
		return response;
	}
	
	@Override
	public void joinGame(IPresenter presenter, CatanColor color, int gameID) {
		JoinGameResponse joinResponse = presenter.getProxy().joinGame(color, gameID, presenter.getCookie());
		presenter.setPlayerDescriptionsForGame(presenter.getGames().get(gameID).getPlayerDescriptions());
		presenter.setCookie(joinResponse.getCookie());
		GetGameModelResponse modelResponse = presenter.getProxy().getGameModel(-1, presenter.getCookie());
		if(modelResponse.isNeedToUpdate()) {
			// Can we start playing?
			List<Player> players = new ArrayList<Player>(modelResponse.getGameModel().getPlayers());
			players.removeAll(Collections.singleton(null));
//			if(players.size() == 4) {
//				presenter.setStateBasedOffString(modelResponse.getGameModel().getTurnTracker().getStatus());
//			}
//			else {
				presenter.setState(new PlayerWaiting());
//			}
			
			presenter.getPlayerInfo().setIndex(modelResponse.getGameModel().getPlayerIndexByID(presenter.getPlayerInfo().getID()));
			presenter.updateServerModel(modelResponse.getGameModel());
		}
		else {
			System.err.println("Error: Unable to process update game model request!");
		}
	}
	
	@Override
	public void update(IPresenter presenter) {
		listGames(presenter);
	}
}
