package server.model;

import shared.definitions.ResourceHand;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public interface IGames {
	public boolean canBuildCity(int gameID, int playerIndex, VertexLocation location);
	
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
	public boolean canOfferTrade(int gameID, int senderIndex, ResourceHand resourceHand);
	
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
	public boolean canMaritimeTrade(int gameID, int playerIndex, int ratio, ResourceType inputResource);
	
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
	public boolean canFinishTurn(int gameID, int playerIndex);
	
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
	public boolean canBuyDevCard(int gameID, int playerIndex);
	
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
	public boolean canPlayYearOfPlenty(int gameID, int playerIndex, ResourceType firstResource, ResourceType secondResource);
	
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
	public boolean canPlayRoadBuilding(int gameID, int playerIndex, EdgeLocation spot1, EdgeLocation spot2);
	
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
	public boolean canPlaySoldier(int gameID, int playerIndex, HexLocation hexLoc, int victimIndex);
	
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
	public boolean canPlayMonopoly(int gameID, int playerIndex);
	
	/**
	 * Determines if robber can be placed in specified location.
	 * @param hexLoc
	 * @return
	 */
	public boolean canPlaceRobber(int gameID, HexLocation hexLoc);
	
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
	public boolean canPlayMonument(int gameID, int playerIndex);
	
}
