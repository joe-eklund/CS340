package client.model;

import java.util.ArrayList;

import client.model.interfaces.IClientModel;
import client.model.interfaces.IHex;
import shared.definitions.GameModel;
import shared.definitions.HexType;
import shared.definitions.ResourceHand;
import shared.definitions.ResourceType;
import shared.definitions.ServerModel;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

import java.util.Map;

/**
 * 
 * @author Epper Marshall
 *
 * The client side representation of the model.
 * 
 */
public class ClientModel implements IClientModel{
	
	private GameModel gameModel;
	private ServerModel serverModel;
	
	/**
	 * ClientModel constructor
	 * @param serverModel
	 */
	public ClientModel(ServerModel serverModel) {
		this.serverModel = serverModel;
		gameModel = new GameModel(serverModel);
	}
	
	/**
	 * Updates the ServerModel to match the version given in the parameters
	 * @param newServerModel
	 * @pre none
	 * @post The server model with in ClientModel is updated to reflect the newServerModel given as a parameter
	 */
	public void updateServerModel(ServerModel newServerModel) {
		this.serverModel = newServerModel;
		gameModel = new GameModel(newServerModel);
	}
	
	/**
	 * Gets the ServerModel
	 * @pre none
	 * @post Returns the current ServerModel.
	 */
	public ServerModel getServerModel() {
		return serverModel;
	}
	
