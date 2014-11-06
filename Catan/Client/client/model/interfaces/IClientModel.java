package client.model.interfaces;

import shared.definitions.ResourceHand;
import shared.definitions.ResourceType;
import shared.definitions.ServerModel;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * The inerface client side representation of the model.
 *
 */
public interface IClientModel {
	
	/**
	 * Updates the ServerModel to match the version given in the parameters
	 * @param newServerModel
	 * @pre none
	 * @post The server model with in ClientModel is updated to reflect the newServerModel given as a parameter
	 */
	public void updateServerModel(ServerModel newServerModel);
	
	/**
	 * Gets the ServerModel
	 * @pre none
	 * @post Returns the current ServerModel.
	 */
	public ServerModel getServerModel();
	
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
	public boolean canAcceptTrade();
	
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
	public boolean canDiscardCards(int playerIndex, ResourceHand resourceHand);
	
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
	public boolean canRollNumber(int playerIndex);
	
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
	public boolean canBuildRoad(int playerIndex, EdgeLocation edgeLocation, boolean free);
	
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
	public boolean canBuildSettlement(int playerIndex, VertexLocation location);
	
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
	public boolean canBuildCity(int playerIndex, VertexLocation location);
	
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
	public boolean canOfferTrade(int senderIndex, ResourceHand resourceHand);
	
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
	public boolean canMaritimeTrade(int playerIndex, int ratio, ResourceType inputResource);
	
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
	public boolean canFinishTurn(int playerIndex);
	
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
	public boolean canBuyDevCard(int playerIndex);
	
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
	public boolean canPlayYearOfPlenty(int playerIndex, ResourceType firstResource, ResourceType secondResource);
	
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
	public boolean canPlayRoadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2);
	
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
	public boolean canPlaySoldier(int playerIndex, HexLocation hexLoc, int victimIndex);
	
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
	public boolean canPlayMonopoly(int playerIndex);
	
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
	public boolean canPlayMonument(int playerIndex);
}
