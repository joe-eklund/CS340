package client.maritime;

import java.util.Observable;
import java.util.Observer;

import shared.definitions.*;
import client.base.*;
import client.main.Catan;
import client.presenter.IPresenter;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController, Observer {

	private IMaritimeTradeOverlay tradeOverlay;
	private IPresenter presenter;
	private ResourceType getResource;
	private ResourceType giveResource;
	
	
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay) {
		
		super(tradeView);
		presenter = Catan.getPresenter();
		presenter.addObserverToModel(this);
		setTradeOverlay(tradeOverlay);
	}
	
	public IMaritimeTradeView getTradeView() {
		
		return (IMaritimeTradeView)super.getView();
	}
	
	public IMaritimeTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	@Override
	public void startTrade() {
		getTradeOverlay().showModal();
	}

	@Override
	public void makeTrade() {
		getTradeOverlay().closeModal();
		//presenter.maritimeTrade(ratio, giveResource, getResource);
	}

	@Override
	public void cancelTrade() {
		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
		getResource = resource;
		
	}

	@Override
	public void setGiveResource(ResourceType resource) {
		giveResource = resource;
	}

	@Override
	public void unsetGetValue() {

	}

	@Override
	public void unsetGiveValue() {

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}

}

