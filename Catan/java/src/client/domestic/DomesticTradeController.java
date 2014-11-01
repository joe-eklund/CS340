package client.domestic;

import java.util.ArrayList;
import java.util.Observable;

import shared.definitions.PlayerDescription;
import shared.definitions.ResourceHand;
import shared.definitions.ResourceType;
import shared.definitions.ServerModel;
import client.base.Controller;
import client.main.Catan;
import client.misc.IWaitView;
import client.model.Player;
import client.model.Resources;
import client.model.TradeOffer;
import client.presenter.IPresenter;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController {

	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	
	private static IPresenter presenter;
	
	private int availableWheat;
	private int availableOre;
	private int availableSheep;
	private int availableWood;
	private int availableBrick;
	
	private int offeredBrick;
	private int offeredOre;
	private int offeredSheep;
	private int offeredWood;
	private int offeredWheat;
	
	private int desiredBrick;
	private int desiredOre;
	private int desiredSheep;
	private int desiredWood;
	private int desiredWheat;
	
	private int brickState;
	private int oreState;
	private int sheepState;
	private int woodState;
	private int wheatState;
	
	private int receiverIndex;
	
	private boolean send;
	private boolean receive;

	/**
	 * DomesticTradeController constructor
	 * 
	 * @param tradeView Domestic trade view (i.e., view that contains the "Domestic Trade" button)
	 * @param tradeOverlay Domestic trade overlay (i.e., view that lets the user propose a domestic trade)
	 * @param waitOverlay Wait overlay used to notify the user they are waiting for another player to accept a trade
	 * @param acceptOverlay Accept trade overlay which lets the user accept or reject a proposed trade
	 */
	public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
									IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay) {

		super(tradeView);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
		presenter = Catan.getPresenter();
		presenter.addObserverToModel(this);
		
		this.availableBrick = 0;
		this.availableOre = 0;
		this.availableSheep = 0;
		this.availableWood = 0;
		this.availableWheat = 0;
		
		this.offeredBrick = 0;
		this.offeredOre = 0;
		this.offeredSheep = 0;
		this.offeredWood = 0;
		this.offeredWheat = 0;
		
		this.desiredBrick = 0;
		this.desiredOre = 0;
		this.desiredSheep = 0;
		this.desiredWood = 0;
		this.desiredWheat = 0;
		
		this.brickState = 0;
		this.oreState = 0;
		this.sheepState = 0;
		this.woodState = 0;
		this.wheatState = 0;
		
		send = false;
		receive = false;
	}
	
	public IDomesticTradeView getTradeView() {
		
		return (IDomesticTradeView)super.getView();
	}

	public IDomesticTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	public IWaitView getWaitOverlay() {
		return waitOverlay;
	}

	public void setWaitOverlay(IWaitView waitView) {
		this.waitOverlay = waitView;
	}

	public IAcceptTradeOverlay getAcceptOverlay() {
		return acceptOverlay;
	}

	public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
		this.acceptOverlay = acceptOverlay;
	}

	@Override
	public void startTrade() {
		if(presenter.isPlayersTurn()) {
			getTradeOverlay().setStateMessage("set the trade you want to make");
			getTradeOverlay().setResourceSelectionEnabled(true);
			getTradeOverlay().setPlayerSelectionEnabled(false);
			getTradeOverlay().setTradeEnabled(false);
	
			Player player = presenter.getClientModel().getServerModel().getPlayerByID(presenter.getPlayerInfo().getID());
		
			this.receiverIndex = -1;
			
			this.availableBrick = player.getBrick();
			this.availableOre = player.getOre();
			this.availableSheep = player.getSheep();
			this.availableWood = player.getWood();
			this.availableWheat = player.getWheat();
		
			boolean decrease = true;
		
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, this.availableBrick > 0, decrease);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, this.availableOre > 0, decrease);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, this.availableSheep > 0, decrease);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, this.availableWood > 0, decrease);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, this.availableWheat > 0, decrease);
		
			this.offeredBrick = 0;
			this.offeredOre = 0;
			this.offeredSheep = 0;
			this.offeredWood = 0;
			this.offeredWheat = 0;
			
			this.desiredBrick = 0;
			this.desiredOre = 0;
			this.desiredSheep = 0;
			this.desiredWood = 0;
			this.desiredWheat = 0;
			
			this.brickState = 0;
			this.oreState = 0;
			this.sheepState = 0;
			this.woodState = 0;
			this.wheatState = 0;
		
			send = false;
			receive = false;
		
			ArrayList<PlayerDescription> otherPlayersList = new ArrayList<PlayerDescription>();
			//PlayerDescription[] gamePlayers = presenter.getPlayerInfoArray();
			ArrayList<Player> gamePlayers = (ArrayList<Player>) presenter.getClientModel().getServerModel().getPlayers();
			for (Player p : gamePlayers) {
				if (p.getPlayerIndex() != presenter.getPlayerInfo().getIndex()) {
					PlayerDescription temp = new PlayerDescription(p.getColor(), p.getPlayerID(), p.getName());
						temp.setIndex(p.getPlayerIndex());
						otherPlayersList.add(temp);
					}
				}
			PlayerDescription[] otherPlayers = new PlayerDescription[3];
			otherPlayers = otherPlayersList.toArray(otherPlayers);
			getTradeOverlay().setPlayers(otherPlayers);
			getTradeOverlay().showModal();
		}
		else {
			getTradeOverlay().setTradeEnabled(false);
			getTradeOverlay().setStateMessage("not your turn");
			getTradeOverlay().setResourceSelectionEnabled(false);
			getTradeOverlay().setPlayerSelectionEnabled(false);
			getTradeOverlay().showModal();
		}
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		boolean increase = false;
		boolean decrease = false;
		
		switch(resource.name().toLowerCase()) {
		case "brick":
			if(this.offeredBrick > 0 && this.brickState == 1) {
				this.offeredBrick--;
				this.availableBrick++;
			}
			else if(this.desiredBrick > 0 && this.brickState == -1) {
				this.desiredBrick--;
			}
			increase = (this.brickState == 1) ? (this.availableBrick > 0) : (19 - this.availableBrick > this.desiredBrick);
			decrease = (this.brickState == 1) ? (this.offeredBrick > 0) : (this.desiredBrick > 0);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, increase, decrease);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, increase, decrease);
			break;
		case "ore":
			if(this.offeredOre > 0 && this.oreState == 1) {
				this.offeredOre--;
				this.availableOre++;
			}
			else if(this.desiredOre > 0 && this.oreState == -1) {
				this.desiredOre--;
			}
			increase = (this.oreState == 1) ? (this.availableOre > 0) : (19 - this.availableOre > this.desiredOre);
			decrease = (this.oreState == 1) ? (this.offeredOre > 0) : (this.desiredOre > 0);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, increase, decrease);
			break;
		case "wheat":
			if(this.offeredWheat > 0 && this.wheatState == 1) {
				this.offeredWheat--;
				this.availableWheat++;
			}
			else if(this.desiredWheat > 0 && this.wheatState == -1) {
				this.desiredWheat--;
			}
			increase = (this.wheatState == 1) ? (this.availableWheat > 0) : (19 - this.availableWheat > this.desiredWheat);
			decrease = (this.wheatState == 1) ? (this.offeredWheat > 0) : (this.desiredWheat > 0);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, increase, decrease);
			break;
		case "sheep":
			if(this.offeredSheep > 0 && this.sheepState == 1) {
				this.offeredSheep--;
				this.availableSheep++;
			}
			else if(this.desiredSheep > 0 && this.sheepState == -1) {
				this.desiredSheep--;
			}
			increase = (this.sheepState == 1) ? (this.availableSheep > 0) : (19 - this.availableSheep > this.desiredSheep);
			decrease = (this.sheepState == 1) ? (this.offeredSheep > 0) : (this.desiredSheep > 0);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, increase, decrease);
			break;
		case "wood":
			if(this.offeredWood > 0 && this.woodState == 1) {
				this.offeredWood--;
				this.availableWood++;
			}
			else if(this.desiredWood > 0 && this.woodState == -1) {
				this.desiredWood--;
			}
			increase = (this.woodState == 1) ? (this.availableWood > 0) : (19 - this.availableWood > this.desiredWood);
			decrease = (this.woodState == 1) ? (this.offeredWood > 0) : (this.desiredWood > 0);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, increase, decrease);
			break;
			default:
				System.err.println("Error in decrease resource for offer trade");
		}
		checkForValidTradeValues();
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		boolean increase = false;
		boolean decrease = false;
		
		switch(resource.name().toLowerCase()) {
		case "brick":
			if(this.availableBrick > 0 && this.brickState == 1) {
				this.offeredBrick++;
				this.availableBrick--;
			}
			else if(this.desiredBrick < 19 - this.availableBrick && this.brickState == -1) {
				this.desiredBrick++;
			}
			increase = (this.brickState == 1) ? (this.availableBrick > 0) : (19 - this.availableBrick > this.desiredBrick);
			decrease = (this.brickState == 1) ? (this.offeredBrick > 0) : (this.desiredBrick > 0);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, increase, decrease);
			break;
		case "ore":
			if(this.availableOre > 0 && this.oreState == 1) {
				this.offeredOre++;
				this.availableOre--;
			}
			else if(this.desiredOre < 19 - this.availableOre && this.oreState == -1) {
				this.desiredOre++;
			}
			increase = (this.oreState == 1) ? (this.availableOre > 0) : (19 - this.availableOre > this.desiredOre);
			decrease = (this.oreState == 1) ? (this.offeredOre > 0) : (this.desiredOre > 0);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, increase, decrease);
			break;
		case "wheat":
			if(this.availableWheat > 0 && this.wheatState == 1) {
				this.offeredWheat++;
				this.availableWheat--;
			}
			else if(this.desiredWheat < 19 - this.availableWheat && this.wheatState == -1) {
				this.desiredWheat++;
			}
			increase = (this.wheatState == 1) ? (this.availableWheat > 0) : (19 - this.availableWheat > this.desiredWheat);
			decrease = (this.wheatState == 1) ? (this.offeredWheat > 0) : (this.desiredWheat > 0);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, increase, decrease);
			break;
		case "sheep":
			if(this.availableSheep > 0 && this.sheepState == 1) {
				this.offeredSheep++;
				this.availableSheep--;
			}
			else if(this.desiredSheep < 19 - this.availableSheep && this.sheepState == -1) {
				this.desiredSheep++;
			}
			increase = (this.sheepState == 1) ? (this.availableSheep > 0) : (19 - this.availableSheep > this.desiredSheep);
			decrease = (this.sheepState == 1) ? (this.offeredSheep > 0) : (this.desiredSheep > 0);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, increase, decrease);
			break;
		case "wood":
			if(this.availableWood > 0 && this.woodState == 1) {
				this.offeredWood++;
				this.availableWood--;
			}
			else if(this.desiredWood < 19 - this.availableWood && this.woodState == -1) {
				this.desiredWood++;
			}
			increase = (this.woodState == 1) ? (this.availableWood > 0) : (19 - this.availableWood > this.desiredWood);
			decrease = (this.woodState == 1) ? (this.offeredWood > 0) : (this.desiredWood > 0);
			getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, increase, decrease);
			break;
			default:
				System.err.println("Error in increase resource for offer trade");
		}
		checkForValidTradeValues();
	}

	@Override
	public void sendTradeOffer() {
		int brick = (this.offeredBrick == 0) ? -this.desiredBrick : this.offeredBrick;
		int wood = (this.offeredWood == 0) ? -this.desiredWood : this.offeredWood;
		int sheep = (this.offeredSheep == 0) ? -this.desiredSheep : this.offeredSheep;
		int wheat = (this.offeredWheat == 0) ? -this.desiredWheat : this.offeredWheat;
		int ore = (this.offeredOre == 0) ? -this.desiredOre : this.offeredOre;
		ResourceHand offer = new ResourceHand(brick, wood, sheep, wheat, ore);
		if(receiverIndex > -1 && receiverIndex < 4) {
			presenter.offerTrade(offer, receiverIndex);
		}
		getTradeOverlay().closeModal();
		getTradeOverlay().reset();
		getWaitOverlay().showModal();
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
		receiverIndex = playerIndex;
		if(receiverIndex > -1 && checkForValidTradeValues()) {
			getTradeOverlay().setTradeEnabled(true);
			getTradeOverlay().setStateMessage("Trade!");
		}
		else {
			getTradeOverlay().setTradeEnabled(false);
			getTradeOverlay().setStateMessage("select a player");
		}
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		Player player = presenter.getClientModel().getServerModel().getPlayerByID(presenter.getPlayerInfo().getID());
		
		switch(resource.name().toLowerCase()) {
		case "brick":
			if (this.brickState != -1) {
				this.brickState = -1;
				this.offeredBrick = 0;
				this.availableBrick = player.getBrick();
				getTradeOverlay().setResourceAmount(ResourceType.BRICK, "0");
				getTradeOverlay().setResourceAmountChangeEnabled(
						ResourceType.BRICK, 19 - this.availableBrick > 0,
						this.desiredBrick > 0);
			}
			break;
		case "ore":
			if (this.oreState != -1) {
				this.oreState = -1;
				this.offeredOre = 0;
				this.availableOre = player.getOre();
				getTradeOverlay().setResourceAmount(ResourceType.ORE, "0");
				getTradeOverlay().setResourceAmountChangeEnabled(
						ResourceType.ORE, 19 - this.availableOre > 0,
						this.desiredOre > 0);
			}
			break;
		case "wheat":
			if (this.wheatState != -1) {
				this.wheatState = -1;
				this.offeredWheat = 0;
				this.availableWheat = player.getWheat();
				getTradeOverlay().setResourceAmount(ResourceType.WHEAT, "0");
				getTradeOverlay().setResourceAmountChangeEnabled(
						ResourceType.WHEAT, 19 - this.availableWheat > 0,
						this.desiredWheat > 0);
			}
			break;
		case "sheep":
			if (this.sheepState != -1) {
				this.sheepState = -1;
				this.offeredSheep = 0;
				this.availableSheep = player.getSheep();
				getTradeOverlay().setResourceAmount(ResourceType.SHEEP, "0");
				getTradeOverlay().setResourceAmountChangeEnabled(
						ResourceType.SHEEP, 19 - this.availableSheep > 0,
						this.desiredSheep > 0);
			}
			break;
		case "wood":
			if (this.woodState != -1) {
				this.woodState = -1;
				this.offeredWood = 0;
				this.availableWood = player.getWood();
				getTradeOverlay().setResourceAmount(ResourceType.WOOD, "0");
				getTradeOverlay().setResourceAmountChangeEnabled(
						ResourceType.WOOD, 19 - this.availableWood > 0,
						this.desiredWood > 0);
			}
			break;
			default:
				System.err.println("Error in set resource to receive in offer trade");
		}
		checkForValidTradeValues();
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		Player player = presenter.getClientModel().getServerModel().getPlayerByID(presenter.getPlayerInfo().getID());
		switch(resource.name().toLowerCase()) {
		case "brick":
			if (this.brickState != 1) {
				this.brickState = 1;
				this.desiredBrick = 0;
				this.offeredBrick = 0;
				this.availableBrick = player.getBrick();
				getTradeOverlay().setResourceAmount(ResourceType.BRICK, "0");
				getTradeOverlay().setResourceAmountChangeEnabled(
						ResourceType.BRICK, this.availableBrick > 0,
						this.offeredBrick > 0);
			}
			break;
		case "ore":
			if (this.oreState != 1) {
				this.oreState = 1;
				this.desiredOre = 0;
				this.offeredOre = 0;
				this.availableOre = player.getOre();
				getTradeOverlay().setResourceAmount(ResourceType.ORE, "0");
				getTradeOverlay().setResourceAmountChangeEnabled(
						ResourceType.ORE, this.availableOre > 0,
						this.offeredOre > 0);
			}
			break;
		case "wheat":
			if (this.wheatState != 1) {
				this.wheatState = 1;
				this.desiredWheat = 0;
				this.offeredWheat = 0;
				this.availableWheat = player.getWheat();
				getTradeOverlay().setResourceAmount(ResourceType.WHEAT, "0");
				getTradeOverlay().setResourceAmountChangeEnabled(
						ResourceType.WHEAT, this.availableWheat > 0,
						this.offeredWheat > 0);
			}
			break;
		case "sheep":
			if (this.sheepState != 1) {
				this.sheepState = 1;
				this.desiredSheep = 0;
				this.offeredSheep = 0;
				this.availableSheep = player.getSheep();
				getTradeOverlay().setResourceAmount(ResourceType.SHEEP, "0");
				getTradeOverlay().setResourceAmountChangeEnabled(
						ResourceType.SHEEP, this.availableSheep > 0,
						this.offeredSheep > 0);
			}
			break;
		case "wood":
			if (this.woodState != 1) {
				this.woodState = 1;
				this.desiredWood = 0;
				this.offeredWood = 0;
				this.availableWood = player.getWood();
				getTradeOverlay().setResourceAmount(ResourceType.WOOD, "0");
				getTradeOverlay().setResourceAmountChangeEnabled(
						ResourceType.WOOD, this.availableWood > 0,
						this.offeredWood > 0);
			}
			break;
			default:
				System.err.println("Error in set resource to send in offer trade");
		}
		checkForValidTradeValues();
	}

	@Override
	public void unsetResource(ResourceType resource) {
		Player player = presenter.getClientModel().getServerModel().getPlayerByID(presenter.getPlayerInfo().getID());
		switch(resource.name().toLowerCase()) {
		case "brick":
				this.brickState = 0;
				this.desiredBrick = 0;
				this.offeredBrick = 0;
				this.availableBrick = player.getBrick();
				getTradeOverlay().setResourceAmount(ResourceType.BRICK, "0");
			break;
		case "ore":
				this.oreState = 0;
				this.desiredOre = 0;
				this.offeredOre = 0;
				this.availableOre = player.getOre();
				getTradeOverlay().setResourceAmount(ResourceType.ORE, "0");
			break;
		case "wheat":
				this.wheatState = 0;
				this.desiredWheat = 0;
				this.offeredWheat = 0;
				this.availableWheat = player.getWheat();
				getTradeOverlay().setResourceAmount(ResourceType.WHEAT, "0");
			break;
		case "sheep":
				this.sheepState = 0;
				this.desiredSheep = 0;
				this.offeredSheep = 0;
				this.availableSheep = player.getSheep();
				getTradeOverlay().setResourceAmount(ResourceType.SHEEP, "0");
			break;
		case "wood":
				this.woodState = 0;
				this.desiredWood = 0;
				this.offeredWood = 0;
				this.availableWood = player.getWood();
				getTradeOverlay().setResourceAmount(ResourceType.WOOD, "0");
			break;
			default:
				System.err.println("Error in unset resource for offer trade");
		}
		checkForValidTradeValues();
	}

	@Override
	public void cancelTrade() {
		getTradeOverlay().closeModal();
		getTradeOverlay().reset();
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		presenter.acceptTrade(willAccept);
		getAcceptOverlay().closeModal();
	}
	
	private boolean checkForValidTradeValues() {
		send = ((this.offeredBrick + this.offeredOre + this.offeredSheep + this.offeredWheat + this.offeredWood) > 0);
		receive = ((this.desiredBrick + this.desiredOre + this.desiredSheep + this.desiredWheat + this.desiredWood) > 0);
		if(send && receive) {
			getTradeOverlay().setStateMessage("select a player");
			getTradeOverlay().setPlayerSelectionEnabled(true);
			getTradeOverlay().setTradeEnabled(false);
			if(this.receiverIndex > -1) {
				getTradeOverlay().setTradeEnabled(true);
				getTradeOverlay().setStateMessage("Trade!");
			}
		}
		else {
			getTradeOverlay().setStateMessage("set the trade you want to make");
			getTradeOverlay().setPlayerSelectionEnabled(false);
			getTradeOverlay().setTradeEnabled(false);
		}
		
		return (send && receive);
	}

	@Override
	public void update(Observable o, Object arg) {
		ServerModel model = presenter.getClientModel().getServerModel();
		TradeOffer currentOffer = model.getTradeOffer();
		if(currentOffer != null) {
			if(currentOffer.getReceiver() == presenter.getPlayerInfo().getIndex() && !this.getAcceptOverlay().isModalShowing()) {
				Resources resources = currentOffer.getOffer();
				boolean canAccept = true;
				Player receiver = model.getPlayerByID(presenter.getPlayerInfo().getID());
				
				int brick = resources.getBrick();
				if(brick > 0) {
					this.getAcceptOverlay().addGetResource(ResourceType.BRICK, brick);
				}
				else if(brick < 0) {
					this.getAcceptOverlay().addGiveResource(ResourceType.BRICK, brick);
					canAccept = (receiver.getBrick() + brick > -1) && canAccept;
				}
				
				int sheep = resources.getSheep();
				if(sheep > 0) {
					this.getAcceptOverlay().addGetResource(ResourceType.SHEEP, sheep);
				}
				else if(sheep < 0) {
					this.getAcceptOverlay().addGiveResource(ResourceType.SHEEP, sheep);
					canAccept = (receiver.getSheep() + sheep > -1) && canAccept;
				}
				int ore = resources.getOre();
				if(ore > 0) {
					this.getAcceptOverlay().addGetResource(ResourceType.ORE, ore);
				}
				else if(ore < 0) {
					this.getAcceptOverlay().addGiveResource(ResourceType.ORE, ore);
					canAccept = (receiver.getOre() + ore > -1) && canAccept;
				}
				int wheat = resources.getWheat();
				if(wheat > 0) {
					this.getAcceptOverlay().addGetResource(ResourceType.WHEAT, wheat);
				}
				else if(wheat < 0) {
					this.getAcceptOverlay().addGiveResource(ResourceType.WHEAT, wheat);
					canAccept = (receiver.getWheat() + wheat > -1) && canAccept;
				}
				int wood = resources.getWood();
				if(wood > 0) {
					this.getAcceptOverlay().addGetResource(ResourceType.WOOD, wood);
				}
				else if(wood < 0) {
					this.getAcceptOverlay().addGiveResource(ResourceType.WOOD, wood);
					canAccept = (receiver.getWood() + wood > -1) && canAccept;
				}
				String senderName = model.getPlayers().get(currentOffer.getSender()).getName();
				this.getAcceptOverlay().setPlayerName(senderName);
				this.getAcceptOverlay().setAcceptEnabled(canAccept);
				this.getAcceptOverlay().showModal();
			}
		}
		else {
			if(this.getWaitOverlay().isModalShowing()) {
				this.getWaitOverlay().closeModal();
			}
		}
		
	}

}

