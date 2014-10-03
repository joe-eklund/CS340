package client.model.junit;

import static org.junit.Assert.*;

import org.junit.*;

import proxy.*;
import shared.definitions.*;
import shared.locations.*;
import client.model.*;

public class ClientModelUnitTest {
	private ClientModel clientModel;
	
	@Before 
	public void setUp() { 
		//Creates a ServerModel object from a test JSON string. Then creates a ClientModel from ServerModel.
		ITranslator translator = new TranslatorJSON();
		clientModel = new ClientModel((ServerModel)translator.translateFrom(gameJSON, ServerModel.class));
	}
	
	@Test
	public void testUpdateServerModel(){
		ITranslator translator = new TranslatorJSON();
		clientModel = new ClientModel((ServerModel)translator.translateFrom(gameJSON, ServerModel.class));
		DevCards deck = new DevCards();
		assertEquals("Server Model should have deck with 2 year of plenty", 2,
				clientModel.getServerModel().getDeck().getYearOfPlenty());
		assertEquals("Server Model should have deck with 2 monopoly", 2, 
				clientModel.getServerModel().getDeck().getMonopoly());
		assertEquals("Server Model should have deck with 14 soldier", 14,
				clientModel.getServerModel().getDeck().getSoldier());
		assertEquals("Server Model should have deck with 2 roadBuilding", 2,
				clientModel.getServerModel().getDeck().getRoadBuilding());
		assertEquals("Server Model should have deck with 5 monument", 5,
				clientModel.getServerModel().getDeck().getMonument());
	}
	
	@Test
	public void testCanAcceptTrade(){
		//Trade offer should be null
		assertEquals("Client Model should not have a trade offer and fail", false,
				clientModel.canAcceptTrade());
		
		//Create invalid trade offer from player 0 to player 1 for 1 wood
		clientModel.getServerModel().setTradeOffer(new TradeOffer(0,1,0,0,0,0,1));
		assertEquals("Client Model should not have a valid trade offer and fail", false,
				clientModel.canAcceptTrade());
		
		//Create valid trade offer from player 0 to player 1 for 1 brick
		clientModel.getServerModel().getPlayers().get(1).setResources(new Resources(2,2,2,2,2));
		clientModel.getServerModel().setTradeOffer(new TradeOffer(0,1,1,0,0,0,0));
		assertEquals("Client Model should have a valid trade offer and pass", true, 
				clientModel.canAcceptTrade());
	}
	
	@Test
	public void testCanDiscardCards(){
		//Status should be rolling, not discarding
		assertEquals("Client Model's status should be Rolling and fail", false, 
				clientModel.canDiscardCards(0, new ResourceHand(0,0,0,0,0)));
		
		//Change status to discarding, still fail because bad resource hand
		clientModel.getServerModel().getTurnTracker().setStatus("Discarding");
		assertEquals("Client Model's status should be Discarding and ResourceHand all 0s and fail", false,
				clientModel.canDiscardCards(0, new ResourceHand(0,0,0,0,0)));
		
		//Set resources and pass in a good resource hand
		clientModel.getServerModel().getPlayers().get(0).setResources(new Resources(2,2,2,2,2));
		assertEquals("Client Model's status should be Discarding and ResourceHand all 1's and pass", true,
				clientModel.canDiscardCards(0, new ResourceHand(1,1,1,1,1)));
	}
	
	@Test
	public void testCanBuildRoad(){
		//Try bad turn
		clientModel.getServerModel().getTurnTracker().setCurrentTurn(1);
		EdgeLocation testEdge = new EdgeLocation(new HexLocation(0, 0), EdgeDirection.SouthEast);
		assertEquals("Client Model turn should be 0 annd fail", false, 
				clientModel.canBuildRoad(0, testEdge));
		
		//Try where you are not next to a road
		testEdge.setHexLoc(new HexLocation(0,1));
		testEdge.setDir(EdgeDirection.NorthWest);
		assertEquals("Trying to build on an invalid edge where you have no neighboring roads should fail", false,
				clientModel.canBuildRoad(0, testEdge));
		
		//Try next to a road that you don't own
		testEdge.setHexLoc(new HexLocation(1,-1));
		testEdge.setDir(EdgeDirection.SouthEast);
		assertEquals("Trying to build on an invalid edge where there is a road you don't own should fail", false,
				clientModel.canBuildRoad(0, testEdge));
		
		//Try water edge
		clientModel.getServerModel().getTurnTracker().setCurrentTurn(0);
		clientModel.getServerModel().getPlayers().get(0).setResources(new Resources(2,2,2,2,2));
		clientModel.getServerModel().getPlayers().get(0).setRoads(15);
		testEdge.setHexLoc(new HexLocation(0,-3));
		assertEquals("Trying to build on water edge and should fail", false,
				clientModel.canBuildRoad(0, testEdge));
		
		//Try valid edge
		testEdge.setHexLoc(new HexLocation(0,1));
		testEdge.setDir(EdgeDirection.SouthEast);
		assertEquals("Trying to build on valid edge and should pass", true,
				clientModel.canBuildRoad(0, testEdge));
	}
	
