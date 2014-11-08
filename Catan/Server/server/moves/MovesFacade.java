package server.moves;

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

/**
 * This Facade implements the sendChat,
 * rollNumber, robPlayer, finishTurn, buyDevCard, Year_of_Plaenty, Road_Building, Soldier, 
 * Monopoly, Monument, buildRoad, buildSettlement, buildCity, 
 * offerTrade, acceptTrade, maritimeTrade, discardCards commands
 *
 */
public class MovesFacade implements IMovesFacade {

	@Override
	public int sendChat(SendChatRequest request) {
		// TODO Auto-generated method stub
		return 0;
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
	public int yearOfPlenty(YearOfPlentyDevRequest request) {
		// TODO Auto-generated method stub
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
	public int buildRoad(BuildRoadRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int buildSettlement(BuildSettlementRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int buildCity(BuildCityRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int offerTrade(OfferTradeRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int acceptTrade(AcceptTradeRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int maritime(MaritimeTradeRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int discardCards(DiscardCardsRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

}
