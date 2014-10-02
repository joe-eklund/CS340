package shared.definitions;

import java.util.List;

import client.model.*;
/**
 * 
 * The GameModel contains all the information relevant to a specific version of the Catan game. This is what is passed between the client and the server.
 */
public class GameModel {
	ServerModel serverModel;

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
}
