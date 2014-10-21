package client.join;

import java.util.Observable;
import java.util.Observer;

import shared.ServerMethodResponses.CreateGameResponse;
import shared.definitions.CatanColor;
import shared.definitions.GameDescription;
import shared.definitions.PlayerDescription;
import shared.definitions.SystemState;
import client.base.Controller;
import client.base.IAction;
import client.main.Catan;
import client.misc.IMessageView;
import client.presenter.IPresenter;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController, Observer {

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	private GameDescription currentGame;
	
	private IPresenter presenter;
	
	/**
	 * JoinGameController constructor
	 * 
	 * @param view Join game view
	 * @param newGameView New game view
	 * @param selectColorView Select color view
	 * @param messageView Message view (used to display error messages that occur while the user is joining a game)
	 */
	public JoinGameController(IJoinGameView view, INewGameView newGameView, 
								ISelectColorView selectColorView, IMessageView messageView) {

		super(view);

		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
	}
	
	public JoinGameController(IJoinGameView view, INewGameView newGameView, 
			ISelectColorView selectColorView, IMessageView messageView, IPresenter presenter) {

		super(view);
		
		setPresenter(presenter);
		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
	}
	
	private void setPresenter(IPresenter presenter2) {
		this.presenter=presenter2;
	}

	public IJoinGameView getJoinGameView() {
		
		return (IJoinGameView)super.getView();
	}
	
	/**
	 * Returns the action to be executed when the user joins a game
	 * 
	 * @return The action to be executed when the user joins a game
	 */
	public IAction getJoinAction() {
		
		return joinAction;
	}

	/**
	 * Sets the action to be executed when the user joins a game
	 * 
	 * @param value The action to be executed when the user joins a game
	 */
	public void setJoinAction(IAction value) {	
		
		joinAction = value;
	}
	
	public INewGameView getNewGameView() {
		
		return newGameView;
	}

	public void setNewGameView(INewGameView newGameView) {
		
		this.newGameView = newGameView;
	}
	
	public ISelectColorView getSelectColorView() {
		
		return selectColorView;
	}
	public void setSelectColorView(ISelectColorView selectColorView) {
		
		this.selectColorView = selectColorView;
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	public void setMessageView(IMessageView messageView) {
		
		this.messageView = messageView;
	}

	@Override
	public void start() {
		//get list of games
		presenter.listGames();
		//this.getJoinGameView().setGames(response.getGameDescriptions(), presenter.getPlayerInfo());
		getJoinGameView().showModal();
	}

	@Override
	public void startCreateNewGame() {
		
		getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame() {
		
		getNewGameView().closeModal();
	}

	@Override
	public void createNewGame() {
		boolean randTiles=this.getNewGameView().getRandomlyPlaceHexes();
		boolean randNums=this.getNewGameView().getRandomlyPlaceNumbers();
		boolean randPorts=this.getNewGameView().getUseRandomPorts();
		String name=this.getNewGameView().getTitle();
		
		CreateGameResponse response=this.presenter.createGame(randTiles,randNums,randPorts,name);
		if(response.isSuccessful()) {
			getNewGameView().closeModal();
		}else {
			messageView.setMessage("Creating a game failed.");
			messageView.showModal();
		}
	}

	@Override
	public void startJoinGame(GameDescription game) {
		//presenter.setSystemState(SystemState.JOINING);
		currentGame = game;
		
		for (PlayerDescription p : game.getPlayerDescriptions()) {
			if (p.getColor() != null && !p.getName().equals(presenter.getPlayerInfo().getName())) {
				getSelectColorView().setColorEnabled(CatanColor.valueOf(p.getColor().toUpperCase()), false);
			}
		}
		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame() {
	
		getJoinGameView().closeModal();
	}

	@Override
	public void joinGame(CatanColor color) {
		// If join succeeded
		getSelectColorView().closeModal();
		getJoinGameView().closeModal();
		presenter.joinGame(getSelectColorView().getSelectedColor(), currentGame.getId());
//		presenter.setSystemState(SystemState.PLAYERWAITING); //important that this does not move
		Catan.getPoller().start();
		joinAction.execute();
	}

	@Override
	public void update(Observable o, Object arg) {
		this.getJoinGameView().setGames(presenter.getGames(), presenter.getPlayerInfo());
	}

}

