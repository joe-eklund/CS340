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

/**
 * This interface defines a Facade containing the sendChat,
 * rollNumber, robPlayer, finishTurn, buyDevCard, Year_of_Plaenty, Road_Building, Soldier, 
 * Monopoly, Monument, buildRoad, buildSettlement, buildCity, 
 * offerTrade, acceptTrade, maritimeTrade, discardCards commands
 *
 */
public interface IMovesFacade {
	public int sendChat(SendChatRequest request);
	public int rollNumber(RollNumberRequest request);
	public int robPlayer(RobPlayerRequest request);
	public int finishTurn(FinishTurnRequest request);
	public int buyDevCard(BuyDevCardRequest request);
	public int yearOfPlenty(YearOfPlentyDevRequest request, CookieParams cookie);
	public int roadBuilding(RoadBuildingDevRequest request);
	public int soldier(SoldierDevRequest request);
	public int monopoly(MonopolyDevRequest request);
	public int monument(MonumentDevRequest request);
	public int buildRoad(BuildRoadRequest request);
	public int buildSettlement(BuildSettlementRequest request);
	public int buildCity(BuildCityRequest request);
	public int offerTrade(OfferTradeRequest request);
	public int acceptTrade(AcceptTradeRequest request);
	public int maritime(MaritimeTradeRequest request);
	public int discardCards(DiscardCardsRequest request);
}
