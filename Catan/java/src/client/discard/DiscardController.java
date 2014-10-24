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
public class DiscardController extends Controller implements IDiscardController, Observer {

	private IPresenter presenter;
	private IWaitView waitView;
	private IDiscardView discardView;
	private int brickMax;
	private int brickDiscardAmount;
	private int oreMax;
	private int oreDiscardAmount;
	private int sheepMax;
	private int sheepDiscardAmount;
	private int wheatMax;
	private int wheatDiscardAmount;
	private int woodMax;
	private int woodDiscardAmount;
	private int totalResources;
	private int totalDiscardSelected;
	
	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView) {
		super(view);
		presenter = Catan.getPresenter();
		presenter.addObserverToModel(this);
		this.waitView = waitView;
		this.discardView = view;	
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {
		switch(resource){
			case BRICK:
				discardView.setResourceDiscardAmount(ResourceType.BRICK, ++brickDiscardAmount);
				updateButtons(brickDiscardAmount,brickMax,resource);
				break;
			case ORE:
				discardView.setResourceDiscardAmount(ResourceType.ORE, ++oreDiscardAmount);
				updateButtons(oreDiscardAmount,oreMax,resource);
				break;
			case SHEEP:
				discardView.setResourceDiscardAmount(ResourceType.SHEEP, ++sheepDiscardAmount);
				updateButtons(sheepDiscardAmount,sheepMax,resource);
				break;
			case WHEAT:
				discardView.setResourceDiscardAmount(ResourceType.WHEAT, ++wheatDiscardAmount);
				updateButtons(wheatDiscardAmount,wheatMax,resource);
				break;
			case WOOD:
				discardView.setResourceDiscardAmount(ResourceType.WOOD, ++woodDiscardAmount);
				updateButtons(woodDiscardAmount,woodMax,resource);
				break;
		}

		totalDiscardSelected++;
		discardView.setStateMessage(totalResources-totalDiscardSelected + "/" + totalResources/2);
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		switch(resource){
			case BRICK:
				discardView.setResourceDiscardAmount(ResourceType.BRICK, --brickDiscardAmount);
				updateButtons(brickDiscardAmount,brickMax,resource);
				break;
			case ORE:
				discardView.setResourceDiscardAmount(ResourceType.ORE, --oreDiscardAmount);
				updateButtons(oreDiscardAmount,oreMax,resource);
				break;
			case SHEEP:
				discardView.setResourceDiscardAmount(ResourceType.SHEEP, --sheepDiscardAmount);
				updateButtons(sheepDiscardAmount,sheepMax,resource);
				break;
			case WHEAT:
				discardView.setResourceDiscardAmount(ResourceType.WHEAT, --wheatDiscardAmount);
				updateButtons(wheatDiscardAmount,wheatMax,resource);
				break;
			case WOOD:
				discardView.setResourceDiscardAmount(ResourceType.WOOD, --woodDiscardAmount);
				updateButtons(woodDiscardAmount,woodMax,resource);
				break;
		}
		totalDiscardSelected--;
		discardView.setStateMessage(totalResources-totalDiscardSelected + "/" + totalResources);
	}
	
	@Override
	public void discard() {
		getDiscardView().closeModal();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (presenter.getState().getStatus().equals("Discarding") && totalResources>=1) {
			discardView.showModal();
			Resources r = presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getID()).getResources();
			discardView.setResourceMaxAmount(ResourceType.BRICK, r.brick);
			discardView.setResourceMaxAmount(ResourceType.ORE, r.ore);
			discardView.setResourceMaxAmount(ResourceType.SHEEP, r.sheep);
			discardView.setResourceMaxAmount(ResourceType.WHEAT, r.wheat);
			discardView.setResourceMaxAmount(ResourceType.WOOD, r.wood);	
			this.brickMax = r.brick;
			this.oreMax = r.ore;
			this.sheepMax = r.sheep;
			this.wheatMax = r.wheat;
			this.woodMax = r.wood;
			totalResources = r.brick+r.ore+r.sheep+r.wheat+r.wood;
		}
		else if(presenter.getState().getStatus().equals("Discarding") && totalResources<7){
			waitView.showModal();
		}
	}

	public void updateButtons(int discardAmount, int totalAmount, ResourceType resource){
		if(discardAmount < totalAmount){
			if(discardAmount == 0){
				discardView.setResourceAmountChangeEnabled(resource, true, false);
			}
			else{				
				discardView.setResourceAmountChangeEnabled(resource, true, true);
			}
		}
		else{
			discardView.setResourceAmountChangeEnabled(resource, false, true);
		}
	}
	
	private void updateResourceValues(){
		
	}
}

//		
//		
//		Resources r = presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getID()).getResources();
//		if(r.brick>0){
//			discardView.setResourceMaxAmount(ResourceType.BRICK, r.brick);
//		}
//		if(r.ore>0){
//			discardView.setResourceMaxAmount(ResourceType.ORE, r.ore);
//		}
//		if(r.sheep>0){
//			discardView.setResourceMaxAmount(ResourceType.SHEEP, r.sheep);
//		}
//		if(r.wheat>0){
//			discardView.setResourceMaxAmount(ResourceType.WHEAT, r.wheat);
//		}
//		if(r.wood>0){
//			discardView.setResourceMaxAmount(ResourceType.WOOD, r.wood);
//		}
//		
//		if(thetotalamountofcards-amountofdiscardcard == 4){
//			discardView.setDiscardButtonEnabled(true);
//		}
//		else discardView.setDiscardButtonEnabled(false);