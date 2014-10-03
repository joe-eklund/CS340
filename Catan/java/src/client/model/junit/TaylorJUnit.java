package client.model.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import proxy.*;
import shared.definitions.*;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import client.model.*;

public class TaylorJUnit{
	private ClientModel clientModel;
	
	@Before 
	public void setUp() { 
		//Creates a ServerModel object from a test JSON string. Then creates a ClientModel from ServerModel.
		ITranslator translator = new TranslatorJSON();
		clientModel = new ClientModel((ServerModel)translator.translateFrom(gameJSON, ServerModel.class));
	}
	
//	@Test
//	public void testUpdateServerModel(){
//		
//	}
	
//	@Test
//	public void testCanAcceptTrade(){
//		//Trade offer should be null
//		assertEquals("Client Model should not have a trade offer and fail", 
//				false, clientModel.canAcceptTrade());
//		
//		//Create invalid trade offer from player 0 to player 1 for 1 wood
//		clientModel.getServerModel().setTradeOffer(new TradeOffer(0,1,0,0,0,0,1));
//		assertEquals("Client Model should not have a valid trade offer and fail",
//				false, clientModel.canAcceptTrade());
//		
//		//Create valid trade offer from player 0 to player 1 for 1 brick
//		clientModel.getServerModel().setTradeOffer(new TradeOffer(0,1,1,0,0,0,0));
//		assertEquals("Client Model should have a valid trade offer and pass",
//				true, clientModel.canAcceptTrade());
//	}
	
//	@Test
//	public void testCanDiscardCards(){
//		//Status should be rolling, not discarding
//		assertEquals("Client Model's status should be Rolling and fail", false, 
//				clientModel.canDiscardCards(0, new ResourceHand(0,0,0,0,0)));
//		
//		//Change status to discarding, still fail because bad resource hand
//		clientModel.getServerModel().getTurnTracker().setStatus("Discarding");
//		assertEquals("Client Model's status should be Discarding and ResourceHand all 0s and fail", false,
//				clientModel.canDiscardCards(0, new ResourceHand(0,0,0,0,0)));
//		
//		//Set resources and pass in a good resource hand
//		clientModel.getServerModel().getPlayers().get(0).setResources(new Resources(3,3,2,2,2));
//		assertEquals("Client Model's status should be Discarding and ResourceHand all 1's and pass", true,
//				clientModel.canDiscardCards(0, new ResourceHand(1,1,1,1,1)));
//		
//	}
//	
//	@Test
//	public void testCanBuildRoad(){
//		
//	}
//	
//	@Test
//	public void testCanBuildSettlement(){
//		
//	}
//	
//	@Test
//	public void testCanBuildCity(){
//		
//	}
//	
//	@Test
//	public void testCanOfferTrade(){
//		
//	}
//	
//	@Test
//	public void testCanMaritimeTrade(){
//		
//	}
//	
//	@Test
//	public void testFinishTurn(){
//		
//	}
	
	@Test
	public void testCanBuyDevCard(){
		clientModel.getServerModel().getPlayers().get(0).setResources(new Resources(0,1,1,0,1));
		DevCards cards = new DevCards();
		cards.updateCards(1, 1, 1, 1, 1);
		clientModel.getServerModel().setDeck(cards);
		
		//All preconditions are set so test should be successful
		assertEquals("All preconditions are set, should get success", true,
				clientModel.canBuyDevCard(0));
		
		//Player doesn't have enough resources to buy dev card
		clientModel.getServerModel().getPlayers().get(0).setResources(new Resources(0,0,0,0,0));
		assertEquals("Player doesn't have enough resources to buy dev card", false, 
				clientModel.canPlayYearOfPlenty(0, ResourceType.BRICK, ResourceType.WHEAT));
		
		//Bank doesn't have any dev cards left
		clientModel.getServerModel().getPlayers().get(0).setResources(new Resources(0,1,1,0,1));
		cards.updateCards(0, 0, 0, 0, 0);
		clientModel.getServerModel().setDeck(cards);
		assertEquals("Bank doesn't have any dev cards left", false, 
				clientModel.canPlayYearOfPlenty(0, ResourceType.BRICK, ResourceType.WHEAT));
	}
	
