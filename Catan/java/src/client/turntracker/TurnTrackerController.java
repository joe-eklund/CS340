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
	private boolean initialized;
	
	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		
		presenter = Catan.getPresenter();
		presenter.addObserverToModel(this);
		initialized = false;
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
		
		if(presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex()).getColor()!=null){
			getView().setLocalPlayerColor(CatanColor.valueOf(presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex()).getColor().toUpperCase()));
		}	
		
		List<Player> players = presenter.getClientModel().getServerModel().getPlayers();
		players.removeAll(Collections.singleton(null));
			
		boolean executed = false;
		for(Player p : players){
			if (!initialized) {
				getView().initializePlayer(p.getPlayerIndex(), p.getName(), CatanColor.valueOf(p.getColor().toUpperCase()));
				executed = true;
			}
			
			getView().updatePlayer(p.getPlayerIndex(), p.getVictoryPoints(), isPlayersTurn(p), ifLargestArmy(p), ifLongestRoad(p));									
		}
		
		if (executed)
			initialized = true;
		
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
	
	private boolean isPlayersTurn(Player p){
		if(p.getPlayerIndex() == presenter.getClientModel().getServerModel().getTurnTracker().getCurrentTurn())
		{
			return true;
		}
		else return false;
	}

	private boolean ifLargestArmy(Player p){
		if(p.getPlayerIndex() == presenter.getClientModel().getServerModel().getTurnTracker().getLargestArmy()){
			return true;
		}
		else return false;
	}
	
	private boolean ifLongestRoad(Player p){
		if(p.getPlayerIndex() == presenter.getClientModel().getServerModel().getTurnTracker().getLongestRoad()){
			return true;
		}
		else return false;
	}
}

