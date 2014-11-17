package server.moves;

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

public class MovesFacadeStub implements IMovesFacade {

	@Override
	public boolean sendChat(SendChatRequest request,CookieParams cookie) throws InvalidMovesRequest{
		// TODO Auto-generated method stub
		return false;
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
	public boolean buildCity(BuildCityRequest request, CookieParams cookie) throws InvalidMovesRequest{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerTrade(OfferTradeRequest request, CookieParams cookie) throws InvalidMovesRequest {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acceptTrade(AcceptTradeRequest request, CookieParams cookie) throws InvalidMovesRequest {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean maritimeTrade(MaritimeTradeRequest request, CookieParams cookie) throws InvalidMovesRequest {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean discardCards(DiscardCardsRequest request, CookieParams cookie) throws InvalidMovesRequest{
		// TODO Auto-generated method stub
		return true;
	}

}
