package client.discard;

import java.util.Observable;
import java.util.Observer;

import shared.definitions.*;
import client.base.*;
import client.main.Catan;
import client.misc.*;
import client.model.Resources;
import client.presenter.IPresenter;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController {

	private IPresenter presenter;
	private IWaitView waitView;
	
	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView) {
		
		super(view);
		presenter = Catan.getPresenter();
//		presenter.addObserverToModel(this);
		this.waitView = waitView;
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {
		Resources r = presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getID()).getResources();
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		
	}

	@Override
	public void discard() {
		getDiscardView().closeModal();
		
	}
}

