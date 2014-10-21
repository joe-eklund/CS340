package client.communication;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.main.Catan;
import client.presenter.IPresenter;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController, Observer {

	private IPresenter presenter;
	
	public ChatController(IChatView view) {
		
		super(view);
		presenter = Catan.getPresenter();
		presenter.addObserverToModel(this);
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		//TODO based on state
		presenter.sendChat(message);
	}

	@Override
	public void update(Observable o, Object arg) {
		getView().setEntries(presenter.getClientModel().getServerModel().getChat().getMessages());
	}

}

