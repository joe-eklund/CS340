package client.map;

import java.util.*;

import shared.definitions.*;
import shared.locations.*;
import client.base.*;
import client.data.*;
import client.main.Catan;
import client.model.City;
import client.model.ClientModel;
import client.model.Port;
import client.model.Road;
import client.model.Settlement;
import client.model.interfaces.IHex;
import client.presenter.IPresenter;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController, Observer {
	
	private IRobView robView;
	private static IPresenter presenter;
	
	public MapController(IMapView view, IRobView robView) {
		
		super(view);
		
		presenter = Catan.getPresenter();
		presenter.addObserverToModel(this);
		
		setRobView(robView);
		
		initFromModel();
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
		
		return true;
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		
		return true;
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		
		return true;
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		
		return true;
	}

	public void placeRoad(EdgeLocation edgeLoc) {
		
		getView().placeRoad(edgeLoc, CatanColor.ORANGE);
	}

	public void placeSettlement(VertexLocation vertLoc) {
		
		getView().placeSettlement(vertLoc, CatanColor.ORANGE);
	}

	public void placeCity(VertexLocation vertLoc) {
		
		getView().placeCity(vertLoc, CatanColor.ORANGE);
	}

	public void placeRobber(HexLocation hexLoc) {
		
		getView().placeRobber(hexLoc);
		
		getRobView().showModal();
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		
		getView().startDrop(pieceType, CatanColor.ORANGE, true);
	}
	
	public void cancelMove() {
		
	}
	
	public void playSoldierCard() {	
		
	}
	
	public void playRoadBuildingCard() {	
		
	}
	
	public void robPlayer(RobPlayerInfo victim) {	
		
	}

	@Override
	public void update(Observable o, Object arg) {
		ClientModel model = presenter.getClientModel();
		Map<HexLocation, IHex> board = model.getGameModel().getBoard();
		
		
		if (presenter.getGameState().equals(GameState.JOINING)) {
			for (HexLocation hexLoc : board.keySet()) {
				IHex hex = board.get(hexLoc);
				getView().addHex(hexLoc, board.get(hexLoc).getType());
				if (hex.getChit() > 0 && hex.getChit() <= 12 && hex.getChit() != 7) {
					getView().addNumber(hexLoc, board.get(hexLoc).getChit());
				}
			}
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
			getView().placeSettlement(city.getLocation(), cityColor);
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
	}
}

