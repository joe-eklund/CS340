package server.game;

import java.util.ArrayList;

import shared.ServerMethodRequests.AddAIRequest;
import shared.ServerMethodRequests.ResetGameRequest;
import shared.definitions.ServerModel;
import shared.model.Bank;
import shared.model.Chat;
import shared.model.Log;
import shared.model.Map;
import shared.model.Player;
import shared.model.TurnTracker;

public class GameFacadeStub implements IGameFacade {
	private ArrayList<String> AIs=new ArrayList<String>(2){{
		add("Longest_Road");
		add("Largest_Army");
	}};
	private ServerModel game=new ServerModel(new Bank(), new Chat(), new Log(), new Map(),
			new ArrayList<Player>(4), null, new TurnTracker(), 0, -1);

	@Override
	public int resetGame(ResetGameRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addAI(AddAIRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ServerModel getGameModel(int id) {
		// TODO Auto-generated method stub
		return game;
	}

	@Override
	public ArrayList<String> listAI() {
		return AIs;
	}

	@Override
	public int gameCommands() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean validGameID(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}