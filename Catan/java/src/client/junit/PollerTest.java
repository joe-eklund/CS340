package client.junit;

import static org.junit.Assert.assertEquals;
import static shared.definitions.TestingConstants.CLIENT_GAME_VERSION;
import static shared.definitions.TestingConstants.VALID_JOINED_GAME_COOKIE;
import static shared.definitions.TestingConstants.getServerModel;

import org.junit.Test;

import proxy.ICommunicator;
import proxy.IServer;
import proxy.ITranslator;
import proxy.MockCommunicator;
import proxy.ProxyServer;
import proxy.TranslatorJSON;
import shared.model.ClientModel;
import client.poller.IPoller;
import client.poller.Poller;
import client.presenter.IPresenter;
import client.presenter.Presenter;

/**
 * a class designed to test Catan client poller
 *
 */
public class PollerTest {
	
	/*
	 * basic test of ability for presenter to update game model
	 * -verifies that presenter clientmodel updates when run() is called
	 * *run() is from implementing runnable() and is fired by poller timer
	 */
	@Test
	public void testPresenterRun() {
		ITranslator jsonTranslator = new TranslatorJSON();
		ICommunicator mockCommunicator = new MockCommunicator();
		ProxyServer.setSingleton(mockCommunicator, jsonTranslator, "UTF-8");
		IServer proxy = ProxyServer.getSingleton();
		ClientModel clientModel = new ClientModel(getServerModel());
		Presenter presenter = new Presenter(clientModel, proxy, VALID_JOINED_GAME_COOKIE);
		assertEquals("Initial version for presenter should be: ", 0, presenter.getVersion());
		presenter.run();
		assertEquals("Model version should be ", proxy.getGameModel(CLIENT_GAME_VERSION, VALID_JOINED_GAME_COOKIE).getGameModel().getVersion(), presenter.getVersion());
	}
	
	/*
	 * basic test of poller updating
	 * -tests ability to initialize, start, and stop poller
	 * -tests that poller gets new game model using TestingConstants
	 * -tests that poller is actually being fired every 2 seconds
	 * 	==> poller is fired on start and ever 2 seconds therafter
	 * 		in 6.5 seconds poller should fire 4 times (at 0s, 2s, 4s, 6s)
	 * 		*0.5 seconds extra given for testing/machine tolerance
	 * 
	 */
	@Test
	public void testPollerUpdate() {
		ITranslator jsonTranslator = new TranslatorJSON();
		ICommunicator mockCommunicator = new MockCommunicator();
		ProxyServer.setSingleton(mockCommunicator, jsonTranslator, "UTF-8");
		final IServer proxy = ProxyServer.getSingleton();
		ClientModel clientModel = new ClientModel(getServerModel());
		final IPresenter presenter = new Presenter(clientModel, proxy, VALID_JOINED_GAME_COOKIE);
		final IPoller poller = new Poller(presenter, 2);	// set up poller to poll updated game every 2 seconds
		assertEquals("Initial version for presenter should be: ", 0, presenter.getVersion());
		poller.start();
		try {
		    Thread.sleep(6500);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		poller.stop();
		assertEquals("Poller number of updates performed", 4, presenter.getPollCycleCount()); //poller performs initial update upon start and updates every 2s thereafter
		assertEquals("Model version should be ", proxy.getGameModel(CLIENT_GAME_VERSION, VALID_JOINED_GAME_COOKIE).getGameModel().getVersion(), presenter.getVersion());
	}
}
