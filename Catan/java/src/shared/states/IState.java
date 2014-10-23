package shared.states;

import shared.ServerMethodResponses.AddAIResponse;
import shared.ServerMethodResponses.ChangeLogLevelResponse;
import shared.ServerMethodResponses.CreateGameResponse;
import shared.ServerMethodResponses.GetGameCommandsResponse;
import shared.ServerMethodResponses.GetGameModelResponse;
import shared.ServerMethodResponses.ListAIResponse;
import shared.ServerMethodResponses.ListGamesResponse;
import shared.ServerMethodResponses.LoginUserResponse;
import shared.ServerMethodResponses.PostGameCommandsResponse;
import shared.ServerMethodResponses.RegisterUserResponse;
import shared.ServerMethodResponses.ResetGameResponse;
import shared.definitions.CatanColor;
import shared.definitions.ResourceHand;
import shared.definitions.ResourceType;
import shared.definitions.ServerLogLevel;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import client.model.Log;
import client.presenter.IPresenter;

public interface IState {
public LoginUserResponse login(IPresenter presenter, String user,String pass);

	public String getStatus();
	
	public ListGamesResponse listGames(IPresenter presenter);
	
	public RegisterUserResponse register(IPresenter presenter, String user,String pass);

	public CreateGameResponse createGame(IPresenter presenter, boolean randTiles,boolean randNums,boolean randPorts,String name);
	
	public void joinGame(IPresenter presenter, CatanColor color, int gameID);
	
	public String[] listAIChoices(IPresenter presenter);
	
	public void buildRoad(IPresenter presenter, EdgeLocation roadLocation);

	public void buildSettlement(IPresenter presenter, VertexLocation vertLoc);

	public GetGameModelResponse getGameModel(IPresenter presenter);

	public ResetGameResponse resetGame(IPresenter presenter);

	public GetGameCommandsResponse getGameCommands(IPresenter presenter);

	public PostGameCommandsResponse postGameCommands(IPresenter presenter, Log commands);

	public ListAIResponse listAI(IPresenter presenter);

	public void addAI(IPresenter presenter, String aiToAdd);

	public ChangeLogLevelResponse changeLogLevel(IPresenter presenter, ServerLogLevel logLevel);

	public void sendChat(IPresenter presenter, String message);

	public void acceptTrade(IPresenter presenter, boolean willAccept);

	public void discardCards(IPresenter presenter, ResourceHand resourceHand);

	public void rollNumber(IPresenter presenter, int number);

	public void buildRoad(IPresenter presenter, EdgeLocation roadLocation, boolean free);

	public void buildSettlement(IPresenter presenter, VertexLocation vertexLocation, boolean free);

	public void buildCity(IPresenter presenter, VertexLocation vertexLocation);

	public void offerTrade(IPresenter presenter, ResourceHand offer, int receiver);

	public void maritimeTrade(IPresenter presenter, int ratio, ResourceType inputResource, ResourceType outputResource);

	public void finishTurn(IPresenter presenter);

	public void buyDevCard(IPresenter presenter);

	public void playYearOfPlentyCard(IPresenter presenter, ResourceType resource1,
			ResourceType resource2);

	public void playRoadBuildingCard(IPresenter presenter, EdgeLocation spot1, EdgeLocation spot2);

	public void playMonopolyCard(IPresenter presenter, ResourceType resource);

	public void playSoldierCard(IPresenter presenter, int victimIndex, HexLocation location);

	public void playMonumentCard(IPresenter presenter);

	public boolean isInAnyPlayingState();

	public void update(IPresenter presenter);

}