	@Test
	public void testCanPlayYearOfPlenty(){
		//Status should be playing, not rolling
				DevCards cards = new DevCards();
				cards.updateCards(0, 0, 0, 0, 1);
				clientModel.getServerModel().getTurnTracker().setStatus("Playing");
				clientModel.getServerModel().getTurnTracker().setCurrentTurn(0);
				clientModel.getServerModel().getPlayers().get(0).setPlayedDevCard(false);
				clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
				
				//All preconditions are set so test should be successful
				assertEquals("All preconditions are set, should get success", true,
						clientModel.canPlayYearOfPlenty(0, ResourceType.BRICK, ResourceType.WHEAT));
				
				clientModel.getServerModel().getTurnTracker().setStatus("Rolling");
				assertEquals("Client Model's status should be Rolling and fail", false, 
						clientModel.canPlayYearOfPlenty(0, ResourceType.BRICK, ResourceType.WHEAT));
				
				//Change status to Playing, still fail because not the player 1's turn
				clientModel.getServerModel().getTurnTracker().setStatus("Playing");
				assertEquals("Not player 1's turn, should fail", false,
						clientModel.canPlayYearOfPlenty(1, ResourceType.BRICK, ResourceType.WHEAT));
				
				//Now checking player 0, whose turn it is, but still fail because player 0 does not have a year of plenty card
				cards.updateCards(0, 0, 0, 0, 0);
				clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
				assertEquals("Client Model's status should be Playing and ResourceHand all 0s and fail", false,
						clientModel.canPlayYearOfPlenty(0, ResourceType.BRICK, ResourceType.WHEAT));
				
				//Player 0 now has year of plenty card but still fail because player 0 flag for already played devCard is set.
				cards.updateCards(1, 0, 0, 0, 0);
				clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
				clientModel.getServerModel().getPlayers().get(0).setPlayedDevCard(true);
				assertEquals("Player already played a dev card this turn, fail", false,
						clientModel.canPlayYearOfPlenty(0, ResourceType.BRICK, ResourceType.WHEAT));
				
				//Bank is out of brick and wheat resource 
				int brick = clientModel.getServerModel().getBank().brick;
				int wheat = clientModel.getServerModel().getBank().wheat;
				clientModel.getServerModel().getBank().brick = 0;
				clientModel.getServerModel().getBank().wheat = 0;
				assertEquals("Bank is out of brick and wheat resource, shoulf fail", false,
						clientModel.canPlayYearOfPlenty(0, ResourceType.BRICK, ResourceType.WHEAT));
				
				clientModel.getServerModel().getBank().brick = brick;
				clientModel.getServerModel().getBank().wheat = wheat;
	}
	
