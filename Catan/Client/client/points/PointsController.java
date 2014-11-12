package client.points;

import java.util.Observable;
import java.util.Observer;

import shared.model.Player;
import client.base.*;
import client.join.IJoinGameView;
import client.join.JoinGameController;
import client.main.Catan;
import client.presenter.IPresenter;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController, Observer{

	private IGameFinishedView finishedView;
	private IPresenter presenter;
	
	/**
	 * PointsController constructor
	 * 
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView) {
		
		super(view);
		presenter = Catan.getPresenter();
		setFinishedView(finishedView);
		presenter.addObserverToModel(this);
	}
	
	public IPointsView getPointsView() {
		
		return (IPointsView)super.getView();
	}
	
	public IGameFinishedView getFinishedView() {
		return finishedView;
	}
	public void setFinishedView(IGameFinishedView finishedView) {
		this.finishedView = finishedView;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (presenter.getState().isInAnyPlayingState()) {
			getPointsView().setPoints(presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex()).getVictoryPoints());
			if (presenter.getClientModel().getServerModel().getWinner() > -1) {
				if (presenter.getPlayerInfo().getIndex() == presenter.getClientModel().getServerModel().getWinner()) {
					getFinishedView().setWinner(presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex()).getName(), true);
					getFinishedView().showModal();
				}
				else {
					getFinishedView().setWinner(presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex()).getName(), false);
					getFinishedView().showModal();
				}
			}
		}
	}
	

	@Override
	public void returnToJoinGame() {
//		presenter.resetGame();
//		Catan.getJoin().start();
//		getFinishedView().closeModal();
	}
	
}

