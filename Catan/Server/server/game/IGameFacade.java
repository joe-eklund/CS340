package server.game;

import shared.ServerMethodRequests.AddAIRequest;
import shared.ServerMethodRequests.ResetGameRequest;

/**
 * This interface defines a Facade containing the model, reset, commands, addAI, listAI commands
 *
 */
public interface IGameFacade {
	public int getGameModel();
	public int resetGame(ResetGameRequest request);
	public int gameCommands();
	public int addAI(AddAIRequest request);
	public int listAI();
}