	@Test
	public void testCanPlayRoadBuilding(){
		
		EdgeLocation spot1 = new EdgeLocation(new HexLocation(-1,2), EdgeDirection.SouthEast);
		EdgeLocation spot2 = new EdgeLocation(new HexLocation(-1,2), EdgeDirection.South);
		clientModel.getServerModel().getTurnTracker().setStatus("Playing");
		clientModel.getServerModel().getTurnTracker().setCurrentTurn(0);
		clientModel.getServerModel().getPlayers().get(0).setPlayedDevCard(false);
		clientModel.getServerModel().getPlayers().get(0).setRoads(2);
		DevCards cards = new DevCards();
		cards.updateCards(0, 0, 1, 0, 0);
		clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
		
		
		//All preconditions are set so test should be successful
		//This also tests whether the second road can be placed according to where the first spot is specified.
		assertEquals("All preconditions for canPlaySoldier are set so test should be successful", true,
				clientModel.canPlayRoadBuilding(0, spot1, spot2));
		
		//Checks when one spot is valid and the other is not
		spot1 = new EdgeLocation(new HexLocation(0,0), EdgeDirection.SouthEast);
		spot2 = new EdgeLocation(new HexLocation(0,1), EdgeDirection.SouthWest);
		assertEquals("Checks when one spot is valid and the other is not, willfail", false, 
				clientModel.canPlayRoadBuilding(0, spot1, spot2));
		
		//Checks when one spot is on water
		spot1 = new EdgeLocation(new HexLocation(-3,0), EdgeDirection.SouthEast);
		spot2 = new EdgeLocation(new HexLocation(0,1), EdgeDirection.SouthWest);
		assertEquals("Checks when one spot is on water, willfail", false, 
				clientModel.canPlayRoadBuilding(0, spot1, spot2));
		
		//Checks if the player has less than two roads available.
		clientModel.getServerModel().getPlayers().get(0).setRoads(0);
		assertEquals("Checks when one spot is on water, willfail", false, 
				clientModel.canPlayRoadBuilding(0, spot1, spot2));
		
		//Status is not playing
		clientModel.getServerModel().getPlayers().get(0).setRoads(2);
		spot1 = new EdgeLocation(new HexLocation(-1,2), EdgeDirection.SouthEast);
		spot2 = new EdgeLocation(new HexLocation(-1,2), EdgeDirection.South);
		clientModel.getServerModel().getTurnTracker().setStatus("Rolling");
		assertEquals("Status is not playing, willfail", false, 
				clientModel.canPlayRoadBuilding(0, spot1, spot2));
		
		//Change status to discarding, still fail because not the player 2's turn
		clientModel.getServerModel().getTurnTracker().setStatus("Playing");
		assertEquals("Change status to discarding, still fail because not the player 2's turn", false,
				clientModel.canPlayRoadBuilding(2, spot1, spot2));
		
		//Player does not have a RoadBuilding card
		cards.updateCards(0, 0, 0, 0, 0);
		clientModel.getServerModel().getPlayers().get(1).setBrick(1);
		clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
		assertEquals("Player does not have a roadbuilding card to play, will fail", false,
				clientModel.canPlayRoadBuilding(0, spot1, spot2));
		
		cards.updateCards(0, 0, 0, 1, 0);
		clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
		clientModel.getServerModel().getPlayers().get(0).setPlayedDevCard(true);
		assertEquals("Player has already played a dev card this turn, will fail", false,
				clientModel.canPlayRoadBuilding(0, spot1, spot2));
	}
	
	@Test
	public void testCanPlaySoldier(){
		DevCards cards = new DevCards();
		cards.updateCards(0, 0, 0, 1, 0);
		HexLocation robberLoc = new HexLocation(0,0);
		clientModel.getServerModel().getTurnTracker().setStatus("Playing");
		clientModel.getServerModel().getTurnTracker().setCurrentTurn(0);
		clientModel.getServerModel().getPlayers().get(0).setPlayedDevCard(false);
		clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
		clientModel.getServerModel().getMap().getRobber().setLocation(robberLoc);
		clientModel.getServerModel().getPlayers().get(1).setBrick(1);
		
		
		//All preconditions are set so test should be successful
		assertEquals("All preconditions for canPlaySoldier are set so test should be successful", true,
				clientModel.canPlaySoldier(0, new HexLocation(1,1), 1));
		
		//Status is not playing
		clientModel.getServerModel().getTurnTracker().setStatus("Rolling");
		assertEquals("Status is not playing, willfail", false, 
				clientModel.canPlaySoldier(0, new HexLocation(1,1), 1));
		
		//Change status to discarding, still fail because not the player 2's turn
		clientModel.getServerModel().getTurnTracker().setStatus("Playing");
		assertEquals("Change status to discarding, still fail because not the player 2's turn", false,
				clientModel.canPlaySoldier(2, new HexLocation(1,1), 1));
		
		//Player does not have a soldier card
		cards.updateCards(0, 0, 0, 0, 0);
		clientModel.getServerModel().getPlayers().get(1).setBrick(1);
		clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
		assertEquals("Player does not have a soilder card to play, will fail", false,
				clientModel.canPlaySoldier(0, new HexLocation(1,1), 1));

		//Player has already played a dev card
		cards.updateCards(0, 0, 0, 1, 0);
		clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
		clientModel.getServerModel().getPlayers().get(0).setPlayedDevCard(true);
		assertEquals("Player has already played a dev card this turn, will fail", false,
				clientModel.canPlaySoldier(0, new HexLocation(1,1), 1));
		
		//Robber can't stay in the same place
		clientModel.getServerModel().getPlayers().get(0).setPlayedDevCard(false);
		assertEquals("When playing soldier card, trying to place robber in location, will fail", false,
				clientModel.canPlaySoldier(0, new HexLocation(0,0), 1));
		
		//Victim doesn't have resource to steal
		clientModel.getServerModel().getPlayers().get(1).setResources(new Resources(0,0,0,0,0));
		assertEquals("Victim doesn't have resource to steal, will fail", false,
				clientModel.canPlaySoldier(0, new HexLocation(1,1), 1));
	}
	
