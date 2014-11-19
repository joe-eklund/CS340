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
import shared.definitions.RoadLocation;
import shared.definitions.ServerModel;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.City;
import shared.model.Hex;
import shared.model.Map;
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
	private int player1TotResources;
	private int player2TotResources;
	private int player3TotResources;
	private int player4TotResources;
	
	
	public MovesFacade(ArrayList<ServerModel> serverModels){
		this.serverModels = serverModels;
		player1TotResources = 1000;
		player2TotResources = 1000;
		player3TotResources = 1000;
		player4TotResources = 1000;
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
		serverGameModel.incrementVersion();
		return serverGameModel;
	}

	@Override
	public ServerModel rollNumber(RollNumberRequest request,CookieParams cookie) throws InvalidMovesRequest{
		if(request == null) {
			throw new InvalidMovesRequest("Error: invalid send chat request");
		} 
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());

		int number=request.getNumber();
		
		if(number==7){
			player1TotResources = serverGameModel.getPlayers().get(0).getResourceCount();
			player2TotResources = serverGameModel.getPlayers().get(1).getResourceCount();
			player3TotResources = serverGameModel.getPlayers().get(2).getResourceCount();
			player4TotResources = serverGameModel.getPlayers().get(3).getResourceCount();
			
			if (player1TotResources <= 7) {
				player1TotResources = 1000;
				serverGameModel.getPlayers().get(0).setDiscarded(true);
			}
			
			if (player2TotResources <= 7) {
				player1TotResources = 1000;
				serverGameModel.getPlayers().get(1).setDiscarded(true);
			}
			
			if (player3TotResources <= 7) {
				player3TotResources = 1000;
				serverGameModel.getPlayers().get(2).setDiscarded(true);
			}
			
			if (player4TotResources <= 7) {
				player4TotResources = 1000;
				serverGameModel.getPlayers().get(3).setDiscarded(true);
			}
			
			serverGameModel.getTurnTracker().setStatus("Discarding");
			serverGameModel.incrementVersion();
			return serverGameModel;
		}
		
		List<City> cities = serverGameModel.getMap().getCities();
		List<Settlement> settlements = serverGameModel.getMap().getSettlements();
		List<Hex> hexes = serverGameModel.getMap().getHexes();
		List<Hex> withNumber=new ArrayList<Hex>();
		VertexLocation NE,E,SE,SW,W,NW,loc;
		int owner;
		String resource;
		
		//execute
		for(int i=0;i<hexes.size();i++){
			if(hexes.get(i).getChit()==number && !serverGameModel.getMap().getRobber().getLocation().equals(hexes.get(i)))
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
		
		ArrayList<String> potentialLoot = new ArrayList<String>();
		
		if (target.getResourceCount() > 0) {
			if (target.getResources().brick > 0)
				potentialLoot.add("brick");
			if (target.getResources().ore > 0)
				potentialLoot.add("ore");
			if (target.getResources().sheep > 0)
				potentialLoot.add("sheep");
			if (target.getResources().wheat > 0)
				potentialLoot.add("wheat");
			if (target.getResources().wood > 0)
				potentialLoot.add("wood");
			
			Random randomGenerator = new Random();
			int lootIndex = randomGenerator.nextInt(potentialLoot.size());
			
			String loot=potentialLoot.get(lootIndex);
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
		}

		serverGameModel.incrementVersion();
		serverGameModel.getTurnTracker().setStatus("Playing");
		
		return serverGameModel;
	}

	@Override
	public ServerModel finishTurn(FinishTurnRequest request, CookieParams cookie) {
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		int owner=request.getPlayerIndex();
		Player player = serverGameModel.getPlayers().get(owner);
		
		DevCards newCards=player.getNewDevCards();
		DevCards oldCards=player.getOldDevCards();
		oldCards.setMonopoly(oldCards.getMonopoly()+newCards.getMonopoly());
		oldCards.setMonument(oldCards.getMonument()+newCards.getMonument());
		oldCards.setRoadBuilding(oldCards.getRoadBuilding()+newCards.getRoadBuilding());
		oldCards.setSoldier(oldCards.getSoldier()+newCards.getSoldier());
		oldCards.setYearOfPlenty(oldCards.getYearOfPlenty()+newCards.getYearOfPlenty());
		newCards=new DevCards();
		player.setNewDevCards(newCards);
		player.setOldDevCards(oldCards);
		
		serverGameModel.getTurnTracker().nextTurn();
		serverGameModel.incrementVersion();
		
		if ((serverGameModel.getTurnTracker().getStatus().equals("SecondRound") && request.getPlayerIndex() == 0) || 
				!serverGameModel.getTurnTracker().getStatus().equals("FirstRound") && !serverGameModel.getTurnTracker().getStatus().equals("SecondRound")) {
			serverGameModel.getTurnTracker().setStatus("Rolling");
		}
	
		return serverGameModel;
	}

	@Override
	public ServerModel buyDevCard(BuyDevCardRequest request, CookieParams cookie) throws InvalidMovesRequest {
		if(request == null) {
			throw new InvalidMovesRequest("Error: invalid buy dev card request");
		} 
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		int owner=request.getPlayerIndex();
		Player player = serverGameModel.getPlayers().get(owner);

		DevCards card=serverGameModel.getDeck();
		Random rand=new Random();
		int c=rand.nextInt(card.getTotalDevCardCount());
		if(c<card.getSoldier()){
			player.getNewDevCards().setSoldier(player.getNewDevCards().getSoldier()+1);
		}else if(c<card.getSoldier()+card.getMonument()){
			player.getNewDevCards().setMonument(player.getNewDevCards().getMonument()+1);
		}else if(c<card.getSoldier()+card.getMonument()+card.getMonopoly()){
			player.getNewDevCards().setMonopoly(player.getNewDevCards().getMonopoly()+1);
		}else if(c<card.getSoldier()+card.getMonument()+card.getMonopoly()+card.getRoadBuilding()){
			player.getNewDevCards().setRoadBuilding(player.getNewDevCards().getRoadBuilding()+1);
		}else{
			player.getNewDevCards().setYearOfPlenty(player.getNewDevCards().getYearOfPlenty()+1);
		}
		serverGameModel.incrementVersion();
		return serverGameModel;
	}

	@Override
	public ServerModel yearOfPlenty(YearOfPlentyDevRequest request, CookieParams cookie) {
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		Bank bank = serverGameModel.getBank();
		int owner = request.getPlayerIndex();
		Player player=serverGameModel.getPlayers().get(owner);
		
		DevCards cards = player.getOldDevCards();
		cards.setYearOfPlenty(cards.getYearOfPlenty()-1);

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
					player.setBrick(player.getBrick()+1);
				}
				else {
					//throw no more of that resource error
				}
			case "ore":
				if(bank.getOre()>0){
					bank.setOre(bank.getOre()-1);	
					player.setOre(player.getOre()+1);				
				}
				else {
					//throw no more of that resource error
				}			
			case "wood":
				if(bank.getWood()>0){
					bank.setWood(bank.getWood()-1);
					player.setWood(player.getWood()+1);					
				}
				else {
					//throw no more of that resource error
				}
			case "sheep":
				if(bank.getSheep()>0){
					bank.setSheep(bank.getSheep()-1);	
					player.setSheep(player.getSheep()+1);				
				}
				else {
					//throw no more of that resource error
				}
			case "wheat":
				if(bank.getWheat()>0){
					bank.setWheat(bank.getWheat()-1);		
					player.setWheat(player.getWheat()+1);			
				}
				else {
					//throw no more of that resource error
				}
			}
		}
		serverGameModel.getPlayers().get(owner).setOldDevCards(cards);
		serverGameModel.setBank(bank);
		serverGameModel.incrementVersion();
		return serverGameModel;
	}

	@Override
	public ServerModel roadBuilding(RoadBuildingDevRequest request, CookieParams cookie) {
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		int owner = request.getPlayerIndex();
		Player player=serverGameModel.getPlayers().get(owner);
		
		RoadLocation spot1 = request.getSpot1();
		RoadLocation spot2 = request.getSpot2();
		
		Map map = serverGameModel.getMap();
		map.getRoads().add(new Road(owner,spot1.getX(),spot1.getY(),spot1.getDirection().toString()));
		map.getRoads().add(new Road(owner,spot2.getX(),spot2.getY(),spot2.getDirection().toString()));
		
		serverGameModel.getPlayers().get(owner).setRoads(player.getRoads()-2);
		
		DevCards cards = player.getOldDevCards();
		cards.setRoadBuilding(cards.getRoadBuilding()-1);
		serverGameModel.getPlayers().get(owner).setOldDevCards(cards);
		
		checkForLongestRoad(serverGameModel);
		
		serverGameModel.setMap(map);
		serverGameModel.incrementVersion();
		return serverGameModel;
	}

	@Override
	public ServerModel soldier(SoldierDevRequest request, CookieParams cookie) {
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		int owner = request.getPlayerIndex();
		Player player=serverGameModel.getPlayers().get(owner);
		
		DevCards cards = player.getOldDevCards();
		cards.setSoldier(cards.getSoldier()-1);
		player.setOldDevCards(cards);
		player.setSoldiers(player.getSoldiers()+1);

		checkForLargestArmy(serverGameModel);
		
		serverGameModel.getTurnTracker().setStatus("Robbing");
		serverGameModel.incrementVersion();
		return serverGameModel;
	}

	@Override
	public ServerModel monopoly(MonopolyDevRequest request, CookieParams cookie) {
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		int owner = request.getPlayerIndex();
		Player player=serverGameModel.getPlayers().get(owner);
		Player other;
		
		String resource = request.getResource();
		int amount=0;
		for(int i=0;i<serverGameModel.getPlayers().size();i++){
			if(i!=owner){
				other=serverGameModel.getPlayers().get(i);
				switch(resource){
				case "ore":
					if(other.getOre()>0){
						amount=other.getOre();
						player.setOre(player.getOre()+amount);
						other.setOre(0);
					}
					break;
				case "sheep":
					if(other.getSheep()>0){
						amount=other.getSheep();
						player.setSheep(player.getSheep()+amount);
						other.setSheep(0);
					}
					break;
				case "wood":
					if(other.getWood()>0){
						amount=other.getWood();
						player.setWood(player.getWood()+amount);
						other.setWood(0);
					}
					break;
				case "wheat":
					if(other.getWheat()>0){
						amount=other.getWheat();
						player.setWheat(player.getWheat()+amount);
						other.setWheat(0);
					}
					break;
				case "brick":
					if(other.getBrick()>0){
						amount=other.getBrick();
						player.setBrick(player.getBrick()+amount);
						other.setBrick(0);
					}
					break;
				}
			}
		}
		
		DevCards cards = player.getOldDevCards();
		cards.setMonopoly(cards.getMonopoly()-1);
		serverGameModel.getPlayers().get(owner).setOldDevCards(cards);
		
		serverGameModel.incrementVersion();
		return serverGameModel;
	}

	@Override
	public ServerModel monument(MonumentDevRequest request, CookieParams cookie) {
		ServerModel serverGameModel = serverModels.get(cookie.getGameID());
		int owner = request.getPlayerIndex();
		Player player=serverGameModel.getPlayers().get(owner);
		
		player.setVictoryPoints(player.getVictoryPoints()+1);
		
		DevCards cards = player.getOldDevCards();
		cards.setMonument(cards.getMonument()-1);
		player.setOldDevCards(cards);

		checkForWinner(serverGameModel,owner);
		
		serverGameModel.incrementVersion();
		return serverGameModel;
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
		String direction = request.getRoadLocation().getDirectionStr();
		Road road = new Road(playerIndex, x, y , direction);
		serverGameModel.getMap().getRoads().add(road);
		serverGameModel.incrementVersion();
		
		player.decrementRoads();
		
		if (!request.isFree()) {
			player.decrementBrick();
			player.decrementWood();
		}

		checkForLongestRoad(serverGameModel);
		
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
		String direction = request.getVertexLocation().getDirectionStr();
		Settlement settlement = new Settlement(playerIndex, x, y , direction);
		serverGameModel.getMap().getSettlements().add(settlement);
		serverGameModel.incrementVersion();
		player.decrementSettlements();
		player.incrementVictoryPoints();
		player.decrementBrick();
		player.decrementSheep();
		player.decrementWheat();
		player.decrementWood();
		
		if (serverGameModel.getTurnTracker().getStatus().equals("SecondRound")) {
			
			HexLocation hexLoc = new HexLocation(x, y);
			HexLocation neighbor1 = null;
			HexLocation neighbor2 = null;
			
			switch(direction) {
			case "NW":
				neighbor1 = hexLoc.getNeighborLoc(EdgeDirection.NorthWest);
				neighbor2 = hexLoc.getNeighborLoc(EdgeDirection.North);
				break;
			case "NE":
				neighbor1 = hexLoc.getNeighborLoc(EdgeDirection.NorthEast);
				neighbor2 = hexLoc.getNeighborLoc(EdgeDirection.North);
				break;
			case "E":
				neighbor1 = hexLoc.getNeighborLoc(EdgeDirection.NorthEast);
				neighbor2 = hexLoc.getNeighborLoc(EdgeDirection.SouthEast);
				break;
			case "SE":
				neighbor1 = hexLoc.getNeighborLoc(EdgeDirection.SouthEast);
				neighbor2 = hexLoc.getNeighborLoc(EdgeDirection.South);
				break;
			case "SW":
				neighbor1 = hexLoc.getNeighborLoc(EdgeDirection.SouthWest);
				neighbor2 = hexLoc.getNeighborLoc(EdgeDirection.South);
				break;
			case "W":
				neighbor1 = hexLoc.getNeighborLoc(EdgeDirection.NorthWest);
				neighbor2 = hexLoc.getNeighborLoc(EdgeDirection.SouthWest);
				break;
			}
			
			for (Hex hex : serverGameModel.getMap().getHexes()) {
				int hexX = hex.getLocation().getX();
				int hexY = hex.getLocation().getY();
				
				if ((hexX == hexLoc.getX() && hexY == hexLoc.getY()) ||
						(hexX == neighbor1.getX() && hexY == neighbor1.getY()) ||
						(hexX == neighbor2.getX() && hexY == neighbor2.getY())) {
					
					String resource = hex.getResourceType();
					
					switch (resource) {
					case "brick":
						player.setBrick(1);
						break;
					case "ore":
						player.setOre(1);
						break;
					case "sheep":
						player.setSheep(1);
						break;
					case "wheat":
						player.setWheat(1);
						break;
					case "wood":
						player.setWood(1);
						break;
					}
				}
			}
			
		}
		
		checkForWinner(serverGameModel,playerIndex);
		
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
		String direction = request.getCityLocation().getDirectionStr();
		City city = new City(playerIndex, x, y , direction);
		serverGameModel.getMap().getCities().add(city);
		serverGameModel.incrementVersion();
		
		ArrayList<Settlement> settlements = serverGameModel.getMap().getSettlements();
		
		for (int i = 0; i < settlements.size(); i++) {
			VertexLocation loc = settlements.get(i).getLocation().getNormalizedLocation();
			if (settlements.get(i).getLocation().getHexLoc().getX() == x &&
					settlements.get(i).getLocation().getHexLoc().getY() == y &&
					settlements.get(i).getLocation().getDir().getDirectionStr().equals(direction)) {
				settlements.remove(i);
				break;
			}
			
			if (loc.getHexLoc().getX() == x &&
					loc.getHexLoc().getY() == y &&
					loc.getDir().getDirectionStr().equals(direction)) {
				settlements.remove(i);
				break;
			}
				
		}
		
		player.decrementCities();
		player.incrementVictoryPoints();
		player.incrementSettlements();
		//3 ore 2wheat
		int newOre = player.getResources().getOre() - 3;
		int newWheat = player.getResources().getWheat() - 2;
		player.getResources().setOre(newOre);
		player.getResources().setWheat(newWheat);
		
		checkForWinner(serverGameModel,playerIndex);
		
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
		
		int p1Resources = serverGameModel.getPlayers().get(0).getResourceCount();
		int p2Resources = serverGameModel.getPlayers().get(1).getResourceCount();
		int p3Resources = serverGameModel.getPlayers().get(2).getResourceCount();
		int p4Resources = serverGameModel.getPlayers().get(3).getResourceCount();
		
		System.out.println(Math.ceil(player2TotResources/2.0));
		boolean a = (p1Resources <= (int) Math.ceil(player1TotResources/2.0));
		boolean b = (p2Resources <= (int) Math.ceil(player2TotResources/2.0));
		boolean c = (p3Resources <= (int) Math.ceil(player3TotResources/2.0));
		boolean d = (p4Resources <= (int) Math.ceil(player4TotResources/2.0));
		
		if (!serverGameModel.getPlayers().get(player.getPlayerIndex()).isDiscarded()) {
			serverGameModel.getPlayers().get(player.getPlayerIndex()).setDiscarded(true);
		}
		
		if (a && b && c && d) {
			serverGameModel.getTurnTracker().setStatus("Robbing");
			serverGameModel.getPlayers().get(0).setDiscarded(false);
			serverGameModel.getPlayers().get(1).setDiscarded(false);
			serverGameModel.getPlayers().get(2).setDiscarded(false);
			serverGameModel.getPlayers().get(3).setDiscarded(false);
		}
		
		serverGameModel.incrementVersion();
		
		return serverGameModel;
	}
	
	private void checkForLongestRoad(ServerModel game){
		int longest=4;
		int playerWith=game.getTurnTracker().getLongestRoad();
		int num;
		for(int i=0;i<game.getPlayers().size();i++){
			num = 19-game.getPlayers().get(i).getRoads();
			
			if(playerWith >= 0 && playerWith < game.getPlayers().size() && 19-game.getPlayers().get(i).getRoads()>longest &&
					game.getPlayers().get(i).getRoads()<game.getPlayers().get(playerWith).getRoads()){
				longest=num;
				playerWith=i;
			}
			
		}
		if(playerWith!=-1)
			game.getTurnTracker().setLongestRoad(playerWith);
	}
	
	private void checkForLargestArmy(ServerModel game){
		int largest=2;
		int playerWith=game.getTurnTracker().getLargestArmy();
		for(int i=0;i<game.getPlayers().size();i++){
			if(game.getPlayers().get(i).getSoldiers() > largest && 
					game.getPlayers().get(i).getSoldiers() > game.getPlayers().get(playerWith).getSoldiers()){
				largest=game.getPlayers().get(i).getSoldiers();
				playerWith=i;
			}
		}
		if(playerWith!=-1)
			game.getTurnTracker().setLargestArmy(playerWith);
	}
	
	private void checkForWinner(ServerModel game,int owner){
		int points=game.getPlayers().get(owner).getVictoryPoints();
		if(points>=10)
			game.setWinner(owner);
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
