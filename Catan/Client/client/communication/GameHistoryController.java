package client.communication;

import java.util.*;

import client.base.*;
import client.main.Catan;
import client.presenter.IPresenter;
import shared.definitions.*;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController, Observer{

	private IPresenter presenter;	
	
	public GameHistoryController(IGameHistoryView view) {
		
		super(view);
		presenter = Catan.getPresenter();
		presenter.addObserverToModel(this);
	}
	
	@Override
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}

	@Override
	public void update(Observable o, Object arg) {
		getView().setEntries(presenter.getClientModel().getGameLog());
	}
	
}

