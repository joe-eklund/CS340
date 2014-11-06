package shared.definitions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import client.model.*;
import client.model.interfaces.IHex;
import shared.locations.*;

import java.util.Map;
/**
 * 
 * The GameModel contains all the information relevant to a specific version of the Catan game. This is what is passed between the client and the server.
 */
public class GameModel {
	ServerModel serverModel;
	Map<HexLocation, IHex> board;

	public GameModel(ServerModel serverModel) {
		this.serverModel = serverModel;
		if(serverModel != null) {
			initializeMap();
		}
	}
	
	/**
	 * Initializes the map to reflect the map given by the server
	 * @pre The server has sent an initial model
	 * @post The map in the GameModel now reflects the map given by the server
	 */
	private void initializeMap() {
		board = new HashMap<HexLocation, IHex>();
		ArrayList<Hex> hexes = serverModel.getMap().getHexes();
		ArrayList<Road> roads = serverModel.getMap().getRoads();
		ArrayList<Settlement> settlements = serverModel.getMap().getSettlements();
		ArrayList<City> cities = serverModel.getMap().getCities();
		
		for (Road road : roads) {
			try {
				road.initializeLocation();
			}
			catch(Exception e) {
				System.err.print(e);
			}
		}
		
		for (Settlement settlement : settlements) {
			try {
				settlement.initializeLocation();
			}
			catch(Exception e) {
				System.err.print(e);
			}
		}
		
		for (City city : cities) {
			try {
				city.initializeLocation();
			}
			catch(Exception e) {
				System.err.print(e);
			}
		}
		
		for (Hex hex : hexes) {
			if (hex.getResourceType() == null)
				hex.setType(HexType.DESERT);
			else
				hex.setType(HexType.valueOf(hex.getResourceType().toUpperCase()));
			
			board.put(hex.getLocation(), hex);
		}
		
		Hex oceanHex;		
		
		int x=0;
		int y=-3;
		int xVar=1;
		int yVar=0;
		while(x!=-1||y!=-2) {
			oceanHex = new Hex(new HexLocation(x, y));
			oceanHex.setType(HexType.WATER);
			board.put(oceanHex.getLocation(), oceanHex);
			if(x==3&&y==-3){
				yVar=1;
				xVar=0;
			}else if(y==0&&x==3){
				xVar=-1;
			}else if(y==3&&x==0){
				yVar=0;
			}else if(x==-3&&y==3){
				xVar=0;
				yVar=-1;
			}else if(y==0&&x==-3){
				xVar=1;
			}
			x+=xVar;
			y+=yVar;
		}
		oceanHex = new Hex(new HexLocation(x, y));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
	}
	
	/**
	 * @obvious the serverModel
	 */
	public ServerModel getServerModel() {
		return serverModel;
	}

	/**
	 * @obvious
	 * @param serverModel the serverModel to set
	 */
	public void setServerModel(ServerModel serverModel) {
		this.serverModel = serverModel;
	}

	/**
	 * @obvious the board
	 */
	public Map<HexLocation, IHex> getBoard() {
		return board;
	}

	/**
	 * @obvious
	 * @param board the board to set
	 */
	public void setBoard(Map<HexLocation, IHex> board) {
		this.board = board;
	}
}
