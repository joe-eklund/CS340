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
	private int brickDiscardAmount = 0;
	private int oreMax;
	private int oreDiscardAmount = 0;
	private int sheepMax;
	private int sheepDiscardAmount = 0;
	private int wheatMax;
	private int wheatDiscardAmount = 0;
	private int woodMax;
	private int woodDiscardAmount = 0;
	private int totalResources;
	private int totalDiscardSelected = 0;
	
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
		discardView.setDiscardButtonEnabled(false);
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
		if(totalDiscardSelected == totalResources/2){
			updateResourceValues();
			discardView.setDiscardButtonEnabled(true);
		}
		else{
			discardView.setDiscardButtonEnabled(false);
		}

		discardView.setStateMessage(totalDiscardSelected + "/" + totalResources/2);
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
		discardView.setDiscardButtonEnabled(false);
		updateResourceValues();

		discardView.setStateMessage(totalDiscardSelected + "/" + totalResources/2);
	}
	
	@Override
	public void discard() {
		ResourceHand resourceHand = new ResourceHand(brickDiscardAmount, woodDiscardAmount, sheepDiscardAmount, wheatDiscardAmount, oreDiscardAmount);
		presenter.discardCards(resourceHand);
		getDiscardView().closeModal();
		brickDiscardAmount=0;
		woodDiscardAmount=0;
		sheepDiscardAmount=0;
		wheatDiscardAmount=0;
		oreDiscardAmount=0;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (presenter.getState().getStatus().equals("Discarding")){
			initDiscardValues();
			if(totalResources>=7) {
				discardView.showModal();
				updateResourceValues();
			}
			else if(totalResources<7){
				waitView.showModal();
			}
		}
		if(!presenter.getState().getStatus().equals("Discarding")){
			waitView.closeModal();
		}
	}

	public void updateButtons(int discardAmount, int totalAmount, ResourceType resource){
		if(totalDiscardSelected == totalResources/2){
			if(discardAmount < totalAmount){
				if(discardAmount == 0){
					discardView.setResourceAmountChangeEnabled(resource, false, false);
				}
				else{				
					discardView.setResourceAmountChangeEnabled(resource, false, true);
				}
			}
			else{
				if(discardAmount == 0){
					discardView.setResourceAmountChangeEnabled(resource, false, false);
				}
				else{
					discardView.setResourceAmountChangeEnabled(resource, false, true);
				}
			}
		}
		else{
			if(discardAmount < totalAmount){
				if(discardAmount == 0){
					discardView.setResourceAmountChangeEnabled(resource, true, false);
				}
				else{				
					discardView.setResourceAmountChangeEnabled(resource, true, true);
				}
			}
			else{
				if(discardAmount == 0){
					discardView.setResourceAmountChangeEnabled(resource, false, false);
				}
				else{
					discardView.setResourceAmountChangeEnabled(resource, false, true);
				}
			}
		}
	}
	
	private void updateResourceValues(){
		updateButtons(brickDiscardAmount,brickMax,ResourceType.BRICK);
		updateButtons(oreDiscardAmount,oreMax,ResourceType.ORE);
		updateButtons(sheepDiscardAmount,sheepMax,ResourceType.SHEEP);
		updateButtons(wheatDiscardAmount,wheatMax,ResourceType.WHEAT);
		updateButtons(woodDiscardAmount,woodMax,ResourceType.WOOD);
	}
	
	private void initDiscardValues(){
		Resources r = presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex()).getResources();
		totalResources = r.brick+r.ore+r.sheep+r.wheat+r.wood;
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
	}
}