	/**
	 * Determines if a trade offer can be accepted.
	 * @pre <ul>
	 * 			<li>The player has been offered a domestic trade</li>
	 * 			<li>The player to accept has enough of the specified resources</li>
	 * 		</ul>
	 * @post<ul>
	 * 			<li>Returns true if preconditions are satisfied, otherwise returns false</li>
	 * 		</ul>
	 */
	public boolean canAcceptTrade() {
		TradeOffer offer = serverModel.getTradeOffer();
		
		//If there is not an offer don't consider further
		if (offer != null) {
			Player receiver = serverModel.getPlayers().get(serverModel.getTradeOffer().getReceiver());
			
			//For the trade, need to check offer, any positive (I think positive, may be negative) 
			//values are what the receiver needs to have in order to accept the trade 
			if (receiver.getBrick() > offer.getOffer().brick &&
				receiver.getOre() > offer.getOffer().ore &&
				receiver.getSheep() > offer.getOffer().sheep &&
				receiver.getWheat() > offer.getOffer().wheat &&
				receiver.getWood() > offer.getOffer().wood) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Determines if a player can discard the cards specified.
	 * @param playerIndex
	 * @param resourceHand
	 * @pre <ul>
	 * 			<li>The state of the client model is Discarding</li>
	 * 			<li>The specified player has over 7 resource cards.</li>
	 * 			<li>The specified player has the cards choosen to discard</li>
	 * 		</ul>
	 * @post<ul>
	 * 			<li>Returns true if preconditions are satisfied, otherwise returns false</li>
	 * 		</ul>
	 */
	public boolean canDiscardCards(int playerIndex, ResourceHand resourceHand) {
	
		//Checks that the game status is "Discarding", that the player has over 7 Resource cards, and that the 
		//player has the cards chosen to discard.
		if (serverModel.getTurnTracker().getStatus().equals("Discarding") && 
			serverModel.getPlayers().get(playerIndex).getResourceCount() > 7 &&
			playerHasResourceInHand(playerIndex, resourceHand)) {
			
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Determines is the specified player can roll the dice
	 * @param playerIndex: the index of the player trying to roll
	 * @pre <ul>
	 * 			<li>The client model's status is "Rolling"</li>
	 * 			<li>It is the specified players turn</li>
	 * 		</ul>
	 * 
	 * @post<ul>
	 * 			<li>Returns true if the preconditions are satisfied, otherwise returns false</li>
	 * 		</ul
	 */
	public boolean canRollNumber(int playerIndex) {
		//checks to see if the game status is "Rolling" and that it is actually the players turn to roll
		if (serverModel.getTurnTracker().getStatus().equals("Rolling") && 
			serverModel.getTurnTracker().getCurrentTurn() == playerIndex) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean playingCommandsPreconditions(int playerIndex) {
		return (playerIndex == serverModel.getTurnTracker().getCurrentTurn() && 
				serverModel.getTurnTracker().getStatus().equals("Playing")) ? true : false;
	}
	
	/**
	 * Determines if a specified player can build a road at the specified Edge Location.
	 * @param playerIndex
	 * @param edgeLocation
	 * @param free
	 * @pre <ul>
	 * 			<li>The client model's status is "Playing"</li>
	 * 			<li>It is the specified players turn</li>
	 * 			<li>The given Edge Location is not occupied by another road</li>
	 * 			<li>The given Edge Location is not on the water</li>
	 * 			<li>The specified players has 1 wood, 1 brick, and 1 road</li>
	 * 		</ul>
	 * @post<ul>
	 * 			<li>Returns true if the preconditions are satisfied, otherwise returns false</li>
	 * 		</ul>
	 */
	public boolean canBuildRoad(int playerIndex, EdgeLocation edgeLocation, boolean free) {
		return checkBuildRoad(playerIndex, edgeLocation, serverModel.getMap().getRoads(), free);
	}
	
	
	private boolean checkBuildRoad(int playerIndex, EdgeLocation edgeLocation, ArrayList<Road> roads, boolean free){
		
		if (!playingCommandsPreconditions(playerIndex)) {
			return false;
		}
		
		if (!free) {
			if (serverModel.getPlayers().get(playerIndex).getBrick() <= 0 || 
				serverModel.getPlayers().get(playerIndex).getWood() <= 0 || 
				serverModel.getPlayers().get(playerIndex).getRoads() <= 0) {
				return false;
			}
		}
		
		EdgeLocation normEdgeLocation = edgeLocation.getNormalizedLocation();
		
		for (Road road : roads) {
			if (road.getLocation().getNormalizedLocation().equals(normEdgeLocation)) {
				return false;
			}
		}
		
		Map<HexLocation, IHex> board = gameModel.getBoard();
		
		HexLocation neighborLoc = normEdgeLocation.getHexLoc().getNeighborLoc(normEdgeLocation.getDir());
		
		//if hex is water and the neighbor in the direction of the edge location is water this location is invalid
		if (!board.containsKey(neighborLoc) || 
			(board.get(normEdgeLocation.getHexLoc()).getType() == HexType.WATER && 
			board.get(neighborLoc).getType() == HexType.WATER)) {
			return false;
		}
	
		switch(normEdgeLocation.getDir()) {
		case NorthEast:
			return checkNorthEastEdge(normEdgeLocation, roads, playerIndex);
		case North:
			return checkNorthEdge(normEdgeLocation, roads, playerIndex);
		case NorthWest:
			return checkNorthWestEdge(normEdgeLocation, roads, playerIndex);
		default:
			return false;
		}
	}
	
	private boolean checkNorthEastEdge(EdgeLocation normEdgeLocation, ArrayList<Road> roads, int playerIndex) {
		HexLocation thisHexLoc = normEdgeLocation.getHexLoc();
		HexLocation NEHexLoc = normEdgeLocation.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast);
		HexLocation SEHexLoc = normEdgeLocation.getHexLoc().getNeighborLoc(EdgeDirection.SouthEast);
		
		for (Road road : roads) {
			if (road.getOwnerIndex() == playerIndex && road.getLocation().getNormalizedLocation().equals(new EdgeLocation(thisHexLoc, EdgeDirection.North))) {
				return true;
			}
			else if (road.getOwnerIndex() == playerIndex && road.getLocation().getNormalizedLocation().equals(new EdgeLocation(NEHexLoc, EdgeDirection.NorthWest))) {
				return true;
			}
			else if (road.getOwnerIndex() == playerIndex && road.getLocation().getNormalizedLocation().equals(new EdgeLocation(SEHexLoc, EdgeDirection.North))) {
				return true;
			}
			else if (road.getOwnerIndex() == playerIndex && road.getLocation().getNormalizedLocation().equals(new EdgeLocation(SEHexLoc, EdgeDirection.NorthWest))) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkNorthEdge(EdgeLocation normEdgeLocation, ArrayList<Road> roads, int playerIndex) {
		HexLocation thisHexLoc = normEdgeLocation.getHexLoc();
		HexLocation NEHexLoc = normEdgeLocation.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast);
		HexLocation NWHexLoc = normEdgeLocation.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest);
		
		for (Road road : roads) {
			if (road.getOwnerIndex() == playerIndex && road.getLocation().getNormalizedLocation().equals(new EdgeLocation(thisHexLoc, EdgeDirection.NorthEast))) {
				return true;
			}
			else if (road.getOwnerIndex() == playerIndex && road.getLocation().getNormalizedLocation().equals(new EdgeLocation(thisHexLoc, EdgeDirection.NorthWest))) {
				return true;
			}
			else if (road.getOwnerIndex() == playerIndex && road.getLocation().getNormalizedLocation().equals(new EdgeLocation(NEHexLoc, EdgeDirection.NorthWest))) {
				return true;
			}
			else if (road.getOwnerIndex() == playerIndex && road.getLocation().getNormalizedLocation().equals(new EdgeLocation(NWHexLoc, EdgeDirection.NorthEast))) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkNorthWestEdge(EdgeLocation normEdgeLocation, ArrayList<Road> roads, int playerIndex) {
		HexLocation thisHexLoc = normEdgeLocation.getHexLoc();
		HexLocation NWHexLoc = normEdgeLocation.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest);
		HexLocation SWHexLoc = normEdgeLocation.getHexLoc().getNeighborLoc(EdgeDirection.SouthWest);
		
		for (Road road : roads) {
			if (road.getOwnerIndex() == playerIndex && road.getLocation().getNormalizedLocation().equals(new EdgeLocation(thisHexLoc, EdgeDirection.North))) {
				return true;
			}
			else if (road.getOwnerIndex() == playerIndex && road.getLocation().getNormalizedLocation().equals(new EdgeLocation(NWHexLoc, EdgeDirection.NorthEast))) {
				return true;
			}
			else if (road.getOwnerIndex() == playerIndex && road.getLocation().getNormalizedLocation().equals(new EdgeLocation(SWHexLoc, EdgeDirection.North))) {
				return true;
			}
			else if (road.getOwnerIndex() == playerIndex && road.getLocation().getNormalizedLocation().equals(new EdgeLocation(SWHexLoc, EdgeDirection.NorthEast))) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Determines is a settlment can be built by the specified player at the specified Vertex Location
	 * @param playerIndex
	 * @param location
	 * @pre <ul>
	 * 			<li>The client model's status is "Playing"</li>
	 * 			<li>It is the specified players turn</li>
	 * 			<li>The given Vertex Location is not occupied by another settlement</li>
	 * 			<li>The given Vertex Location is not on the water</li>
	 * 			<li>The given Vertex Location is connected to one fo the specified players roads</li>
	 * 			<li>The specified player has 1 wood, 1 brick, 1 wheat, 1 sheep, and 1 settlement</li>
	 * 		</ul>
	 * @post<ul>
	 * 			<li>Returns true if the preconditions are satisfied, otherwise returns false</li>
	 * 		</ul>
	 */
	public boolean canBuildSettlement(int playerIndex, VertexLocation location) {
		VertexLocation normVerLoc = location.getNormalizedLocation();
		
		if (!playingCommandsPreconditions(playerIndex)) {
			return false;
		}
		
		Player player = serverModel.getPlayers().get(playerIndex);
		if (player.getBrick() < 1 || 
			player.getWood() < 1 ||
			player.getWheat() < 1 ||
			player.getSheep() < 1 ||
			player.getSettlements() < 1) {
			
			return false;
		}
		
		
		ArrayList<Settlement> settlements = serverModel.getMap().getSettlements();
		
		for (Settlement settlement : settlements) {
			if (settlement.getLocation().getNormalizedLocation().equals(normVerLoc)) {
				return false;
			}
		}
		
		Map<HexLocation, IHex> board = gameModel.getBoard();
		
		EdgeDirection edgeDir;
		if (normVerLoc.getDir() == VertexDirection.NorthEast) {
			edgeDir = EdgeDirection.NorthEast;
		}
		else {
			edgeDir = EdgeDirection.NorthWest;
		}
		
		HexLocation sideNeighborLoc = normVerLoc.getHexLoc().getNeighborLoc(edgeDir);
		HexLocation northNeighborLoc = normVerLoc.getHexLoc().getNeighborLoc(EdgeDirection.North);
		
		//if hex is water and the neighbor in the direction of the vertex location is water this location is invalid
		if (!board.containsKey(northNeighborLoc) || !board.containsKey(sideNeighborLoc) || 
			(board.get(northNeighborLoc).getType() == HexType.WATER && 
			board.get(sideNeighborLoc).getType() == HexType.WATER)) {
			
			return false;
		}
	
		switch(normVerLoc.getDir()) {
		case NorthEast:
			return checkNorthEastVertex(normVerLoc, settlements, player);
		case NorthWest:
			return checkNorthWestVertex(normVerLoc, settlements, player);
		default:
			return false;
		}
	}
	
	private boolean checkNorthEastVertex(VertexLocation normVerLoc, ArrayList<Settlement> settlements, Player player) {
		
		VertexLocation vertex1 = new VertexLocation(normVerLoc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast), VertexDirection.NorthWest);
		VertexLocation vertex2 = new VertexLocation(normVerLoc.getHexLoc().getNeighborLoc(EdgeDirection.SouthEast), VertexDirection.NorthWest);
		VertexLocation vertex3 = new VertexLocation(normVerLoc.getHexLoc(), VertexDirection.NorthWest);
		
		for (Settlement settlement : settlements) {
			
			if (settlement.getLocation().getNormalizedLocation().equals(vertex1) ||
				settlement.getLocation().getNormalizedLocation().equals(vertex2) ||
				settlement.getLocation().getNormalizedLocation().equals(vertex3)) {
				
				return false;
			}
		}
		
		EdgeLocation edge1 = new EdgeLocation(normVerLoc.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast), EdgeDirection.NorthWest);
		EdgeLocation edge2= new EdgeLocation(normVerLoc.getHexLoc(), EdgeDirection.North);
		EdgeLocation edge3 = new EdgeLocation(normVerLoc.getHexLoc(), EdgeDirection.NorthEast);
		
		ArrayList<Road> roads = serverModel.getMap().getRoads();
		
		for (Road road : roads) {
			if ((road.getLocation().getNormalizedLocation().equals(edge1) && player.getPlayerIndex() == road.getOwnerIndex()) ||
				(road.getLocation().getNormalizedLocation().equals(edge2) && player.getPlayerIndex() == road.getOwnerIndex()) ||
				(road.getLocation().getNormalizedLocation().equals(edge3) && player.getPlayerIndex() == road.getOwnerIndex())) {
				
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkNorthWestVertex(VertexLocation normVerLoc, ArrayList<Settlement> settlements, Player player) {
		
		VertexLocation vertex1 = new VertexLocation(normVerLoc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest), VertexDirection.NorthEast);
		VertexLocation vertex2 = new VertexLocation(normVerLoc.getHexLoc().getNeighborLoc(EdgeDirection.SouthWest), VertexDirection.NorthEast);
		VertexLocation vertex3 = new VertexLocation(normVerLoc.getHexLoc(), VertexDirection.NorthEast);
		
		for (Settlement settlement : settlements) {
			
			if (settlement.getLocation().getNormalizedLocation().equals(vertex1) ||
				settlement.getLocation().getNormalizedLocation().equals(vertex2) ||
				settlement.getLocation().getNormalizedLocation().equals(vertex3)) {
				
				return false;
			}
		}
		
		EdgeLocation edge1 = new EdgeLocation(normVerLoc.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest), EdgeDirection.NorthEast);
		EdgeLocation edge2= new EdgeLocation(normVerLoc.getHexLoc(), EdgeDirection.North);
		EdgeLocation edge3 = new EdgeLocation(normVerLoc.getHexLoc(), EdgeDirection.NorthWest);
		
		ArrayList<Road> roads = serverModel.getMap().getRoads();
		
		for (Road road : roads) {
			if ((road.getLocation().getNormalizedLocation().equals(edge1) && player.getPlayerIndex() == road.getOwnerIndex()) ||
				(road.getLocation().getNormalizedLocation().equals(edge2) && player.getPlayerIndex() == road.getOwnerIndex()) ||
				(road.getLocation().getNormalizedLocation().equals(edge3) && player.getPlayerIndex() == road.getOwnerIndex())) {
				
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Determines is the specified player can build a city at the specified Vertex Location
	 * @param playerIndex
	 * @param location
	 * @pre <ul>
	 * 			<li>The client model's status is "Playing"</li>
	 * 			<li>It is the specified players turn</li>
	 * 			<li>The given Vertex Location has a settlement of the specified player already built on i</li>
	 * 			<li>The specified player has 2 wheat, 3 ore, and 1 city</li>
	 * 		</ul>
	 * @post<ul>
	 * 			<li>Returns true if the preconditions are satisfied, otherwise returns false</li>
	 * 		</ul>
	 */
	public boolean canBuildCity(int playerIndex, VertexLocation location) {
		VertexLocation normVerLoc = location.getNormalizedLocation();
		
		Player player = serverModel.getPlayers().get(playerIndex);
		if (player.getWheat() < 2 || player.getOre() < 3 || player.getCities() < 1) {
			return false;
		}
		
		ArrayList<Settlement> settlements = serverModel.getMap().getSettlements();
		
		for (Settlement settlement : settlements) {
			if (player.getPlayerIndex() == settlement.getOwnerIndex() &&
				settlement.getLocation().getNormalizedLocation().equals(normVerLoc)) {
				
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Determines if the specified player can offer a trade
	 * @param senderIndex
	 * @param resourceHand
	 * @pre <ul>
	 * 			<li>The client model's status is "Playing"</li>
	 * 			<li>It is the specified players turn</li>
	 * 			<li>The specified player has enough of each resource that he/she is going to trade</li>
	 * 		</ul>
	 * @post<ul>
	 * 			<li>Returns true if the preconditions are satisfied, otherwise returns false</li>
	 * 		</ul>
	 */
	public boolean canOfferTrade(int senderIndex, ResourceHand resourceHand) {
		if((resourceHand.getBrick() == 0 && resourceHand.getOre() == 0 && resourceHand.getSheep() == 0 &&
				resourceHand.getWheat() == 0 && resourceHand.getWood() == 0) || 
				resourceHand.getBrick() < 0 || resourceHand.getOre() < 0 || 
				resourceHand.getSheep() < 0 || resourceHand.getWheat() < 0 ||
				resourceHand.getWood() < 0){
			return false;
		}
		return playerHasResourceInHand(senderIndex, resourceHand);
	}
	
	/**
	 * Determines is a player can make a maritime trade
	 * @param playerIndex
	 * @param ratio
	 * @param inputResource
	 * @pre <ul>
	 * 			<li>The client model's status is "Playing"</li>
	 * 			<li>It is the specified players turn</li>
	 * 			<li>The specified player has enough of each resource that he/she is going to trade</li>
	 * 		</ul>
	 * @post<ul>
	 * 			<li>Returns true if the preconditions are satisfied, otherwise returns false</li>
	 * 		</ul>
	 */
	public boolean canMaritimeTrade(int playerIndex, int ratio, ResourceType inputResource) {
		//Checks to see if the player has enough of the specified resource to trade
		if (serverModel.getPlayers().get(playerIndex).hasResource(inputResource, ratio)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Determines is the specified player can finish his/her turn
	 * @pre <ul>
	 * 			<li>The client model's status is "Playing"</li>
	 * 			<li>It is the specified players turn</li>
	 * 		</ul>
	 * @post<ul>
	 * 			<li>Returns true if the preconditions are satisfied, otherwise returns false</li>
	 * 		</ul>
	 */
	public boolean canFinishTurn(int playerIndex) {
		if (playingCommandsPreconditions(playerIndex)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Determines if the specified player can buy a development card
	 * @param playerIndex
	 * @pre <ul>
	 * 			<li>The client model's status is "Playing"</li>
	 * 			<li>It is the specified players turn</li>
	 * 			<li>The specified player has 1 ore, 1 wheat, and 1 sheep</li>
	 * 			<li>There are development cards left in the deck</li>
	 * 		</ul>
	 * @post<ul>
	 * 			<li>Returns true if the preconditions are satisfied, otherwise returns false</li>
	 * 		</ul>
	 */
	public boolean canBuyDevCard(int playerIndex) {
		Player player = serverModel.getPlayers().get(playerIndex);
		
		if (playingCommandsPreconditions(playerIndex) && player.getOre() > 0 && player.getWheat() > 0 && player.getSheep() > 0 && serverModel.getDeck().getTotalDevCardCount() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Determines if a specified can play the Year of Plenty development card
	 * @param playerIndex
	 * @param firstResource
	 * @param secondResource
	 * @pre <ul>
	 * 			<li>The specified player has not played a development card yet this turn</li>
	 * 			<li>The specified player has a year of plenty card that they received before this turn began</li>
	 * 			<li>It is the specified player's turn</li>
	 * 			<li>The client model status is "Playing"</li>
	 * 			<li>The are enough of the specified resources in the bank that the player wants to receive</li>
	 * 		</ul>
	 * @post<ul>
	 * 			<li>Returns true if the preconditions are satisfied, otherwise returns false</li>
	 * 		</ul>
	 */
	public boolean canPlayYearOfPlenty(int playerIndex, ResourceType firstResource, ResourceType secondResource) {
		//Checks status, players turn, has player already played dev card, if the player has a year of plenty card,
		//and that the bank has the two resources requested.
		if (generalDevCardPreconditions(playerIndex) && 
			serverModel.getPlayers().get(playerIndex).getOldDevCards().getYearOfPlenty() > 0 &&
			serverModel.getBank().hasResource(firstResource) && serverModel.getBank().hasResource(secondResource)) {
			
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Determine if the specified player can play the Road Building development card
	 * @param playerIndex
	 * @param spot1
	 * @param spot2
	 * @pre <ul>
	 * 			<li>The specified player has not played a development card yet this turn</li>
	 * 			<li>The specified player has a Road Building card that they received before this turn began</li>
	 * 			<li>It is the specified player's turn</li>
	 * 			<li>The client model status is "Playing"</li>
	 * 			<li>Both Edge Locations specified do not already have roads built at that location<li>
	 * 			<li>The first Edge Location is connected to one of the specified player's roads</li>
	 * 			<li>The second Edge Location is connected to one of the specified player's roads or to the first Edge Location given</li>
	 * 			<li>Neither Edge Location given is on the water</li>
	 * 			<li>The specified player has 2 roads</li> 
	 * 		</ul>
	 * @post<ul>
	 * 			<li>Returns true if the preconditions are satisfied, otherwise returns false</li>
	 * 		</ul>
	 */
	public boolean canPlayRoadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {
		if (generalDevCardPreconditions(playerIndex) &&
			serverModel.getPlayers().get(playerIndex).getOldDevCards().getRoadBuilding() > 0 &&
			serverModel.getPlayers().get(playerIndex).getRoads() >= 2) {
			
			ArrayList<Road> roads = new ArrayList<Road>(serverModel.getMap().getRoads());
			roads.add(new Road(playerIndex, spot1));
			
			if (checkBuildRoad(playerIndex, spot1, serverModel.getMap().getRoads(), true) && checkBuildRoad(playerIndex, spot2, roads, true)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	/**
	 * Determines if a specified player can play the Soldier development card
	 * @param playerIndex
	 * @param hexLoc
	 * @param victimIndex
	 * @pre <ul>
	 * 			<li>The specified player has not played a development card yet this turn</li>
	 * 			<li>The specified player has a Soldier card that they received before this turn began</li>
	 * 			<li>It is the specified player's turn</li>
	 * 			<li>The client model status is "Playing"</li>
	 * 			<li>The Hex location given is not the current location of the Robber</li>
	 * 			<li>The victim has resource cards</li>
	 * 		</ul>
	 * @post<ul>
	 * 			<li>Returns true if the preconditions are satisfied, otherwise returns false</li>
	 * 		</ul>
	 */
	public boolean canPlaySoldier(int playerIndex, HexLocation hexLoc, int victimIndex) {
		//Checks status, players turn, has player already played dev card, if the player has a soldier card,
		//If the robber is not already in the designated HexLocation, and if the victim has cards to steal.
		if (generalDevCardPreconditions(playerIndex) && 
			serverModel.getPlayers().get(playerIndex).getOldDevCards().getSoldier() > 0 &&
			!serverModel.getMap().getRobber().getLocation().equals(hexLoc) && 
			serverModel.getPlayers().get(victimIndex).getResources().totalResourcesCount() > 0) {
			
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Determines if the specified player can play the Monopoly Development card
	 * @param playerIndex
	 * @pre <ul>
	 * 			<li>The specified player has not played a development card yet this turn</li>
	 * 			<li>The specified player has a Monopoly card that they received before this turn began</li>
	 * 			<li>It is the specified player's turn</li>
	 * 			<li>The client model status is "Playing"</li>
	 * 		</ul>
	 * @post<ul>
	 * 			<li>Returns true if the preconditions are satisfied, otherwise returns false</li>
	 * 		</ul>
	 */
	public boolean canPlayMonopoly(int playerIndex) {
		//Checks status, players turn, has player already played dev card, and if the player has a monopoly card
		if (generalDevCardPreconditions(playerIndex) && 
			serverModel.getPlayers().get(playerIndex).getOldDevCards().getMonopoly() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Determines if the specified player can play the Monument development card
	 * @param playerIndex
	 * @pre <ul>
	 * 			<li>The specified player has not played a development card yet this turn</li>
	 * 			<li>The specified player has a Monument card that they received before this turn began</li>
	 * 			<li>It is the specified player's turn</li>
	 * 			<li>The client model status is "Playing"</li>
	 * 		</ul>
	 * @post<ul>
	 * 			<li>Returns true if the preconditions are satisfied, otherwise returns false</li>
	 * 		</ul>
	 */
	public boolean canPlayMonument(int playerIndex) {
		//Checks status, players turn, has player already played dev card, and if the player has a monument card
		if (generalDevCardPreconditions(playerIndex) && serverModel.getPlayers().get(playerIndex).getOldDevCards().getMonument() > 0) {	
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean generalDevCardPreconditions(int playerIndex) {
		if (serverModel.getTurnTracker().getStatus().equals("Playing") && 
			serverModel.getTurnTracker().getCurrentTurn() == playerIndex &&
			!serverModel.getPlayers().get(playerIndex).hasPlayedDevCard()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean playerHasResourceInHand(int playerIndex, ResourceHand resourceHand) {
		
		//Each if statement checks to see if the value in resourceHand is not negative, if it is positive
		//then the player needs to have that much resource in order for the method to return true, so the if statement
		//also checks to see if the player has enough of the specified resource
		if (resourceHand.getBrick() > 0 && 
			!serverModel.getPlayers().get(playerIndex).hasResource(ResourceType.BRICK, resourceHand.getBrick())) {
			
			return false;
		}
		
		if (resourceHand.getOre() > 0 && 
			!serverModel.getPlayers().get(playerIndex).hasResource(ResourceType.ORE, resourceHand.getOre())) {
			
			return false;
		}
		
		if (resourceHand.getSheep() > 0 && 
			!serverModel.getPlayers().get(playerIndex).hasResource(ResourceType.SHEEP, resourceHand.getSheep())) {
			
			return false;
		}
		
		if (resourceHand.getWheat() > 0 && 
			!serverModel.getPlayers().get(playerIndex).hasResource(ResourceType.WHEAT, resourceHand.getWheat())) {
			return false;
		}
		
		if (resourceHand.getWood() > 0 && 
			!serverModel.getPlayers().get(playerIndex).hasResource(ResourceType.WOOD, resourceHand.getWood())) {
			
			return false;
		}
		
		return true;
	}
}

