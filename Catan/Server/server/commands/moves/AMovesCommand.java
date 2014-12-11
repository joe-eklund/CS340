package server.commands.moves;

import server.commands.ACommand;
import server.cookie.CookieParams;
import server.moves.IMovesFacade;

public abstract class AMovesCommand extends ACommand implements IMovesCommand {
	protected transient IMovesFacade executor;
	protected CookieParams cookieParms;
	
	public AMovesCommand(String name, IMovesFacade executor, CookieParams cookieParams) {
		super(name);
		this.executor = executor;
		this.cookieParms = cookieParams;
	}

	@Override
	public void setExecutor(IMovesFacade executor) {
		this.executor = executor;
	}
	
	@Override
	public int getGameID() {
		return this.cookieParms.getGameID();
	}

}
