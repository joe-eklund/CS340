package shared.states;

import java.util.Collections;
import java.util.List;

import shared.ServerMethodResponses.AddAIResponse;
import shared.ServerMethodResponses.GetGameModelResponse;
import client.model.Player;
import client.presenter.IPresenter;

public class PlayerWaiting extends State {
	public PlayerWaiting() {
		super("PlayerWaiting");
	}
	
	@Override 
	public GetGameModelResponse getGameModel(IPresenter presenter) {
		GetGameModelResponse response = presenter.getProxy().getGameModel(-1, presenter.getCookie());
		
		return response;
	}
	
	@Override
	public void addAI(IPresenter presenter, String aiToAdd) {
//		AddAIResponse response = presenter.getProxy().addAI("LARGEST_ARMY", presenter.getCookie());
		AddAIResponse response = presenter.getProxy().addAI(aiToAdd, presenter.getCookie());
		
	}
}