	@Test
	public void testCanPlayMonopoly(){
		//Status should be playing, not rolling
		DevCards cards = new DevCards();
		cards.updateCards(1, 0, 0, 0, 0);
		clientModel.getServerModel().getTurnTracker().setStatus("rolling");
		clientModel.getServerModel().getTurnTracker().setCurrentTurn(0);
		clientModel.getServerModel().getPlayers().get(0).setPlayedDevCard(false);
		clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
		
		assertEquals("Client Model's status should be Rolling and fail", false, 
				clientModel.canPlayMonopoly(0));
		
		//Change status to Playing, still fail because not the player 1's turn
		clientModel.getServerModel().getTurnTracker().setStatus("Playing");
		assertEquals("Change status to Playing, still fail because not the player 1's turn", false,
				clientModel.canPlayMonopoly(1));
		
		//Now checking player 0, whose turn it is, but still fail because player 0 does not have a monopoly card
		cards.updateCards(0, 0, 0, 0, 0);
		clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
		assertEquals("Now checking player 0, whose turn it is, but still fail because player 0 does not have a monopoly card", false,
				clientModel.canPlayMonopoly(0));
		
		//Player 0 now has monopoly card but still fail because player 0 flag for already played devCard is set.
		cards.updateCards(1, 0, 0, 0, 0);
		clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
		clientModel.getServerModel().getPlayers().get(0).setPlayedDevCard(true);
		assertEquals("Player 0 now has monopoly card but still fail because player 0 flag for already played devCard is set.", false,
				clientModel.canPlayMonopoly(0));
		
		//All preconditions are set so test should be successful
		clientModel.getServerModel().getPlayers().get(0).setPlayedDevCard(false);
		assertEquals("All preconditions are set so test should be successful", true,
				clientModel.canPlayMonopoly(0));
	}
	
	@Test
	public void testCanPlayMonument(){
		//Status should be playing, not rolling
		DevCards cards = new DevCards();
		cards.updateCards(0, 1, 0, 0, 0);
		clientModel.getServerModel().getTurnTracker().setStatus("rolling");
		clientModel.getServerModel().getTurnTracker().setCurrentTurn(0);
		clientModel.getServerModel().getPlayers().get(0).setPlayedDevCard(false);
		clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
		
		assertEquals("Client Model's status should be Rolling and fail", false, 
				clientModel.canPlayMonument(0));
		
		//Change status to discarding, still fail because not the player 1's turn
		clientModel.getServerModel().getTurnTracker().setStatus("Playing");
		assertEquals("Change status to discarding, still fail because not the player 1's turn", false,
				clientModel.canPlayMonument(1));
		
		//Now checking player 0, whose turn it is, but still fail because player 0 does not have a monument card
		cards.updateCards(0, 0, 0, 0, 0);
		clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
		assertEquals("Now checking player 0, whose turn it is, but still fail because player 0 does not have a monument card", false,
				clientModel.canPlayMonument(0));
		
		//Player 0 now has monument card but still fail because player 0 flag for already played devCard is set.
		cards.updateCards(0, 1, 0, 0, 0);
		clientModel.getServerModel().getPlayers().get(0).setOldDevCards(cards);
		clientModel.getServerModel().getPlayers().get(0).setPlayedDevCard(true);
		assertEquals("Player 0 now has monument card but still fail because player 0 flag for already played devCard is set.", false,
				clientModel.canPlayMonument(0));
		
		//All preconditions are set so test should be successful
		clientModel.getServerModel().getPlayers().get(0).setPlayedDevCard(false);
		assertEquals("All preconditions are set so test should be successful", true,
				clientModel.canPlayMonument(0));
	}
	
