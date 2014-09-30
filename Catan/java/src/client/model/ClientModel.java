package client.model;

import shared.definitions.GameModel;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

/**
 * 
 * @author Epper Marshall
 *
 * The client side representation of the model.
 * 
 */
public class ClientModel {
	
	private GameModel gameModel;
	
	public ClientModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	
	public void updateGameModel(GameModel newGameModel) {
		this.gameModel = newGameModel;
	}
	
	public GameModel getGameModel() {
		return gameModel;
	}
	
	public boolean canSendChat() {
		
	}
	
	public boolean canAcceptTrade() {
		TradeOffer offer = gameModel.getTradeOffer();
		
		//If there is not an offer don't consider further
		if (offer != null) {
			Player receiver = gameModel.getPlayers().get(gameModel.getTradeOffer().getReceiver());
			
			//For the trade, need to check offer, any positive (I think positive, may be negative) 
			//values are what the receiver needs to have in order to accept the trade 
			if (receiver.getBrick() > offer.getOffer().bricks &&
				receiver.getOre() > offer.getOffer().ores &&
				receiver.getSheep() > offer.getOffer().sheeps &&
				receiver.getWheat() > offer.getOffer().wheats &&
				receiver.getWood() > offer.getOffer().woods) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean canDiscardCards() {
		
	}
	
	public boolean canRollNumber() {
		
	}
	
	public boolean buildRoad() {
		
	}
	
	public boolean canBuildSettlement() {
		
	}
	
	public boolean canBuildCity() {
		
	}
	
	public boolean canOfferTrade() {
		
	}
	
	public boolean canMaritimeTrade() {
		
	}
	
	public boolean canFinishPlaying() {
		
	}
	
	public boolean canBuyDevCard(int playerIndex) {
		Player player = gameModel.getPlayers.get(playerIndex);
		
		if (player.getOre() > 0 && player.getWheat() > 0 && player.getSheep() > 0) {
			
		}
		else {
			return false;
		}
	}
	
	public boolean canPlayYearOfPlenty(int playerIndex, ResourceType firstResource, ResourceType secondResource) {
		//Checks status, players turn, has player already played dev card, if the player has a year of plenty card,
		//and that the bank has the two resources requested.
		if (generalDevCardPreconditions(playerIndex) && 
			gameModel.getPlayers().get(playerIndex).getOldDevCards().getYearOfPlenty() > 0 &&
			gameModel.getBank().hasResource(firstResource) && gameModel.getBank().hasResource(secondResource)) {
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean canPlayRoadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {
		//TODO not all preconditions are checked here
		if (generalDevCardPreconditions(playerIndex) &&
			gameModel.getPlayers().get(playerIndex).getOldDevCards().getRoadBuilding() > 0 &&
			gameModel.getPlayers().get(playerIndex).getRoads() >= 2) {
			
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean canPlaySoldier(int playerIndex, HexLocation hexLoc, int victimIndex) {
		//Checks status, players turn, has player already played dev card, if the player has a soldier card,
		//If the robber is not already in the designated HexLocation, and if the victim has cards to steal.
		if (generalDevCardPreconditions(playerIndex) && 
			gameModel.getPlayers().get(playerIndex).getOldDevCards().soldier > 0 &&
			!gameModel.getMap().getRobber().getCurrentLocation().equals(hexLoc) && 
			gameModel.getPlayers().get(victimIndex).getResources().totalResourcesCount() > 0) {
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean canPlayMonopoly(int playerIndex) {
		//Checks status, players turn, has player already played dev card, and if the player has a monopoly card
		if (generalDevCardPreconditions(playerIndex) && 
			gameModel.getPlayers().get(playerIndex).getOldDevCards().monopoly > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean canPlayMonument(int playerIndex) {
		//Checks status, players turn, has player already played dev card, and if the player has a monument card
		if (generalDevCardPreconditions(playerIndex) && gameModel.getPlayers().get(playerIndex).getOldDevCards().monument > 0) {	
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean generalDevCardPreconditions(int playerIndex) {
		if (gameModel.getTurnTracker().getStatus().equals("Playing") && 
			gameModel.getTurnTracker().getCurrentTurn() == playerIndex &&
			!gameModel.getPlayers().get(playerIndex).hasPlayedDevCard()) {
			return true;
		}
		else {
			return false;
		}
	}
}

