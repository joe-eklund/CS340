package shared.states;

import client.presenter.IPresenter;
import shared.ServerMethodResponses.LoginUserResponse;
import shared.ServerMethodResponses.RegisterUserResponse;
import shared.definitions.PlayerDescription;

public class LoggingIn extends State {
	@Override
	public LoginUserResponse login(IPresenter presenter, String user, String pass) {
		LoginUserResponse response = presenter.getProxy().loginUser(user, pass);
		presenter.setCookie(response.getCookie());
		presenter.setPlayerInfo(new PlayerDescription(null, response.getUserID(), response.getName()));
		return response;
	}
	
	@Override
	public RegisterUserResponse register(IPresenter presenter, String user, String pass) {
		RegisterUserResponse response = presenter.getProxy().registerUser(user, pass);
		presenter.setCookie(response.getCookie());
		presenter.setPlayerInfo(new PlayerDescription(null,response.getUserID(),response.getName()));
		return response;
	}
}
