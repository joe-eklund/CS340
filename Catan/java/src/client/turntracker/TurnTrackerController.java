package client.turntracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
import client.base.Controller;
import client.main.Catan;
import client.model.Player;
import client.presenter.IPresenter;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController, Observer {
	private IPresenter presenter;

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		
		presenter = Catan.getPresenter();
		presenter.addObserverToModel(this);
		
		initFromModel();
	}
	
	@Override
	public ITurnTrackerView getView() {
		
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
		presenter.finishTurn();
	}
	
	private void initFromModel() {
		//<temp>
		getView().setLocalPlayerColor(CatanColor.RED);
		//</temp>
	}

	@Override
	public void update(Observable o, Object arg) {
		boolean la=false;
		boolean lr=false;
		
		if(presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex()).getColor()!=null){
			getView().setLocalPlayerColor(CatanColor.valueOf(presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex()).getColor().toUpperCase()));
			
			List<Player> players = presenter.getClientModel().getServerModel().getPlayers();
			players.removeAll(Collections.singleton(null));
			
			for(Player p : players){
				getView().initializePlayer(p.getPlayerIndex(), 
						p.getName(), 
						CatanColor.valueOf(p.getColor().toUpperCase()));
				if(presenter.getClientModel().getServerModel().getTurnTracker().getLongestRoad()>0&&presenter.isPlayersTurn())
					lr=true;
				else
					lr=false;
				if(presenter.getClientModel().getServerModel().getTurnTracker().getLargestArmy()>0&&presenter.isPlayersTurn())
					la=true;
				else
					la=false;
				getView().updatePlayer(presenter.getClientModel().getServerModel().getTurnTracker().getCurrentTurn(), p.getVictoryPoints(), presenter.isPlayersTurn(), la, lr);
			}
		}
		if(presenter.getState().getStatus().equals("Playing") && presenter.isPlayersTurn()) {
			getView().updateGameState("Finish Turn", true);
		}
		else if (presenter.getState().getStatus().equals("Rolling") && presenter.isPlayersTurn()) {
			getView().updateGameState("Finish Turn", false);
		}
		else {
			getView().updateGameState("Waiting for other Players", false);
		}
	}

}

