package client.roll;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import client.base.*;
import client.main.Catan;
import client.presenter.IPresenter;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController, Observer {
	
	private IRollView rollView;
	private IRollResultView resultView;
	private IPresenter presenter;
	
	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView) {

		super(view);
		
		rollView = view;
		setResultView(resultView);
		presenter = Catan.getPresenter();
		presenter.addObserverToModel(this);
	}
	
	public IRollResultView getResultView() {
		return resultView;
	}
	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
	}

	public IRollView getRollView() {
		return (IRollView)getView();
	}
	
	@Override
	public void rollDice() {
		Random randomGenerator1 = new Random();
		Random randomGenerator2 = new Random();
		int dice1 = randomGenerator1.nextInt(6) + 1;
		int dice2 = randomGenerator2.nextInt(6) + 1;
		int diceRoll = dice1 + dice2;
//		System.out.println("dice1 " + dice1 + " dice2 " + dice2);
		getResultView().setRollValue(7);
		rollView.closeModal();
		getResultView().showModal();	
		presenter.rollNumber(diceRoll);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (presenter.getState().getStatus().equals("Rolling") && presenter.isPlayersTurn()) {
			rollView.showModal();
			rollView.startTimer();
		}
	}

}

