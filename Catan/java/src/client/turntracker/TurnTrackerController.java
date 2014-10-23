package client.turntracker;

import java.util.Observable;

import shared.definitions.CatanColor;
import client.base.Controller;
import client.main.Catan;
import client.presenter.IPresenter;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {
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

