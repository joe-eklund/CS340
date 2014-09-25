package proxy;

import java.util.List;
import java.util.Map;

public class ProxyServer implements IServer{
	private ICommunicator clientCommunicator;
	
	public ProxyServer(ICommunicator clientCommunicator) {
		this.clientCommunicator = clientCommunicator;
	}

	@Override
	public String loginUser(String username, String password) {
		// create loginUserParam object based on username and password
		// call clientCommunicator.executeCommand("loginUser", loginUserParam);
		// return (loginUserResult.message != null) ? loginUserResult : null
		return null;
	}

	@Override
	public String registerUser(String username, String password) {
		// create registerUserParam object based on username and password
		// call clientCommunicator.executeCommand("registerUser", registerUserParam);
		// return (registerUserResult.message != null) ? registerUserResult : null
		return null;
	}

	@Override
	public List<GameDescription> listGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameDescription createGame(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void joinGame(String color, int gameID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameModel getGameModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel resetGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Log> getGameCommands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel postGameCommands(List<Log> commands) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> listAI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAI(String aiToAdd) {
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
	public void rollNumber(int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildRoad(boolean free, EdgeLocation roadLocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildSettlement(boolean free) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildCity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void offerTrade(ResourceHand offer, playerIndex receiver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void maritimeTrade(int ratio, Resource inputResource,
			Resource outputResource) {
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
	public void playYearOfPlentyCard(Resource resourceOne, Resource resourceTwo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playRoadBuildingCard(EdgeLocation spot1, EdgeLocation spot2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playMonopolyCard(Resource resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playSoldierCard(HexLocation location, playerIndex victimIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playMonumentCard() {
		// TODO Auto-generated method stub
		
	}

}
