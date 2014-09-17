package proxy;

import java.util.List;
import java.util.Map;

import shared.definitions.ResourceType;

public interface IServerProxy {	
	
	//Josh Begin
	
	/*
	 * Logs an existing user into the game server
	 * 
	 * @pre none
	 * 
	 * @post
	 */
	void loginUser(String username, String password);
	
	/*
	 *  This call performs the following 2 actions:
	 *  1)Creates a new user account
	 *	2) Logs the new user into the game server
	 * 
	 * @pre
	 * 
	 * @post
	 * 
	 */
	void registerUser(String username, String password);
	
	/*
	 * This call returns information about all of the current games
	 * 
	 * @pre
	 * 
	 * @post
	 * 
	 */
	void listGames();
	
	/*
	 * Creates an empty game on the server
	 * 
	 * @pre
	 * 
	 * @post
	 * 
	 */
	void createGame(String name);
	
	/*
	 * Adds a player to the game and sets their game cookie
	 * 
	 * @pre
	 * 
	 * @post
	 * 
	 */
	void joinGame(String color, int gameID);
	
	/*
	 * Fetches the JSON for the model of the current game.
	 * 
	 * @pre
	 * 
	 * @post
	 * 
	 */
	void getGameModel();
	
	/*
	 * Resets the game to how it was after all the players joined
	 * 
	 * @pre
	 * 
	 * @post
	 * 
	 */
	void resetGame();
	
	/*
	 * Gets a list of all the commands played on a game
	 * 
	 * @pre
	 * 
	 * @post
	 * 
	 */
	void getGameCommands();
	
	/*
	 * Applies a list of commands to the current game.
	 * 
	 * @pre
	 * 
	 * @post
	 * 
	 */
	void postGameCommands(String commands);
	
	/*
	 * Lists the available AI types that may be added to a game
	 * 
	 * @pre
	 * 
	 * @post
	 * 
	 */
	void listAI();
	
	/*
	 * Adds an AI to the game
	 * 
	 * @pre
	 * 
	 * @post
	 * 
	 */
	void addAI();
	
	/*
	 * Sets the server’s logging level
	 * 
	 * @pre
	 * 
	 * @post
	 * 
	 */
	void changeLogLevel(String logLevel);
	
	/*
	 * Sends chat message to game chat log
	 * 
	 * @pre
	 * 
	 * @post
	 * 
	 */
	void sendChat(String message);
	
	/*
	 * Accept another player's trade offer
	 * 
	 * @pre
	 * 
	 * @post
	 * 
	 */
	void acceptTrade(boolean willAccept);
	
	/*
	 * Discards cards from players hand
	 * 
	 * @pre
	 * 
	 * @post
	 */
	void discardCards(List<ResourceType> resources, Map<ResourceType, Integer> resourceHand);
	//Josh End
	
	// Taylor Begin
	void rollNumber();
	void buildRoad();
	void buildSettlement();
	void buildCity();
	void offerTrade();
	void maritimeTrade();
	void finishTurn();
	void buyDevCard();
	void playYearOfPlentyCard();
	void playRoadBuildingCard();
	void playMonopolyCard();
	void playSoldierCard();
	void playVictoryPointCard();
	//Taylor End
}