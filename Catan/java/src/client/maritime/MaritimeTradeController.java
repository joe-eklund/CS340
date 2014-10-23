package client.maritime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import shared.definitions.*;
import client.base.*;
import client.main.Catan;
import client.model.Player;
import client.model.Port;
import client.presenter.IPresenter;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController, Observer {

	private IMaritimeTradeOverlay tradeOverlay;
	private IPresenter presenter;
	private int woodRatio;
	private int brickRatio;
	private int sheepRatio;
	private int wheatRatio;
	private int oreRatio;
	private int generalRatio;
	private int currentRatio;
	private ResourceType getResource;
	private ResourceType giveResource;
	private ResourceType[] enabledResources;
	private ResourceType[] allResources = {ResourceType.WOOD, ResourceType.BRICK, ResourceType.SHEEP, 
			ResourceType.WHEAT, ResourceType.ORE};
	
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
		woodRatio = 4;
		brickRatio = 4;
		sheepRatio = 4;
		wheatRatio = 4;
		oreRatio = 4;
		generalRatio = 4;
		currentRatio = -1;
		if(!presenter.isPlayersTurn()){
			getTradeOverlay().setStateMessage("Not your turn.");
			getTradeOverlay().setTradeEnabled(false);
			getTradeOverlay().disableGiveResources();
		}
		else{
			getTradeOverlay().setStateMessage("Trade!");
			getTradeOverlay().setTradeEnabled(false);
			Set<Port> ports = presenter.getClientModel().getServerModel().getRatios(presenter.getPlayerInfo().getIndex());
			//Get port ratios and set local variables
			for(Port p : ports){
				switch(p.getResourceType()){
				case "WOOD":	woodRatio = p.getRatio();
								break;
				case "BRICK":	brickRatio = p.getRatio();
								break;
				case "SHEEP":	sheepRatio = p.getRatio();
								break;
				case "WHEAT":	wheatRatio = p.getRatio();
								break;
				case "ORE":		oreRatio = p.getRatio();
								break;
				default:	generalRatio = 3;//No resource type so its a general port
				}
			}
			//Grab player and check all resources against current ratios
			Player player = presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex());
			player.setBrick(4);
			player.setWood(1);
			player.setSheep(1);
			player.setWheat(1);
			player.setOre(1);
			List<ResourceType> tempResourceList = new ArrayList<ResourceType>();
			if (player.getWood() >= woodRatio) {
				tempResourceList.add(ResourceType.WOOD);
			} else if (player.getWood() >= generalRatio) {
				woodRatio = generalRatio;
				tempResourceList.add(ResourceType.WOOD);
			}
			if (player.getBrick() >= brickRatio) {
				tempResourceList.add(ResourceType.BRICK);
			} else if (player.getBrick() >= generalRatio) {
				brickRatio = generalRatio;
				tempResourceList.add(ResourceType.BRICK);
			}
			if (player.getSheep() >= sheepRatio) {
				tempResourceList.add(ResourceType.SHEEP);
			} else if (player.getSheep() >= generalRatio) {
				sheepRatio = generalRatio;
				tempResourceList.add(ResourceType.SHEEP);
			}
			if (player.getWheat() >= wheatRatio) {
				tempResourceList.add(ResourceType.WHEAT);
			} else if (player.getWheat() >= generalRatio) {
				wheatRatio = generalRatio;
				tempResourceList.add(ResourceType.WHEAT);
			}
			if (player.getOre() >= oreRatio) {
				tempResourceList.add(ResourceType.ORE);
			} else if (player.getOre() >= generalRatio) {
				oreRatio = generalRatio;
				tempResourceList.add(ResourceType.ORE);
			}
			//Build array of resources to enable based on ratios and player's resources
			enabledResources = new ResourceType[tempResourceList.size()];
			enabledResources = tempResourceList.toArray(enabledResources);
			getTradeOverlay().showGiveOptions(enabledResources);
		}
		getTradeOverlay().showModal();
	}

	@Override
	public void makeTrade() {
		getTradeOverlay().closeModal();
		presenter.maritimeTrade(currentRatio, giveResource, getResource);
	}

	@Override
	public void cancelTrade() {
		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
		getResource = resource;
		getTradeOverlay().selectGetOption(resource, 1);
		getTradeOverlay().setTradeEnabled(true);//Now they can trade
	}

	@Override
	public void setGiveResource(ResourceType resource) {
		giveResource = resource;
		int amount;
		//Grab resource ratio
		switch(resource){
			case WOOD:	amount = woodRatio;
						break;
			case BRICK:	amount = brickRatio;
						break;
			case SHEEP:	amount = sheepRatio;
						break;
			case WHEAT:	amount = wheatRatio;
						break;
			case ORE:	amount = oreRatio;
						break;
			default:	amount = -1;
		}
		currentRatio = amount;
		getTradeOverlay().selectGiveOption(resource, amount);
		getTradeOverlay().showGetOptions(allResources);
	}

	@Override
	public void unsetGetValue() {
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().hideGiveOptions();
		getTradeOverlay().showGiveOptions(enabledResources);
		getTradeOverlay().setTradeEnabled(false);
	}

	@Override
	public void unsetGiveValue() {
		getTradeOverlay().hideGiveOptions();
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().showGiveOptions(enabledResources);
		getTradeOverlay().setTradeEnabled(false);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
			
	}
}