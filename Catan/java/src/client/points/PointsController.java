package client.points;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.main.Catan;
import client.model.Player;
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
		getPointsView().setPoints(presenter.getClientModel().getServerModel().getPlayers().get(presenter.getPlayerInfo().getIndex()).getVictoryPoints());
		for(Player p : presenter.getClientModel().getServerModel().getPlayers()){
			if(p.getVictoryPoints()==10){
				getFinishedView().setWinner(p.getName(), presenter.isPlayersTurn());
				getFinishedView().showModal();
			}
		}
	}
	
}

