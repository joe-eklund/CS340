package server.model;

import java.util.ArrayList;

import shared.definitions.ResourceHand;
import shared.definitions.ResourceType;
import shared.definitions.ServerModel;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class Games implements IGames{
	private ArrayList<ServerModel> games;

	public Games() {
		
	}
	
	public Games(ArrayList<ServerModel> games) {
		
	}
	
	@Override
	public boolean canBuildCity(int gameID, int playerIndex,
			VertexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canOfferTrade(int gameID, int senderIndex,
			ResourceHand resourceHand) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canMaritimeTrade(int gameID, int playerIndex, int ratio,
			ResourceType inputResource) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canFinishTurn(int gameID, int playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBuyDevCard(int gameID, int playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayYearOfPlenty(int gameID, int playerIndex,
			ResourceType firstResource, ResourceType secondResource) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayRoadBuilding(int gameID, int playerIndex,
			EdgeLocation spot1, EdgeLocation spot2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaySoldier(int gameID, int playerIndex,
			HexLocation hexLoc, int victimIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonopoly(int gameID, int playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceRobber(int gameID, HexLocation hexLoc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlayMonument(int gameID, int playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the games
	 */
	public ArrayList<ServerModel> getGames() {
		return games;
	}

	/**
	 * @param games the games to set
	 */
	public void setGames(ArrayList<ServerModel> games) {
		this.games = games;
	}
	
}
