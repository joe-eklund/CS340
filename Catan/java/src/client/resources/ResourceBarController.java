package client.resources;

import java.util.*;

import shared.definitions.ServerModel;
import client.base.*;
import client.main.Catan;
import client.model.Player;
import client.presenter.IPresenter;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController {

	private Map<ResourceBarElement, IAction> elementActions;
	private IPresenter presenter;
	
	public ResourceBarController(IResourceBarView view) {

		super(view);
		
		elementActions = new HashMap<ResourceBarElement, IAction>();
		presenter = Catan.getPresenter();
		presenter.addObserverToModel(this);
	}

	@Override
	public IResourceBarView getView() {
		return (IResourceBarView)super.getView();
	}

	/**
	 * Sets the action to be executed when the specified resource bar element is clicked by the user
	 * 
	 * @param element The resource bar element with which the action is associated
	 * @param action The action to be executed
	 */
	public void setElementAction(ResourceBarElement element, IAction action) {

		elementActions.put(element, action);
	}

	@Override
	public void buildRoad() {
		executeElementAction(ResourceBarElement.ROAD);
	}

	@Override
	public void buildSettlement() {
		executeElementAction(ResourceBarElement.SETTLEMENT);
	}

	@Override
	public void buildCity() {
		executeElementAction(ResourceBarElement.CITY);
	}

	@Override
	public void buyCard() {
		executeElementAction(ResourceBarElement.BUY_CARD);
	}

	@Override
	public void playCard() {
		executeElementAction(ResourceBarElement.PLAY_CARD);
	}
	
	private void executeElementAction(ResourceBarElement element) {
		
		if (elementActions.containsKey(element)) {
			
			IAction action = elementActions.get(element);
			action.execute();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		int playerIndex = presenter.getPlayerInfo().getIndex();
		ServerModel model = presenter.getClientModel().getServerModel();
		Player player = model.getPlayerByID(playerIndex);
		getView().setElementAmount(ResourceBarElement.BRICK, player.getBrick());
		getView().setElementAmount(ResourceBarElement.ORE, player.getOre());
		getView().setElementAmount(ResourceBarElement.SHEEP, player.getSheep());
		getView().setElementAmount(ResourceBarElement.WHEAT, player.getWheat());
		getView().setElementAmount(ResourceBarElement.WOOD, player.getWood());
		
		getView().setElementAmount(ResourceBarElement.CITY, player.getCities());
		getView().setElementAmount(ResourceBarElement.SETTLEMENT, player.getSettlements());
		getView().setElementAmount(ResourceBarElement.ROAD, player.getRoads());
		
		getView().setElementAmount(ResourceBarElement.SOLDIERS, player.getSoldiers());
	}

}

