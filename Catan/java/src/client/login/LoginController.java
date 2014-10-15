package client.login;

import shared.ServerMethodResponses.LoginUserResponse;
import client.base.Controller;
import client.base.IAction;
import client.misc.IMessageView;
import client.presenter.IPresenter;


/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

	private IMessageView messageView;
	private IAction loginAction;
	
	private IPresenter presenter;
	
	/**
	 * LoginController constructor
	 * 
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(ILoginView view, IMessageView messageView, IPresenter presenter) {
		
		super(view);
		
		this.presenter = presenter;
		this.messageView = messageView;
	}
	
	public LoginController(ILoginView view, IMessageView messageView) {
		
		super(view);
		
		this.messageView = messageView;
	}
	
	public ILoginView getLoginView() {
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * 
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {
		
		return loginAction;
	}

	@Override
	public void start() {
		
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		
		// TODO: log in user
		//boolean validLogin;
		String password=this.getLoginView().getLoginPassword();
		String username=this.getLoginView().getLoginUsername();

		LoginUserResponse response=this.presenter.login(username, password);
		if(response.isSuccessful()) {
			//successful authentication
			getLoginView().closeModal();
			loginAction.execute();
		}else {
			//invalid credentials
			messageView.setMessage(response.getMessage());
			messageView.showModal();
		}
	}

	@Override
	public void register() {
		
		// TODO: register new user (which, if successful, also logs them in)
		
		// If register succeeded
		getLoginView().closeModal();
		loginAction.execute();
	}

}

