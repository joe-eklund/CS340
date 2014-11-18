package server.moves;

import java.util.ArrayList;
import java.util.List;

import client.exceptions.ClientModelException;
import server.commands.moves.BuildCityCommand;
import server.commands.moves.YearOfPlentyCommand;
import server.cookie.CookieParams;
import server.games.InvalidCreateGameRequest;
import shared.ServerMethodRequests.AcceptTradeRequest;
import shared.ServerMethodRequests.BuildCityRequest;
import shared.ServerMethodRequests.BuildRoadRequest;
import shared.ServerMethodRequests.BuildSettlementRequest;
import shared.ServerMethodRequests.BuyDevCardRequest;
import shared.ServerMethodRequests.DiscardCardsRequest;
import shared.ServerMethodRequests.FinishTurnRequest;
import shared.ServerMethodRequests.MaritimeTradeRequest;
import shared.ServerMethodRequests.MonopolyDevRequest;
import shared.ServerMethodRequests.MonumentDevRequest;
import shared.ServerMethodRequests.OfferTradeRequest;
import shared.ServerMethodRequests.RoadBuildingDevRequest;
import shared.ServerMethodRequests.RobPlayerRequest;
import shared.ServerMethodRequests.RollNumberRequest;
import shared.ServerMethodRequests.SendChatRequest;
import shared.ServerMethodRequests.SoldierDevRequest;
import shared.ServerMethodRequests.YearOfPlentyDevRequest;
import shared.definitions.GameModel;
import shared.definitions.ServerModel;
import shared.model.Bank;
import shared.model.City;
import shared.model.DevCards;
import shared.model.Player;
import shared.model.Road;
import shared.model.TradeOffer;

/**
 * This Facade implements the sendChat,
 * rollNumber, robPlayer, finishTurn, buyDevCard, Year_of_Plaenty, Road_Building, Soldier, 
 * Monopoly, Monument, buildRoad, buildSettlement, buildCity, 
 * offerTrade, acceptTrade, maritimeTrade, discardCards commands
 *
 */
public class MovesFacade implements IMovesFacade {

	private YearOfPlentyCommand yearOfPlentyCommand = new YearOfPlentyCommand();
	private ArrayList<ServerModel> serverModels;
	
	public MovesFacade(ArrayList<ServerModel> serverModels){
		this.serverModels = serverModels;
	}
	
	@Override
	public boolean sendChat(SendChatRequest request,CookieParams cookie) throws InvalidMovesRequest{
		if(request == null) {
			throw new InvalidMovesRequest("Error: invalid send chat request");
		} 
		
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		
		//execute
		int playerIndex = request.getPlayerIndex();
		String playerName = serverGameModel.getPlayers().get(playerIndex).getName();
		String message = request.getContent();
		
		serverGameModel.getChat().addMessage(playerName, message);
		//set version?
		return true;
	}

