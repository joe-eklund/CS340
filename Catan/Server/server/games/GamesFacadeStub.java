package server.games;

import java.util.ArrayList;
import java.util.List;

import client.exceptions.ClientModelException;
import shared.ServerMethodRequests.CreateGameRequest;
import shared.ServerMethodRequests.JoinGameRequest;
import shared.definitions.GameDescription;
import shared.definitions.Location;
import shared.definitions.ServerModel;
import shared.model.Resources;
import shared.model.Road;
import shared.model.Settlement;

public class GamesFacadeStub extends AGamesFacade {

	/**
	 * 
	 */
	//@SuppressWarnings("serial")
	public GamesFacadeStub(List<ServerModel> serverModels) {
		super(serverModels);
		this.gameDescriptionsList = new ArrayList<GameDescription>();
		/*
		this.gameDescriptionsList = new ArrayList<GameDescription>(){{
			add(new GameDescription("Empty Game", 0, new ArrayList<PlayerDescription>(4)));
			add(new GameDescription("Second Game", 1, new ArrayList<PlayerDescription>(4) {{
				add(new PlayerDescription("red", 0, "Bobby"));
				add(new PlayerDescription("blue", 1, "Billy"));
				add(new PlayerDescription("green", 2, "Sandy"));
				add(new PlayerDescription("yellow", 3, "Cathy"));
			}}));
		}};
		*/
		
		try {
			this.createGame(new CreateGameRequest(false, false, false, "Empty Game"));
			this.createGame(new CreateGameRequest(false, false, false, "Second Game"));
			this.joinGame(new JoinGameRequest(1, "red"), "Bobby", 0);
			this.joinGame(new JoinGameRequest(1, "blue"), "Billy", 1);
			this.joinGame(new JoinGameRequest(1, "green"), "Sandy", 2);
			this.joinGame(new JoinGameRequest(1, "yellow"), "Cathy", 3);
			
			this.createGame(new CreateGameRequest(false, false, false, "After Setup Game"));
			this.joinGame(new JoinGameRequest(2, "red"), "Bobby", 0);
			this.joinGame(new JoinGameRequest(2, "blue"), "Billy", 1);
			this.joinGame(new JoinGameRequest(2, "green"), "Sandy", 2);
			this.joinGame(new JoinGameRequest(2, "yellow"), "Cathy", 3);
			
			try {
				ServerModel serverModel = serverModels.get(2);
				
				//Player 0
				//Road(playerIndex, x, y, direction
				Road road = new Road(0, 0, 0 , "N");
				serverModel.getMap().getRoads().add(road);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(0).decrementRoads();
				
				Settlement settlement = new Settlement(0, 0, 0, "NE");
				serverModel.getMap().getSettlements().add(settlement);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(0).decrementSettlements();
			
				road = new Road(0, 0, 0 , "S");
				serverModel.getMap().getRoads().add(road);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(0).decrementRoads();
				
				settlement = new Settlement(0, 0, 0, "SW");
				serverModel.getMap().getSettlements().add(settlement);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(0).decrementSettlements();
				serverModel.getPlayers().get(0).setVictoryPoints(2);
				
				
				//Player 1
				road = new Road(1, 0, 2 , "N");
				serverModel.getMap().getRoads().add(road);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(1).decrementRoads();
				
				settlement = new Settlement(1, 0, 2, "NE");
				serverModel.getMap().getSettlements().add(settlement);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(1).decrementSettlements();
				
				road = new Road(1, 0, 2 , "S");
				serverModel.getMap().getRoads().add(road);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(1).decrementRoads();
				
				settlement = new Settlement(1, 0, 2, "SW");
				serverModel.getMap().getSettlements().add(settlement);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(1).decrementSettlements();
				serverModel.getPlayers().get(1).setVictoryPoints(2);
				
				//Player 2
				road = new Road(2, 0, -2 , "N");
				serverModel.getMap().getRoads().add(road);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(2).decrementRoads();
				
				settlement = new Settlement(2, 0, -2, "NE");
				serverModel.getMap().getSettlements().add(settlement);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(2).decrementSettlements();
				
				//Road(playerIndex, x, y, direction
				road = new Road(2, 0, -2 , "S");
				serverModel.getMap().getRoads().add(road);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(2).decrementRoads();
				
				settlement = new Settlement(2, 0, -2, "SW");
				serverModel.getMap().getSettlements().add(settlement);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(2).decrementSettlements();
				serverModel.getPlayers().get(2).setVictoryPoints(2);
				
				//Player 3
				road = new Road(3, 2, 0 , "N");
				serverModel.getMap().getRoads().add(road);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(3).decrementRoads();
				
				settlement = new Settlement(3, 2, 0, "NE");
				serverModel.getMap().getSettlements().add(settlement);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(3).decrementSettlements();
				
				//Road(playerIndex, x, y, direction
				road = new Road(3, 2, 0, "S");
				serverModel.getMap().getRoads().add(road);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(3).decrementRoads();
				
				settlement = new Settlement(3, 2, 0, "SW");
				serverModel.getMap().getSettlements().add(settlement);
				serverModel.incrementVersion();
				serverModel.getPlayers().get(3).decrementSettlements();
				serverModel.getPlayers().get(0).setVictoryPoints(2);
				
				serverModel.getTurnTracker().setCurrentTurn(0);
				serverModel.getTurnTracker().setStatus("Rolling");
				
				//Giving player 0 resources
				serverModel.getPlayers().get(0).setResources(new Resources(10, 10, 10, 10, 10));
				
			} catch (ClientModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			/*
			this.addPlayerToGameModel(1, "Bobby", "red", 0);
			this.addPlayerToGameModel(1, "Billy", "blue", 1);
			this.addPlayerToGameModel(1, "Sandy", "green", 2);
			this.addPlayerToGameModel(1, "Cathy", "yellow", 3);
			*/
		} catch (InvalidGamesRequest e) {
			e.printStackTrace();
		}
	}
}
