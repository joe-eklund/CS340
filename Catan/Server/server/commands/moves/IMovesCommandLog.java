package server.commands.moves;

import java.util.List;

import server.commands.ICommandLog;
import server.commands.games.GamesCommandLog;
import server.commands.users.UsersCommandLog;
import server.moves.IMovesFacade;

public interface IMovesCommandLog extends ICommandLog {

	public void setFacade(IMovesFacade movesFacade);
	
	public void storeAll(List<IMovesCommand> commands);

	public void store(IMovesCommand command);

	public void setGamesLog(GamesCommandLog gamesLog);
	
	public void setUsersLog(UsersCommandLog usersLog);

	public void setDeltaThreshold(int deltaThreshold);

}
