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
		DevCards deck = model.getDeck();
		Bank bank = model.getBank();
		
		if(deck.getYearOfPlenty()>0){
			deck.setYearOfPlenty(deck.getYearOfPlenty()-1);			
		}
		else{
			//throw no more of that resource error
		}
		
		String resource1 = request.getResource1();
		String resource2 = request.getResource2();
		List<String> resources = new ArrayList<String>();
		resources.add(resource1);
		resources.add(resource2);
		for(String resource : resources){	
			switch(resource){
			case "brick":
				if(bank.getBrick()>0){
					bank.setBrick(bank.getBrick()-1);					
				}
				else {
					//throw no more of that resource error
				}
			case "ore":
				if(bank.getOre()>0){
					bank.setOre(bank.getOre()-1);					
				}
				else {
					//throw no more of that resource error
				}			
			case "wood":
				if(bank.getWood()>0){
					bank.setWood(bank.getWood()-1);					
				}
				else {
					//throw no more of that resource error
				}
			case "sheep":
				if(bank.getSheep()>0){
					bank.setSheep(bank.getSheep()-1);					
				}
				else {
					//throw no more of that resource error
				}
			case "wheat":
				if(bank.getWheat()>0){
					bank.setWheat(bank.getWheat()-1);					
				}
				else {
					//throw no more of that resource error
				}
			}
		}
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

	@Override
	public void setParam(Object param) {
		// TODO Auto-generated method stub
		
	}
}
