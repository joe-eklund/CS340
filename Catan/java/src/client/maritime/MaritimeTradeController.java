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
	ResourceType[] enabledResources = {ResourceType.WOOD, ResourceType.BRICK, ResourceType.SHEEP, ResourceType.WHEAT, ResourceType.ORE};
	
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
		if(!presenter.isPlayersTurn()){
			getTradeOverlay().setStateMessage("Not your turn.");
			getTradeOverlay().setTradeEnabled(false);
		}
		else{
			getTradeOverlay().setStateMessage("Trade!");
			getTradeOverlay().setTradeEnabled(true);
		}
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
		getTradeOverlay().selectGetOption(resource, 1);
	}

	@Override
	public void setGiveResource(ResourceType resource) {
		giveResource = resource;
		getTradeOverlay().selectGiveOption(resource, 1);//temp, 1 should be the smallest ratio for that resource
		//TODO set enabled resources based on map from epper
		getTradeOverlay().showGetOptions(enabledResources);
	}

	@Override
	public void unsetGetValue() {
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().hideGiveOptions();
		getTradeOverlay().showGiveOptions(enabledResources);
		
	}

	@Override
	public void unsetGiveValue() {
		getTradeOverlay().hideGiveOptions();
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().showGiveOptions(enabledResources);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}

}

