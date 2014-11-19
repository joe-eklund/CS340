package server.commands.moves;

import java.util.ArrayList;
import java.util.List;

import server.commands.ACommand;
import shared.ServerMethodRequests.YearOfPlentyDevRequest;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParam(Object param) {
		// TODO Auto-generated method stub
		
	}
}
