package server.commands.moves;

import server.commands.ICommandLog;
import server.moves.IMovesFacade;

public interface IMovesCommandLog extends ICommandLog {

	public void setFacade(IMovesFacade movesFacade);

	public void store(IMovesCommand command);

}
