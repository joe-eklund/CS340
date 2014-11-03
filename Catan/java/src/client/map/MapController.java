package client.map;

import java.util.*;

import shared.definitions.*;
import shared.locations.*;
import client.base.*;
import client.data.*;
import client.main.Catan;
import client.model.City;
import client.model.ClientModel;
import client.model.Player;
import client.model.Port;
import client.model.Road;
import client.model.Settlement;
import client.model.interfaces.IHex;
import client.presenter.IPresenter;
import client.resources.ResourceBarElement;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController, Observer {
	
	private IRobView robView;
	private static IPresenter presenter;
	private boolean haveInitializedHexes;
	private boolean playingRoadBuildingCard;
	private int numRoadsPlaced;
	private EdgeLocation edgeLoc1;
	private EdgeLocation edgeLoc2;
	private HexLocation robberSpot;
	
	public MapController(IMapView view, IRobView robView) {
		
		super(view);
		
		presenter = Catan.getPresenter();
		presenter.addObserverToModel(this);
		
		setRobView(robView);
		
		initFromModel();
		
		haveInitializedHexes = false;
		playingRoadBuildingCard = false;
		robberSpot=null;
	}
	
	public IMapView getView() {
		
		return (IMapView)super.getView();
	}
	
	private IRobView getRobView() {
		return robView;
	}
	private void setRobView(IRobView robView) {
		this.robView = robView;
	}
	
	protected void initFromModel() {
		Random rand = new Random();

		for (int x = 0; x <= 3; ++x) {
			
			int maxY = 3 - x;			
			for (int y = -3; y <= maxY; ++y) {				
				int r = rand.nextInt(HexType.values().length);
				HexType hexType = HexType.values()[r];
				HexLocation hexLoc = new HexLocation(x, y);
				getView().addHex(hexLoc, hexType);
			}
			
			if (x != 0) {
				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) {
					int r = rand.nextInt(HexType.values().length);
					HexType hexType = HexType.values()[r];
					HexLocation hexLoc = new HexLocation(-x, y);
					getView().addHex(hexLoc, hexType);
				}
			}
		}
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		if (!playingRoadBuildingCard) {
			if (presenter.getState().getStatus().equals("FirstRound") || presenter.getState().getStatus().equals("SecondRound")) {
				return presenter.canPlaceRoad(edgeLoc, true);
			}
			else {
				return presenter.canPlaceRoad(edgeLoc, false);
			}
		}
		else {
			if (numRoadsPlaced == 0) {
				return presenter.canPlaceRoad(edgeLoc, true);
			}
			else {
				if (edgeLoc.getHexLoc().getX() == 1 && edgeLoc.getHexLoc().getY() == -2)
					return presenter.getClientModel().canPlayRoadBuilding(presenter.getPlayerInfo().getIndex(), edgeLoc1, edgeLoc);
				else
					return presenter.getClientModel().canPlayRoadBuilding(presenter.getPlayerInfo().getIndex(), edgeLoc1, edgeLoc);
			}
		}
			
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
//		if ((vertLoc.getHexLoc().getX() == 1 && vertLoc.getHexLoc().getY() == 1) || (vertLoc.getHexLoc().getX() == 2 && vertLoc.getHexLoc().getY() == 0)) {
//			presenter.getClientModel().getServerModel().getMap().getRoads().add(new Road(presenter.getPlayerInfo().getIndex(), 1, 1, "North"));
//			presenter.getClientModel().getServerModel().getMap().getRoads().add(new Road(presenter.getPlayerInfo().getIndex(), 2,0, "NorthWest"));
			return presenter.canPlaceSettlement(vertLoc);
//		}
//		return false;
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
//		if ((vertLoc.getHexLoc().getX() == 2 && vertLoc.getHexLoc().getY() == 1)) {
			return presenter.canPlaceCity(vertLoc);
//		}
		//return false;
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		return presenter.canPlaceRobber(hexLoc);
	}

	public void placeRoad(EdgeLocation edgeLoc) {
		if (!playingRoadBuildingCard)
			presenter.buildRoad(edgeLoc);
		else {
			if (numRoadsPlaced == 0) {
				numRoadsPlaced++;
				edgeLoc1 = edgeLoc;
			} 
			else {
				presenter.playRoadBuildingCard(edgeLoc1, edgeLoc);
				numRoadsPlaced = 0;
				playingRoadBuildingCard = false;
			}
		}
	}

	public void placeSettlement(VertexLocation vertLoc) {
		presenter.buildSettlement(vertLoc);
//		getView().placeSettlement(vertLoc, CatanColor.ORANGE);
	}

	public void placeCity(VertexLocation vertLoc) {
		presenter.buildCity(vertLoc);
//		getView().placeCity(vertLoc, CatanColor.ORANGE);
	}

	public void placeRobber(HexLocation hexLoc) {
		//setRobView(new RobView());
		getRobView().setPlayers(presenter.getClientModel().getServerModel().getVictims(presenter.getPlayerInfo().getIndex(),hexLoc));
		getRobView().showModal();
		robberSpot=hexLoc;
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		getView().startDrop(pieceType, CatanColor.valueOf(presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex()).getColor().toUpperCase()), true);
	}
	
	public void cancelMove() {
		
	}
	
	public void playSoldierCard() {	
		if(presenter.isPlayersTurn()){
			getView().startDrop(PieceType.ROBBER, CatanColor.valueOf(presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex()).getColor().toUpperCase()), false);
		}
	}
	
	public void playRoadBuildingCard() {
		if(presenter.isPlayersTurn()){
			playingRoadBuildingCard = true;
			numRoadsPlaced = 0;
//			Player p=presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex());
//			p.setBrick(p.getBrick()+2);
//			p.setWood(p.getWood()+2);
			getView().roadBuildingPhase(CatanColor.valueOf(presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex()).getColor().toUpperCase()));
		}
	}
	
	public void robPlayer(RobPlayerInfo victim) {
		if(presenter.getState().getStatus().equals("Robbing")){
			if(victim!=null){
				presenter.robPlayer(presenter.getPlayerInfo().getIndex(),victim.getPlayerIndex(), robberSpot);
			}else{
				presenter.robPlayer(presenter.getPlayerInfo().getIndex(), -1, robberSpot);
			}
		}else{
			if(victim!=null){
				presenter.playSoldierCard(presenter.getPlayerInfo().getIndex(),victim.getPlayerIndex(), robberSpot);
			}else{
				presenter.playSoldierCard(presenter.getPlayerInfo().getIndex(), -1, robberSpot);
			}
		}
		robberSpot=null;
	}

	@Override
	public void update(Observable o, Object arg) {
		ClientModel model = presenter.getClientModel();
		Map<HexLocation, IHex> board = model.getGameModel().getBoard();
		if(presenter.getState().getStatus().equals("Robbing")&&robberSpot==null&&presenter.isPlayersTurn()){
			getView().startDrop(PieceType.ROBBER, CatanColor.valueOf(presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex()).getColor().toUpperCase()), false);
		}
		
		if (!haveInitializedHexes) {
			for (HexLocation hexLoc : board.keySet()) {
				IHex hex = board.get(hexLoc);
				getView().addHex(hexLoc, board.get(hexLoc).getType());
				if (hex.getChit() > 0 && hex.getChit() <= 12 && hex.getChit() != 7) {
					getView().addNumber(hexLoc, board.get(hexLoc).getChit());
				}
			}
			haveInitializedHexes = true;
		}
		
		ArrayList<Road> roads = model.getServerModel().getMap().getRoads();
		ArrayList<City> cities = model.getServerModel().getMap().getCities();
		ArrayList<Settlement> settlements = model.getServerModel().getMap().getSettlements();
		
		
		for (Road road : roads) {
			CatanColor roadColor = CatanColor.valueOf(model.getServerModel().getPlayers().get(road.getOwnerIndex()).getColor().toUpperCase());
			getView().placeRoad(road.getLocation(), roadColor);
		}
		
		for (Settlement settlement : settlements) {
			CatanColor settlementColor = CatanColor.valueOf(model.getServerModel().getPlayers().get(settlement.getOwnerIndex()).getColor().toUpperCase());
			getView().placeSettlement(settlement.getLocation(), settlementColor);
		}
		
		for (City city : cities) {
			CatanColor cityColor = CatanColor.valueOf(model.getServerModel().getPlayers().get(city.getOwnerIndex()).getColor().toUpperCase());
			getView().placeCity(city.getLocation(), cityColor);
		}
		
		ArrayList<Port> ports = model.getServerModel().getMap().getPorts();
		for (Port port : ports) {
			String resourceType = port.getResourceType();
			if (resourceType == null) {
				resourceType = "THREE";
			}
			else {
				resourceType = port.getResourceType().toUpperCase();
			}
			getView().addPort(new EdgeLocation(port.getLocation(), EdgeDirection.determineDirection(port.getDirection())), PortType.valueOf(resourceType));
		}
		
		getView().placeRobber(model.getServerModel().getMap().getRobber().getLocation());
		
		if ((presenter.getState().getStatus().equals("FirstRound") || presenter.getState().getStatus().equals("SecondRound")) &&
				presenter.isPlayersTurn())  {
			getView().setUpPhase(CatanColor.valueOf(presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex()).getColor().toUpperCase()), presenter);
		}
	}
}

