package server.commands.moves;

import server.commands.ACommand;
import server.moves.MovesFacade;
import shared.ServerMethodRequests.YearOfPlentyDevRequest;
import shared.definitions.GameModel;

/**
 * The command class in charge of playing the year of plenty development card for a player
 *
 */
public class YearOfPlentyCommand extends ACommand {

	private YearOfPlentyDevRequest request;
	private GameModel model;
	public YearOfPlentyCommand() {
		super("YearOfPlentyCommand");
	}

	@Override
	public void execute() {
		if(model.getServerModel().getDeck().getYearOfPlenty()>0) {
			model.getServerModel().getDeck().setYearOfPlenty(model.getServerModel().getDeck().getYearOfPlenty()-1);	
		}
		else {
			
		}
	}

	public void getRequestItem(YearOfPlentyDevRequest request){
		this.request = request;
	}

	public void setGameModel(GameModel gameModel) {
		this.model = gameModel;
	}
}