	@Test
	public void testCanBuildSettlement(){
		
	}
	
	@Test
	public void testCanBuildCity(){
		//Try building a valid city
		clientModel.getServerModel().getPlayers().get(0).setResources(new Resources(5,5,5,5,5));
		clientModel.getServerModel().getPlayers().get(0).setCities(2);
		assertEquals("Trying to build on a valid vertex and should pass", true,
				clientModel.canBuildCity(0, new VertexLocation(new HexLocation(0,1), VertexDirection.SouthEast)));
		
		//Try building where there is no settlement
		assertEquals("Trying to build on an invalid vertex and should fail", false,
				clientModel.canBuildCity(0, new VertexLocation(new HexLocation(1,1), VertexDirection.NorthEast)));
		
		//Try building where there is a settlement but you don't own it
		assertEquals("Trying to build on an invalid vertex and should fail", false,
				clientModel.canBuildCity(0, new VertexLocation(new HexLocation(1,-2), VertexDirection.SouthEast)));
	}
	
	@Test
	public void testCanOfferTrade(){
		//Test valid trade offer
		clientModel.getServerModel().getPlayers().get(0).setResources(new Resources(5,5,5,5,5));
		assertEquals("Trying to offer a valid trade and should pass", true,
				clientModel.canOfferTrade(0, new ResourceHand(1,1,1,1,1)));
		
		//Test trade offer without having enough resources
		clientModel.getServerModel().getPlayers().get(0).setResources(new Resources(0,0,0,0,0));
		assertEquals("Trying to offer an invalid trade don't have enough resources and should fail", false,
				clientModel.canOfferTrade(0, new ResourceHand(1,0,0,0,0)));
		
		//Test trade offer of all 0's resource hand but have resources
		clientModel.getServerModel().getPlayers().get(0).setResources(new Resources(5,5,5,5,5));
		assertEquals("Trying to offer an invalid trade with resource hand of 0's should fail", false,
				clientModel.canOfferTrade(0, new ResourceHand(0,0,0,0,0)));
		
		//Test trade offer of all -'s resource hand but have resources
		clientModel.getServerModel().getPlayers().get(0).setResources(new Resources(5,5,5,5,5));
		assertEquals("Trying to offer an invalid trade with resource hand of -'s should fail", false,
				clientModel.canOfferTrade(0, new ResourceHand(-1,-2,-3,-4,-5)));
	}
	
	@Test
	public void testCanMaritimeTrade(){
		//Test valid maritime trade
		clientModel.getServerModel().getPlayers().get(0).setResources(new Resources(5,5,5,5,5));
		assertEquals("Trying valid maritime trade and should pass", true,
				clientModel.canMaritimeTrade(0, 2, ResourceType.BRICK));
		
		//Test invalid maritime trade with not having enough resources
		clientModel.getServerModel().getPlayers().get(0).setResources(new Resources(0,0,0,0,0));
		assertEquals("Trying valid maritime trade and should pass", false,
				clientModel.canMaritimeTrade(0, 2, ResourceType.ORE));
	}
	
	@Test
	public void testFinishTurn(){
		//Test valid can finish turn by having Playing status
		clientModel.getServerModel().getTurnTracker().setStatus("Playing");
		assertEquals("Trying valid can finish turn with Playing status and should pass",true,
				clientModel.canFinishTurn());
		
		//Test invalid can finish turn by having Rolling status
		clientModel.getServerModel().getTurnTracker().setStatus("Rolling");
		assertEquals("Trying invalid can finish turn with Rolling status and should fail",false,
				clientModel.canFinishTurn());
	}
	
	@Test
	public void testCanBuyDevCard(){
		
	}
	
	@Test
	public void testCanPlayYearOfPlenty(){
		
	}
	
	@Test
	public void testCanPlayRoadBuilding(){
		
	}
	
	@Test
	public void testCanPlaySoldier(){
		
	}
	
	@Test
	public void testCanPlayMonopoly(){
		
	}
	
	@Test
	public void testCanPlayMonument(){
		
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