	public static void main(String[] args) 
	{
		String[] testClasses = new String[] 
		{
				"client.TaylorJUnit"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}
	
	final String gameJSON = "{\n" + 
			"  \"deck\": {\n" + 
			"    \"yearOfPlenty\": 2,\n" + 
			"    \"monopoly\": 2,\n" + 
			"    \"soldier\": 14,\n" + 
			"    \"roadBuilding\": 2,\n" + 
			"    \"monument\": 5\n" + 
			"  },\n" + 
			"  \"map\": {\n" + 
			"    \"hexes\": [\n" + 
			"      {\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 0,\n" + 
			"          \"y\": -2\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"brick\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 1,\n" + 
			"          \"y\": -2\n" + 
			"        },\n" + 
			"        \"number\": 4\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"wood\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 2,\n" + 
			"          \"y\": -2\n" + 
			"        },\n" + 
			"        \"number\": 11\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"brick\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": -1,\n" + 
			"          \"y\": -1\n" + 
			"        },\n" + 
			"        \"number\": 8\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"wood\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 0,\n" + 
			"          \"y\": -1\n" + 
			"        },\n" + 
			"        \"number\": 3\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"ore\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 1,\n" + 
			"          \"y\": -1\n" + 
			"        },\n" + 
			"        \"number\": 9\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"sheep\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 2,\n" + 
			"          \"y\": -1\n" + 
			"        },\n" + 
			"        \"number\": 12\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"ore\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": -2,\n" + 
			"          \"y\": 0\n" + 
			"        },\n" + 
			"        \"number\": 5\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"sheep\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": -1,\n" + 
			"          \"y\": 0\n" + 
			"        },\n" + 
			"        \"number\": 10\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"wheat\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 0,\n" + 
			"          \"y\": 0\n" + 
			"        },\n" + 
			"        \"number\": 11\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"brick\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 1,\n" + 
			"          \"y\": 0\n" + 
			"        },\n" + 
			"        \"number\": 5\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"wheat\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 2,\n" + 
			"          \"y\": 0\n" + 
			"        },\n" + 
			"        \"number\": 6\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"wheat\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": -2,\n" + 
			"          \"y\": 1\n" + 
			"        },\n" + 
			"        \"number\": 2\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"sheep\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": -1,\n" + 
			"          \"y\": 1\n" + 
			"        },\n" + 
			"        \"number\": 9\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"wood\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 0,\n" + 
			"          \"y\": 1\n" + 
			"        },\n" + 
			"        \"number\": 4\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"sheep\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 1,\n" + 
			"          \"y\": 1\n" + 
			"        },\n" + 
			"        \"number\": 10\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"wood\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": -2,\n" + 
			"          \"y\": 2\n" + 
			"        },\n" + 
			"        \"number\": 6\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"ore\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": -1,\n" + 
			"          \"y\": 2\n" + 
			"        },\n" + 
			"        \"number\": 3\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"resource\": \"wheat\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 0,\n" + 
			"          \"y\": 2\n" + 
			"        },\n" + 
			"        \"number\": 8\n" + 
			"      }\n" + 
			"    ],\n" + 
			"    \"roads\": [\n" + 
			"      {\n" + 
			"        \"owner\": 2,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"S\",\n" + 
			"          \"x\": 1,\n" + 
			"          \"y\": -1\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"owner\": 3,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"SW\",\n" + 
			"          \"x\": 2,\n" + 
			"          \"y\": -2\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"owner\": 0,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"S\",\n" + 
			"          \"x\": 0,\n" + 
			"          \"y\": 1\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"owner\": 1,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"SW\",\n" + 
			"          \"x\": -2,\n" + 
			"          \"y\": 1\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"owner\": 2,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"S\",\n" + 
			"          \"x\": 0,\n" + 
			"          \"y\": 0\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"owner\": 0,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"SW\",\n" + 
			"          \"x\": 2,\n" + 
			"          \"y\": 0\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"owner\": 1,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"S\",\n" + 
			"          \"x\": -1,\n" + 
			"          \"y\": -1\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"owner\": 3,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"SW\",\n" + 
			"          \"x\": -1,\n" + 
			"          \"y\": 1\n" + 
			"        }\n" + 
			"      }\n" + 
			"    ],\n" + 
			"    \"cities\": [],\n" + 
			"    \"settlements\": [\n" + 
			"      {\n" + 
			"        \"owner\": 3,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"SE\",\n" + 
			"          \"x\": 1,\n" + 
			"          \"y\": -2\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"owner\": 2,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"SW\",\n" + 
			"          \"x\": 0,\n" + 
			"          \"y\": 0\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"owner\": 2,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"SW\",\n" + 
			"          \"x\": 1,\n" + 
			"          \"y\": -1\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"owner\": 1,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"SW\",\n" + 
			"          \"x\": -1,\n" + 
			"          \"y\": -1\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"owner\": 0,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"SE\",\n" + 
			"          \"x\": 0,\n" + 
			"          \"y\": 1\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"owner\": 1,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"SW\",\n" + 
			"          \"x\": -2,\n" + 
			"          \"y\": 1\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"owner\": 0,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"SW\",\n" + 
			"          \"x\": 2,\n" + 
			"          \"y\": 0\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"owner\": 3,\n" + 
			"        \"location\": {\n" + 
			"          \"direction\": \"SW\",\n" + 
			"          \"x\": -1,\n" + 
			"          \"y\": 1\n" + 
			"        }\n" + 
			"      }\n" + 
			"    ],\n" + 
			"    \"radius\": 3,\n" + 
			"    \"ports\": [\n" + 
			"      {\n" + 
			"        \"ratio\": 2,\n" + 
			"        \"resource\": \"sheep\",\n" + 
			"        \"direction\": \"NW\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 3,\n" + 
			"          \"y\": -1\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"ratio\": 2,\n" + 
			"        \"resource\": \"wheat\",\n" + 
			"        \"direction\": \"S\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": -1,\n" + 
			"          \"y\": -2\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"ratio\": 2,\n" + 
			"        \"resource\": \"wood\",\n" + 
			"        \"direction\": \"NE\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": -3,\n" + 
			"          \"y\": 2\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"ratio\": 3,\n" + 
			"        \"direction\": \"NW\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 2,\n" + 
			"          \"y\": 1\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"ratio\": 3,\n" + 
			"        \"direction\": \"SW\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 3,\n" + 
			"          \"y\": -3\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"ratio\": 3,\n" + 
			"        \"direction\": \"SE\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": -3,\n" + 
			"          \"y\": 0\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"ratio\": 2,\n" + 
			"        \"resource\": \"brick\",\n" + 
			"        \"direction\": \"NE\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": -2,\n" + 
			"          \"y\": 3\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"ratio\": 3,\n" + 
			"        \"direction\": \"N\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 0,\n" + 
			"          \"y\": 3\n" + 
			"        }\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"ratio\": 2,\n" + 
			"        \"resource\": \"ore\",\n" + 
			"        \"direction\": \"S\",\n" + 
			"        \"location\": {\n" + 
			"          \"x\": 1,\n" + 
			"          \"y\": -3\n" + 
			"        }\n" + 
			"      }\n" + 
			"    ],\n" + 
			"    \"robber\": {\n" + 
			"      \"x\": 0,\n" + 
			"      \"y\": -2\n" + 
			"    }\n" + 
			"  },\n" + 
			"  \"players\": [\n" + 
			"    {\n" + 
			"      \"resources\": {\n" + 
			"        \"brick\": 0,\n" + 
			"        \"wood\": 1,\n" + 
			"        \"sheep\": 1,\n" + 
			"        \"wheat\": 1,\n" + 
			"        \"ore\": 0\n" + 
			"      },\n" + 
			"      \"oldDevCards\": {\n" + 
			"        \"yearOfPlenty\": 0,\n" + 
			"        \"monopoly\": 0,\n" + 
			"        \"soldier\": 0,\n" + 
			"        \"roadBuilding\": 0,\n" + 
			"        \"monument\": 0\n" + 
			"      },\n" + 
			"      \"newDevCards\": {\n" + 
			"        \"yearOfPlenty\": 0,\n" + 
			"        \"monopoly\": 0,\n" + 
			"        \"soldier\": 0,\n" + 
			"        \"roadBuilding\": 0,\n" + 
			"        \"monument\": 0\n" + 
			"      },\n" + 
			"      \"roads\": 13,\n" + 
			"      \"cities\": 4,\n" + 
			"      \"settlements\": 3,\n" + 
			"      \"soldiers\": 0,\n" + 
			"      \"victoryPoints\": 2,\n" + 
			"      \"monuments\": 0,\n" + 
			"      \"playedDevCard\": false,\n" + 
			"      \"discarded\": false,\n" + 
			"      \"playerID\": 0,\n" + 
			"      \"playerIndex\": 0,\n" + 
			"      \"name\": \"Sam\",\n" + 
			"      \"color\": \"orange\"\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"resources\": {\n" + 
			"        \"brick\": 1,\n" + 
			"        \"wood\": 0,\n" + 
			"        \"sheep\": 1,\n" + 
			"        \"wheat\": 0,\n" + 
			"        \"ore\": 1\n" + 
			"      },\n" + 
			"      \"oldDevCards\": {\n" + 
			"        \"yearOfPlenty\": 0,\n" + 
			"        \"monopoly\": 0,\n" + 
			"        \"soldier\": 0,\n" + 
			"        \"roadBuilding\": 0,\n" + 
			"        \"monument\": 0\n" + 
			"      },\n" + 
			"      \"newDevCards\": {\n" + 
			"        \"yearOfPlenty\": 0,\n" + 
			"        \"monopoly\": 0,\n" + 
			"        \"soldier\": 0,\n" + 
			"        \"roadBuilding\": 0,\n" + 
			"        \"monument\": 0\n" + 
			"      },\n" + 
			"      \"roads\": 13,\n" + 
			"      \"cities\": 4,\n" + 
			"      \"settlements\": 3,\n" + 
			"      \"soldiers\": 0,\n" + 
			"      \"victoryPoints\": 2,\n" + 
			"      \"monuments\": 0,\n" + 
			"      \"playedDevCard\": false,\n" + 
			"      \"discarded\": false,\n" + 
			"      \"playerID\": 1,\n" + 
			"      \"playerIndex\": 1,\n" + 
			"      \"name\": \"Brooke\",\n" + 
			"      \"color\": \"red\"\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"resources\": {\n" + 
			"        \"brick\": 0,\n" + 
			"        \"wood\": 1,\n" + 
			"        \"sheep\": 1,\n" + 
			"        \"wheat\": 1,\n" + 
			"        \"ore\": 0\n" + 
			"      },\n" + 
			"      \"oldDevCards\": {\n" + 
			"        \"yearOfPlenty\": 0,\n" + 
			"        \"monopoly\": 0,\n" + 
			"        \"soldier\": 0,\n" + 
			"        \"roadBuilding\": 0,\n" + 
			"        \"monument\": 0\n" + 
			"      },\n" + 
			"      \"newDevCards\": {\n" + 
			"        \"yearOfPlenty\": 0,\n" + 
			"        \"monopoly\": 0,\n" + 
			"        \"soldier\": 0,\n" + 
			"        \"roadBuilding\": 0,\n" + 
			"        \"monument\": 0\n" + 
			"      },\n" + 
			"      \"roads\": 13,\n" + 
			"      \"cities\": 4,\n" + 
			"      \"settlements\": 3,\n" + 
			"      \"soldiers\": 0,\n" + 
			"      \"victoryPoints\": 2,\n" + 
			"      \"monuments\": 0,\n" + 
			"      \"playedDevCard\": false,\n" + 
			"      \"discarded\": false,\n" + 
			"      \"playerID\": 10,\n" + 
			"      \"playerIndex\": 2,\n" + 
			"      \"name\": \"Pete\",\n" + 
			"      \"color\": \"red\"\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"resources\": {\n" + 
			"        \"brick\": 0,\n" + 
			"        \"wood\": 1,\n" + 
			"        \"sheep\": 1,\n" + 
			"        \"wheat\": 0,\n" + 
			"        \"ore\": 1\n" + 
			"      },\n" + 
			"      \"oldDevCards\": {\n" + 
			"        \"yearOfPlenty\": 0,\n" + 
			"        \"monopoly\": 0,\n" + 
			"        \"soldier\": 0,\n" + 
			"        \"roadBuilding\": 0,\n" + 
			"        \"monument\": 0\n" + 
			"      },\n" + 
			"      \"newDevCards\": {\n" + 
			"        \"yearOfPlenty\": 0,\n" + 
			"        \"monopoly\": 0,\n" + 
			"        \"soldier\": 0,\n" + 
			"        \"roadBuilding\": 0,\n" + 
			"        \"monument\": 0\n" + 
			"      },\n" + 
			"      \"roads\": 13,\n" + 
			"      \"cities\": 4,\n" + 
			"      \"settlements\": 3,\n" + 
			"      \"soldiers\": 0,\n" + 
			"      \"victoryPoints\": 2,\n" + 
			"      \"monuments\": 0,\n" + 
			"      \"playedDevCard\": false,\n" + 
			"      \"discarded\": false,\n" + 
			"      \"playerID\": 11,\n" + 
			"      \"playerIndex\": 3,\n" + 
			"      \"name\": \"Mark\",\n" + 
			"      \"color\": \"green\"\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"log\": {\n" + 
			"    \"lines\": [\n" + 
			"      {\n" + 
			"        \"source\": \"Sam\",\n" + 
			"        \"message\": \"Sam built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Sam\",\n" + 
			"        \"message\": \"Sam built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Sam\",\n" + 
			"        \"message\": \"Sam's turn just ended\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Brooke\",\n" + 
			"        \"message\": \"Brooke built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Brooke\",\n" + 
			"        \"message\": \"Brooke built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Brooke\",\n" + 
			"        \"message\": \"Brooke's turn just ended\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Pete\",\n" + 
			"        \"message\": \"Pete built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Pete\",\n" + 
			"        \"message\": \"Pete built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Pete\",\n" + 
			"        \"message\": \"Pete's turn just ended\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Mark\",\n" + 
			"        \"message\": \"Mark built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Mark\",\n" + 
			"        \"message\": \"Mark built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Mark\",\n" + 
			"        \"message\": \"Mark's turn just ended\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Mark\",\n" + 
			"        \"message\": \"Mark built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Mark\",\n" + 
			"        \"message\": \"Mark built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Mark\",\n" + 
			"        \"message\": \"Mark's turn just ended\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Pete\",\n" + 
			"        \"message\": \"Pete built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Pete\",\n" + 
			"        \"message\": \"Pete built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Pete\",\n" + 
			"        \"message\": \"Pete's turn just ended\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Brooke\",\n" + 
			"        \"message\": \"Brooke built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Brooke\",\n" + 
			"        \"message\": \"Brooke built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Brooke\",\n" + 
			"        \"message\": \"Brooke's turn just ended\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Sam\",\n" + 
			"        \"message\": \"Sam built a road\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Sam\",\n" + 
			"        \"message\": \"Sam built a settlement\"\n" + 
			"      },\n" + 
			"      {\n" + 
			"        \"source\": \"Sam\",\n" + 
			"        \"message\": \"Sam's turn just ended\"\n" + 
			"      }\n" + 
			"    ]\n" + 
			"  },\n" + 
			"  \"chat\": {\n" + 
			"    \"lines\": []\n" + 
			"  },\n" + 
			"  \"bank\": {\n" + 
			"    \"brick\": 23,\n" + 
			"    \"wood\": 21,\n" + 
			"    \"sheep\": 20,\n" + 
			"    \"wheat\": 22,\n" + 
			"    \"ore\": 22\n" + 
			"  },\n" + 
			"  \"turnTracker\": {\n" + 
			"    \"status\": \"Rolling\",\n" + 
			"    \"currentTurn\": 0,\n" + 
			"    \"longestRoad\": -1,\n" + 
			"    \"largestArmy\": -1\n" + 
			"  },\n" + 
			"  \"winner\": -1,\n" + 
			"  \"version\": 0\n" + 
			"}";
}


