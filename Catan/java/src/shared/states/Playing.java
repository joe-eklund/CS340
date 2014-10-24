package shared.states;

import shared.ServerMethodResponses.GetGameModelResponse;
import shared.ServerMethodResponses.MoveResponse;
import shared.definitions.ResourceHand;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import client.presenter.IPresenter;

public class Playing extends GamePlay {
	public Playing() {
		super("Playing");
	}
	@Override 
	public GetGameModelResponse getGameModel(IPresenter presenter) {
		return presenter.getProxy().getGameModel(presenter.getVersion(), presenter.getCookie());
//		return presenter.getProxy().getGameModel(-1, presenter.getCookie());
	}
	
	@Override
	public void buildRoad(IPresenter presenter, EdgeLocation roadLocation) {
		MoveResponse response = presenter.getProxy().buildRoad(presenter.getPlayerInfo().getIndex(), roadLocation, false, presenter.getCookie());
		if(response != null && response.isSuccessful()) {
			presenter.updateServerModel(response.getGameModel());
		}
		else {
			System.err.println("Error building road in playing state");
		}
	}
	
	@Override
	public void buildSettlement(IPresenter presenter, VertexLocation vertLoc) {
		MoveResponse response = presenter.getProxy().buildSettlement(presenter.getPlayerInfo().getIndex(), vertLoc, false, presenter.getCookie());
		if(response != null && response.isSuccessful()) {			
			presenter.updateServerModel(response.getGameModel());
		}
		else {
			System.err.println("Error building settlement in playing state");
		}
	}
	@Override
	public void playMonumentCard(IPresenter presenter) {
		MoveResponse response=presenter.getProxy().playMonumentCard(presenter.getPlayerInfo().getIndex(), presenter.getCookie());
		if(response != null && response.isSuccessful()) {			
			presenter.updateServerModel(response.getGameModel());
		}
		else {
			System.err.println("Error playing monument in playing state");
		}
	}
	@Override
	public void playYearOfPlentyCard(IPresenter presenter,ResourceType resource1, ResourceType resource2) {
			MoveResponse response=presenter.getProxy().playYearOfPlentyCard(presenter.getPlayerInfo().getIndex(), resource1, resource2, presenter.getCookie());
			if(response != null && response.isSuccessful()) {			
				presenter.updateServerModel(response.getGameModel());
			}
			else {
				System.err.println("Error playing year of plenty in playing state");
			}
	}

	@Override
	public void playRoadBuildingCard(IPresenter presenter, EdgeLocation spot1,
			EdgeLocation spot2) {
		MoveResponse response=presenter.getProxy().playRoadBuildingCard(presenter.getPlayerInfo().getIndex(), spot1, spot2, presenter.getCookie());
		if(response != null && response.isSuccessful()) {			
			presenter.updateServerModel(response.getGameModel());
		}
		else {
			System.err.println("Error playing road building in playing state");
		}
	}

	@Override
	public void playMonopolyCard(IPresenter presenter, ResourceType resource) {
		MoveResponse response=presenter.getProxy().playMonopolyCard(presenter.getPlayerInfo().getIndex(), resource, presenter.getCookie());
		if(response != null && response.isSuccessful()) {			
			presenter.updateServerModel(response.getGameModel());
		}
		else {
			System.err.println("Error playing monopoly in playing state");
		}
	}
	@Override
	public void playSoldierCard(IPresenter presenter, int playerIndex, int victimIndex,
			HexLocation location) {
		MoveResponse response=presenter.getProxy().playSoldierCard(playerIndex, victimIndex, location, presenter.getCookie());
		if(response != null && response.isSuccessful()) {			
			presenter.updateServerModel(response.getGameModel());
		}
		else {
			System.err.println("Error playing soldier during play state");
		}
	}
	
	@Override
	public void buildCity(IPresenter presenter, VertexLocation vertLoc) {
		MoveResponse response = presenter.getProxy().buildCity(presenter.getPlayerInfo().getIndex(), vertLoc, presenter.getCookie());
		if(response != null && response.isSuccessful()) {			
			presenter.updateServerModel(response.getGameModel());
		}
		else {
			System.err.println("Error building City in playing state");
		}
	}

	@Override
	public void buyDevCard(IPresenter presenter) {
		presenter.getProxy().buyDevCard(presenter.getPlayerInfo().getIndex(), presenter.getCookie());
	}
	
	@Override
	public void maritimeTrade(IPresenter presenter, int ratio,
			ResourceType inputResource, ResourceType outputResource) {
		MoveResponse response = presenter.getProxy().maritimeTrade(presenter.getPlayerInfo().getIndex(), ratio, inputResource, outputResource, presenter.getCookie());
		if(response != null && response.isSuccessful()) {			
			presenter.updateServerModel(response.getGameModel());
		}
		else {
			System.err.println("Error with maritime trade in playing state");
		}
	}
	
	@Override
	public void finishTurn(IPresenter presenter) {
		MoveResponse response = presenter.getProxy().finishTurn(presenter.getPlayerInfo().getIndex(), presenter.getCookie());
		if(response != null && response.isSuccessful()) {			
			presenter.updateServerModel(response.getGameModel());
			presenter.setVersion(presenter.getVersion()-1);
		}
		else {
			System.err.println("Error with ending turn in playing state");
		}
	}
	
	@Override
	public void offerTrade(IPresenter presenter, ResourceHand offer,
			int receiver) {
		MoveResponse response = presenter.getProxy().offerTrade(presenter.getPlayerInfo().getIndex(), offer, receiver, presenter.getCookie());
		if(response != null && response.isSuccessful()) {			
			presenter.updateServerModel(response.getGameModel());
			presenter.setVersion(presenter.getVersion());
		}
		else {
			System.err.println("Error with offering trade in playing state");
		}
	}
	
	@Override
	public void acceptTrade(IPresenter presenter, boolean willAccept) {
		MoveResponse response = presenter.getProxy().acceptTrade(presenter.getPlayerInfo().getIndex(), willAccept, presenter.getCookie());
		if(response != null && response.isSuccessful()) {			
			presenter.updateServerModel(response.getGameModel());
			presenter.setVersion(presenter.getVersion());
		}
		else {
			System.err.println("Error with accepting trade in playing state");
		}
	}
}
