package client.join;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import shared.ServerMethodResponses.CreateGameResponse;
import shared.definitions.CatanColor;
import shared.definitions.GameDescription;
import shared.definitions.PlayerDescription;
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
	private int numGames;
	private int numPlayers;
	private ArrayList<CatanColor> currentColors;
	
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
		
		numGames = 0;
		numPlayers = 0;
	}
	
	public JoinGameController(IJoinGameView view, INewGameView newGameView, 
			ISelectColorView selectColorView, IMessageView messageView, IPresenter presenter) {

		super(view);
		
		setPresenter(presenter);
		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
		
		numGames = 0;
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
		numGames = 0;
		numPlayers = 0;
		//get list of games
		presenter.listGames();
		//this.getJoinGameView().setGames(response.getGameDescriptions(), presenter.getPlayerInfo());
		getJoinGameView().showModal();
		//Catan.getPoller().stop();
		Catan.getPoller().start();
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
		getSelectColorView().resetEnabled();
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
		joinAction.execute();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (presenter.getGames().size() > numGames || getNewNumberOfPlayers() > numPlayers) {
			this.getJoinGameView().setGames(presenter.getGames(), presenter.getPlayerInfo());
			numGames = presenter.getGames().size();
			numPlayers = getNewNumberOfPlayers();
		}
		
		if (currentGame != null) {
			GameDescription newGame = presenter.getGames().get(currentGame.getId());
			for (int i = 0; i < newGame.getPlayerDescriptions().size(); i++) {
				if (i < currentGame.getPlayerDescriptions().size()) {
					if (!currentGame.getPlayerDescriptions().get(i).getColor().equals(newGame.getPlayerDescriptions().get(i).getColor())) {
						getSelectColorView().setColorEnabled(CatanColor.valueOf(currentGame.getPlayerDescriptions().get(i).getColor().toUpperCase()), true);
						getSelectColorView().setColorEnabled(CatanColor.valueOf(newGame.getPlayerDescriptions().get(i).getColor().toUpperCase()), false);
					}
				}
				else {
					getSelectColorView().setColorEnabled(CatanColor.valueOf(newGame.getPlayerDescriptions().get(i).getColor().toUpperCase()), false);
				}
			}
		}
	}
	
	public int getNewNumberOfPlayers() {
		int newNumPlayers = 0;
		for (int i = 0; i < presenter.getGames().size(); i++) {
			newNumPlayers += presenter.getGames().get(i).getPlayerDescriptions().size();
		}
		
		return newNumPlayers;
	}
}

