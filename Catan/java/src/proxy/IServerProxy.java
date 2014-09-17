package proxy;

import java.net.HttpURLConnection;

public interface IServerProxy {
	//Josh Begin
	void loginUser();
	void registerUser();
	void listGames();
	void createGame();
	void joinGame();
	void getGameModel();
	void resetGame();
	void getGameCommands();
	void postGameCommands();
	void listAI();
	void addAI();
	void changeLogLevel();
	void sendChat();
	void acceptTrade();
	void discardCards();
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