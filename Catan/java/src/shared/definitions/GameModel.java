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
		initializeMap();
	}
	
	private void initializeMap() {
		board = new HashMap<HexLocation, IHex>();
		ArrayList<Hex> hexes = serverModel.getMap().getHexes();
		ArrayList<Road> roads = serverModel.getMap().getRoads();
		
		for (Road road : roads) {
			try {
				road.initializeLocation();
			}
			catch(Exception e) {
				System.err.print(e);
			}
		}
		
		for (Hex hex : hexes) {
			hex.setType(HexType.LAND);
			board.put(hex.getLocation(), hex);
		}
		
		Hex oceanHex;
		
		oceanHex = new Hex(new HexLocation(0, -3));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(1, -3));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(2, -3));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(3, -3));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(3, -2));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(3, -1));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(3, 0));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(2, 1));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(1, 2));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(0, 3));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(-1, 3));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(-2, 3));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);

		oceanHex = new Hex(new HexLocation(-3, 3));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);

		oceanHex = new Hex(new HexLocation(-3, 2));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(-3, 1));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(-3, 0));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(-2, -1));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
		oceanHex = new Hex(new HexLocation(-1, -2));
		oceanHex.setType(HexType.WATER);
		board.put(oceanHex.getLocation(), oceanHex);
		
	}
	
	/**
	 * @return the serverModel
	 */
	public ServerModel getServerModel() {
		return serverModel;
	}

	/**
	 * @param serverModel the serverModel to set
	 */
	public void setServerModel(ServerModel serverModel) {
		this.serverModel = serverModel;
	}

	/**
	 * @return the board
	 */
	public Map<HexLocation, IHex> getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Map<HexLocation, IHex> board) {
		this.board = board;
	}
}
