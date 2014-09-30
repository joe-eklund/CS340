package client.model;

import shared.definitions.GameModel;

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
	
	public boolean canBuyDevCard() {
		
	}
	
	public boolean canPlayYearOfPlenty() {
		
	}
	
	public boolean canPlayRoadBuilding() {
		
	}
	
	public boolean canPlaySoldier() {
		
	}
	
	public boolean canPlayMonopoly() {
		
	}
	
	public boolean canPlayMonument() {
		
	}
}

