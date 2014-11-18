package server.moves;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import client.exceptions.ClientModelException;
import server.commands.moves.BuildCityCommand;
import server.commands.moves.YearOfPlentyCommand;
import server.cookie.CookieParams;
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
import shared.definitions.DevCardType;
import shared.definitions.GameModel;
import shared.definitions.ServerModel;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.City;
import shared.model.Hex;
import shared.model.Player;
import shared.model.Bank;
import shared.model.City;
import shared.model.DevCards;
import shared.model.Player;
import shared.model.Road;
import shared.model.Settlement;
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
	public ServerModel sendChat(SendChatRequest request,CookieParams cookie) throws InvalidMovesRequest{
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
		return serverGameModel;
	}

	@Override
	public ServerModel rollNumber(RollNumberRequest request,CookieParams cookie) throws InvalidMovesRequest{
		if(request == null) {
			throw new InvalidMovesRequest("Error: invalid send chat request");
		} 
		
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());

		int number=request.getNumber();
		
		List<City> cities = serverGameModel.getMap().getCities();
		List<Settlement> settlements = serverGameModel.getMap().getSettlements();
		List<Hex> hexes = serverGameModel.getMap().getHexes();
		List<Hex> withNumber=new ArrayList<Hex>();
		VertexLocation NE,E,SE,SW,W,NW,loc;
		int owner;
		String resource;
		
		//execute
		for(int i=0;i<hexes.size();i++){
			if(hexes.get(i).getChit()==number)
				withNumber.add(hexes.get(i));
		}
		
		for(int i=0;i<withNumber.size();i++){
			NE=new VertexLocation(withNumber.get(i).getLocation(),VertexDirection.NorthEast).getNormalizedLocation();
			E=new VertexLocation(withNumber.get(i).getLocation(),VertexDirection.East).getNormalizedLocation();
			SE=new VertexLocation(withNumber.get(i).getLocation(),VertexDirection.SouthEast).getNormalizedLocation();
			SW=new VertexLocation(withNumber.get(i).getLocation(),VertexDirection.SouthWest).getNormalizedLocation();
			W=new VertexLocation(withNumber.get(i).getLocation(),VertexDirection.West).getNormalizedLocation();
			NW=new VertexLocation(withNumber.get(i).getLocation(),VertexDirection.NorthWest).getNormalizedLocation();
			resource=withNumber.get(i).getResourceType();
			for(int c=0;c<cities.size();c++){
				loc=cities.get(c).getLocation().getNormalizedLocation();
				owner=cities.get(c).getOwnerIndex();
				if(loc.equals(NE)){
					incrementResources(serverGameModel,owner,resource,2);
				}
				if(loc.equals(E)){
					incrementResources(serverGameModel,owner,resource,2);
				}
				if(loc.equals(SE)){
					incrementResources(serverGameModel,owner,resource,2);
				}
				if(loc.equals(SW)){
					incrementResources(serverGameModel,owner,resource,2);
				}
				if(loc.equals(W)){
					incrementResources(serverGameModel,owner,resource,2);
				}
				if(loc.equals(NW)){
					incrementResources(serverGameModel,owner,resource,2);
				}
			}
			for(int s=0;s<settlements.size();s++){
				loc=settlements.get(s).getLocation().getNormalizedLocation();
				owner=settlements.get(s).getOwnerIndex();
				if(loc.equals(NE)){
					incrementResources(serverGameModel,owner,resource,1);
				}
				if(loc.equals(E)){
					incrementResources(serverGameModel,owner,resource,1);
				}
				if(loc.equals(SE)){
					incrementResources(serverGameModel,owner,resource,1);
				}
				if(loc.equals(SW)){
					incrementResources(serverGameModel,owner,resource,1);
				}
				if(loc.equals(W)){
					incrementResources(serverGameModel,owner,resource,1);
				}
				if(loc.equals(NW)){
					incrementResources(serverGameModel,owner,resource,1);
				}
			}
		}
		
		serverGameModel.getTurnTracker().setStatus("Playing");
		serverGameModel.incrementVersion();
		return serverGameModel;
	}

	@Override
	public ServerModel robPlayer(RobPlayerRequest request, CookieParams cookie) throws InvalidMovesRequest {
		if(request == null) {
			throw new InvalidMovesRequest("Error: invalid rob player request");
		} 
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		Player player=serverGameModel.getPlayers().get(request.getPlayerIndex());
		Player target=serverGameModel.getPlayers().get(request.getVictimIndex());
		String loot=request.getType();
		if(loot.equals("wood")){
			player.setWood(player.getWood()+1);
			target.setWood(target.getWood()-1);
		}else if(loot.equals("wheat")){
			player.setWheat(player.getWheat()+1);
			target.setWheat(target.getWheat()-1);
		}else if(loot.equals("ore")){
			player.setOre(player.getOre()+1);
			target.setOre(target.getOre()-1);
		}else if(loot.equals("brick")){
			player.setBrick(player.getBrick()+1);
			target.setBrick(target.getBrick()-1);
		}else if(loot.equals("sheep")){
			player.setSheep(player.getSheep()+1);
			target.setSheep(target.getSheep()-1);
		}
		return serverGameModel;
	}

	@Override
	public ServerModel finishTurn(FinishTurnRequest request, CookieParams cookie) {
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		serverGameModel.getTurnTracker().nextTurn();
		serverGameModel.incrementVersion();
		serverGameModel.getTurnTracker().setStatus("Rolling");
		return serverGameModel;
	}

	@Override
	public ServerModel buyDevCard(BuyDevCardRequest request, CookieParams cookie) throws InvalidMovesRequest {
		if(request == null) {
			throw new InvalidMovesRequest("Error: invalid buy dev card request");
		} 
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		int owner=request.getPlayerIndex();

		DevCards card=serverGameModel.getDeck();
		Random rand=new Random();
		int c=rand.nextInt(card.getTotalDevCardCount());
		if(c<card.getSoldier()){
			serverGameModel.getPlayers().get(owner).getNewDevCards().setSoldier(serverGameModel.getPlayers().get(owner).getNewDevCards().getSoldier()+1);
		}else if(c<card.getSoldier()+card.getMonument()){
			serverGameModel.getPlayers().get(owner).getNewDevCards().setMonument(serverGameModel.getPlayers().get(owner).getNewDevCards().getMonument()+1);
		}else if(c<card.getSoldier()+card.getMonument()+card.getMonopoly()){
			serverGameModel.getPlayers().get(owner).getNewDevCards().setMonopoly(serverGameModel.getPlayers().get(owner).getNewDevCards().getMonopoly()+1);
		}else if(c<card.getSoldier()+card.getMonument()+card.getMonopoly()+card.getRoadBuilding()){
			serverGameModel.getPlayers().get(owner).getNewDevCards().setRoadBuilding(serverGameModel.getPlayers().get(owner).getNewDevCards().getRoadBuilding()+1);
		}else{
			serverGameModel.getPlayers().get(owner).getNewDevCards().setYearOfPlenty(serverGameModel.getPlayers().get(owner).getNewDevCards().getYearOfPlenty()+1);
		}
		return serverGameModel;
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
		Player player = serverGameModel.getPlayers().get(playerIndex);
		int x = request.getRoadLocation().getX();
		int y = request.getRoadLocation().getY(); 
		String direction = request.getRoadLocation().getDirection().name();
		Road road = new Road(playerIndex, x, y , direction);
		serverGameModel.getMap().getRoads().add(road);
		serverGameModel.incrementVersion();
		player.decrementRoads();
		player.decrementBrick();
		player.decrementWood();
		return serverGameModel;
	}

	@Override
	public ServerModel buildSettlement(BuildSettlementRequest request, CookieParams cookie) throws InvalidMovesRequest, ClientModelException {
		if(request == null) {
			throw new InvalidMovesRequest("Error: invalid build city request");
		} 
		
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		
		//execute
		int playerIndex = request.getPlayerIndex();
		Player player = serverGameModel.getPlayers().get(playerIndex);
		int x = request.getVertexLocation().getX();
		int y = request.getVertexLocation().getY(); 
		String direction = request.getVertexLocation().getDirection().name();
		Settlement settlement = new Settlement(playerIndex, x, y , direction);
		serverGameModel.getMap().getSettlements().add(settlement);
		serverGameModel.incrementVersion();
		player.decrementSettlements();
		player.incrementVictoryPoints();
		player.decrementBrick();
		player.decrementSheep();
		player.decrementWheat();
		player.decrementWood();
		
		return serverGameModel;
	}

	@Override
	public ServerModel buildCity(BuildCityRequest request, CookieParams cookie) throws InvalidMovesRequest, ClientModelException {
		if(request == null) {
			throw new InvalidMovesRequest("Error: invalid build city request");
		} 
		
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		
		//execute
		int playerIndex = request.getPlayerIndex();
		Player player = serverGameModel.getPlayers().get(playerIndex);
		int x = request.getCityLocation().getX();
		int y = request.getCityLocation().getY(); 
		String direction = request.getCityLocation().getDirection().name();
		City city = new City(playerIndex, x, y , direction);
		serverGameModel.getMap().getCities().add(city);
		serverGameModel.incrementVersion();
		serverGameModel.getTurnTracker().nextTurn();
		player.decrementCities();
		player.incrementVictoryPoints();
		player.incrementSettlements();
		//3 ore 2wheat
		int newOre = player.getResources().getOre() - 3;
		int newWheat = player.getResources().getWheat() - 2;
		player.getResources().setOre(newOre);
		player.getResources().setWheat(newWheat);
		
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
		serverGameModel.incrementVersion();
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
		
		int senderBrick = serverGameModel.getPlayers().get(sender).getBrick() - brick;
		int senderOre = serverGameModel.getPlayers().get(sender).getOre() - ore;
		int senderSheep = serverGameModel.getPlayers().get(sender).getSheep() - sheep;
		int senderWheat = serverGameModel.getPlayers().get(sender).getWheat() - wheat;
		int senderWood = serverGameModel.getPlayers().get(sender).getWood() - wood;
		
		serverGameModel.getPlayers().get(sender).setBrick(senderBrick);
		serverGameModel.getPlayers().get(sender).setOre(senderOre);
		serverGameModel.getPlayers().get(sender).setSheep(senderSheep);
		serverGameModel.getPlayers().get(sender).setWheat(senderWheat);
		serverGameModel.getPlayers().get(sender).setWood(senderWood);
		
		int receiverBrick = serverGameModel.getPlayers().get(receiver).getBrick() + brick;
		int receiverOre = serverGameModel.getPlayers().get(receiver).getOre() + ore;
		int receiverSheep = serverGameModel.getPlayers().get(receiver).getSheep() + sheep;
		int receiverWheat = serverGameModel.getPlayers().get(receiver).getWheat() + wheat;
		int receiverWood = serverGameModel.getPlayers().get(receiver).getWood() + wood;
		
		serverGameModel.getPlayers().get(receiver).setBrick(receiverBrick);	
		serverGameModel.getPlayers().get(receiver).setOre(receiverOre);
		serverGameModel.getPlayers().get(receiver).setSheep(receiverSheep);
		serverGameModel.getPlayers().get(receiver).setWheat(receiverWheat);
		serverGameModel.getPlayers().get(receiver).setWood(receiverWood);
		serverGameModel.incrementVersion();
		serverGameModel.setTradeOffer(null);
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
	
	private void incrementResources(ServerModel game,int owner,String resource,int amount){
		if(resource.equals("wood")){
			game.getPlayers().get(owner).setWood(game.getPlayers().get(owner).getWood()+amount);
		}else if(resource.equals("sheep")){
			game.getPlayers().get(owner).setSheep(game.getPlayers().get(owner).getSheep()+amount);
		}else if(resource.equals("ore")){
			game.getPlayers().get(owner).setOre(game.getPlayers().get(owner).getOre()+amount);
		}else if(resource.equals("wheat")){
			game.getPlayers().get(owner).setWheat(game.getPlayers().get(owner).getWheat()+amount);
		}else if(resource.equals("brick")){
			game.getPlayers().get(owner).setBrick(game.getPlayers().get(owner).getBrick()+amount);
		}
	}
}