	@Override
	public int rollNumber(RollNumberRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int robPlayer(RobPlayerRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int finishTurn(FinishTurnRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int buyDevCard(BuyDevCardRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int yearOfPlenty(YearOfPlentyDevRequest request, CookieParams cookie) {
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		DevCards deck = serverGameModel.getDeck();
		Bank bank = serverGameModel.getBank();
		
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
		return 0;
	}

	@Override
	public int roadBuilding(RoadBuildingDevRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int soldier(SoldierDevRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int monopoly(MonopolyDevRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int monument(MonumentDevRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ServerModel buildRoad(BuildRoadRequest request, CookieParams cookie) throws InvalidMovesRequest {
		if(request == null) {
			throw new InvalidMovesRequest("Error: invalid build city request");
		} 
		
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		
		//execute
		int playerIndex = request.getPlayerIndex();
		int x = request.getRoadLocation().getX();
		int y = request.getRoadLocation().getY(); 
		String direction = request.getRoadLocation().getDirection().name();
		Road road = new Road(playerIndex, x, y , direction);
		serverGameModel.getMap().getRoads().add(road);
		//set version?
		
		return serverGameModel;
	}

	@Override
	public int buildSettlement(BuildSettlementRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ServerModel buildCity(BuildCityRequest request, CookieParams cookie) throws InvalidMovesRequest, ClientModelException {
		if(request == null) {
			throw new InvalidMovesRequest("Error: invalid build city request");
		} 
		
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		
		//execute
		int playerIndex = request.getPlayerIndex();
		int x = request.getCityLocation().getX();
		int y = request.getCityLocation().getY(); 
		String direction = request.getCityLocation().getDirection().name();
		City city = new City(playerIndex, x, y , direction);
		serverGameModel.getMap().getCities().add(city);
		//set version?
		return serverGameModel;
	}

	@Override
	public ServerModel offerTrade(OfferTradeRequest request, CookieParams cookie) throws InvalidMovesRequest {
		if(request == null) {
			throw new InvalidMovesRequest("Error: invalid offer trade request");
		} 
		
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		
		//execute
		int brick = request.getOffer().getBrick();
		int ore = request.getOffer().getOre();
		int sheep = request.getOffer().getSheep();
		int wheat = request.getOffer().getWheat();
		int wood = request.getOffer().getWood();
		
		TradeOffer tradeOffer = new TradeOffer(request.getPlayerIndex(), request.getReceiver(), brick, ore, sheep, wheat, wood);
		serverGameModel.setTradeOffer(tradeOffer);
		return serverGameModel;
	}

	@Override
	public ServerModel acceptTrade(AcceptTradeRequest request, CookieParams cookie) throws InvalidMovesRequest {
		if(request == null) {
			throw new InvalidMovesRequest("Error: invalid accept trade request");
		} 
		
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		
		//execute
		int sender = serverGameModel.getTradeOffer().getSender();
		int receiver = serverGameModel.getTradeOffer().getReceiver();
		int brick = serverGameModel.getTradeOffer().getOffer().getBrick();
		int ore = serverGameModel.getTradeOffer().getOffer().getOre();
		int sheep = serverGameModel.getTradeOffer().getOffer().getSheep();
		int wheat = serverGameModel.getTradeOffer().getOffer().getWheat();
		int wood = serverGameModel.getTradeOffer().getOffer().getWood();
		
		int senderBrick = serverGameModel.getPlayers().get(sender).getBrick() + brick;
		int senderOre = serverGameModel.getPlayers().get(sender).getOre() + ore;
		int senderSheep = serverGameModel.getPlayers().get(sender).getSheep() + sheep;
		int senderWheat = serverGameModel.getPlayers().get(sender).getWheat() + wheat;
		int senderWood = serverGameModel.getPlayers().get(sender).getWood() + wood;
		
		serverGameModel.getPlayers().get(sender).setBrick(senderBrick);
		serverGameModel.getPlayers().get(sender).setOre(senderOre);
		serverGameModel.getPlayers().get(sender).setSheep(senderSheep);
		serverGameModel.getPlayers().get(sender).setWheat(senderWheat);
		serverGameModel.getPlayers().get(sender).setWood(senderWood);
		
		int receiverBrick = serverGameModel.getPlayers().get(receiver).getBrick() - brick;
		int receiverOre = serverGameModel.getPlayers().get(receiver).getOre() - ore;
		int receiverSheep = serverGameModel.getPlayers().get(receiver).getSheep() - sheep;
		int receiverWheat = serverGameModel.getPlayers().get(receiver).getWheat() - wheat;
		int receiverWood = serverGameModel.getPlayers().get(receiver).getWood() - wood;
		
		serverGameModel.getPlayers().get(receiver).setBrick(receiverBrick);	
		serverGameModel.getPlayers().get(receiver).setOre(receiverOre);
		serverGameModel.getPlayers().get(receiver).setSheep(receiverSheep);
		serverGameModel.getPlayers().get(receiver).setWheat(receiverWheat);
		serverGameModel.getPlayers().get(receiver).setWood(receiverWood);	
		
		return serverGameModel;
	}

	@Override
	public ServerModel maritimeTrade(MaritimeTradeRequest request, CookieParams cookie) throws InvalidMovesRequest {
		if(request == null) {
			throw new InvalidMovesRequest("Error: invalid maritime trade request");
		} 
		
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		
		//execute
		Player player = serverGameModel.getPlayers().get(request.getPlayerIndex());
		int ratio = request.getRatio();
		String inputResource = request.getInputResource().toLowerCase();
		String outputResource = request.getOutputResource().toLowerCase();
		
		switch (inputResource) {
		case "brick":
			int playerBrick = player.getBrick() - ratio;
			player.setBrick(playerBrick);
			break;
		case "ore":
			int playerOre = player.getOre() - ratio;
			player.setOre(playerOre);
			break;
		case "sheep":
			int playerSheep = player.getSheep() - ratio;
			player.setSheep(playerSheep);
			break;
		case "wheat":
			int playerWheat = player.getWheat() - ratio;
			player.setWheat(playerWheat);
			break;
		case "wood":
			int playerWood = player.getWood() - ratio;
			player.setWood(playerWood);
			break;
		}
		
		switch (outputResource) {
		case "brick":
			int playerBrick = player.getBrick() + 1;
			player.setBrick(playerBrick);
			break;
		case "ore":
			int playerOre = player.getOre() + 1;
			player.setOre(playerOre);
			break;
		case "sheep":
			int playerSheep = player.getSheep() + 1;
			player.setSheep(playerSheep);
			break;
		case "wheat":
			int playerWheat = player.getWheat() + 1;
			player.setWheat(playerWheat);
			break;
		case "wood":
			int playerWood = player.getWood() + 1;
			player.setWood(playerWood);
			break;
		}
		
		return serverGameModel;
	}

	@Override
	public ServerModel discardCards(DiscardCardsRequest request, CookieParams cookie) throws InvalidMovesRequest{
		if(request == null) {
			throw new InvalidMovesRequest("Error: invalid discard cards request");
		} 
		
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		Player player = serverGameModel.getPlayers().get(request.getPlayerIndex());
		int playerBrick = player.getBrick() - request.getDiscardedCards().getBrick();
		int playerOre = player.getOre() - request.getDiscardedCards().getOre();
		int playerSheep = player.getSheep() - request.getDiscardedCards().getSheep();
		int playerWheat = player.getWheat() - request.getDiscardedCards().getWheat();
		int playerWood = player.getWood() - request.getDiscardedCards().getWood();
		
		player.setBrick(playerBrick);
		player.setOre(playerOre);
		player.setSheep(playerSheep);
		player.setWheat(playerWheat);
		player.setWood(playerWood);
	
		return serverGameModel;
	}
}
