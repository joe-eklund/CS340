package client.maritime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import shared.definitions.*;
import shared.model.Player;
import shared.model.Port;
import client.base.*;
import client.main.Catan;
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
			getTradeOverlay().hideGetOptions();
		}
		else{
			getTradeOverlay().setStateMessage("Trade!");
			getTradeOverlay().setTradeEnabled(false);
			getTradeOverlay().hideGetOptions();
			Set<Port> ports = presenter.getClientModel().getServerModel().getRatios(presenter.getPlayerInfo().getIndex());
			//Get port ratios and set local variables
			for (Port p : ports) {
				if(p.getResourceType() == null){
					generalRatio = 3;
				}
				if (p.getResourceType() != null) {
					switch (p.getResourceType()) {
					case "wood":
						woodRatio = p.getRatio();
						break;
					case "brick":
						brickRatio = p.getRatio();
						break;
					case "sheep":
						sheepRatio = p.getRatio();
						break;
					case "wheat":
						wheatRatio = p.getRatio();
						break;
					case "ore":
						oreRatio = p.getRatio();
						break;
					default:
						generalRatio = 3;// No resource type so its a general
						break;
					}
				}
			}
			
			//Grab player and check all resources against current ratios
			Player player = presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex());
			List<ResourceType> tempResourceList = new ArrayList<ResourceType>();
			if (player.getWood() >= woodRatio && (generalRatio == 4 || woodRatio == 2)) {
				tempResourceList.add(ResourceType.WOOD);
			} else if (player.getWood() >= generalRatio) {
				woodRatio = generalRatio;
				tempResourceList.add(ResourceType.WOOD);
			}
			if (player.getBrick() >= brickRatio && (generalRatio == 4 || brickRatio == 2)) {
				tempResourceList.add(ResourceType.BRICK);
			} else if (player.getBrick() >= generalRatio) {
				brickRatio = generalRatio;
				tempResourceList.add(ResourceType.BRICK);
			}
			if (player.getSheep() >= sheepRatio && (generalRatio == 4 || sheepRatio == 2)) {
				tempResourceList.add(ResourceType.SHEEP);
			} else if (player.getSheep() >= generalRatio) {
				sheepRatio = generalRatio;
				tempResourceList.add(ResourceType.SHEEP);
			}
			if (player.getWheat() >= wheatRatio && (generalRatio == 4 || wheatRatio == 2)) {
				tempResourceList.add(ResourceType.WHEAT);
			} else if (player.getWheat() >= generalRatio) {
				wheatRatio = generalRatio;
				tempResourceList.add(ResourceType.WHEAT);
			}
			if (player.getOre() >= oreRatio && (generalRatio == 4 || oreRatio == 2)) {
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
		//Grab resource ratio
		switch(resource){
			case WOOD:	currentRatio = woodRatio;
						break;
			case BRICK:	currentRatio = brickRatio;
						break;
			case SHEEP:	currentRatio = sheepRatio;
						break;
			case WHEAT:	currentRatio = wheatRatio;
						break;
			case ORE:	currentRatio = oreRatio;
						break;
			default:	currentRatio = -1;
						break;
		}
		getTradeOverlay().selectGiveOption(resource, currentRatio);
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