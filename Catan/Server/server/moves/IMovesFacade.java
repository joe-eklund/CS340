package server.moves;

import client.exceptions.ClientModelException;
import server.cookie.CookieParams;
import shared.ServerMethodRequests.AcceptTradeRequest;
import shared.ServerMethodRequests.BuildCityRequest;
import shared.ServerMethodRequests.BuildRoadRequest;
import shared.ServerMethodRequests.BuildSettlementRequest;
import shared.ServerMethodRequests.BuyDevCardRequest;
import shared.ServerMethodRequests.DiscardCardsRequest;
import shared.ServerMethodRequests.FinishTurnRequest;
import shared.ServerMethodRequests.JoinGameRequest;
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
	public boolean sendChat(SendChatRequest request, CookieParams cookie) throws InvalidMovesRequest;
	public int rollNumber(RollNumberRequest request);
	public int robPlayer(RobPlayerRequest request);
	public int finishTurn(FinishTurnRequest request);
	public int buyDevCard(BuyDevCardRequest request);
	public int yearOfPlenty(YearOfPlentyDevRequest request, CookieParams cookie);
	public int roadBuilding(RoadBuildingDevRequest request);
	public int soldier(SoldierDevRequest request);
	public int monopoly(MonopolyDevRequest request);
	public int monument(MonumentDevRequest request);
	public boolean buildRoad(BuildRoadRequest request, CookieParams cookie) throws InvalidMovesRequest;
	public int buildSettlement(BuildSettlementRequest request);
	public boolean buildCity(BuildCityRequest request, CookieParams cookie) throws InvalidMovesRequest, ClientModelException; 
	public boolean offerTrade(OfferTradeRequest request, CookieParams cookie) throws InvalidMovesRequest;
	public boolean acceptTrade(AcceptTradeRequest request, CookieParams cookie) throws InvalidMovesRequest;
	public boolean maritimeTrade(MaritimeTradeRequest request, CookieParams cookie) throws InvalidMovesRequest;
	public boolean discardCards(DiscardCardsRequest request, CookieParams cookie) throws InvalidMovesRequest;
}
