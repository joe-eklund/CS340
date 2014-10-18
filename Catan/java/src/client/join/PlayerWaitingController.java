package client.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
import shared.definitions.GameState;
import client.base.*;
import client.data.PlayerInfo;
import client.main.Catan;
import client.model.Player;
import client.presenter.IPresenter;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, Observer {
	
	static IPresenter presenter;
	
	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
		presenter = Catan.getPresenter();
		presenter.addObserverToModel(this);
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
		
		getView().setPlayers(getPlayerInfoArray());
		getView().setAIChoices(getAIChoicesArray());
		getView().showModal();
	}

	@Override
	public void addAI() {

		
		getView().closeModal();
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Updating Method called in PlayerWating Controller");
		if (presenter.getGameState().equals(GameState.PLAYERWAITING)) {
			getView().setPlayers(getPlayerInfoArray());
		}
	}
	
	private PlayerInfo[] getPlayerInfoArray() {
		List<Player> players = new ArrayList<Player>(presenter.getClientModel().getServerModel().getPlayers());
		
		players.removeAll(Collections.singleton(null));
		
		PlayerInfo[] playerInfoArray = new PlayerInfo[players.size()];
		for (int i = 0; i < players.size(); i++) {
			PlayerInfo playerInfo = new PlayerInfo();
			Player player = players.get(i);
			if (player != null) {
				playerInfo.setId(player.getPlayerID());
				playerInfo.setName(player.getName());
				playerInfo.setPlayerIndex(player.getPlayerIndex());
				playerInfo.setColor(CatanColor.valueOf(player.getColor().toUpperCase()));
				playerInfoArray[i] = playerInfo;
			}
		}
		
		return playerInfoArray;
	}
	
	private String[] getAIChoicesArray() {
		return presenter.listAIChoices();
	}
}

