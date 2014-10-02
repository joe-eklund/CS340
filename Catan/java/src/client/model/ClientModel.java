package client.model;

import java.util.ArrayList;

import shared.definitions.GameModel;
import shared.definitions.ResourceHand;
import shared.definitions.ResourceType;
import shared.definitions.ServerModel;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

/**
 * 
 * @author Epper Marshall
 *
 * The client side representation of the model.
 * 
 */
public class ClientModel {
	
	private 
	private ServerModel serverModel;
	
	public ClientModel(ServerModel serverModel) {
		this.serverModel = serverModel;
	}
	
	public void updateServerModel(ServerModel newServerModel) {
		this.serverModel = newServerModel;
	}
	
	public ServerModel getServerModel() {
		return serverModel;
	}
	
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
	
	public boolean canDiscardCards(int playerIndex, ResourceHand resourceHand) {
		//TODO do we check if the oldDev card hand has 7 cards or the newDev card hand?
		//TODO does it have to be the players turn to discard?  The spec doesn't list that as a precondition
		
		//Checks that the game status is "Discarding", that the player has over 7 Dev cards, and that the 
		//player has the cards chosen to discard.
		if (serverModel.getTurnTracker().getStatus().equals("Discarding") && 
			serverModel.getPlayers().get(playerIndex).getNewDevCards().getTotalDevCardCount() > 7 &&
			playerHasResourceInHand(playerIndex, resourceHand)) {
			
			return true;
		}
		else {
			return false;
		}
	}
	
	//TODO check to see if "Rolling" status has a capital R or not
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
	
	public boolean canBuildRoad(int playerIndex, EdgeLocation edgeLocation) {
		
		if (playerIndex != serverModel.getTurnTracker().getCurrentTurn()) {
			return false;
		}
		
		if (serverModel.getPlayers().get(playerIndex).getBrick() <= 0 || 
			serverModel.getPlayers().get(playerIndex).getWood() <= 0 || 
			serverModel.getPlayers().get(playerIndex).getRoads() <= 0) {
			return false;
		}
		
		ArrayList<Road> roads = serverModel.getMap().getRoads();
		
		EdgeLocation normEdgeLocation = edgeLocation.getNormalizedLocation();
		
		for (Road road : roads) {
			if (road.getLocation().equals(normEdgeLocation)) {
				return false;
			}
		}
		
		switch(edgeLocation.getDir()) {
		case NorthEast:
			checkNorthEastEdge(normEdgeLocation);
		case North:
			checkNorthEdge(normEdgeLocation);
		case NorthWest:
		}
		
	public boolean buildRoad() {
		return false;
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
	
	public boolean canBuildSettlement() {
		return false;
	}
	
	public boolean canBuildCity() {
		return false;
	}
	
	public boolean canOfferTrade(int senderIndex, ResourceHand resourceHand) {
		return playerHasResourceInHand(senderIndex, resourceHand);
	}
	
	public boolean canMaritimeTrade(int playerIndex, int ratio, ResourceType inputResource) {
		//TODO do I need to check to make sure ratio is 2, 3, or 4?
		//Checks to see if the player has enough of the specified resource to trade
		if (serverModel.getPlayers().get(playerIndex).hasResource(inputResource, ratio)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//DONE
	public boolean canFinishPlaying() {
		if (serverModel.getTurnTracker().getStatus().equals("Playing")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean canBuyDevCard(int playerIndex) {
		Player player = serverModel.getPlayers().get(playerIndex);
		
		//TODO check that there are dev cards left in the deck (where the heck is the deck!?)
		if (player.getOre() > 0 && player.getWheat() > 0 && player.getSheep() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
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
	
	public boolean canPlayRoadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {
		//TODO not all preconditions are checked here
		if (generalDevCardPreconditions(playerIndex) &&
			serverModel.getPlayers().get(playerIndex).getOldDevCards().getRoadBuilding() > 0 &&
			serverModel.getPlayers().get(playerIndex).getRoads() >= 2) {
			
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean canPlaySoldier(int playerIndex, HexLocation hexLoc, int victimIndex) {
		//Checks status, players turn, has player already played dev card, if the player has a soldier card,
		//If the robber is not already in the designated HexLocation, and if the victim has cards to steal.
		if (generalDevCardPreconditions(playerIndex) && 
			serverModel.getPlayers().get(playerIndex).getOldDevCards().soldier > 0 &&
			!serverModel.getMap().getRobber().getLocation().equals(hexLoc) && 
			serverModel.getPlayers().get(victimIndex).getResources().totalResourcesCount() > 0) {
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean canPlayMonopoly(int playerIndex) {
		//Checks status, players turn, has player already played dev card, and if the player has a monopoly card
		if (generalDevCardPreconditions(playerIndex) && 
			serverModel.getPlayers().get(playerIndex).getOldDevCards().monopoly > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean canPlayMonument(int playerIndex) {
		//Checks status, players turn, has player already played dev card, and if the player has a monument card
		if (generalDevCardPreconditions(playerIndex) && serverModel.getPlayers().get(playerIndex).getOldDevCards().monument > 0) {	
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

