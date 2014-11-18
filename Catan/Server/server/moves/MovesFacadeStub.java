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
import shared.definitions.ServerModel;

public class MovesFacadeStub implements IMovesFacade {

	@Override
	public ServerModel sendChat(SendChatRequest request,CookieParams cookie) throws InvalidMovesRequest{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerModel rollNumber(RollNumberRequest request,CookieParams cookie) throws InvalidMovesRequest {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerModel robPlayer(RobPlayerRequest request,CookieParams cookie) throws InvalidMovesRequest {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean finishTurn(FinishTurnRequest request,CookieParams cookie) throws InvalidMovesRequest {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ServerModel buyDevCard(BuyDevCardRequest request,CookieParams cookie) throws InvalidMovesRequest {
		// TODO Auto-generated method stub
		return null;
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
	public ServerModel buildRoad(BuildRoadRequest request, CookieParams cookie) throws InvalidMovesRequest {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerModel buildSettlement(BuildSettlementRequest request, CookieParams cookie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerModel buildCity(BuildCityRequest request, CookieParams cookie) throws InvalidMovesRequest{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerModel offerTrade(OfferTradeRequest request, CookieParams cookie) throws InvalidMovesRequest {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerModel acceptTrade(AcceptTradeRequest request, CookieParams cookie) throws InvalidMovesRequest {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerModel maritimeTrade(MaritimeTradeRequest request, CookieParams cookie) throws InvalidMovesRequest {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerModel discardCards(DiscardCardsRequest request, CookieParams cookie) throws InvalidMovesRequest{
		// TODO Auto-generated method stub
		return null;
	}

}
