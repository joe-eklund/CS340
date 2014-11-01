package client.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
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
	
	private int numOfWaiting;
	private int oldNumOfWaiting;
	public PlayerWaitingController(IPlayerWaitingView view) {
		
		super(view);
		presenter = Catan.getPresenter();
		presenter.addObserverToModel(this);
		
		numOfWaiting = 0;
		oldNumOfWaiting = 0;
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
		PlayerInfo[] pInfo = getPlayerInfoArray();
		if (pInfo.length < 4) {
			getView().setPlayers(pInfo);
			getView().setAIChoices(getAIChoicesArray());
			getView().showModal();
		}	
	}

	@Override
	public void addAI() {
		presenter.addAI(getView().getSelectedAI());
	}

	@Override
	public void update(Observable o, Object arg) {
		if (presenter.getState().getStatus().equals("PlayerWaiting")) {
			System.out.println("Updating Method called in PlayerWating Controller");
			
			PlayerInfo[] pInfo = getPlayerInfoArray();
			getView().setPlayers(pInfo);

			
			if (numOfWaiting < pInfo.length) {
				//getView().showModal();  //for some reason this freezes the game
				numOfWaiting = pInfo.length;
			}
			
			if(oldNumOfWaiting < numOfWaiting) {
				getView().closeModal();
				getView().showModal();
				oldNumOfWaiting = numOfWaiting;
			}
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

