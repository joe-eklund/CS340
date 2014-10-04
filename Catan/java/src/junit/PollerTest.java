package junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import proxy.ICommunicator;
import proxy.IServer;
import proxy.ITranslator;
import proxy.MockCommunicator;
import proxy.ProxyServer;
import proxy.TranslatorJSON;
import client.model.ClientModel;
import client.poller.IPoller;
import client.poller.Poller;
import client.presenter.IPresenter;
import client.presenter.Presenter;

public class PollerTest {
	
	@Test
	public void testPresenterRun() {
		ITranslator jsonTranslator = new TranslatorJSON();
		ICommunicator mockCommunicator = new MockCommunicator();
		ProxyServer.setSingleton(mockCommunicator, jsonTranslator, "UTF-8");
		IServer proxy = ProxyServer.getSingleton();
		ClientModel clientModel = new ClientModel(TestingConstants.getServerModel());
		Presenter presenter = new Presenter(clientModel, proxy, TestingConstants.VALID_JOINED_GAME_COOKIE);
		assertEquals("Initial version for presenter should be: ", 0, presenter.getVersion());
		presenter.run();
		assertEquals("Model version should be ", proxy.getGameModel(TestingConstants.CLIENT_GAME_VERSION, TestingConstants.VALID_JOINED_GAME_COOKIE).getGameModel().getVersion(), presenter.getVersion());
	}
	
	@Test
	public void testPollerUpdate() {
		ITranslator jsonTranslator = new TranslatorJSON();
		ICommunicator mockCommunicator = new MockCommunicator();
		ProxyServer.setSingleton(mockCommunicator, jsonTranslator, "UTF-8");
		final IServer proxy = ProxyServer.getSingleton();
		ClientModel clientModel = new ClientModel(TestingConstants.getServerModel());
		final IPresenter presenter = new Presenter(clientModel, proxy, TestingConstants.VALID_JOINED_GAME_COOKIE);
		final IPoller poller = new Poller(presenter, 2);
		assertEquals("Initial version for presenter should be: ", 0, presenter.getVersion());
		poller.start();
		try {
		    Thread.sleep(6500);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		poller.stop();
		assertEquals("Poller number of updates performed", 4, presenter.getPollCycleCount()); //poller performs initial update upon start and updates every 2s thereafter
		assertEquals("Model version should be ", proxy.getGameModel(TestingConstants.CLIENT_GAME_VERSION, TestingConstants.VALID_JOINED_GAME_COOKIE).getGameModel().getVersion(), presenter.getVersion());
	}
}
