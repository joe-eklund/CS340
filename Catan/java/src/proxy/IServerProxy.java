package proxy;

import java.net.HttpURLConnection;

public abstract class IServerProxy {
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
	void discardCards;
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
}