package client.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
import shared.definitions.GameState;
import shared.definitions.SystemState;
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
		//This all needs to be changed
		//where ever this model is closed then the state changes needs to be changed right after it.
		getView().closeModal();
		presenter.setSystemState(SystemState.GAMING);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Updating Method called in PlayerWating Controller");
		if (presenter.getSystemState().equals(SystemState.PLAYERWAITING)) {
			System.out.println("UPDATING PLAYER WAITING");
			getView().setPlayers(getPlayerInfoArray());
			
//			for(int i = 5; i > 0; i--) {
//				try {
//				    Thread.sleep(1000);                 //1000 milliseconds is one second.
//				} catch(InterruptedException ex) {
//				    Thread.currentThread().interrupt();
//				}
//				
//				System.out.println(i);
//				String labelText = "Game Starting in: " + Integer.toString(i);
//				getView().setLabelText(labelText);
//			}
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

