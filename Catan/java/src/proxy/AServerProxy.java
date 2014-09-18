package proxy;

import java.util.List;
import java.util.Map;

import shared.definitions.ResourceType;

public class AServerProxy implements IServerProxy {
	
	private int gameVersion;
	private int catanUserID;
	private int gameID;
	private String catanUserCookie;
	private String color;
	
	public AServerProxy() {
		
	}

	@Override
	public void loginUser(String username, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerUser(String username, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void listGames() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createGame(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void joinGame(String color, int gameID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getGameModel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getGameCommands() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postGameCommands(String commands) {
		// TODO Auto-generated method stub

	}

	@Override
	public void listAI() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAI() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeLogLevel(String logLevel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendChat(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void acceptTrade(boolean willAccept) {
		// TODO Auto-generated method stub

	}

	@Override
	public void discardCards(List<ResourceType> resources,
			Map<ResourceType, Integer> resourceHand) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rollNumber() {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildRoad() {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildSettlement() {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildCity() {
		// TODO Auto-generated method stub

	}

	@Override
	public void offerTrade() {
		// TODO Auto-generated method stub

	}

	@Override
	public void maritimeTrade() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void buyDevCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playYearOfPlentyCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playRoadBuildingCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playMonopolyCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playSoldierCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playVictoryPointCard() {
		// TODO Auto-generated method stub

	}

}
