package client.main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import proxy.ClientCommunicator;
import proxy.ProxyServer;
import proxy.TranslatorJSON;
import shared.states.LoggingIn;
import client.base.IAction;
import client.catan.CatanPanel;
import client.join.IJoinGameController;
import client.join.JoinGameController;
import client.join.JoinGameView;
import client.join.NewGameView;
import client.join.PlayerWaitingController;
import client.join.PlayerWaitingView;
import client.join.SelectColorView;
import client.login.LoginController;
import client.login.LoginView;
import client.misc.MessageView;
import client.model.ClientModel; 
import client.poller.IPoller;
import client.poller.Poller;
import client.presenter.IPresenter;
import client.presenter.Presenter;

/**
 * Main entry point for the Catan program
 */
@SuppressWarnings("serial")
public class Catan extends JFrame
{
	private static Presenter presenter;
	private static Poller poller;
	
	private static JoinGameController joinController;
	
	public static IJoinGameController getJoin(){
		return joinController;
	}
	
	public static IPresenter getPresenter() {
		return presenter;
	}
	
	public static IPoller getPoller() {
		return poller;
	}
	
	private CatanPanel catanPanel;
	
	public Catan(JoinGameController joinController)
	{
		
		client.base.OverlayView.setWindow(this);
		
		this.setTitle("Settlers of Catan");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		catanPanel = new CatanPanel(joinController);
		this.setContentPane(catanPanel);
		
		display();
	}
	
	private void display()
	{
		pack();
		setVisible(true);
	}
	
	//
	// Main
	//
	
	public static void main(final String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				TranslatorJSON translator = new TranslatorJSON();
				ClientCommunicator communicator=new ClientCommunicator("localhost",8080,translator);
//				ClientCommunicator communicator=new ClientCommunicator("107.170.212.54",8081,translator);
				ProxyServer.setSingleton(communicator, translator, "UTF-8");
				ProxyServer proxy = ProxyServer.getSingleton();
				ClientModel clientmodel = new ClientModel(null);
				presenter = new Presenter(clientmodel,proxy,"");
				presenter.setState(new LoggingIn());
				clientmodel.setPresenter(presenter);
				poller = new Poller(presenter, 100);
				
				PlayerWaitingView playerWaitingView = new PlayerWaitingView();
				final PlayerWaitingController playerWaitingController = new PlayerWaitingController(playerWaitingView);
				playerWaitingView.setController(playerWaitingController);
				
				
				
				JoinGameView joinView = new JoinGameView();
				NewGameView newGameView = new NewGameView();
				SelectColorView selectColorView = new SelectColorView();
				MessageView joinMessageView = new MessageView();
				joinController = new JoinGameController(joinView,
														newGameView,
														selectColorView,
														joinMessageView,
														presenter);
				
				new Catan(joinController);
				
				//Adding joinController as observer
				presenter.addObserver(joinController);
				
				joinController.setJoinAction(new IAction() {
					@Override
					public void execute()
					{
						playerWaitingController.start();
					}
				});
				joinView.setController(joinController);
				newGameView.setController(joinController);
				selectColorView.setController(joinController);
				joinMessageView.setController(joinController);
				
				LoginView loginView = new LoginView();
				MessageView loginMessageView = new MessageView();
				
				LoginController loginController = new LoginController(loginView,
																	  loginMessageView,
																	  presenter);
				loginController.setLoginAction(new IAction() {
					@Override
					public void execute()
					{
						joinController.start();
					}
				});
				loginView.setController(loginController);
				
				loginController.start();
				
				//Thread thread = new Thread(presenter);
				//thread.start();
			}
		});
	}
	
}

