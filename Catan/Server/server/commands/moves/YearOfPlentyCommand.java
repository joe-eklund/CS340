package server.commands.moves;

import java.util.ArrayList;
import java.util.List;

import server.commands.ACommand;
import server.moves.MovesFacade;
import shared.ServerMethodRequests.YearOfPlentyDevRequest;
import shared.definitions.GameModel;
import shared.definitions.ServerModel;
import shared.model.Bank;
import shared.model.DevCards;

/**
 * The command class in charge of playing the year of plenty development card for a player
 *
 */
public class YearOfPlentyCommand extends ACommand {

	private YearOfPlentyDevRequest request;
	private ServerModel model;
	
	public YearOfPlentyCommand() {
		super("YearOfPlentyCommand");
	}

	@Override
	public void execute() {
		
	}

	public void getRequestItem(YearOfPlentyDevRequest request){
	}

	public void setServerGameModel(ServerModel serverGameModel) {
		this.model = serverGameModel;
	}

	public ServerModel getGameModel() {
		return model;
	}

	public void setRequestItem(YearOfPlentyDevRequest request) {
		this.request = request;	
	}
}
