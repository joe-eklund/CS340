package client.devcards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.definitions.ServerModel;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import client.base.*;
import client.main.Catan;
import client.model.City;
import client.model.ClientModel;
import client.model.DevCards;
import client.model.Player;
import client.model.Port;
import client.model.Road;
import client.model.Settlement;
import client.model.interfaces.IHex;
import client.presenter.IPresenter;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController {

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
	private static IPresenter presenter;
	
	/**
	 * DevCardController constructor
	 * 
	 * @param view "Play dev card" view
	 * @param buyCardView "Buy dev card" view
	 * @param soldierAction Action to be executed when the user plays a soldier card.  It calls "mapController.playSoldierCard()".
	 * @param roadAction Action to be executed when the user plays a road building card.  It calls "mapController.playRoadBuildingCard()".
	 */
	public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView, 
								IAction soldierAction, IAction roadAction) {

		super(view);

		presenter = Catan.getPresenter();
		presenter.addObserverToModel(this);
		
		this.buyCardView = buyCardView;
		this.soldierAction = soldierAction;
		this.roadAction = roadAction;
	}

	public IPlayDevCardView getPlayCardView() {
		return (IPlayDevCardView)super.getView();
	}

	public IBuyDevCardView getBuyCardView() {
		return buyCardView;
	}

	@Override
	public void startBuyCard() {
		if(presenter.getClientModel().canBuyDevCard(presenter.getPlayerInfo().getIndex())) {
			buyCardView.showModal();
		}
	}

	@Override
	public void cancelBuyCard() {
		
		getBuyCardView().closeModal();
	}

	@Override
	public void buyCard() {
		presenter.buyDevCard();
		getBuyCardView().closeModal();
	}

	@Override
	public void startPlayCard() {
		if(presenter.getClientModel().getServerModel().getPlayerByID(presenter.getPlayerInfo().getIndex()).getOldDevCards().getTotalDevCardCount()>0){
			getPlayCardView().showModal();
		}
	}

	@Override
	public void cancelPlayCard() {

		getPlayCardView().closeModal();
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
		presenter.playMonopolyCard(resource);
	}

	@Override
	public void playMonumentCard() {
		presenter.playMonumentCard();
	}

	@Override
	public void playRoadBuildCard() {
		
		roadAction.execute();
	}

	@Override
	public void playSoldierCard() {
		
		soldierAction.execute();
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		presenter.playYearOfPlentyCard(resource1, resource2);
	}

	@Override
	public void update(Observable o, Object arg) {
		int playerIndex = presenter.getPlayerInfo().getIndex();
		ServerModel model = presenter.getClientModel().getServerModel();
		Player player = model.getPlayerByID(playerIndex);
		DevCards cards = player.getOldDevCards();
		
		getPlayCardView().setCardAmount(DevCardType.MONOPOLY, cards.getMonopoly());
		getPlayCardView().setCardAmount(DevCardType.MONUMENT, cards.getMonument());
		getPlayCardView().setCardAmount(DevCardType.ROAD_BUILD, cards.getRoadBuilding());
		getPlayCardView().setCardAmount(DevCardType.SOLDIER, cards.getSoldier());
		getPlayCardView().setCardAmount(DevCardType.YEAR_OF_PLENTY, cards.getYearOfPlenty());
	}

}

