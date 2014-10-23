package shared.states;

import shared.ServerMethodResponses.GetGameModelResponse;
import shared.ServerMethodResponses.MoveResponse;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import client.presenter.IPresenter;

public class Playing extends GamePlay {
	public Playing() {
		super("Playing");
	}
	
	@Override 
	public GetGameModelResponse getGameModel(IPresenter presenter) {
		return presenter.getProxy().getGameModel(presenter.getVersion(), presenter.getCookie());
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
		}
		else {
			System.err.println("Error with ending turn in playing state");
		}
	}
}
